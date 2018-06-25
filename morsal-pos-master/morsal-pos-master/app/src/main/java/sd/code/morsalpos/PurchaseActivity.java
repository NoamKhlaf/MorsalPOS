package sd.code.morsalpos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.vanstone.trans.api.SystemApi;
import com.vanstone.utils.CommonConvert;

import sd.code.morsalpos.common.PortalApplication;

public class PurchaseActivity extends AppCompatActivity {

    private View purchase,purchase_back,purchase_reversal,refund,nec,topup;
    PortalApplication application ;

   // static {
     //   System.loadLibrary("A90JavahCore");
    //}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);


        //String CurDir = getApplicationContext().getFilesDir().getAbsolutePath();
        //SystemApi.SystemInit_Api(0, CommonConvert.StringToBytes(CurDir+"/" + "\0"),  PurchaseActivity.this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        application = (PortalApplication)getApplication() ;

        topup = (View)findViewById(R.id.topup);
        nec = (View)findViewById(R.id.nec);
        purchase = (View)findViewById(R.id.purchase_trans);
        purchase_back  = (View)findViewById(R.id.purchase_back_trans);
        purchase_reversal = (View)findViewById(R.id.purchase_reversal_trans);
        refund= (View)findViewById(R.id.refund);
        topup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),TopupActivity.class);
                startActivity(intent);

            }
        });


        nec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),ElectricityActivity.class);
                startActivity(intent);

            }
        });
        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),PurchaseTransActivity.class);
                startActivity(intent);

            }
        });

        purchase_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),PurchaseBackActivity.class);
                startActivity(intent);

            }
        });

        purchase_reversal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),PurchaseReversalActivity.class);
                startActivity(intent);

            }
        });

        refund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),RefundActivity.class);
                startActivity(intent);

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();


//        if(application.getToken() == null){
//            finish();
//        }


    }

}
