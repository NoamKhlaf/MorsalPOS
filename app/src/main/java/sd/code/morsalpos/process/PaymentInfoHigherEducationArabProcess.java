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


public class PaymentInfoHigherEducationArabProcess extends AsyncTask<Void, Void, Void> {

    String data;
    private static JSONArray result;
    ProgressDialog progressDialog ;
    Activity activity ;
    View submit ;

    CardInfo card ;
    PortalApplication application ;
    TextView txtResult,txtAmount;
    String resultMessage = "";
    View   view1,   view2;
    String amount ;
    boolean invalidSession = false ;
    Resources resources;




    boolean isSuccess = false ;

    String payeeId;String paymentInfo, extraInfo;


    public PaymentInfoHigherEducationArabProcess(Activity activity , PortalApplication application, View submit, CardInfo card, TextView txtResult,
                                                 String payeeId, String paymentInfo, TextView txtAmount, String extraInfo, View view1, View view2){

        this.activity =activity ;
        this.card = card ;
        this.application = application;
        this.submit = submit ;
        this.txtResult=txtResult;
        this.payeeId=payeeId;
        this.paymentInfo=paymentInfo;
        this.view1=view1;
        this.view2=view2;
        this.txtAmount=txtAmount;
        this.extraInfo=extraInfo;
        resources = activity.getResources();

        progressDialog = new ProgressDialog(activity, R.style.AppTheme_Dark_Dialog);

    }

    public boolean getBillInfo(){

        TerminalService services = new TerminalService();
        String data = "" ;

        try {


            if(application.getPublicKey() != null) {


                    String paymentData = services.billInquiry(application.getToken(),application.getPublicKey() ,card,payeeId,paymentInfo);

                    JSONObject paymentObj = new JSONObject(paymentData);
                    if(paymentObj != null && paymentObj.has("responseCode") && Integer.parseInt(paymentObj.get("responseCode").toString()) == 0){

                         JSONObject billInfo =   new JSONObject(paymentObj.get("billInfo").toString());


                        try {
                                resultMessage   =   resources.getString(R.string.bill_info)+": \n"+extraInfo+"\n"+
                                resources.getString(R.string.pan)+": "+paymentObj.get("PAN").toString()+"\n" +
                                resources.getString(R.string.amount)+": "+billInfo.get("dueAmount").toString() ;
                                amount = billInfo.get("dueAmount").toString();
                                isSuccess = true ;

                        } catch (Exception ex) {
                            resultMessage = activity.getResources().getString(R.string.no_amount);
                        }







                    }else if(paymentObj != null){


                        if(paymentObj.has("detail")){

                            JSONObject errorDetail =   new JSONObject(paymentObj.get("detail").toString());

                            if(errorDetail.has("error_code") && errorDetail.get("error_code").toString().equals("permission_denied")  ){

                                invalidSession = true;

                            }else{

                                resultMessage += ErrorLog.getMorsalError(paymentObj,activity);
                            }
                        } else {
                            resultMessage += ErrorLog.getMorsalError(paymentObj,activity);
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
        txtAmount.setText(amount);

        if(isSuccess){
             view1.setVisibility(View.GONE);
             view2.setVisibility(View.VISIBLE);

        }

        if(invalidSession) {
            Toast.makeText(activity, "Session has ended !!!", Toast.LENGTH_SHORT).show();
            application.setToken(null);
            activity.finish();
        }


    }


}

