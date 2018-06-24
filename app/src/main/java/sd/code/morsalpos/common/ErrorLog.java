package sd.code.morsalpos.common;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Locale;

import sd.code.morsalpos.Type.ErrorDescription;
import sd.code.morsalpos.database.ErrorCodeDataSource;

/**
 * Created by alaa on 2/13/2018.
 */

public class ErrorLog {


    public static String getMorsalError(JSONObject errorObj, Activity activity){

       String resultMessage = "";

        String lang = Locale.getDefault().getLanguage();


        ErrorCodeDataSource dataSource = new ErrorCodeDataSource(activity);

        Iterator<String> iter = errorObj.keys();
        while (iter.hasNext()) {
            String key = iter.next();

            if(!key.contains("EBS")) {
                String label   =  key;
                ErrorDescription labelInfo = dataSource.getErrorDescription(key);

                if(labelInfo != null) {

                    if (lang.equals("en")) {
                        label= dataSource.getErrorDescription(key).getLabelEn() ;
                    } else {
                        label= dataSource.getErrorDescription(key).getLabelAr();

                    }

                }
                resultMessage += label + ":\n";

            }



            try {

                Object value = errorObj.get(key);

                if(value instanceof JSONObject){

                }else if(value instanceof JSONArray){


                    JSONArray elements = errorObj.getJSONArray(key);

                    if(elements != null){

                        for (int i = 0; i < elements.length(); i++) {

                            JSONObject ele = new JSONObject(elements.get(i).toString());

                            Iterator<String> iterInner = ele.keys();

                            while (iterInner.hasNext()) {
                                String keyElement = iterInner.next();
                                try {
                                   // if(keyElement.equals("error_message")){
                                     //   resultMessage+= ele.get(keyElement).toString()+"\n" ;
                                   // }
                                    String errorMessage = "";

                                    if(keyElement.equals("error_code")){
                                        ErrorDescription errorInfo = dataSource.getErrorDescription(key,ele.get(keyElement).toString());

                                        if(errorInfo != null){
                                            if (lang.equals("en")) {
                                                errorMessage = errorInfo.getMsgEn();
                                            }else {
                                                errorMessage = errorInfo.getMsgAr();
                                            }
                                         }else {
                                            errorMessage = ele.get("error_message").toString();

                                        }

                                        resultMessage+= errorMessage+"\n" ;
                                    }


                                } catch (Exception e) {
                                }
                            }
                        }
                    }
                }

            } catch (JSONException e) {

            }
        }


        return resultMessage;


    }
}
