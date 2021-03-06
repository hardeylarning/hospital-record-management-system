package com.rocktech.hospitalrms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Hrms";
    private static final int DATABASE_VERSION = 1;
    private Context context;
    private static final String TABLE_NAME = "accounts";
    private static final String COLUMN_ID = "_id";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String STUDENT_ID = "student_id";
    private static final String DEPARTMENT = "department";
    private static final String FACULTY = "faculty";
    private static final String LEVEL = "level";
    private static final String AGE = "age";
    private static final String PHONENO = "phone";
    private static final String GENDER = "gender";
    private static final String REGDATE = "reg_date";

    // reports
    private static final String TABLE_NAME1 = "reports";
    private static final String COUNT_DATE = "count_date";
    private static final String REPORT = "report_description";
    private static final String NAME = "name";
    private static final String ATTENDANCE = "attendance";
    private static final String DIAGNOSIS = "diagnosis";
    private static final String TEST = "test";
    private static final String DRUG = "drug";
    private static final String OUTCOME = "outcome";
    private static final String DATE = "report_date";

    DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE "+TABLE_NAME+
                "( " +COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                FIRSTNAME + " TEXT, " +
                LASTNAME + " TEXT," +
                STUDENT_ID + " TEXT," +
                DEPARTMENT + " TEXT," +
                FACULTY + " TEXT," +
                LEVEL + " TEXT," +
                PHONENO + " TEXT," +
                GENDER + " TEXT," +
                AGE + " TEXT," +
                REGDATE + " TEXT,"+
                COUNT_DATE + " INTEGER);";

        String query1 = "CREATE TABLE "+TABLE_NAME1+
                "( " +COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                STUDENT_ID + " TEXT, " +
                REPORT + " TEXT, " +
                DATE + " TEXT, " +
                NAME + " TEXT, " +
                PHONENO + " TEXT," +
                GENDER + " TEXT," +
                AGE + " TEXT," +
                ATTENDANCE + " TEXT," +
                DIAGNOSIS + " TEXT," +
                TEST + " TEXT," +
                DRUG + " TEXT, " +
                OUTCOME + " TEXT, " +
                COUNT_DATE + " INTEGER);";
        // to execute query
        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.execSQL(query1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i< i1) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
            //whenever we upgrade table, onCreate must be called
            onCreate(db);
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        if (newVersion <= oldVersion){
//            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
//            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME1);
//            onCreate(db);
//        }
      //  super.onDowngrade(db, oldVersion, newVersion);
    }
    void addUser(String firstName, String lastName, String studentId, String department, String faculty,
                 String level, String phoneNo, String gender, String age, String date, int count_date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FIRSTNAME, firstName);
        cv.put(LASTNAME, lastName);
        cv.put(STUDENT_ID, studentId);
        cv.put(DEPARTMENT, department);
        cv.put(FACULTY, faculty);
        cv.put(LEVEL, level);
        cv.put(PHONENO, phoneNo);
        cv.put(GENDER, gender);
        cv.put(AGE, age);
        cv.put(REGDATE, date);
        cv.put(COUNT_DATE, count_date);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result  == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(context, "Successfully Added"+gender+" "+age, Toast.LENGTH_LONG).show();
        }
    }

    //read all

    Cursor readAll(){
        String query = "SELECT * FROM "+ TABLE_NAME+" ORDER BY _id DESC ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    //
    void updateData(String firstName, String lastName, String studentId, String department, String faculty, String level){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FIRSTNAME, firstName);
        cv.put(LASTNAME, lastName);
        cv.put(STUDENT_ID, studentId);
        cv.put(DEPARTMENT, department);
        cv.put(FACULTY, faculty);
        cv.put(LEVEL, level);

        long result = db.update(TABLE_NAME, cv, "student_id=?", new String[]{studentId});

        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(context, "Successfully Updated.", Toast.LENGTH_LONG).show();
        }
    }
    //
    Cursor readUser(String std_id){
        SQLiteDatabase db = this.getWritableDatabase();
        //long result = db.delete(TABLE_NAME, "_id=?", new  String[]{row_id});
        String query = "SELECT * FROM "+ TABLE_NAME +" WHERE student_id =?";

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, new String[]{std_id});
        }
        return cursor;
    }
    //
    Cursor loginUser(String std_id, String std_psd){
        SQLiteDatabase db = this.getWritableDatabase();
        //long result = db.delete(TABLE_NAME, "_id=?", new  String[]{row_id});
        String query = "SELECT * FROM "+ TABLE_NAME +" WHERE student_id =? AND lastname =?";

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, new String[]{std_id, std_psd});
        }
        return cursor;
    }

    //

    void deleteData(String row_id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result = sqLiteDatabase.delete(TABLE_NAME, "_id=?", new  String[]{row_id});

        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_LONG).show();
        }
    }
    void deleteAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM "+TABLE_NAME);
    }


    // Report Table

    void addReport(String studentId, String report, String date, String name, String phone, String gender, String age,
                   String attendance, String diagnosis, String test, String drug, String outcome, int count_date){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            // db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
            ContentValues cv = new ContentValues();
            cv.put(STUDENT_ID, studentId);
            cv.put(REPORT, report);
            //  cv.put(COUNT, count);
            cv.put(DATE, date);
            cv.put(NAME, name);
            cv.put(PHONENO, phone);
            cv.put(GENDER, gender);
            cv.put(AGE, age);
            cv.put(ATTENDANCE, attendance);
            cv.put(DIAGNOSIS, diagnosis);
            cv.put(TEST, test);
            cv.put(DRUG, drug);
            cv.put(OUTCOME, outcome);
            cv.put(COUNT_DATE, count_date);

            long result = db.insert(TABLE_NAME1, null, cv);
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

    Cursor readAllReport(){
        String query = "SELECT * FROM "+ TABLE_NAME1+" ORDER BY _id DESC ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    //
    Cursor readUserReport(String std_id){
        SQLiteDatabase db = this.getWritableDatabase();
        //long result = db.delete(TABLE_NAME, "_id=?", new  String[]{row_id});
        String query = "SELECT * FROM "+ TABLE_NAME1 +" WHERE student_id =? ORDER BY _id DESC ";

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, new String[]{std_id});
        }
        return cursor;
    }
    //
    Cursor readReportMonth(int count_date){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME1 +" WHERE ("+count_date+"-count_date)<=31";
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readReportMonth(int count_date, String diagnosis){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME1 +" WHERE diagnosis = '"+diagnosis+"' AND ("+count_date+"-count_date)<=31";
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    //
    Cursor readUserMonth(int count_date){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME +" WHERE ("+count_date+"-count_date)<=31";
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    //
    Cursor readOutcomeQuarter(int count_date, String outcome, String gender){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME1 +" WHERE (outcome = '"+outcome+"' AND gender = '"+gender+"') AND ("+count_date+"-count_date)<=90";
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    Cursor readDiagnosisQuarter(int count_date, String gender){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME1 +" WHERE (outcome <> 'NT') AND (gender = '"+gender+"') AND ("+count_date+"-count_date)<=90";
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    Cursor readDiagnosisMonth(int count_date){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME1 +" WHERE outcome <> 'NT' AND ("+count_date+"-count_date)<=31";
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    // TODO: 12/21/2020
    Cursor readUserQuarter(int count_date, String gender){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME +" WHERE gender = '"+gender+"' AND ("+count_date+"-count_date)<=90";
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    Cursor searchUser(String std_id){
        SQLiteDatabase db = this.getWritableDatabase();
        //long result = db.delete(TABLE_NAME, "_id=?", new  String[]{row_id});
        String query = "SELECT * FROM "+ TABLE_NAME +" WHERE student_id LIKE '%"+std_id+"%'";

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateReport(String reportId, String report, String date, String attendance,
                      String diagnosis, String test, String drug, String outcome, int count_date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(REPORT, report);
        cv.put(DATE, date);
        cv.put(ATTENDANCE, attendance);
        cv.put(DIAGNOSIS, diagnosis);
        cv.put(TEST, test);
        cv.put(DRUG, drug);
        cv.put(OUTCOME, outcome);
        cv.put(COUNT_DATE, count_date);

        long result = db.update(TABLE_NAME1, cv, "_id=?", new String[]{reportId});

        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(context, "Successfully Updated.", Toast.LENGTH_LONG).show();
        }
    }
    void deleteReport(String row_id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result = sqLiteDatabase.delete(TABLE_NAME1, "_id=?", new  String[]{row_id});

        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_LONG).show();
        }
    }

}
