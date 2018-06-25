package sd.code.morsalpos;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.vanstone.trans.api.MagCardApi;
import com.vanstone.trans.api.PiccApi;
import com.vanstone.trans.api.PrinterApi;
import com.vanstone.trans.api.SystemApi;
import com.vanstone.utils.CommonConvert;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import cn.refactor.lib.colordialog.PromptDialog;
import sd.code.morsalpos.common.Common;
import sd.code.morsalpos.common.Config;
import sd.code.morsalpos.common.PortalApplication;
import sd.code.morsalpos.common.Security;
import sd.code.morsalpos.process.CallUrl;

public class CashOutActivity extends AppCompatActivity {
    final static String TAG = CashOutActivity.class.getSimpleName();

    EditText inputPhone ;
    EditText voucherNumber;
    EditText tranAmount;
    Button btnGet;
    boolean cancel = false;
    PortalApplication application;
    Map<String, String> resMap;


    static {
        System.loadLibrary("A90JavahCore");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_out);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inputPhone = (EditText) findViewById(R.id.input_phone);
        voucherNumber = (EditText) findViewById(R.id.voucher_number);
        tranAmount = (EditText) findViewById(R.id.tran_amount);
        application = (PortalApplication) getApplication();

        String CurDir = getApplicationContext().getFilesDir().getAbsolutePath();
        SystemApi.SystemInit_Api(0, CommonConvert.StringToBytes(CurDir + "/" + "\0"), CashOutActivity.this);

        MagCardApi.MagOpen_Api();
        MagCardApi.MagReset_Api();
        PiccApi.PiccOpen_Api();

        resMap = new HashMap<>();

        btnGet = (Button) findViewById(R.id.btn_pay);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(inputPhone.getText().toString())) {
                    inputPhone.setError(getString(R.string.error_empty_filed));
                    cancel = true;
                }
                if (TextUtils.isEmpty(voucherNumber.getText().toString())) {
                    voucherNumber.setError(getString(R.string.error_empty_filed));
                    cancel = true;
                }
                if (!cancel) {
                    Map<String, String> map = new HashMap<>();

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
                    Date now = new Date();

                    map.put("tranDateTime", format.format(now));
                    map.put("systemTraceAuditNumber", String.valueOf(Security.getSystemTraceAuditNumber()));
                    map.put("terminalId", application.getTerminaID());
                    map.put("phoneNumber", inputPhone.getText().toString());
                    map.put("voucherNumber", voucherNumber.getText().toString());
                    map.put("tranAmount", tranAmount.getText().toString());

                    Log.d(TAG, "Map: "+map.toString());
                    final ProgressDialog dialog = new ProgressDialog(CashOutActivity.this, R.style.AppTheme_Dark_Dialog);
                    dialog.setMessage("Running...");
                    dialog.setCancelable(false);
                    dialog.show();

                    CallUrl callUrl = new CallUrl(Config.URL_CASH_OUT_VOUCHER, new JSONObject(map)) {
                        @Override
                        public void afterRequest(JSONObject response) {
                            dialog.dismiss();
                            String content = "";
                            resMap = new HashMap<>();
                            try {

                                String tradeTime = Common.formatDate(response.getString("tranDateTime"), "ddMMyyHHmmss", "HH:mm:ss");
                                String tradeDate = Common.formatDate(response.getString("tranDateTime"), "ddMMyyHHmmss", "dd/MM/yyyy");
                                String tranAmount = response.getString("tranAmount");
                                String responseCode = response.getString("responseCode");
                                String responseStatus = response.getString("responseStatus");
                                String voucherNumber = response.getString("voucherNumber");
                                String disputeRRN = response.getString("disputeRRN");
                                String transactionId = response.getString("transactionId");
                                String approvalCode = response.getString("approvalCode");
                                String phoneNumber = response.getString("phoneNumber");
                                String responseMessage = response.getString("responseMessage");
                                String referenceNumber = response.getString("referenceNumber");
                                String tranCurrencyCode = response.getString("tranCurrencyCode");
                                String systemTraceAuditNumber = response.getString("systemTraceAuditNumber");

                                resMap.put("time", tradeTime);
                                resMap.put("date", tradeDate);

                                resMap.put("responseMessage", responseMessage);
                                resMap.put("responseCode", responseCode);
                                resMap.put("referenceNumber", referenceNumber);
                                resMap.put("approvalCode", approvalCode);
                                resMap.put("systemTraceAuditNumber", systemTraceAuditNumber);
                                resMap.put("responseStatus", responseStatus);
                                resMap.put("voucherNumber", voucherNumber);
                                resMap.put("phoneNumber", phoneNumber);

                                resMap.put("tranCurrencyCode", tranCurrencyCode);
                                resMap.put("tranAmount", tranAmount);
                                resMap.put("disputeRRN", disputeRRN);
                                resMap.put("transactionId", transactionId);
                                content =
                                        "Transaction time: "+tradeTime+"\n" +
                                                "Transaction date: "+tradeDate+"\n" +
                                                "Response message: "+responseMessage+"\n" +
                                                "Phone number: "+phoneNumber+"\n" +
                                                "Voucher Number: " +voucherNumber+"\n";

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            new PromptDialog(CashOutActivity.this)
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
                                            Toast.makeText(CashOutActivity.this, "Printing...", Toast.LENGTH_SHORT).show();
                                        }
                                    }).show();
                        }
                        @Override
                        public void onError(VolleyError error) {
                            Log.e(TAG, "Error: "+error.toString());
                            dialog.dismiss();
                            new PromptDialog(CashOutActivity.this)
                                    .setDialogType(PromptDialog.DIALOG_TYPE_WRONG)
                                    .setAnimationEnable(true)
                                    .setTitleText("Error")
                                    .setContentText(error.toString())
                                    .setPositiveListener("Close", new PromptDialog.OnPositiveListener() {
                                        @Override
                                        public void onClick(PromptDialog dialog) {
                                            dialog.dismiss();
                                        }
                                    }).show();
                            Toast.makeText(CashOutActivity.this, "Error occur.", Toast.LENGTH_SHORT).show();
                        }
                    };
                    callUrl.execute();

                }
            }
        });
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

        PrinterApi.PrnStr_Api("**** "+getString(R.string.generate_voucher)+" *****");
        PrinterApi.PrnStr_Api(getString(R.string.date)+" "+resMap.get("date"));
        PrinterApi.PrnStr_Api(getString(R.string.time)+" "+resMap.get("time"));

        PrinterApi.PrnStr_Api(getString(R.string.merchant_id)+" "+application.getMerchantID());
        PrinterApi.PrnStr_Api(getString(R.string.terminal_id)+" "+application.getTerminaID());

        PrinterApi.PrnStr_Api(getString(R.string.prompt_phone)+ " " +resMap.get("phoneNumber"));

        PrinterApi.PrnStr_Api("******* "+resMap.get("responseMessage")+"  ********");

        PrinterApi.PrnStr_Api(getString(R.string.stan)+" "+resMap.get("systemTraceAuditNumber"));
        PrinterApi.PrnStr_Api(getString(R.string.amount)+": "+resMap.get("tranAmount")+ " "+resMap.get("tranCurrencyCode"));

        PrinterApi.PrnStr_Api(getString(R.string.prompt_phone)+" "+resMap.get("phoneNumber"));

        PrinterApi.PrnStr_Api(getString(R.string.reference_no)+" "+resMap.get("referenceNumber"));

        PrinterApi.PrnStr_Api(getString(R.string.approval_code)+" "+resMap.get("approvalCode"));
        PrinterApi.PrnStr_Api(getString(R.string.status)+" "+resMap.get("responseStatus"));

        PrinterApi.PrnStr_Api(getString(R.string.disput_rrn)+" "+resMap.get("disputeRRN"));
        PrinterApi.PrnStr_Api(getString(R.string.transaction_id)+" "+resMap.get("transactionId"));

        PrinterApi.PrnStr_Api(getString(R.string.reference_id)+" "+resMap.get("referenceNumber"));
        PrinterApi.PrnStr_Api(getString(R.string.response_code)+" "+resMap.get("responseCode"));
        PrinterApi.PrnStr_Api(getString(R.string.respoonse_message)+" "+resMap.get("responseMessage"));
        PrinterApi.PrnStr_Api("==============================");
        PrinterApi.PrnStr_Api(getString(R.string.voucher_number)+": "+resMap.get("voucherNumber"));
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
