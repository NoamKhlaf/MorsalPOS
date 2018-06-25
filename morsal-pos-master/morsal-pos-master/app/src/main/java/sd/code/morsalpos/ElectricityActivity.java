package sd.code.morsalpos;

import android.annotation.SuppressLint;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import cn.refactor.lib.colordialog.PromptDialog;
import sd.code.morsalpos.adapter.PayeeListAdapter;
import sd.code.morsalpos.Type.PayeeInfo;
import sd.code.morsalpos.Type.PayeeType;
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
import sd.code.morsalpos.database.DatabaseHandler;
import sd.code.morsalpos.database.PayeeTable;
import sd.code.morsalpos.process.BillPaymentProcess;
import sd.code.morsalpos.process.PaymentProcess;

public class ElectricityActivity extends AppCompatActivity {

    public static ArrayList<PayeeInfo> payees = new ArrayList<PayeeInfo>();
    PayeeListAdapter payeeAdapter;
    AppCompatSpinner payeeList;
    PortalApplication application;
    Button sumbit;
    EditText iPin, meter, amount, pan;
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
        setContentView(R.layout.activity_electricity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        application = (PortalApplication) getApplication();

        initHandler();

        String CurDir = getApplicationContext().getFilesDir().getAbsolutePath();
        SystemApi.SystemInit_Api(0, CommonConvert.StringToBytes(CurDir + "/" + "\0"), ElectricityActivity.this);

        MagCardApi.MagOpen_Api();
        MagCardApi.MagReset_Api();
        PiccApi.PiccOpen_Api();

        payeeList = (AppCompatSpinner) findViewById(R.id.payeeList);

        sumbit = (Button) findViewById(R.id.btn_pay);
        iPin = (EditText) findViewById(R.id.iPin);
        meter = (EditText) findViewById(R.id.input_meter);
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
                if (Integer.parseInt(amount.getText().toString()) > 1000) {
                    amount.setError("max is 1000");
                    cancel = true;
                }

                if (TextUtils.isEmpty(meter.getText().toString())) {
                    meter.setError(getString(R.string.error_empty_filed));
                    cancel = true;
                }
                if (meter.getText().length() != 11) {
                    meter.setError(getString(R.string.invalid_value));
                    cancel = true;
                }
                if (pan.getText().toString().length() != 16 && pan.getText().toString().length() != 19) {
                    pan.setError(getString(R.string.error_invalid_card));
                    cancel = true;
                }
                if (TextUtils.isEmpty(pan.getText().toString())) {
                    pan.setError(getString(R.string.error_empty_filed));
                    cancel = true;
                }

                if (!cancel) {

                    // Finish the registration screen and return to the Login activity
                    String yearFormat = year.getSelectedItem().toString().substring(2);
                    String expDate = yearFormat + month.getSelectedItem().toString();
                    String payeeId = payees.get(payeeList.getSelectedItemPosition()).getPayeeNumber();
                    String panStr = pan.getText().toString();
                    String iPinStr = iPin.getText().toString();
                    String amountStr = amount.getText().toString();
                    String personalPaymentInfo = meter.getText().toString();

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

                    map.put("tranAmount", amountStr);
                    map.put("personalPaymentInfo", personalPaymentInfo);
                    map.put("payeeId", payeeId);

                    BillPaymentProcess process = new BillPaymentProcess(ElectricityActivity.this, map) {
                        @Override
                        public void onResponse(JSONObject response) {
                            StringBuilder content = new StringBuilder();
                            resMap = new HashMap<>();
                            try {
                                String tradeTime = Common.formatDate(response.getString("tranDateTime"), "ddMMyyHHmmss", "dd-MM-yyyy HH:mm:ss"); // 020518145732
                                String additionalData = response.getString("additionalData");
                                Map<String, String> map = Common.formatAdditionalData(additionalData);

                                double tranFee = response.getDouble("tranFee");
                                String personalPaymentInfo = response.getString("personalPaymentInfo");
                                double tranAmount = response.getDouble("tranAmount");
                                String tranCurrencyCode = response.getString("tranCurrencyCode");
                                double additionalAmount = response.getDouble("additionalAmount");

                                resMap.put("time", tradeTime);
                                resMap.put("tranFee", tranFee+"");
                                resMap.put("personalPaymentInfo", personalPaymentInfo);
                                resMap.put("Amount", tranAmount + " " + tranCurrencyCode);
                                resMap.put("tranCurrencyCode", tranCurrencyCode);
                                resMap.put("additionalAmount", additionalAmount+"");
                                resMap.put("responseMessage", response.getString("responseMessage"));
                                resMap.put("systemTraceAuditNumber", response.getString("systemTraceAuditNumber"));
                                resMap.put("referenceNumber", response.getString("referenceNumber"));
                                resMap.put("responseCode", response.getString("responseCode"));

                                content = new StringBuilder("Transaction time: \n" + tradeTime + "\n" +
                                        "Amount: " + tranAmount + " " + tranCurrencyCode +
                                        "Transaction fee: " + tranFee + "\n" +
                                        "Meter No: " + personalPaymentInfo + "\n" +
                                        "Additional amount: " + additionalAmount + "\n");
                                for ( Map.Entry<String, String> entry : map.entrySet()) {
                                    String key = entry.getKey();
                                    String value = entry.getValue();
                                    resMap.put(key, value);
                                    content.append(key).append(": ").append(value).append("\n");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            new PromptDialog(ElectricityActivity.this)
                                    .setDialogType(PromptDialog.DIALOG_TYPE_SUCCESS)
                                    .setAnimationEnable(true)
                                    .setTitleText("Success")
                                    .setContentText(content.toString())
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
                                            Toast.makeText(ElectricityActivity.this, "Printing...", Toast.LENGTH_SHORT).show();
                                        }
                                    }).show();

                        }

                        @Override
                        public void onErrorOccur(VolleyError error) {

                        }
                    };
                    process.execute();

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

        DatabaseHandler db = new DatabaseHandler(ElectricityActivity.this);

        PayeeTable payeeTable = new PayeeTable(ElectricityActivity.this);

        payees.clear();
        payees.addAll(payeeTable.getPayeesByType(PayeeType.PAYEE_TYPE_NEC));
        payeeAdapter = new PayeeListAdapter(this, payees);
        payeeList.setAdapter(payeeAdapter);


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

    public void PrtCardInfo() {
        byte[] PrnBuf = new byte[128];
        PrinterApi.PrnClrBuff_Api();
        PrinterApi.PrnFontSet_Api(32, 32, 0);
        PrinterApi.PrnSetGray_Api(15);
        PrinterApi.PrnLineSpaceSet_Api((short) 5, 0);
        PrinterApi.PrnStr_Api("\t\tPOS Receipt");
        PrinterApi.PrnStr_Api("\t\tCARREFOUR");
        PrinterApi.PrnStr_Api("\t\tKhartoum");
        PrinterApi.PrnFontSet_Api(24, 24, 0);
        PrinterApi.PrnStr_Api("********* Electricity Payment ********");
        PrinterApi.PrnStr_Api("DATE:");
        PrinterApi.PrnStr_Api(resMap.get("date"));
        PrinterApi.PrnStr_Api("TIME:");
        PrinterApi.PrnStr_Api(resMap.get("time"));

        PrinterApi.PrnStr_Api("Merchant ID:" + application.getMerchantID());
        PrinterApi.PrnStr_Api("TERMINAL NO:" + application.getTerminaID());

        PrinterApi.PrnStr_Api("Card Number:");
        PrinterApi.PrnStr_Api(pan.getText().toString());
        PrinterApi.PrnStr_Api("Card Holder Name:");
        PrinterApi.PrnStr_Api(cardHolderName);

        PrinterApi.PrnStr_Api("***********  " + resMap.get("responseMessage") + "  ***********");

        PrinterApi.PrnStr_Api("STAN: " + resMap.get("systemTraceAuditNumber"));
        PrinterApi.PrnStr_Api("Reference No: " + resMap.get("referenceNumber"));
        PrinterApi.PrnStr_Api("Receipt No: " + resMap.get("systemTraceAuditNumber"));

        PrinterApi.PrnStr_Api("Units: " + resMap.get("Units"));
        PrinterApi.PrnStr_Api("Paid Amount: " + resMap.get("Amount"));
        PrinterApi.PrnStr_Api("Sale Amount: " + resMap.get("SaleAmount"));
        PrinterApi.PrnStr_Api("Fees: " + resMap.get("tranFee"));
        PrinterApi.PrnStr_Api("Reference Id: " + resMap.get("referenceNumber"));
        PrinterApi.PrnStr_Api("Customer Name: " + resMap.get("CustomerName"));
        PrinterApi.PrnStr_Api("Account No: " + resMap.get("MeterNumber"));
        PrinterApi.PrnStr_Api("Meter No: " + resMap.get("MeterNumber"));
        PrinterApi.PrnStr_Api("Reference: " + resMap.get("ReferenceNumber"));
        PrinterApi.PrnStr_Api("Operator Message: " + resMap.get("OperatorMessage"));
        PrinterApi.PrnStr_Api("Response Code: " + resMap.get("responseCode"));
        PrinterApi.PrnStr_Api("Response Message: " + resMap.get("responseMessage"));

        PrinterApi.PrnStr_Api("AUTH No: ");

        PrinterApi.PrnStr_Api("==============================");
        PrinterApi.PrnStr_Api("Token: "+resMap.get("Token"));
        PrinterApi.PrnStr_Api("==============================");
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
