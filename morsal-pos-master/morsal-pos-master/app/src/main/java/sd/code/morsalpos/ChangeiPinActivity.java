package sd.code.morsalpos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import sd.code.morsalpos.adapter.CardListAdapter;
 import sd.code.morsalpos.Type.PayeeInfo;
import sd.code.morsalpos.common.CardInfo;
import sd.code.morsalpos.common.PortalApplication;
import sd.code.morsalpos.database.DatabaseHandler;
import sd.code.morsalpos.process.ChangeIPINProcess;

public class ChangeiPinActivity extends AppCompatActivity {

    View requestCode;
    public static ArrayList<CardInfo> cards = new ArrayList<CardInfo>() ;
    public static ArrayList<PayeeInfo> payees = new ArrayList<PayeeInfo>() ;
    CardListAdapter adapter ;
    AppCompatSpinner cardList  ;
    PortalApplication application ;
    Button sumbit ;
    EditText iPin,newIPIN,cNewIPIN  ;
    TextView result;
    boolean cancel = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pin);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        application = (PortalApplication)getApplication() ;

        cardList =(AppCompatSpinner)findViewById(R.id.cards);

        sumbit =(Button)findViewById(R.id.btn_change);

        iPin =(EditText)findViewById(R.id.iPin);
        newIPIN =(EditText)findViewById(R.id.newIPIN);
        cNewIPIN =(EditText)findViewById(R.id.cNewIPIN);


        result =(TextView)findViewById(R.id.txt_result);





        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( TextUtils.isEmpty(iPin.getText().toString())  ) {
                    iPin.setError(getString(R.string.error_empty_filed));
                    cancel = true;
                }

                if ( TextUtils.isEmpty(newIPIN.getText().toString()) ) {
                    newIPIN.setError(getString(R.string.error_empty_filed));
                    cancel = true;
                }

                if (TextUtils.isEmpty(cNewIPIN.getText().toString()) ) {
                    cNewIPIN.setError(getString(R.string.error_empty_filed));

                    cancel = true;
                }

                if ( !newIPIN.getText().toString().equals(cNewIPIN.getText().toString()) ) {
                    result.setVisibility(View.VISIBLE);
                    result.setText(getString(R.string.error_pass_no_equals));
                    cancel = true;
                }
                // Finish the registration screen and return to the Login activity

                if(!cancel) {

                    CardInfo card = cards.get(cardList.getSelectedItemPosition());
                    card.setiPin(iPin.getText().toString());
                    card.setExpDate(card.getExpDate().replace(",", ""));
                    new ChangeIPINProcess(ChangeiPinActivity.this, application, sumbit, card, result, newIPIN.getText().toString()).execute();

                }else {
                    cancel = false;
                }

            }
        });



        DatabaseHandler db = new DatabaseHandler(ChangeiPinActivity.this);
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
