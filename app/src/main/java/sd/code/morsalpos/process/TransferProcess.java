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


public class TransferProcess extends AsyncTask<Void, Void, Void> {

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

    String toCard;double tranAmount;
    Resources resources;


    public TransferProcess(Activity activity , PortalApplication application, View submit, CardInfo card, TextView txtResult, String toCard, double tranAmount){

        this.activity =activity ;
        this.card = card ;
        this.application = application;
        this.submit = submit ;
        this.txtResult=txtResult;
        this.toCard=toCard;
        this.tranAmount=tranAmount;

        resources = activity.getResources();


        progressDialog = new ProgressDialog(activity, R.style.AppTheme_Dark_Dialog);

    }

    public boolean transfer(){

        TerminalService services = new TerminalService();
        String data = "" ;

        try {


            if(application.getPublicKey() != null) {


                    String paymentData = services.transfer(application.getToken(),application.getPublicKey() ,card,toCard,tranAmount);

                    JSONObject balanceObj = new JSONObject(paymentData);
                    if(balanceObj != null && balanceObj.has("responseStatus") &&  balanceObj.get("responseStatus").toString().equals("Successful") ){



                        resultMessage   =  resources.getString(R.string.successful)+" \n" +
                                resources.getString(R.string.select_card_from)+": "+balanceObj.get("PAN").toString()+" \n"+
                                resources.getString(R.string.select_card_to)+": "+balanceObj.get("toCard").toString()+" \n"+
                                resources.getString(R.string.amount)+": "+balanceObj.get("tranAmount").toString()+" "+ resources.getString(R.string.sdg)+" \n"+
                                resources.getString(R.string.fee)+": "+balanceObj.get("acqTranFee").toString() ;




                            try {
                                if(balanceObj.get("balance") != null){

                                JSONObject balanceValuesObj = new JSONObject(balanceObj.get("balance").toString());

                                resultMessage += "\n" +resources.getString(R.string.available_balance_is)+": " + balanceValuesObj.get("available").toString()+" "+ resources.getString(R.string.sdg);
                                }
                            }catch(Exception e){}




                    }else if(balanceObj != null){


                        if(balanceObj.has("detail")){

                            JSONObject errorDetail =   new JSONObject(balanceObj.get("detail").toString());

                            if(errorDetail.has("error_code") && errorDetail.get("error_code").toString().equals("permission_denied")  ){

                                invalidSession = true;

                            }else{

                                resultMessage += ErrorLog.getMorsalError(balanceObj,activity);
                            }
                        } else {
                            resultMessage += ErrorLog.getMorsalError(balanceObj,activity);
                        }

                    } else {

                        resultMessage = activity.getResources().getString(R.string.unknown_error);

                    }

                //  isSuccess = true ;


            }else {

                 invalidSession = true;

             }

        } catch (Exception ex) {
            // TODO Auto-generated catch block
            Log.e("transfer error data", ex.toString());
        }
        return isSuccess;
    }



    @Override
    protected Void doInBackground(Void... arg0) {
        // TODO Auto-generated method stub

        transfer();


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

