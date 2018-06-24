package sd.code.morsalpos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import sd.code.morsalpos.common.PortalApplication;

public class BillActivity extends AppCompatActivity {

    private View payment,info;

    PortalApplication application ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        application = (PortalApplication)getApplication() ;


        payment = (View)findViewById(R.id.bill_payment);
        info = (View)findViewById(R.id.bill_info);


        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),BillPaymentActivity.class);
                startActivity(intent);

            }
        });


        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),BillInquiryActivity.class);
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
    }

}
