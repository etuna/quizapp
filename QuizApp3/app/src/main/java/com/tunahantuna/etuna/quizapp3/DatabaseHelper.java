package com.tunahantuna.etuna.quizapp3;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by etuna on 8/17/17.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "Questions.db";
    public static String TABLE_NAME = "Question_dataTable";

    public static String COL1 = "ID";
    public static String COL2 = "Category";
    public static String COL3 = "Question";
    public static String COL4 = "Answer";
    public static String COL5 = "Option1";
    public static String COL6 = "Option2";
    public static String COL7 = "Option3";
    public static String COL8 = "Option4";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT ,CATEGORY TEXT, QUESTION TEXT, ANSWER TEXT, OPTION1 TEXT, OPTION2 TEXT, OPTION3 TEXT, OPTION4 TEXT) ");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF  EXISTS" + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    public void insertItem(String category,String question, String answer, String option1, String option2, String option3, String option4){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("CATEGORY",category);
        cv.put("QUESTION",question);
        cv.put("ANSWER",answer);
        cv.put("OPTION1",option1);
        cv.put("OPTION2",option1);
        cv.put("OPTION3",option1);
        cv.put("OPTION4",option1);

        db.insert(TABLE_NAME ,null, cv);



    }
}
