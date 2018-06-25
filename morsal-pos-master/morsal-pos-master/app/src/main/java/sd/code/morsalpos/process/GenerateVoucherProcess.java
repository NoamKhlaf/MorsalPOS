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


public class GenerateVoucherProcess extends AsyncTask<Void, Void, Void> {

    String data;
    private static JSONArray result;
    ProgressDialog progressDialog ;
    Activity activity ;
    View submit ;

    CardInfo card ;
    PortalApplication application ;
    TextView txtResult;
    String resultMessage= "";

    boolean isSuccess = false ;
    boolean invalidSession = false ;

    String voucherNumber;double tranAmount;
    Resources resources;



    public GenerateVoucherProcess(Activity activity , PortalApplication application, View submit, CardInfo card, TextView txtResult, String voucherNumber, double tranAmount){

        this.activity =activity ;
        this.card = card ;
        this.application = application;
        this.submit = submit ;
        this.txtResult=txtResult;
        this.voucherNumber=voucherNumber;
        this.tranAmount=tranAmount;
        resources = activity.getResources();


        progressDialog = new ProgressDialog(activity, R.style.AppTheme_Dark_Dialog);

    }

    public boolean checkBalance(){

        TerminalService services = new TerminalService();


        try {


            if(application.getPublicKey() != null) {

                    String voucherData = services.generateVoucher(application.getToken(),application.getPublicKey(),card,voucherNumber,tranAmount);

                    JSONObject voucherObj = new JSONObject(voucherData);

                    if(voucherObj != null && voucherObj.has("responseStatus") &&  voucherObj.get("responseStatus").toString().equals("Successful") ){

                        resultMessage   =  resources.getString(R.string.successful)+"... \n"+resources.getString(R.string.voucher_code)+": "+voucherObj.get("voucherCode").toString()+" \n"+
                                resources.getString(R.string.amount)+": "+voucherObj.get("tranAmount").toString()+"\n" +
                                resources.getString(R.string.prompt_phone)+": "+voucherObj.get("voucherNumber").toString() ;


                    }else if(voucherObj != null){


                        if(voucherObj.has("detail")){

                            JSONObject errorDetail =   new JSONObject(voucherObj.get("detail").toString());

                            if(errorDetail.has("error_code") && errorDetail.get("error_code").toString().equals("permission_denied")  ){

                                invalidSession = true;

                            }else{

                                resultMessage += ErrorLog.getMorsalError(voucherObj,activity);
                            }
                        } else {
                            resultMessage += ErrorLog.getMorsalError(voucherObj,activity);
                        }

                    } else {

                        resultMessage = activity.getResources().getString(R.string.unknown_error);

                    }





            }else {

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

        checkBalance();


        return null;
    }

    @Override
    public void onPreExecute() {

        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        submit.setVisibility(View.GONE);


    }


    @Override
    public void onPostExecute(Void unused) {

        progressDialog.dismiss();
        submit.setVisibility(View.VISIBLE);
        txtResult.setVisibility(View.VISIBLE);

        txtResult.setText(resultMessage);

        if(invalidSession) {
            Toast.makeText(activity, "Session has ended !!!", Toast.LENGTH_SHORT).show();
            application.setToken(null);
            activity.finish();
        }



    }


}

