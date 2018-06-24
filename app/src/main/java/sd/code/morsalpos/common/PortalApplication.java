package sd.code.morsalpos.common;

import android.app.Application;
import android.text.TextUtils;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import sd.code.morsalpos.Type.UserInfo;


/**
 * Created by master on 11/11/2016.
 */
public class PortalApplication extends Application {

    String mobileNumber,fullName,token,publicKey, terminaID, merchantID, workingKey;
    UserInfo userInfo ;
    int id ;

    public static final String TAG = PortalApplication.class.getSimpleName();
    private RequestQueue mRequestQueue;
    private static PortalApplication mInstance;


    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        userInfo = new UserInfo();
        mInstance = this;

    }

    public String getTerminaID() {
        return terminaID;
    }

    public void setTerminaID(String terminaID) {
        this.terminaID = terminaID;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public static synchronized PortalApplication getInstance() {
        return mInstance;
    }

    public com.android.volley.RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public String getWorkingKey() {
        return workingKey;
    }

    public void setWorkingKey(String workingKey) {
        this.workingKey = workingKey;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }
    public String getMerchantID(){
        return merchantID;
    }
}



