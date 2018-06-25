package sd.code.morsalpos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import sd.code.morsalpos.adapter.TransactionAdapter;
import sd.code.morsalpos.common.PortalApplication;
import sd.code.morsalpos.common.TransactionInfo;

public class TransactionActivity extends AppCompatActivity {

    ListView listView ;

    PortalApplication application ;
    TransactionAdapter adapter ;

    public static ArrayList<TransactionInfo> transactions = new ArrayList<TransactionInfo>() ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView =(ListView)findViewById(R.id.list);

        transactions.add(new TransactionInfo("1212121212121212","Oct 2017","TOP UP","200 SDG"));
        transactions.add(new TransactionInfo("1212121212121212","Jan 2017","Transfer to card","200 SDG"));
        transactions.add(new TransactionInfo("1212121212121212","Aug 2017","TOP UP","200 SDG"));
        transactions.add(new TransactionInfo("1212121212121212","Sep 2017","Bill Payment","200 SDG"));
        transactions.add(new TransactionInfo("1212121212121212","Aug 2017","TOP UP","200 SDG"));



        adapter = new TransactionAdapter(this,transactions);
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

}
