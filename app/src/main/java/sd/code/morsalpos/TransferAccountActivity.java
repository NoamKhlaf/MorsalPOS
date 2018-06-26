package sd.code.morsalpos;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.vanstone.trans.api.EmvLibApi;
import com.vanstone.trans.api.IcApi;
import com.vanstone.trans.api.MagCardApi;
import com.vanstone.trans.api.PiccApi;
import com.vanstone.trans.api.PrinterApi;
import com.vanstone.trans.api.SystemApi;
import com.vanstone.trans.api.constants.CoreDefConstants;
import com.vanstone.utils.CommonConvert;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import cn.refactor.lib.colordialog.PromptDialog;
import sd.code.morsalpos.common.AnsiX98PinHandler;
import sd.code.morsalpos.common.Card;
import sd.code.morsalpos.common.CardInfo;
import sd.code.morsalpos.common.Common;
import sd.code.morsalpos.common.Config;
import sd.code.morsalpos.common.EmvCommon;
import sd.code.morsalpos.common.GlobalConstants;
import sd.code.morsalpos.common.PortalApplication;
import sd.code.morsalpos.common.Security;
import sd.code.morsalpos.common.TCardAccountInfo;
import sd.code.morsalpos.process.CallUrl;

public class TransferAccountActivity extends AppCompatActivity {
    private final static String TAG = TransferAccountActivity.class.getSimpleName();
    View requestCode;


    PortalApplication application;
    Button sumbit, scan;
    EditText iPin, toAccount, amount, pan;
    AppCompatSpinner year, month;
    TextView result;
    boolean cancel = false;
    Map<String, String> resMap;

    String card_no, expiryDate, cardHolderName;

    private boolean slotted_card = true;
    private Handler handler;

    @SuppressLint("HandlerLeak")
    private void initHandler() {
        handler = new Handler() {
            // �ص����� : ���յ�Messageʱ,�Զ�����
            public void dispatchMessage(Message msg) {
                String yearStr = "20"+expiryDate.substring(0, 2);
                String monthStr = expiryDate.substring(2);

                String[] expYear= getResources().getStringArray(R.array.expYear);
                for (int i = 0 ; i < expYear.length; i++) {
                    if(expYear[i].equals(yearStr)){
                        year.setSelection(i);
                        break;
                    }
                }
                month.setSelection(Integer.parseInt(monthStr)-1);

                pan.setText(card_no);
            }
        };
    }
    static {
        System.loadLibrary("A90JavahCore");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_account);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        application = (PortalApplication) getApplication();
        initHandler();

        String CurDir = getApplicationContext().getFilesDir().getAbsolutePath();
        SystemApi.SystemInit_Api(0, CommonConvert.StringToBytes(CurDir + "/" + "\0"), TransferAccountActivity.this);

        try{
            MagCardApi.MagOpen_Api();
            MagCardApi.MagReset_Api();
            PiccApi.PiccOpen_Api();
        } catch (Exception e){
            e.printStackTrace();
        }

        sumbit = (Button) findViewById(R.id.btn_pay);


        iPin = (EditText) findViewById(R.id.iPin);
        toAccount = (EditText) findViewById(R.id.input_account);
        amount = (EditText) findViewById(R.id.input_amount);
        pan = (EditText) findViewById(R.id.pan);

        year = (AppCompatSpinner) findViewById(R.id.year);
        month = (AppCompatSpinner) findViewById(R.id.month);

        result = (TextView) findViewById(R.id.txt_result);


        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(iPin.getText().toString())) {
                    iPin.setError(getString(R.string.error_empty_filed));
                    cancel = true;
                } else if (iPin.getText().toString().length() != 4) {
                    iPin.setError(getString(R.string.error_invalid_password));
                    cancel = true;
                }

                if (TextUtils.isEmpty(amount.getText().toString())) {
                    amount.setError(getString(R.string.error_empty_filed));
                    cancel = true;
                }

                if (TextUtils.isEmpty(toAccount.getText().toString())) {
                    toAccount.setError(getString(R.string.error_empty_filed));
                    cancel = true;
                } else if (toAccount.getText().toString().length() != 30&& toAccount.getText().toString().length() != 19) {
                    toAccount.setError(getString(R.string.error_invalid_account));
                    cancel = true;
                }
                if (TextUtils.isEmpty(pan.getText().toString())) {
                    pan.setError(getString(R.string.error_empty_filed));
                    cancel = true;
                } else if (pan.getText().toString().length() != 16 && pan.getText().toString().length() != 19) {
                    pan.setError(getString(R.string.error_invalid_card));
                    cancel = true;
                }


                if (!cancel) {
                    // Finish the registration screen and return to the Login activity
                    String yearFormat = year.getSelectedItem().toString().substring(2);
                    String panStr = pan.getText().toString();
                    String iPinStr = iPin.getText().toString();
                    String expDate = yearFormat + month.getSelectedItem().toString();
                    String toAccountStr = toAccount.getText().toString();
                    String amountStr = amount.getText().toString();

                    AnsiX98PinHandler pinHandler = new AnsiX98PinHandler(Config.TMK, application.getWorkingKey(), panStr, iPinStr);
                    iPinStr = pinHandler.getPinBlock();

                    Map<String, String> map = new HashMap<>();

                    map.put("PAN", panStr);
                    map.put("PIN", iPinStr);
                    map.put("expDate", expDate);
                    map.put("terminalId", application.getTerminaID());

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
                    Date now = new Date();
                    map.put("tranDateTime", format.format(now));
                    map.put("systemTraceAuditNumber", String.valueOf(Security.getSystemTraceAuditNumber()));
                    map.put("tranCurrencyCode", "SDG");
                    map.put("toAccount", toAccountStr);
                    map.put("tranAmount", amountStr);

                    Log.d(TAG, "Map: " + map.toString());
                    final ProgressDialog dialog = new ProgressDialog(TransferAccountActivity.this, R.style.AppTheme_Dark_Dialog);
                    dialog.setMessage("Running...");
                    dialog.setCancelable(false);
                    dialog.show();
                    CallUrl callUrl = new CallUrl(Config.URL_TRANSFARE_ACCOUNT, new JSONObject(map)) {
                        @Override
                        public void afterRequest(JSONObject response) {
                            dialog.dismiss();
                            String content = "";
                            resMap = new HashMap<>();
                            try {

                                String tradeTime = Common.formatDate(response.getString("tranDateTime"), "ddMMyyHHmmss", "HH:mm:ss");
                                String tradeDate = Common.formatDate(response.getString("tranDateTime"), "ddMMyyHHmmss", "dd/MM/yyyy");

                                double tranAmount = response.getDouble("tranAmount");
                                String tranCurrencyCode = response.getString("tranCurrencyCode");
                                String toAccount = response.getString("toAccount");
                                double tranFee = response.getDouble("tranFee");
                                double additionalAmount = response.getDouble("additionalAmount");
                                String responseCode = response.getString("responseCode");
                                String referenceNumber = response.getString("referenceNumber");
                                String responseMessage = response.getString("responseMessage");
                                String approvalCode = response.getString("approvalCode");
                                String systemTraceAuditNumber = response.getString("systemTraceAuditNumber");
                                String fromCard = pan.getText().toString();

                                resMap.put("time", tradeTime);
                                resMap.put("date", tradeDate);
                                resMap.put("toAccount", toAccount);
                                resMap.put("tranFee", tranFee+"");
                                resMap.put("additionalAmount", additionalAmount+"");
                                resMap.put("tranAmount", tranAmount+"");
                                resMap.put("tranCurrencyCode", tranCurrencyCode);
                                resMap.put("responseCode", responseCode);
                                resMap.put("referenceNumber", referenceNumber);
                                resMap.put("responseMessage", responseMessage);
                                resMap.put("approvalCode", approvalCode);
                                resMap.put("systemTraceAuditNumber", systemTraceAuditNumber);
                                resMap.put("fromCard", fromCard);

                                content =
                                        "Transaction time: \n"+tradeTime+"\n" +
                                                "Amount: \n"+tranAmount+" "+tranCurrencyCode+"\n" +
                                                "To Account: \n"+toAccount+"\n" +
                                                "Transaction fee: "+tranFee+"\n" +
                                                "Additional Amount: "+additionalAmount;

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            new PromptDialog(TransferAccountActivity.this)
                                    .setDialogType(PromptDialog.DIALOG_TYPE_SUCCESS)
                                    .setAnimationEnable(true)
                                    .setTitleText("Success")
                                    .setContentText(content)
                                    .setPositiveListener("Print", new PromptDialog.OnPositiveListener() {
                                        @Override
                                        public void onClick(PromptDialog dialog) {
                                            dialog.dismiss();

                                            new Thread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    PrtCardInfo();
                                                }
                                            }).start();
                                            Toast.makeText(TransferAccountActivity.this, "Printing...", Toast.LENGTH_SHORT).show();
                                        }
                                    }).show();
                        }

                        @Override
                        public void onError(VolleyError error) {

                            dialog.dismiss();
                            new PromptDialog(TransferAccountActivity.this)
                                    .setDialogType(PromptDialog.DIALOG_TYPE_WRONG)
                                    .setAnimationEnable(true)
                                    .setTitleText("Error")
                                    .setContentText(error.toString())
                                    .setPositiveListener("Print", new PromptDialog.OnPositiveListener() {
                                        @Override
                                        public void onClick(PromptDialog dialog) {
                                            dialog.dismiss();
                                            Toast.makeText(TransferAccountActivity.this, "Printing...", Toast.LENGTH_SHORT).show();
                                        }
                                    }).show();
                            Toast.makeText(TransferAccountActivity.this, "Error occur.", Toast.LENGTH_SHORT).show();
                        }
                    };
                    callUrl.execute();


                } else {
                    cancel = false;
                }
            }
        });

        new Thread() {
            public void run() {
                while (slotted_card) {
                    int timerid = 0;
                    timerid = SystemApi.TimerSet_Api();
                    boolean cardflag, iccardflag, picccardflag;
                    byte[] CardData = new byte[1024];
                    byte[] usCardLenAddr = new byte[4];
                    byte[] CardType = new byte[2];
                    byte[] SerialNo = new byte[20];
                    while (SystemApi.TimerCheck_Api(timerid, 40 * 1000) == 0) {
                        cardflag = false;
                        iccardflag = false;
                        picccardflag = false;
                        if (MagCardApi.MagRead_Api(CardData, usCardLenAddr) == 0x31) {
                            cardflag = true;
                        }
                        if (!cardflag && IcApi.IccDetect_Api(0) == 0x00) {
                            iccardflag = true;
                        }
                        if (!cardflag
                                && !iccardflag
                                && PiccApi.PiccCheck_Api(0, CardType, SerialNo) == 0) {
                            picccardflag = true;
                        }
                        if (cardflag) {
                            if (Card.GetTrackData(CardData) == 0) {
                                Card.GetCardFromTrack(
                                        GlobalConstants.PosCom.stTrans.MainAcc,
                                        GlobalConstants.PosCom.Track2,
                                        GlobalConstants.PosCom.Track3);

                                card_no = new String(
                                        GlobalConstants.PosCom.stTrans.MainAcc);

                                //pan.setText(Arrays.toString(GlobalConstants.PosCom.Track1));
                                String data = new String(GlobalConstants.PosCom.Track1);
                                expiryDate = Common.tokenizeExpDate(data);
                                cardHolderName = Common.tokenizeCardHolderName(data);

                                handler.sendEmptyMessage(0);
                                slotted_card = false;
                                Log.d("Card Number", "Card number: " + card_no);
                                break;
                            }
                        } else if (iccardflag) {
                            card_no = "Processing...";// new
                            handler.sendEmptyMessage(0);
                            // ByteUtils.memset(Date, 0, Date.length);
                            EmvLibApi.EmvSetIcCardType_Api(CoreDefConstants.PEDICCARD); // Ic��ʽѡ��
                            TCardAccountInfo pAccInfo = new TCardAccountInfo();
                            if (EmvCommon.EmvCardProcSL(2, 1, pAccInfo) == 0) {
                                card_no = new String(pAccInfo.MainAcc).trim();

                                String data = new String(GlobalConstants.PosCom.Track1);
                                expiryDate = Common.tokenizeExpDate(data);
                                cardHolderName = Common.tokenizeCardHolderName(data);

                                handler.sendEmptyMessage(0);
                                slotted_card = false;

                                Log.d("Card Number", "Card number: " + card_no);
                                break;
                            } else {
                                card_no = "Read IC failed";// new
                                handler.sendEmptyMessage(0);
                                SystemApi.Delay_Api(1000);
                                Log.d("Card Number", "Card number: " + card_no);
                            }
                        } else if (picccardflag) {
                            card_no = "Processing...";// new
                            handler.sendEmptyMessage(0);
                            EmvLibApi.EmvSetIcCardType_Api(3); // PICC��ʽѡ��
                            TCardAccountInfo pAccInfo = new TCardAccountInfo();
                            if (EmvCommon.EmvCardProcSL(2, 1, pAccInfo) == 0) {
                                card_no = new String(pAccInfo.MainAcc).trim();
                                String data = new String(GlobalConstants.PosCom.Track1);
                                expiryDate = Common.tokenizeExpDate(data);
                                cardHolderName = Common.tokenizeCardHolderName(data);

                                handler.sendEmptyMessage(0);
                                slotted_card = false;
                                Log.d("Card Number", "Card number: " + card_no);
                                break;
                            } else {
                                card_no = "Read CL failed!";// new
                                handler.sendEmptyMessage(0);
                                SystemApi.Delay_Api(1000);
                                Log.d("Card Number", "Card number: " + card_no);
                            }
                        }
                    }
                }
            }
        }.start();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void PrtCardInfo() {
        byte[] PrnBuf = new byte[128];

        PrinterApi.PrnClrBuff_Api();
        PrinterApi.SetLang_Api(PrinterApi.LANG_PERSIAN, PrinterApi.ENCODING_UTF8);
        PrinterApi.PrnFontSet_Api(32, 32, 0);
        PrinterApi.PrnSetGray_Api(15);
        PrinterApi.PrnLineSpaceSet_Api((short) 5, 0);

//        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
//        Bitmap resized = Bitmap.createScaledBitmap(bm,(int)(bm.getWidth()*0.24), (int)(bm.getHeight()*0.24), true);
//        PrinterApi.PrnLogo_Api(resized);

        PrinterApi.PrnStr_Api("      "+"POS Receipt");
        PrinterApi.PrnStr_Api("       "+"CARREFOUR");
        PrinterApi.PrnStr_Api("        "+"Khartoum");
        PrinterApi.PrnFontSet_Api(24, 24, 2);

        PrinterApi.PrnStr_Api("**** "+getString(R.string.account_transfare)+" *****");
        PrinterApi.PrnStr_Api(getString(R.string.date)+" "+resMap.get("date"));
        PrinterApi.PrnStr_Api(getString(R.string.time)+" "+resMap.get("time"));

        PrinterApi.PrnStr_Api(getString(R.string.merchant_id)+" "+application.getMerchantID());
        PrinterApi.PrnStr_Api(getString(R.string.terminal_id)+" "+application.getTerminaID());

        PrinterApi.PrnStr_Api(getString(R.string.card_number)+ " " +pan.getText().toString());
        PrinterApi.PrnStr_Api(getString(R.string.card_holder_name)+" "+((cardHolderName == null)? Config.DEFAULT_NAME: cardHolderName));

        PrinterApi.PrnStr_Api("******* "+resMap.get("responseMessage")+"  ********");

        PrinterApi.PrnStr_Api(getString(R.string.stan)+" "+resMap.get("systemTraceAuditNumber"));
        PrinterApi.PrnStr_Api(getString(R.string.receipt_no)+" "+resMap.get("systemTraceAuditNumber"));
        PrinterApi.PrnStr_Api(getString(R.string.amount)+": "+resMap.get("tranAmount"));

        PrinterApi.PrnStr_Api(getString(R.string.card_number)+" "+resMap.get("fromCard"));
        PrinterApi.PrnStr_Api(getString(R.string.to_account_number)+" "+resMap.get("toAccount"));
        PrinterApi.PrnStr_Api(getString(R.string.reference_no)+" "+resMap.get("referenceNumber"));

        PrinterApi.PrnStr_Api(getString(R.string.approval_code)+" "+resMap.get("approvalCode"));
        PrinterApi.PrnStr_Api(getString(R.string.reference_id)+" "+resMap.get("systemTraceAuditNumber"));
        PrinterApi.PrnStr_Api(getString(R.string.response_code)+" "+resMap.get("responseCode"));
        PrinterApi.PrnStr_Api(getString(R.string.respoonse_message)+" "+resMap.get("responseMessage"));
        PrinterApi.PrnStr_Api(getString(R.string.tran_fee)+" "+resMap.get("tranFee"));
        PrinterApi.PrnStr_Api(getString(R.string.auth_no)+"");

        PrinterApi.PrnStr_Api("\n");
        PrinterApi.PrnStr_Api("\n");
        PrinterApi.PrnStr_Api("******* "+getString(R.string.merchant_copy)+" ********");
        // print merchant copy here
        for(int i = 0 ; i < 5; i++) {
            PrinterApi.PrnStr_Api("\n");
        }

        PrintData();
    }
    public void PrintData() {
        int ret = PrinterApi.PrnStart_Api();
        String Buf;
        if (ret == 2) {
            Buf = "Return：" + ret + "\tpaper is not enough";
        } else if (ret == 3) {
            Buf = "Return：" + ret + "\ttoo hot";
        } else if (ret == 4) {
            Buf = "Return：" + ret + "\tPLS put it back\nPress any key to reprint";
        } else if (ret == 0) {
            PrinterApi.PrnClrBuff_Api();
            return;
        }
        PrinterApi.PrnClrBuff_Api();
    }

}
