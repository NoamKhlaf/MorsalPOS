package sd.code.morsalpos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import sd.code.morsalpos.Type.InfoData;

/**
 * Created by alaa on 12/29/2017.
 */

public class FormKindTable {

      DatabaseHandler sqLiteOpenHelper ;

    public FormKindTable(Context context){

        sqLiteOpenHelper = new DatabaseHandler(context);


    }

    public long addFormKind(InfoData info) {


        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.INFO_ID, info.getId());
        values.put(DatabaseHandler.INFO_TITLE, info.getTitle());
        values.put(DatabaseHandler.INFO_TITLE_EN, info.getTitleEn());

        // Inserting Row
        long flag = db.insert(DatabaseHandler.TABLE_FORM_KIND, null, values);
        db.close(); // Closing database connection

        return  flag;
    }

    public void addForms() {

        List<InfoData> courses = new ArrayList<>();
        courses.add(new InfoData(1,"General admission-first round","القبول الدور الأول – قبول عام" ));
        courses.add(new InfoData(2,"Special admission","القبول الخاص" ));
        courses.add(new InfoData(3,"Sons of higher education staff","قبول أبناء العاملين" ));
        courses.add(new InfoData(6,"General admission-second round","القبول العام الدور الثاني" ));
        courses.add(new InfoData(7,"Special admission-vacant seats","شواغر القبول الخاص" ));
        courses.add(new InfoData(8,"Private institutions direct admission","مباشر التعليم الأهلي" ));
        courses.add(new InfoData(9,"Diploma in public institutions","دبلوم حكومي" ));


        for(InfoData course : courses){

            addFormKind(course);


        }


    }

    // Getting All courses
    public List<InfoData> getForms() {
        List<InfoData> courses = new ArrayList<InfoData>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DatabaseHandler.TABLE_FORM_KIND   ;

        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                InfoData info = new InfoData();
                info.setId(Integer.parseInt(cursor.getString(0)));
                info.setTitle(cursor.getString(1));
                info.setTitleEn(cursor.getString(2));

                // Adding payee to list
                courses.add(info);
            } while (cursor.moveToNext());
        }

        // return card list
        return courses;
    }





}
