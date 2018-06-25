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

import java.util.Iterator;

import sd.code.morsalpos.R;
import sd.code.morsalpos.common.CardInfo;
import sd.code.morsalpos.common.ErrorLog;
import sd.code.morsalpos.common.PortalApplication;
import sd.code.morsalpos.service.TerminalService;


public class BillInfoProcess extends AsyncTask<Void, Void, Void> {

    String data;
    private static JSONArray result;
    ProgressDialog progressDialog;
    Activity activity;
    View submit;

    CardInfo card;
    PortalApplication application;
    TextView txtResult;
    String resultMessage = "";

    boolean isSuccess = false;

    String payeeId;
    String paymentInfo;
    boolean invalidSession = false;
    Resources resources;


    public BillInfoProcess(Activity activity, PortalApplication application, View submit, CardInfo card, TextView txtResult, String payeeId, String paymentInfo) {

        this.activity = activity;
        this.card = card;
        this.application = application;
        this.submit = submit;
        this.txtResult = txtResult;
        this.payeeId = payeeId;
        this.paymentInfo = paymentInfo;
        progressDialog = new ProgressDialog(activity, R.style.AppTheme_Dark_Dialog);

        resources = activity.getResources();

    }

    public boolean getBillInfo() {


        TerminalService services = new TerminalService();
        String data = "";

        try {

            if (application.getPublicKey() != null) {

                String paymentData = services.billInquiry(application.getToken(), application.getPublicKey(), card, payeeId, paymentInfo);

                JSONObject paymentObj = new JSONObject(paymentData);
                if (paymentObj != null && paymentObj.has("responseCode") && Integer.parseInt(paymentObj.get("responseCode").toString()) == 0) {


                    if (paymentObj.get("billInfo") != null) {

                        resultMessage += resources.getString(R.string.successful) + "... \n" + resources.getString(R.string.bill_info) + ":";
                        JSONObject billInfo = new JSONObject(paymentObj.get("billInfo").toString());

                        Iterator<String> iter = billInfo.keys();
                        while (iter.hasNext()) {
                            String key = iter.next();
                            Object value = billInfo.get(key);
                            String info = value.toString();

                            try {

                                if (key.equals("billedAmount")) {
                                    key = resources.getString(R.string.billedAmount);
                                } else if (key.equals("totalAmount")) {
                                    key = resources.getString(R.string.totalAmount);
                                } else if (key.equals("unbilledAmount")) {
                                    key = resources.getString(R.string.unbilledAmount);
                                } else if (key.equals("last4Digits")) {
                                    key = resources.getString(R.string.last4Digits);
                                } else if (key.equals("contractNumber")) {
                                    key = resources.getString(R.string.contractNumber);
                                } else if (key.equals("lastInvoiceDate")) {
                                    key = resources.getString(R.string.lastInvoiceDate);
                                } else if (key.equals("lastInvoiceDate")) {
                                    key = resources.getString(R.string.lastInvoiceDate);
                                } else if (key.equals("total")) {
                                    key = resources.getString(R.string.total);
                                } else if (key.equals("SubscriberID")) {
                                    key = resources.getString(R.string.SubscriberID);
                                } else if (key.equals("billAmount")) {
                                    key = resources.getString(R.string.billAmount);
                                } else if (key.equals("InvoiceStatus")) {
                                    key = resources.getString(R.string.InvoiceStatus);
                                    if (value.toString().equals("0")) {
                                        info = resources.getString(R.string.CANCELED);
                                    } else if (value.toString().equals("1")) {
                                        info = resources.getString(R.string.PENDING);
                                    } else if (value.toString().equals("2")) {
                                        info = resources.getString(R.string.PAID);
                                    }
                                } else if (key.equals("PayerName")) {
                                    key = resources.getString(R.string.PayerName);
                                } else if (key.equals("UnitName")) {
                                    key = resources.getString(R.string.UnitName);
                                } else if (key.equals("ServiceNumber")) {
                                    key = resources.getString(R.string.ServiceNumber);
                                } else if (key.equals("TotalAmount")) {
                                    key = resources.getString(R.string.TotalAmount);
                                } else if (key.equals("DueAmount")) {
                                    key = resources.getString(R.string.DueAmount);
                                } else if (key.equals("InvoiceExpiry")) {
                                    key = resources.getString(R.string.InvoiceExpiry);
                                } else if (key.equals("ServiceName")) {
                                    key = resources.getString(R.string.ServiceName);
                                } else if (key.equals("ReferenceId")) {
                                    key = resources.getString(R.string.ReferenceId);
                                }
                                resultMessage += "\n" + key + ": " + info;
                            } catch (Exception e) {

                            }
                        }
                    }

                } else if (paymentObj != null) {


                    if (paymentObj.has("detail")) {

                        JSONObject errorDetail = new JSONObject(paymentObj.get("detail").toString());

                        if (errorDetail.has("error_code") && errorDetail.get("error_code").toString().equals("permission_denied")) {

                            invalidSession = true;

                        } else {

                            resultMessage += ErrorLog.getMorsalError(paymentObj, activity);
                        }
                    } else {
                        resultMessage += ErrorLog.getMorsalError(paymentObj, activity);
                    }

                } else {

                    resultMessage = activity.getResources().getString(R.string.unknown_error);

                }

                //  isSuccess = true ;

            } else {

                invalidSession = true;
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

        getBillInfo();


        return null;
    }

    @Override
    public void onPreExecute() {

        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please wait...");
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

