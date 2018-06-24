
package sd.code.morsalpos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.vanstone.trans.api.MagCardApi;
import com.vanstone.trans.api.PiccApi;
import com.vanstone.trans.api.PrinterApi;
import com.vanstone.trans.api.SystemApi;
import com.vanstone.utils.CommonConvert;
import sd.code.morsalpos.R;

public class PrintExampleActivity extends AppCompatActivity {

    static {
        System.loadLibrary("A90JavahCore");
    }
      TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.print_example);

        String CurDir = getApplicationContext().getFilesDir().getAbsolutePath();
        SystemApi.SystemInit_Api(0, CommonConvert.StringToBytes(CurDir+"/" + "\0"), PrintExampleActivity.this);



        Button btnPrint  = (Button)findViewById(R.id.btn_print);
        Button btnCardReader  = (Button)findViewById(R.id.btn_card);

        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MagCardApi.MagOpen_Api();
                MagCardApi.MagReset_Api();
                PiccApi.PiccOpen_Api();

                PrtCardInfo();


            }
        });

        btnCardReader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),CardReaderActivity.class);
                startActivity(intent);

            }
        });




    }


      public static void PrtCardInfo() {
          byte[] PrnBuf = new byte[128];
          PrinterApi.PrnClrBuff_Api();
          PrinterApi.PrnFontSet_Api(32, 32, 0);
          PrinterApi.PrnSetGray_Api(15);
          PrinterApi.PrnLineSpaceSet_Api((short) 5, 0);
          PrinterApi.PrnStr_Api("     POS Receipt");
          PrinterApi.PrnFontSet_Api(24, 24, 0);
          PrinterApi.PrnStr_Api("       \t\tCARDHOLDER COPY");
          PrinterApi.PrnStr_Api("------------------------------------------------");
          PrinterApi.PrnStr_Api("MERCHANT NAME:");
          PrinterApi.PrnStr_Api("CARREFOUR");
          PrinterApi.PrnStr_Api("MERCHANT NO.: ");
          PrinterApi.PrnStr_Api("120401124594");
          PrinterApi.PrnStr_Api("TERMINAL NO.: ");
          PrinterApi.PrnStr_Api("TRANS TYPE.: ");
          PrinterApi.PrnFontSet_Api(32, 32, 0);
          PrinterApi.PrnStr_Api("Sale");
          PrinterApi.PrnFontSet_Api(24, 24, 0);

          PrinterApi.PrnStr_Api(new String(PrnBuf).trim());
          PrinterApi.PrnStr_Api("DATE/TIME:");
          PrinterApi.PrnStr_Api("2018/02/23 22:22:22");
          PrinterApi.PrnStr_Api("AMOUNT:");
          PrinterApi.PrnFontSet_Api(32, 32, 0);
          PrinterApi.PrnHTSet_Api(1);
          PrinterApi.PrnStr_Api("RMB:40000 SDG" );
          PrinterApi.PrnFontSet_Api(24, 24, 0);
          PrinterApi.PrnHTSet_Api(0);
          PrinterApi.PrnStr_Api("CARDHOLDER SIGNATURE:\n\n\n\n");
          PrinterApi.PrnStr_Api("------------------------------------------------");
          PrinterApi.PrnStr_Api("I accept this trade and allow it on my account");
          PrinterApi.PrnStr_Api("--------------x--------------------x-----------");
          for(int i = 0 ; i < 5; i++) {
              PrinterApi.PrnStr_Api("\n");
          }
          PrintData();
      }

      public static int PrintData() {
          int ret = PrinterApi.PrnStart_Api();
          String Buf;
          if (ret == 2) {
              Buf = "Return：" + ret + "\tpaper is not enough";
          } else if (ret == 3) {
              Buf = "Return：" + ret + "\ttoo hot";
          } else if (ret == 4) {
              Buf = "Return：" + ret + "\tPLS put it back\nPress any key to reprint";
          } else if (ret == 0) {
              PrinterApi.PrnClrBuff_Api();
              return 0;
          }
          PrinterApi.PrnClrBuff_Api();
          return -1;
      }

}
