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

import sd.code.morsalpos.R;
import sd.code.morsalpos.common.CardInfo;
import sd.code.morsalpos.common.ErrorLog;
import sd.code.morsalpos.common.PortalApplication;
import sd.code.morsalpos.service.TerminalService;


public class checkBalanceProcess extends AsyncTask<Void, Void, Void> {

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
    boolean invalidSession = false;
    Resources resources;


    public checkBalanceProcess(Activity activity, PortalApplication application, View submit, CardInfo card, TextView txtResult) {

        this.activity = activity;
        this.card = card;
        this.application = application;
        this.submit = submit;
        this.txtResult = txtResult;
        resources = activity.getResources();

        progressDialog = new ProgressDialog(activity, R.style.AppTheme_Dark_Dialog);

    }

    public boolean checkBalance() {

        TerminalService services = new TerminalService();
        String data = "";
        try {
            if (application.getPublicKey() != null) {
                String balanceData = services.checkBalance(application.getToken(), application.getPublicKey(), card);
                JSONObject balanceObj = new JSONObject(balanceData);
                if (balanceObj != null && balanceObj.has("responseCode") && Integer.parseInt(balanceObj.get("responseCode").toString()) == 0) {
                    JSONObject balanceValuesObj = new JSONObject(balanceObj.get("balance").toString());
                    resultMessage = resources.getString(R.string.available_balance_is) + ": " + balanceValuesObj.get("available").toString() + " " + resources.getString(R.string.sdg);
                } else if (balanceObj != null) {
                    if (balanceObj.has("detail")) {
                        JSONObject errorDetail = new JSONObject(balanceObj.get("detail").toString());
                        if (errorDetail.has("error_code") && errorDetail.get("error_code").toString().equals("permission_denied")) {
                            invalidSession = true;
                        } else {
                            resultMessage += ErrorLog.getMorsalError(balanceObj, activity);
                        }
                    } else {
                        resultMessage += ErrorLog.getMorsalError(balanceObj, activity);
                    }
                } else {
                    resultMessage = activity.getResources().getString(R.string.unknown_error);
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

        checkBalance();


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

