package sd.code.morsalpos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import sd.code.morsalpos.common.PortalApplication;

public class TransfersActivity extends AppCompatActivity {

    private View transferToCard, transferToAccount, transferPhone, cashOut;

    PortalApplication application ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfers);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        application = (PortalApplication)getApplication() ;


        transferToCard = (View)findViewById(R.id.transfer);
        // transaction = (View)findViewById(R.id.transaction);
        transferToAccount = (View)findViewById(R.id.transfer_account);
        transferPhone = findViewById(R.id.transfer_phone);
        cashOut = findViewById(R.id.cash_out);

        transferToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),TransferActivity.class);
                startActivity(intent);

            }
        });

        transferToAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),TransferAccountActivity.class);
                startActivity(intent);

            }
        });

        transferPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), TransfarePhoneActivity.class));
            }
        });

        cashOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CashOutActivity.class));
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
//             finish();
//         }


    }



}
