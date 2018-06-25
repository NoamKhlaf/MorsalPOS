package sd.code.morsalpos.process;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import sd.code.morsalpos.common.Config;
import sd.code.morsalpos.common.PortalApplication;

/**
 * Created by Mustafa on 13/05/2018.
 */

public abstract class CallUrl {
    private static final String TAG = CallUrl.class.getSimpleName();

    private JSONObject params;
    private String url;
    protected CallUrl(String url, JSONObject params){
        this.params = params;
        this.url = url;
    }

    public void execute() {
        Log.d(TAG, "Loading products from the server...");
        String tag_string_req = "DoRequest";
        // load from

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "Response: " + response.toString());

                        afterRequest(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                /*Log.e(TAG, "Error on call GetContent api" + error.toString());
                onError(error);*/
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        JSONObject obj = new JSONObject(res);
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        Log.e(TAG, "Error : " + response.toString());
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                }
            }/**/
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
               /* headers.put("Accept","application/json; charset=utf-8");
                headers.put("Content-Type","application/json");*/
              headers.put("Content-Type", "application/json; charset=utf-8");
               headers.put("API-KEY", Config.API_KEY);
                return headers;
            }
        };
        // Adding request to request queue
        PortalApplication.getInstance().addToRequestQueue(jsonObjReq, tag_string_req);
        Log.d("headers", "h: " +jsonObjReq+tag_string_req);

    }

    public abstract void afterRequest(JSONObject response);
    public abstract void onError(VolleyError error);
}

