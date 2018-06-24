package sd.code.morsalpos.process;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

import sd.code.morsalpos.R;
import sd.code.morsalpos.Type.PurchaseInfo;
import sd.code.morsalpos.common.Common;
import sd.code.morsalpos.common.ErrorLog;
import sd.code.morsalpos.common.PortalApplication;
import sd.code.morsalpos.service.TerminalService;


public class PurchaseProcess extends AsyncTask<Void, Void, Void> {

    String data;
    private static JSONArray result;
    ProgressDialog progressDialog;
    Activity activity;
    View submit;

    PortalApplication application;
    TextView txtResult;
    String resultMessage = "";

    boolean isSuccess = false;
    boolean invalidSession = false;
    Resources resources;
    PurchaseInfo purchaseInfo;

    Map<String, String> resMap;

    public PurchaseProcess(Activity activity, PortalApplication application, View submit, TextView txtResult, PurchaseInfo purchaseInfo, Map<String, String> resMap) {

        this.activity = activity;
        this.application = application;
        this.submit = submit;
        this.txtResult = txtResult;
        resources = activity.getResources();
        this.purchaseInfo = purchaseInfo;
        progressDialog = new ProgressDialog(activity, R.style.AppTheme_Dark_Dialog);

        this.resMap = resMap;
    }

    public boolean purchase() {

        TerminalService services = new TerminalService();
        String data = "";
        try {
            Log.e("aaaa", application.getWorkingKey());
            if (application.getWorkingKey() != null) {
                String purchaseResult = services.purchase(purchaseInfo);
                JSONObject purchaseObj = new JSONObject(purchaseResult);

                String tradeTime = Common.formatDate(purchaseObj.getString("tranDateTime"), "ddMMyyHHmmss", "HH:mm:ss");
                String tradeDate = Common.formatDate(purchaseObj.getString("tranDateTime"), "ddMMyyHHmmss", "dd/MM/yyyy");
                double tranAmount = purchaseObj.getDouble("tranAmount");

                String tranFee = purchaseObj.getString("tranFee");
                String responseMessage = purchaseObj.getString("responseMessage");
                String responseCode = purchaseObj.getString("responseCode");
                String referenceNumber = purchaseObj.getString("referenceNumber");
                String responseStatus = purchaseObj.getString("responseStatus");
                double additionalAmount = purchaseObj.getDouble("additionalAmount");
                String tranCurrencyCode = purchaseObj.getString("tranCurrencyCode");


                String approvalCode = purchaseObj.getString("approvalCode");
                String systemTraceAuditNumber = purchaseObj.getString("systemTraceAuditNumber");

                resMap.put("time", tradeTime);
                resMap.put("date", tradeDate);
                resMap.put("tranAmount", tranAmount+"");

                resMap.put("tranFee", tranFee);
                resMap.put("responseMessage", responseMessage);
                resMap.put("responseCode", responseCode);
                resMap.put("referenceNumber", referenceNumber);
                resMap.put("approvalCode", approvalCode);
                resMap.put("systemTraceAuditNumber", systemTraceAuditNumber);
                resMap.put("responseStatus", responseStatus);
                resMap.put("tranCurrencyCode", tranCurrencyCode);
                resMap.put("additionalAmount", additionalAmount+"");

                if (purchaseObj.has("responseCode") && Integer.parseInt(purchaseObj.get("responseCode").toString()) == 0) {
                    resultMessage = purchaseResult;
                } else {
                    if (purchaseObj.has("detail")) {
                        JSONObject errorDetail = new JSONObject(purchaseObj.get("detail").toString());
                        if (errorDetail.has("error_code") && errorDetail.get("error_code").toString().equals("permission_denied")) {
                            invalidSession = true;
                        } else {
                            resultMessage += ErrorLog.getMorsalError(purchaseObj, activity);
                        }
                    } else {
                        resultMessage += ErrorLog.getMorsalError(purchaseObj, activity);
                    }
                }
            } else {

                invalidSession = true;
                Log.e("keyData", "is null");

            }

        } catch (Exception ex) {
            // TODO Auto-generated catch block
            Log.e("checkBalance error data", ex.toString());
        }
        return isSuccess;
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        // TODO Auto-generated method stub

        purchase();


        return null;
    }

    @Override
    public void onPreExecute() {

        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(" Please Wait...");
        progressDialog.show();
        submit.setVisibility(View.GONE);
    }


    @Override
    public void onPostExecute(Void unused) {

        progressDialog.dismiss();
        submit.setVisibility(View.VISIBLE);
        txtResult.setVisibility(View.VISIBLE);

        txtResult.setText(resultMessage);

        if (invalidSession) {
            Toast.makeText(activity, "Session has ended !!!", Toast.LENGTH_SHORT).show();
            application.setToken(null);
            activity.finish();
        }
    }
}

