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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import sd.code.morsalpos.R;
import sd.code.morsalpos.common.CardInfo;
import sd.code.morsalpos.common.ErrorLog;
import sd.code.morsalpos.common.PortalApplication;
import sd.code.morsalpos.service.TerminalService;


public class PaymentProcess extends AsyncTask<Void, Void, Void> {

    String data;
    private static JSONArray result;
    ProgressDialog progressDialog ;
    Activity activity ;
    View submit ;

    CardInfo card ;
    PortalApplication application ;
    TextView txtResult;
    String resultMessage= "";
    boolean invalidSession = false ;
    Resources resources;


    String extraInfo;

    boolean isSuccess = false ;

    String payeeId;String paymentInfo;double tranAmount;


    public PaymentProcess(Activity activity , PortalApplication application, View submit, CardInfo card, TextView txtResult, String payeeId, String paymentInfo, double tranAmount, String extraInfo){

        this.activity =activity ;
        this.card = card ;
        this.application = application;
        this.submit = submit ;
        this.txtResult=txtResult;
        this.payeeId=payeeId;
        this.paymentInfo=paymentInfo;
        this.tranAmount=tranAmount;
        this.extraInfo=extraInfo;

        resources = activity.getResources();

        progressDialog = new ProgressDialog(activity, R.style.AppTheme_Dark_Dialog);

    }

    public boolean pay(){

        TerminalService services = new TerminalService();

        try {


            if(application.getPublicKey() != null) {

                    String paymentData = services.payment(application.getToken(),application.getPublicKey() ,card,payeeId,paymentInfo,tranAmount);

                    JSONObject tranObj = new JSONObject(paymentData);
                    if(tranObj != null && tranObj.has("responseStatus") &&  tranObj.get("responseStatus").toString().equals("Successful") ){


                        resultMessage   =  resources.getString(R.string.successful)+"... \n"+extraInfo +" \n"+
                                resources.getString(R.string.pan)+": "+tranObj.get("PAN").toString()+"\n" +
                                resources.getString(R.string.amount)+": "+tranObj.get("tranAmount").toString()+" "+ resources.getString(R.string.sdg)+"\n" +
                                resources.getString(R.string.fee)+": "+tranObj.get("acqTranFee").toString()+"\n"+
                                resources.getString(R.string.fee_issuer)+": "+tranObj.get("issuerTranFee").toString()  ;

                        try {
                            if(tranObj.get("balance") != null){

                                JSONObject balanceValuesObj = new JSONObject(tranObj.get("balance").toString());

                                resultMessage += "\n" +resources.getString(R.string.available_balance_is)+": " + balanceValuesObj.get("available").toString()+" "+ resources.getString(R.string.sdg);
                            }
                        }catch(Exception e){}


                        if(tranObj.get("billInfo") != null) {

                            JSONObject billInfo = new JSONObject(tranObj.get("billInfo").toString());

                            if (billInfo != null) {

                                Iterator<String> iter = billInfo.keys();

                                if(iter != null && iter.hashCode() > 0 ) {
                                    resultMessage += "\n"+resources.getString(R.string.bill_info)+":";

                                    while (iter.hasNext()) {
                                        String key = iter.next();
                                        try {

                                            Object value = billInfo.get(key);

                                            if (key.equals("opertorMessage")) {
                                                key = resources.getString(R.string.opertorMessage);
                                            } else if (key.equals("unitsInKWh")) {
                                                key = resources.getString(R.string.unitsInKWh);
                                            } else if (key.equals("meterNumber")) {
                                                key = resources.getString(R.string.meterNumber);
                                            } else if (key.equals("accountNo")) {
                                                key = resources.getString(R.string.accountNo);
                                            } else if (key.equals("meterFees")) {
                                                key = resources.getString(R.string.meterFees);
                                            } else if (key.equals("netAmount")) {
                                                key = resources.getString(R.string.netAmount);
                                            } else if (key.equals("waterFees")) {
                                                key = resources.getString(R.string.waterFees);
                                            } else if (key.equals("customerName")) {
                                                key = resources.getString(R.string.customerName);
                                            } else if (key.equals("token")) {
                                                key = resources.getString(R.string.token);
                                            }else if (key.equals("subNewBalance")) {
                                                key = resources.getString(R.string.subNewBalance);
                                            }else if (key.equals("receiptNo")) {
                                                key = resources.getString(R.string.subNewBalance);
                                            }else if (key.equals("ServiceName")) {
                                                key = resources.getString(R.string.ServiceName);
                                            }else if (key.equals("PayerName")) {
                                                key = resources.getString(R.string.PayerName);
                                            }else if (key.equals("TotalAmount")) {
                                                key = resources.getString(R.string.TotalAmount);
                                            }else if (key.equals("ReferenceId")) {
                                                key = resources.getString(R.string.ReferenceId);
                                            }else if (key.equals("UnitName")) {
                                                key = resources.getString(R.string.UnitName);
                                            }else if (key.equals("BankCode")) {
                                                key = resources.getString(R.string.BankCode);
                                            }else if (key.equals("E-15ReceiptNumber")) {
                                                key = resources.getString(R.string.E15ReceiptNumber);
                                            }else if (key.equals("DeclarantNumber")) {
                                                key = resources.getString(R.string.declarantNumber);
                                            }else if (key.equals("DeclarantName")) {
                                                key = resources.getString(R.string.decName);
                                            }else if (key.equals("Dec.No")) {
                                                key = resources.getString(R.string.decNumber);
                                            }





                                            resultMessage += "\n" + key + ": " + value.toString();
                                        } catch (JSONException e) {

                                        }
                                    }
                                }
                            }
                        }



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


                //  isSuccess = true ;

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

