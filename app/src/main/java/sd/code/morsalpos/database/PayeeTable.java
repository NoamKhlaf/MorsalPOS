package sd.code.morsalpos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import sd.code.morsalpos.Type.PayeeInfo;
import sd.code.morsalpos.Type.PayeeType;

/**
 * Created by alaa on 12/29/2017.
 */

public class PayeeTable  {

      DatabaseHandler sqLiteOpenHelper ;

    public PayeeTable(Context context){

        sqLiteOpenHelper = new DatabaseHandler(context);


    }

    public long addPayee(PayeeInfo info) {


        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.PAYEE_NUMBER, info.getPayeeNumber());
        values.put(DatabaseHandler.PAYEE_NAME, info.getPayeeName());
        values.put(DatabaseHandler.PAYEE_NAME_AR, info.getPayeeNameAr());
        values.put(DatabaseHandler.PAYEE_TYPE, info.getPayeeType());

        // Inserting Row
        long flag = db.insert(DatabaseHandler.TABLE_PAYEE, null, values);
        db.close(); // Closing database connection

        return  flag;
    }

    public void addPayees() {

        List<PayeeInfo> payees = new ArrayList<>();
        payees.add(new PayeeInfo(1,"0010010001","Zain","زين", PayeeType.PAYEE_TYPE_TOPUP));
        payees.add(new PayeeInfo(2,"0010010003","MTN","ام تي ان", PayeeType.PAYEE_TYPE_TOPUP));
        payees.add(new PayeeInfo(3,"0010010005","Sudani","سوداني", PayeeType.PAYEE_TYPE_TOPUP));

        payees.add(new PayeeInfo(4,"0010010002","Zain","زين", PayeeType.PAYEE_TYPE_BILL_PAYMENT));
        payees.add(new PayeeInfo(5,"0010010004","MTN","ام تي ان", PayeeType.PAYEE_TYPE_BILL_PAYMENT));
        payees.add(new PayeeInfo(6,"0010010006","Sudani","سوداني", PayeeType.PAYEE_TYPE_BILL_PAYMENT));

        payees.add(new PayeeInfo(7,"0010020001","NEC","الكهرباء", PayeeType.PAYEE_TYPE_NEC));

        payees.add(new PayeeInfo(8,"0010030002","Higher Education","التعليم العالي ", PayeeType.PAYEE_TYPE_Higher_Education));
        payees.add(new PayeeInfo(9,"0010030003","Customs","الجمارك", PayeeType.PAYEE_TYPE_Customs));
        payees.add(new PayeeInfo(10,"0010030004","Higher Education Arab","التعليم العالي - الشهادة العربية", PayeeType.PAYEE_TYPE_Higher_Education_ARAB));
        payees.add(new PayeeInfo(11,"0010050001","E15","اورنيك 15", PayeeType.PAYEE_E15));
        //payees.add(new PayeeInfo(11,"0010030003","Customs","", PayeeType.PAYEE_TYPE_Customs));

        for(PayeeInfo payeeInfo : payees){
            addPayee(payeeInfo);
        }
    }

    // Getting All card
    public List<PayeeInfo> getPayeesByType(int type) {
        List<PayeeInfo> payeeList = new ArrayList<PayeeInfo>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DatabaseHandler.TABLE_PAYEE + " WHERE  "+DatabaseHandler.PAYEE_TYPE +" = ? " ;

        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,  new String[] { String.valueOf(type) });

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PayeeInfo payeeInfo = new PayeeInfo();
                payeeInfo.setId(Integer.parseInt(cursor.getString(0)));
                payeeInfo.setPayeeNumber(cursor.getString(1));
                payeeInfo.setPayeeName(cursor.getString(2));
                payeeInfo.setPayeeNameAr(cursor.getString(3));

                // Adding payee to list
                payeeList.add(payeeInfo);
            } while (cursor.moveToNext());
        }

        // return card list
        return payeeList;
    }

    public int getPayeesCount() {
        String countQuery = "SELECT  * FROM " + DatabaseHandler.TABLE_PAYEE ;
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();

        cursor.close();
        db.close();

        // return count
        return count;
    }



}
