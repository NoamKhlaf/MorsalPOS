package sd.code.morsalpos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import sd.code.morsalpos.common.CardInfo;
import sd.code.morsalpos.database.DatabaseHandler;

public class AddCardActivity extends AppCompatActivity {

    private View requestCode;

    EditText cardName, pan;
    CheckBox asDefault;
    int defaultFlag = 0;

    AppCompatSpinner year, month;
    boolean cancel = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button addCardButton = (Button) findViewById(R.id.btn_add_card);

        cardName = (EditText) findViewById(R.id.card_name);
        pan = (EditText) findViewById(R.id.pan);

        year = (AppCompatSpinner) findViewById(R.id.year);
        month = (AppCompatSpinner) findViewById(R.id.month);
        asDefault = (CheckBox) findViewById(R.id.asDefault);


        addCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity

                if (asDefault.isChecked()) {
                    defaultFlag = 1;
                }

                if (TextUtils.isEmpty(cardName.getText().toString())) {
                    cardName.setError(getString(R.string.error_empty_filed));
                    cancel = true;
                }

                if (TextUtils.isEmpty(pan.getText().toString())) {
                    pan.setError(getString(R.string.error_empty_filed));
                    cancel = true;
                } else if (pan.getText().toString().length() != 16 && pan.getText().toString().length() != 19) {
                    pan.setError(getString(R.string.error_invalid_card));
                    cancel = true;
                }

                if (!cancel) {

                    String yearFormat = year.getSelectedItem().toString().substring(2);
                    CardInfo card = new CardInfo(cardName.getText().toString(), pan.getText().toString(), yearFormat + "," + month.getSelectedItem().toString(), defaultFlag);

                    DatabaseHandler db = new DatabaseHandler(AddCardActivity.this);
                    db.addCard(card);

                    finish();

                } else {
                    cancel = false;
                }
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

}
