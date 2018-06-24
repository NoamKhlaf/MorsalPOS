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

public class StudentCourseTable {

      DatabaseHandler sqLiteOpenHelper ;

    public StudentCourseTable(Context context){

        sqLiteOpenHelper = new DatabaseHandler(context);


    }

    public long addCourse(InfoData info) {


        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.INFO_ID, info.getId());
        values.put(DatabaseHandler.INFO_TITLE, info.getTitle());
        values.put(DatabaseHandler.INFO_TITLE_EN, info.getTitleEn());

        // Inserting Row
        long flag = db.insert(DatabaseHandler.TABLE_STUDENT_COURSE, null, values);
        db.close(); // Closing database connection

        return  flag;
    }

    public void addCourses() {

        List<InfoData> courses = new ArrayList<>();
        courses.add(new InfoData(1,"Academic","اكاديمي" ));
        courses.add(new InfoData(2,"Agricultural","زراعي" ));
        courses.add(new InfoData(3,"commercial","تجاري" ));
        courses.add(new InfoData(4,"Industrial","صناعي" ));
        courses.add(new InfoData(5,"Womanly","نسوي" ));
        courses.add(new InfoData(6,"Ahlia","اهلية" ));
        courses.add(new InfoData(7,"Readings","قراءات" ));


        for(InfoData course : courses){

            addCourse(course);


        }


    }

    // Getting All courses
    public List<InfoData> getCourses() {
        List<InfoData> courses = new ArrayList<InfoData>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DatabaseHandler.TABLE_STUDENT_COURSE   ;

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
