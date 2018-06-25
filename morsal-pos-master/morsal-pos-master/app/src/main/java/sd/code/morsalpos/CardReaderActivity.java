
package sd.code.morsalpos;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.TextView;


import com.vanstone.trans.api.EmvLibApi;
import com.vanstone.trans.api.IcApi;
import com.vanstone.trans.api.MagCardApi;
import com.vanstone.trans.api.PiccApi;
import com.vanstone.trans.api.SystemApi;
import com.vanstone.trans.api.constants.CoreDefConstants;

import sd.code.morsalpos.R;
import sd.code.morsalpos.common.Card;
import sd.code.morsalpos.common.EmvCommon;
import sd.code.morsalpos.common.GlobalConstants;
import sd.code.morsalpos.common.TCardAccountInfo;

public class CardReaderActivity extends AppCompatActivity {



    TextView card_No;
    private Handler handler;
    private String card_no;
    private boolean sloted_card = true;
    private boolean passflag = true;

    private String card_no_dis = "Card No.:";
    private String cardNumber;


     TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_reader);

        card_No = (TextView) findViewById(R.id.card_no);


        initHandler();

        MagCardApi.MagOpen_Api();
        MagCardApi.MagReset_Api();
        PiccApi.PiccOpen_Api();

        new Thread() {
            public void run() {
                while (sloted_card == true) {
                    int timerid = 0;
                    timerid = SystemApi.TimerSet_Api();
                    boolean cardflag = false, iccardflag = false, picccardflag = false;
                    byte[] CardData = new byte[1024];
                    byte[] usCardLenAddr = new byte[4];
                    byte[] CardType = new byte[2];
                    byte[] SerialNo = new byte[20];
                    while (SystemApi.TimerCheck_Api(timerid, 40 * 1000) == 0) {
                        cardflag = false;
                        iccardflag = false;
                        picccardflag = false;
                        if (MagCardApi.MagRead_Api(CardData, usCardLenAddr) == 0x31) {
                            cardflag = true;
                        }
                        if (!cardflag && IcApi.IccDetect_Api(0) == 0x00) {
                            iccardflag = true;
                        }
                        if (!cardflag
                                && !iccardflag
                                && PiccApi.PiccCheck_Api(0, CardType, SerialNo) == 0) {
                            picccardflag = true;
                        }
                        if (cardflag) {
                            if (Card.GetTrackData(CardData) == 0) {
                                Card.GetCardFromTrack(
                                        GlobalConstants.PosCom.stTrans.MainAcc,
                                        GlobalConstants.PosCom.Track2,
                                        GlobalConstants.PosCom.Track3);
                                card_no = card_no_dis
                                        + new String(
                                        GlobalConstants.PosCom.stTrans.MainAcc);
                                cardNumber = new String(
                                        GlobalConstants.PosCom.stTrans.MainAcc);
                                handler.sendEmptyMessage(0);
                                sloted_card = false;
                                break;
                            }
                        } else if (iccardflag) {
                            card_no = "Processing...";// new
                            handler.sendEmptyMessage(0);
                            // ByteUtils.memset(Date, 0, Date.length);
                            EmvLibApi
                                    .EmvSetIcCardType_Api(CoreDefConstants.PEDICCARD); // Ic��ʽѡ��
                            TCardAccountInfo pAccInfo = new TCardAccountInfo();
                            if (EmvCommon.EmvCardProcSL(2, 1, pAccInfo) == 0) {
                                card_no = card_no_dis
                                        + new String(pAccInfo.MainAcc).trim();
                                cardNumber = new String(pAccInfo.MainAcc)
                                        .trim();
                                handler.sendEmptyMessage(0);
                                sloted_card = false;
                                //
                                break;
                            } else {
                                card_no = "Read IC failed";// new
                                handler.sendEmptyMessage(0);
                                SystemApi.Delay_Api(1000);
                                continue;
                            }

                        } else if (picccardflag) {
                            card_no = "Processing...";// new
                            handler.sendEmptyMessage(0);
                            EmvLibApi.EmvSetIcCardType_Api(3); // PICC��ʽѡ��
                            TCardAccountInfo pAccInfo = new TCardAccountInfo();
                            if (EmvCommon.EmvCardProcSL(2, 1, pAccInfo) == 0) {
                                card_no = card_no_dis
                                        + new String(pAccInfo.MainAcc).trim();
                                cardNumber = new String(pAccInfo.MainAcc)
                                        .trim();
                                handler.sendEmptyMessage(0);
                                sloted_card = false;
                                break;
                            } else {
                                card_no = "Read CL failed!";// new
                                handler.sendEmptyMessage(0);
                                SystemApi.Delay_Api(1000);
                                continue;
                            }
                        }
                    }
                }
            }
        }.start();


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

    private void initHandler() {
        handler = new Handler() {
            // �ص����� : ���յ�Messageʱ,�Զ�����
            public void dispatchMessage(Message msg) {
                card_No.setText(card_no);
                sloted_card = true;
            }
        };

    }

    @Override
    protected void onStop() {
        sloted_card = false;
        super.onStop();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        sloted_card = false;
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // �����������̣����ؼ�������
            if (GlobalConstants.isPinPad == 0
                    && card_No.getText().equals("PLS input password")) {
                passflag = false;
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
