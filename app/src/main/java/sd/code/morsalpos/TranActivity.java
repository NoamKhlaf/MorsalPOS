package sd.code.morsalpos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import sd.code.morsalpos.common.PortalApplication;

public class TranActivity extends AppCompatActivity {

    private View transfer, generate;

    PortalApplication application ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tran);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        application = (PortalApplication)getApplication() ;


        transfer = (View)findViewById(R.id.transfer);
       // transaction = (View)findViewById(R.id.transaction);
        generate = (View)findViewById(R.id.generate);


        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),TransfersActivity.class);
                startActivity(intent);

            }
        });


       // transaction.setOnClickListener(new View.OnClickListener() {
       //     @Override
       //     public void onClick(View v) {
        // /       // Finish the registration screen and return to the Login activity
        //        Intent intent = new Intent(getApplicationContext(),TransactionActivity.class);
         //       startActivity(intent);

         //   }
       // });

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),VouchersActivity.class);
                startActivity(intent);

            }
        });
//nadan

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
