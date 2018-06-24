package sd.code.morsalpos.process;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import cn.refactor.lib.colordialog.PromptDialog;
import sd.code.morsalpos.BillPaymentActivity;
import sd.code.morsalpos.R;
import sd.code.morsalpos.common.AnsiX98PinHandler;
import sd.code.morsalpos.common.Config;
import sd.code.morsalpos.common.PortalApplication;
import sd.code.morsalpos.common.Security;

/**
 * Created by Mustafa on 13/05/2018.
 */

public abstract class BillPaymentProcess {
    private final static String TAG = BillPaymentProcess.class.getSimpleName();
    Activity activity;
    PortalApplication application;
    Map<String, String> map;
    public BillPaymentProcess(Activity activity, Map<String, String> map){
        this.activity = activity;
        this.application = (PortalApplication) activity.getApplication();
        this.map = map;
    }
    public void execute(){
        Log.d(TAG, "Map: " + map.toString());
        final ProgressDialog dialog = new ProgressDialog(activity, R.style.AppTheme_Dark_Dialog);
        dialog.setMessage("Running...");
        dialog.setCancelable(false);
        dialog.show();
        CallUrl callUrl = new CallUrl(Config.URL_BILL_PAYMENT, new JSONObject(map)) {
            @Override
            public void afterRequest(JSONObject response) {
                dialog.dismiss();
                onResponse(response);
            }

            @Override
            public void onError(VolleyError error) {
                // parse a json error...
                dialog.dismiss();
                onErrorOccur(error);
            }
        };
        callUrl.execute();
    }
    public abstract void onResponse(JSONObject response);
    public abstract void onErrorOccur(VolleyError error);
}
