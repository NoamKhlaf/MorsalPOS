package sd.code.morsalpos;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.vanstone.trans.api.MagCardApi;
import com.vanstone.trans.api.PiccApi;
import com.vanstone.trans.api.SystemApi;
import com.vanstone.utils.CommonConvert;

import sd.code.morsalpos.common.PortalApplication;
import sd.code.morsalpos.database.DatabaseHandler;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    PortalApplication application ;
 //
    View balanceView,electricityView,topupView,billPayView,purchaseView,accountView,billView,transferView,e15View,changePIN,topupCard, miniStatement;
    TextView userPhoneView,fullNameView ;

    static {
        System.loadLibrary("A90JavahCore");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String CurDir = getApplicationContext().getFilesDir().getAbsolutePath();
        SystemApi.SystemInit_Api(0, CommonConvert.StringToBytes(CurDir + "/" + "\0"), MainActivity.this);

//        MagCardApi.MagOpen_Api();
//        MagCardApi.MagReset_Api();
//        PiccApi.PiccOpen_Api();


        balanceView = (View)findViewById(R.id.view_balance);
        billView = (View)findViewById(R.id.view_bill_payment);
        transferView = (View)findViewById(R.id.view_transfer);
        e15View = (View)findViewById(R.id.view_e_government);
        purchaseView = (View)findViewById(R.id.view_purchase);
        changePIN = (View)findViewById(R.id.view_change_pin);
        topupCard = (View)findViewById(R.id.view_topup_card);
        miniStatement = findViewById(R.id.view_mini_statement);

        balanceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),BalanceActivity.class);
                startActivity(intent);

            }
        });

        purchaseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),PurchaseActivity.class);
                startActivity(intent);

            }
        });


        billView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),BillActivity.class);
                startActivity(intent);

            }
        });

        transferView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),TransfersActivity.class);
                startActivity(intent);

            }
        });

        e15View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),GovernmentActivity.class);
                startActivity(intent);

            }
        });

        changePIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),ChangePINActivity.class);
                startActivity(intent);

            }
        });

        topupCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),TopupCardActivity.class);
                startActivity(intent);
            }
        });
        miniStatement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MiniStatementActivity.class));
            }
        });
        DatabaseHandler db=new DatabaseHandler(getApplicationContext());
        if(!db.isConfigExist("terminalID")){
            Intent intent = new Intent(getApplicationContext(),TerminalSettingActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_card) {

            Intent i = new Intent(MainActivity.this,CardActivity.class);
            startActivity(i);


        } else if (id == R.id.nav_account) {

            Intent i = new Intent(MainActivity.this,AccountActivity.class);
            startActivity(i);


        } else if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_info) {

            Intent i = new Intent(MainActivity.this,AboutActivity.class);
            startActivity(i);


        } else if (id == R.id.nav_logout) {

            Toast.makeText(getApplicationContext(), " Logout", Toast.LENGTH_SHORT).show();


            AccountManager accountManager   = AccountManager.get(this);


            Account[] accounts = accountManager.getAccounts();

            for (Account account : accounts) {
                if(account.type.equals("Morsal")) {
                    accountManager.removeAccount(account, null, null);
                }
            }


            Intent i = new Intent(MainActivity.this,SplashActivity.class);
            startActivity(i);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}
