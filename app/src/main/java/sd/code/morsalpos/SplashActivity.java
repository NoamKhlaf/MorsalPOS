package sd.code.morsalpos;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vanstone.trans.api.SystemApi;
import com.vanstone.utils.CommonConvert;

import org.json.JSONArray;
import org.json.JSONObject;

import sd.code.morsalpos.common.ConnectionDetector;
import sd.code.morsalpos.common.PortalApplication;
import sd.code.morsalpos.database.DatabaseHandler;
import sd.code.morsalpos.database.ErrorCodeDataSource;
import sd.code.morsalpos.database.FormKindTable;
import sd.code.morsalpos.database.PayeeTable;
import sd.code.morsalpos.database.StudentCourseTable;
import sd.code.morsalpos.service.TerminalService;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity {

    Button loginB;
    TextView internetError;
    ProgressBar progress;
    String login = null, password;
    boolean networkErrorFlag = false;

    AccountManager manager;
    private Account[] accounts;
    PortalApplication application;

    static {
        System.loadLibrary("A90JavahCore");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        loginB = (Button) findViewById(R.id.dummy_button);
        internetError = (TextView) findViewById(R.id.internet_error);

        PayeeTable payeeTable = new PayeeTable(SplashActivity.this);

        if (payeeTable.getPayeesCount() == 0) {
            payeeTable.addPayees();

            StudentCourseTable studentCourseTable = new StudentCourseTable(SplashActivity.this);
            studentCourseTable.addCourses();

            FormKindTable formKindTable = new FormKindTable(SplashActivity.this);
            formKindTable.addForms();

            ErrorCodeDataSource errorCodeDataSource = new ErrorCodeDataSource(this);
            errorCodeDataSource.addErrorDescriptions();

        }


        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                ConnectionDetector connectionDetector = new ConnectionDetector(SplashActivity.this);

                if (connectionDetector.isConnectingToInternet()) {
                    internetError.setVisibility(View.GONE);
                    loginB.setVisibility(View.GONE);

                    new Splash().execute();

                } else {
                    internetError.setVisibility(View.VISIBLE);
                    loginB.setVisibility(View.VISIBLE);

                }

            }
        });

        application = (PortalApplication) getApplication();


        progress = (ProgressBar) findViewById(R.id.progress_form);

        ConnectionDetector connectionDetector = new ConnectionDetector(SplashActivity.this);

        if (connectionDetector.isConnectingToInternet()) {

            new Splash().execute();

            //new IsAlive(progress,"19000019").check();

        } else {
            internetError.setVisibility(View.VISIBLE);
            loginB.setVisibility(View.VISIBLE);

        }
    }


    public class Splash extends AsyncTask<Void, Void, Void> {

        String text = "";

        public void check() {

            DatabaseHandler db = new DatabaseHandler(getApplicationContext());
            if (db.isConfigExist("terminalID")) {

                try {
                    String terminalID = db.getConfigByParam("terminalID").getVal();
                    String merchantID = db.getConfigByParam("merchantID").getVal();
                    Log.d("merchantID", merchantID);
                    TerminalService terminalService = new TerminalService();

                    String data = terminalService.isAlive(terminalID);
                    JSONObject isAliveData = new JSONObject(data);

                    if (isAliveData.has("responseMessage")) {
                        if (isAliveData.get("responseMessage").toString().equals("Approval")) {
                            application.setTerminaID(terminalID);
                            application.setMerchantID(merchantID);

                            String workingKeyData = terminalService.getWorkingKey(terminalID);
                            JSONObject workingKeyObj = new JSONObject(workingKeyData);
                            Log.e("alaa", workingKeyObj.get("workingKey").toString());

                            application.setWorkingKey(workingKeyObj.get("workingKey").toString());
                            Log.e("alaa", workingKeyObj.get("workingKey").toString());

                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    } else if (isAliveData.has("terminalId")) {
                        JSONArray jsonArray = isAliveData.getJSONArray("terminalId");
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        if (jsonObject.get("error_code") != null) {
                            String error_code = jsonObject.get("error_code").toString();
                            if (error_code.equals("not_found")) {
                                Intent intent = new Intent(getApplicationContext(), TerminalSettingActivity.class);
                                intent.putExtra("not_valid", "1");
                                startActivity(intent);
                                finish();
                            }
                        }
                    } else {
                        throw new Exception(data);
                    }
                    //Thread.sleep(15000);
                } catch (Exception e) {
                    networkErrorFlag = true;

                }


            } else {

                Intent intent = new Intent(getApplicationContext(), TerminalSettingActivity.class);
                startActivity(intent);
                finish();
            }

        }


        @Override
        protected Void doInBackground(Void... arg0) {
            // TODO Auto-generated method stub

            check();


            return null;
        }

        @Override
        public void onPreExecute() {

            progress.setVisibility(View.VISIBLE);


        }


        @Override
        public void onPostExecute(Void unused) {

            progress.setVisibility(View.GONE);

            if (networkErrorFlag) {
                internetError.setVisibility(View.VISIBLE);
                loginB.setVisibility(View.VISIBLE);
            }

            String CurDir = getApplicationContext().getFilesDir().getAbsolutePath();
            SystemApi.SystemInit_Api(0, CommonConvert.StringToBytes(CurDir + "/" + "\0"), SplashActivity.this);

        }


    }


}


