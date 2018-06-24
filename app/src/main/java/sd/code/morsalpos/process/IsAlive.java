package sd.code.morsalpos.process;

import android.app.ProgressDialog;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.zxing.client.result.ProductParsedResult;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import sd.code.morsalpos.common.Config;
import sd.code.morsalpos.common.PortalApplication;
import sd.code.morsalpos.common.Security;

/**
 * Created by alaa on 5/6/2018.
 */

public class IsAlive {

    ProgressBar progress ;
    String terminald;
    public IsAlive(ProgressBar progress,String terminald){

        this.progress = progress;
        this.terminald = terminald ;
    }

    private void check() {
         String tag_string_req = "CHECK_IS_ALIVE";
        if(progress != null){
            progress.setVisibility(View.VISIBLE);
        }
        // load from
        Map<String, String> postParam = new HashMap<>();
        postParam.put("terminalId", terminald);
        postParam.put("systemTraceAuditNumber", String.valueOf(Security.getSystemTraceAuditNumber()));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        Date now = new Date();
        postParam.put("tranDateTime", format.format(now));

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Config.API_URL + "isAlive/", new JSONObject(postParam),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response:", response.toString());
                        if(progress != null) {
                            progress.setVisibility(View.GONE);
                        }

                        boolean isSuccess ;
                        try {
                            isSuccess = response.getBoolean("success");
                        } catch (Exception e) {
                            Log.e("", "Error response in products, message: " + e.toString());
                            isSuccess = false;
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("errr", "Error on call GetContent api" + error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        // Adding request to request queue
        PortalApplication
                .getInstance().addToRequestQueue(jsonObjReq, tag_string_req);
    }
}
