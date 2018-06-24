package sd.code.morsalpos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import sd.code.morsalpos.common.PortalApplication;

public class GovernmentActivity extends AppCompatActivity {

    private View e15, edu,eduArab ,custom,e15_info,custom_info ;
    PortalApplication application ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_government);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        application = (PortalApplication)getApplication() ;

        e15 = (View)findViewById(R.id.e_15);
        edu = (View)findViewById(R.id.h_edu);
        e15_info = (View)findViewById(R.id.e_15_info);
        edu = (View)findViewById(R.id.h_edu);
        eduArab = (View)findViewById(R.id.h_edu_arab);
        custom = (View)findViewById(R.id.custom);
        custom_info = (View)findViewById(R.id.custom_info);


        e15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),E15PaymentActivity.class);
                startActivity(intent);

            }
        });

        e15_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),E15InfoActivity.class);
                startActivity(intent);

            }
        });


        edu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),HigherEducationActivity.class);
                startActivity(intent);

            }
        });


        eduArab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),HigherEducationArabActivity.class);
                startActivity(intent);

            }
        });

        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),CustomActivity.class);
                startActivity(intent);

            }
        });

        custom_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),CustomInfoActivity.class);
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

//
//        if(application.getToken() == null){
//            finish();
//        }


    }


}
