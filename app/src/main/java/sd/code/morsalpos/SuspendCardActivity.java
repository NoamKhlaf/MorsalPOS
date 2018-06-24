
package sd.code.morsalpos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import sd.code.morsalpos.adapter.CardListAdapter;
import sd.code.morsalpos.common.CardInfo;
import sd.code.morsalpos.common.PortalApplication;
import sd.code.morsalpos.database.DatabaseHandler;
import sd.code.morsalpos.process.ReportCardAsLostProcess;

public class SuspendCardActivity extends AppCompatActivity {

        View requestCode;
        public static ArrayList<CardInfo> cards = new ArrayList<CardInfo>() ;
         CardListAdapter adapter ;
         AppCompatSpinner cardList  ;
        PortalApplication application ;
        Button sumbit ;
         TextView result;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_suspend_card);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            application = (PortalApplication)getApplication() ;

            cardList =(AppCompatSpinner)findViewById(R.id.cards);

            sumbit =(Button)findViewById(R.id.btn_do);

            result =(TextView)findViewById(R.id.txt_result);



            sumbit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Finish the registration screen and return to the Login activity
                    CardInfo card = cards.get(cardList.getSelectedItemPosition());

                     new ReportCardAsLostProcess(SuspendCardActivity.this,application,sumbit,result,card.getPan()).execute();

                }
            });



            DatabaseHandler db = new DatabaseHandler(SuspendCardActivity.this);
            cards.clear();
            cards.addAll(db.getAllCards());
            adapter = new CardListAdapter(this,cards);
            cardList.setAdapter(adapter);

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

    }
