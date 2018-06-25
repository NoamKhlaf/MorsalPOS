package sd.code.morsalpos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sd.code.morsalpos.common.ConfigBean;
import sd.code.morsalpos.database.DatabaseHandler;


/**
 * Created by code on 26/02/18.
 */

public class TerminalSettingActivity extends AppCompatActivity {
    Button setTermId = null;
    EditText termID = null;
    EditText merchantId = null;
    DatabaseHandler db = new DatabaseHandler(TerminalSettingActivity.this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terminal_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTermId = (Button) findViewById(R.id.btn_setTerm);
        termID = (EditText) findViewById(R.id.termID);
        merchantId = (EditText) findViewById(R.id.merchant_id);
        final ConfigBean configBean = db.getConfigByParam("terminalID");
        if (configBean.getParam() != null) {
            setTermId.setText(getResources().getString(R.string.update_terminal));
            termID.setText(configBean.getVal());
        } else {
            setTermId.setText(getResources().getString(R.string.set_terminal));
        }
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.getString("not_valid") != null) {
                Toast.makeText(TerminalSettingActivity.this, "Invalid Terminal ID", Toast.LENGTH_LONG).show();
            }
        }
        termID.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    if (termID.getText().toString().trim()
                            .length() < 8) {
                        termID.setError(getString(R.string.termID_validation));
                    } else {
                        termID.setError(null);
                    }
                }
            }
        });
        setTermId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag = false;
                if (termID.getText().toString().trim().length() < 8) {
                    termID.setError(getString(R.string.termID_validation));
                } else {
                    termID.setError(null);
                    flag = true;
                }
                if (flag) {
                    if (configBean.getParam() != null) {
                        ConfigBean cb = db.getConfigByParam("terminalID");
                        cb.setVal(termID.getText().toString());
                        long res = db.updateConfig(cb);

                        ConfigBean cbMerchantId = db.getConfigByParam("merchantID");
                        cbMerchantId.setVal(merchantId.getText().toString());
                        db.updateConfig(cbMerchantId);

                        if (res != -1) {
                            Toast.makeText(getApplicationContext(), "Terminal ID Updated Successfully", Toast.LENGTH_LONG).show();
                            finish();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    } else {
                        ConfigBean cb = new ConfigBean();
                        cb.setParam("terminalID");
                        cb.setVal(termID.getText().toString());
                        cb.setDescription("Terminal ID");
                        long res = db.addConfig(cb);

                        ConfigBean cbMerchantId = new ConfigBean();
                        cbMerchantId.setParam("merchantID");
                        cbMerchantId.setVal(merchantId.getText().toString());
                        cbMerchantId.setDescription("Merchant ID");
                        db.addConfig(cbMerchantId);

                        if (res != -1) {
                            Toast.makeText(getApplicationContext(), "Terminal ID Added Successfully", Toast.LENGTH_LONG).show();
                            finish();
                            Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
