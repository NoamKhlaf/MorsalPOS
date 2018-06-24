package sd.code.morsalpos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import sd.code.morsalpos.adapter.CardAdapter;
 import sd.code.morsalpos.common.CardInfo;
import sd.code.morsalpos.common.PortalApplication;
import sd.code.morsalpos.database.DatabaseHandler;

public class CardActivity extends AppCompatActivity {

    ListView listView ;

    PortalApplication application ;
     CardAdapter adapter ;
    Button addCard ;

    public static ArrayList<CardInfo> cards = new ArrayList<CardInfo>() ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView =(ListView)findViewById(R.id.list);
        addCard =(Button)findViewById(R.id.btn_new_card);

        addCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),AddCardActivity.class);
                startActivity(intent);

            }
        });

        DatabaseHandler db = new DatabaseHandler(CardActivity.this);
        cards.clear();
        cards.addAll(db.getAllCards());

        adapter = new CardAdapter(this,cards);
        listView.setAdapter(adapter);

       // progress = (ProgressBar)findViewById(R.id.progress_q) ;
        //emptyView = (TextView)findViewById(R.id.empty) ;



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

        DatabaseHandler db = new DatabaseHandler(CardActivity.this);
        cards.clear();
        cards.addAll(db.getAllCards());
        adapter.notifyDataSetChanged();

    }


}
