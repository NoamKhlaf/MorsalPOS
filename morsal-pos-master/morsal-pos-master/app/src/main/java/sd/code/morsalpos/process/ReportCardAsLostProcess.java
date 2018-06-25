package sd.code.morsalpos.process;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;

import sd.code.morsalpos.R;
import sd.code.morsalpos.common.PortalApplication;
import sd.code.morsalpos.service.TerminalService;


public class ReportCardAsLostProcess extends AsyncTask<Void, Void, Void> {

    String data;
    private static JSONArray result;
    ProgressDialog progressDialog ;
    Activity activity ;
    View submit ;

     PortalApplication application ;
    TextView txtResult;
    String resultMessage;
    Resources resources;


    boolean isSuccess = false ;

    String pan;


    public ReportCardAsLostProcess(Activity activity , PortalApplication application, View submit, TextView txtResult, String pan){

        this.activity =activity ;
        this.pan = pan ;
        this.application = application;
        this.submit = submit ;
        this.txtResult=txtResult;

        resources = activity.getResources();

        progressDialog = new ProgressDialog(activity, R.style.AppTheme_Dark_Dialog);

    }

    public boolean reportCardAsLost(){

        TerminalService services = new TerminalService();
        String data = "" ;

        try {

                    boolean result = services.reportCardAsLost(application.getToken(),pan);

                    if(result){

                        resultMessage = resources.getString(R.string.reported_successfully);
                    }else {
                        resultMessage = resources.getString(R.string.enter_a_valid_card); ;
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

        reportCardAsLost();


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


    }


}

