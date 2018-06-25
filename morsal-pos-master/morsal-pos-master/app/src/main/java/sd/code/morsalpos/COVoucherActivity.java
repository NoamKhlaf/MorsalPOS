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

import sd.code.morsalpos.common.CardInfo;
import sd.code.morsalpos.common.PortalApplication;

public class COVoucherActivity extends AppCompatActivity {

    View requestCode;

    PortalApplication application;
    Button sumbit;
    EditText iPin, mobile, voucher_number, amount, pan;
    TextView result;
    boolean cancel = false;
    AppCompatSpinner year, month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covoucher);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        application = (PortalApplication) getApplication();


        sumbit = (Button) findViewById(R.id.btn_pay);
        iPin = (EditText) findViewById(R.id.iPin);
        mobile = (EditText) findViewById(R.id.input_mobile);
        voucher_number = (EditText) findViewById(R.id.voucher_number);
        amount = (EditText) findViewById(R.id.input_amount);
        pan = (EditText) findViewById(R.id.pan);

        year = (AppCompatSpinner) findViewById(R.id.year);
        month = (AppCompatSpinner) findViewById(R.id.month);

        result = (TextView) findViewById(R.id.txt_result);


        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(iPin.getText().toString())) {
                    iPin.setError(getString(R.string.error_empty_filed));
                    cancel = true;
                } else if (iPin.getText().toString().length() != 4) {
                    iPin.setError(getString(R.string.error_invalid_password));
                    cancel = true;
                }

                if (TextUtils.isEmpty(amount.getText().toString())) {
                    amount.setError(getString(R.string.error_empty_filed));
                    cancel = true;
                }

                if (TextUtils.isEmpty(mobile.getText().toString())) {
                    mobile.setError(getString(R.string.error_empty_filed));
                    cancel = true;
                }
                if (TextUtils.isEmpty(voucher_number.getText().toString())) {
                    voucher_number.setError(getString(R.string.error_empty_filed));
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
                    // Finish the registration screen and return to the Login activity
                    String yearFormat = year.getSelectedItem().toString().substring(2);
                    CardInfo card = new CardInfo();
                    card.setPan(pan.getText().toString());
                    card.setExpDate(yearFormat + month.getSelectedItem().toString());
                    card.setiPin(iPin.getText().toString());

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
