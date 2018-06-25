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


public class HigherEducationArabProcess extends AsyncTask<Void, Void, Void> {

    String data;
    private static JSONArray result;
    ProgressDialog progressDialog ;
    Activity activity ;
    View submit ;

    CardInfo card ;
    PortalApplication application ;
    TextView txtResult;
    String resultMessage = "";
    boolean invalidSession = false ;
    Resources resources;



    String extraInfo;
    View finish;

    boolean isSuccess = false ;

    String payeeId;String paymentInfo;double tranAmount;


    public HigherEducationArabProcess(Activity activity , PortalApplication application, View submit, CardInfo card, TextView txtResult, String payeeId, String paymentInfo, double tranAmount, String extraInfo, View finish){

        this.activity =activity ;
        this.card = card ;
        this.application = application;
        this.submit = submit ;
        this.txtResult=txtResult;
        this.payeeId=payeeId;
        this.paymentInfo=paymentInfo;
        this.tranAmount=tranAmount;
        this.extraInfo=extraInfo;
        this.finish = finish;

        resources = activity.getResources();
        progressDialog = new ProgressDialog(activity, R.style.AppTheme_Dark_Dialog);

    }

    public boolean pay(){

        TerminalService services = new TerminalService();


        try {


            if(application.getPublicKey() != null) {


                    String paymentData = services.payment(application.getToken(),application.getPublicKey(),card,payeeId,paymentInfo,tranAmount);

                    JSONObject tranObj = new JSONObject(paymentData);
                    if(tranObj != null && tranObj.has("responseStatus") &&  tranObj.get("responseStatus").toString().equals("Successful") ){

                         JSONObject billInfo =   new JSONObject(tranObj.get("billInfo").toString());

                         resultMessage   =  resources.getString(R.string.successful)+" ... \n"+extraInfo+"\n"+
                                 resources.getString(R.string.pan)+": "+tranObj.get("PAN").toString()+"\n" +
                                 resources.getString(R.string.amount)+": "+tranObj.get("tranAmount").toString()+" "+tranObj.get("tranCurrency").toString()+"\n" +
                                 resources.getString(R.string.fee)+": "+tranObj.get("acqTranFee").toString()+ "\n" +
                                 resources.getString(R.string.fee_issuer)+": "+tranObj.get("issuerTranFee").toString()+ "\n" +
                                 resources.getString(R.string.bill_info)+": \n" +
                                 resources.getString(R.string.student_name)+": "+billInfo.get("englishName").toString()+" \n" +
                                 resources.getString(R.string.formNo)+": "+billInfo.get("formNo").toString()+" \n" +
                                 resources.getString(R.string.receiptNo)+": "+billInfo.get("receiptNo").toString() ;

                    }else if(tranObj != null){


                        if(tranObj.has("detail")){

                            JSONObject errorDetail =   new JSONObject(tranObj.get("detail").toString());

                            if(errorDetail.has("error_code") && errorDetail.get("error_code").toString().equals("permission_denied")  ){

                                invalidSession = true;

                            }else{

                                resultMessage += ErrorLog.getMorsalError(tranObj,activity);
                            }
                        } else {
                            resultMessage += ErrorLog.getMorsalError(tranObj,activity);
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

        pay();


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

        submit.setVisibility(View.GONE);
        txtResult.setVisibility(View.VISIBLE);

        txtResult.setText(resultMessage);

        finish.setVisibility(View.VISIBLE);

        if(invalidSession) {
            Toast.makeText(activity, "Session has ended !!!", Toast.LENGTH_SHORT).show();
            application.setToken(null);
            activity.finish();
        }


    }


}

