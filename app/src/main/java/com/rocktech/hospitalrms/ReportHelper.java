package com.rocktech.hospitalrms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ReportHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Hrms";
    private static final int DATABASE_VERSION = 3;
    private Context context;
    private static final String TABLE_NAME = "student_reports";
    private static final String COLUMN_ID = "_id";
    private static final String COUNT = "report_count";
    private static final String REPORT = "report_description";
    private static final String DATE = "report_date";
    private static final String STUDENT_ID = "student_id";
    private static final String TAG = "ReportHelper";

    ReportHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE "+TABLE_NAME+
                "( " +COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                STUDENT_ID + " TEXT, " +
                REPORT + " TEXT, " +
                DATE + " TEXT);";
        // to execute query
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        //whenever we upgrade table, onCreate must be called
        onCreate(db);

    }
    //

    void addReport(String studentId, String report, String date){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
           // db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
            ContentValues cv = new ContentValues();
            cv.put(STUDENT_ID, studentId);
            cv.put(REPORT, report);
          //  cv.put(COUNT, count);
            cv.put(DATE, date);
            long result = db.insert(TABLE_NAME, null, cv);
            if (result  == -1){
                Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(context, "Report has been successfully added", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e){
            Toast.makeText(context, "Failed "+e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
    //
    Cursor readAllReport(){
        String query = "SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    //
    Cursor readData(String std_id){
        SQLiteDatabase db = this.getWritableDatabase();
        //long result = db.delete(TABLE_NAME, "_id=?", new  String[]{row_id});
        String query = "SELECT * FROM "+ TABLE_NAME +" WHERE student_id =?";

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, new String[]{std_id});
        }
        return cursor;
    }
}
