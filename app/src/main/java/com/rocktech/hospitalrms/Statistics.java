package com.rocktech.hospitalrms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Statistics extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    TextView textMonth,monthMalaria,monthTyphoid,monthPID,monthPUD,monthOther,monthDiagnosis;
    TextView quarterPatientMale, quarterPatientFemale, quarterDiagnosisMale, quarterDiagnosisFemale;
    TextView quarterAdmissionMale, quarterAdmissionFemale,  quarterReferralMale, quarterReferralFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        databaseHelper = new DatabaseHelper(this);
        textMonth = findViewById(R.id.textMonth);
        monthMalaria = findViewById(R.id.monthMalaria);
        monthTyphoid = findViewById(R.id.monthTyphoid);
        monthPID = findViewById(R.id.monthPid);
        monthPUD = findViewById(R.id.monthPUD);
        monthOther = findViewById(R.id.monthOther);
        monthDiagnosis = findViewById(R.id.monthDiagnosis);
        quarterAdmissionFemale = findViewById(R.id.quarterAdmissionFemale);
        quarterAdmissionMale = findViewById(R.id.quarterAdmissionMale);
        quarterDiagnosisFemale = findViewById(R.id.quarterDiagnosisFemale);
        quarterDiagnosisMale = findViewById(R.id.quarterDiagnosisMale);
        quarterPatientFemale = findViewById(R.id.quarterPatientFemale);
        quarterPatientMale = findViewById(R.id.quarterPatientMale);
        quarterReferralFemale = findViewById(R.id.quarterReferralFemale);
        quarterReferralMale = findViewById(R.id.quarterReferralMale);
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMd");
        int date = Integer.parseInt(dateFormat1.format(new Date()));

        getMonthRecords(date);
        getDiagnosisMonth(date,monthDiagnosis);
        getMonthView(date,"Malaria", monthMalaria);
        getMonthView(date,"Others", monthOther);
        getMonthView(date,"Pelvic Inflammatory Disease", monthPID);
        getMonthView(date,"Peptic Ulcer Disease", monthPUD);
        getMonthView(date,"Typhoid", monthTyphoid);
        //
        getUsersQuarter(date,"Male",quarterPatientMale);
        getUsersQuarter(date,"Female",quarterPatientFemale);
        //
        getOutcomesQuarter(date,"A","Male", quarterAdmissionMale);
        getOutcomesQuarter(date,"A","Female", quarterAdmissionFemale);
        //
        getOutcomesQuarter(date,"RO","Male", quarterReferralMale);
        getOutcomesQuarter(date,"RO","Female", quarterReferralFemale);
        //
        getDiagnosisQuarter(date,"Male", quarterDiagnosisMale);
        getDiagnosisQuarter(date,"Female", quarterDiagnosisFemale);

//        getMonthMalaria(date);
//        getMonthOther(date);
//        getMonthPid(date);
//        getMonthPud(date);
//        getMonthTyphoid(date);

    }
    //
    void getMonthRecords(int date){
        Cursor cursor = databaseHelper.readUserMonth(date);
        if (cursor.getCount() > 0){
            textMonth.setText(String.valueOf(cursor.getCount()));
        }
    }
    void getMonthView(int date, String diagnosis, TextView textView){
        Cursor cursor = databaseHelper.readReportMonth(date, diagnosis);
        if (cursor.getCount() > 0){
            textView.setText(String.valueOf(cursor.getCount()));
        }
    }
    void getUsersQuarter(int date, String gender, TextView textView){
        Cursor cursor = databaseHelper.readUserQuarter(date,gender);
        if (cursor.getCount() > 0){
            textView.setText(String.valueOf(cursor.getCount()));
        }
    }

    void getOutcomesQuarter(int date, String outcome, String gender, TextView textView){
        Cursor cursor = databaseHelper.readOutcomeQuarter(date,outcome,gender);
        if (cursor.getCount() > 0){
            textView.setText(String.valueOf(cursor.getCount()));
        }
    }
    //
    void getDiagnosisQuarter(int date, String gender, TextView textView){
        Cursor cursor = databaseHelper.readDiagnosisQuarter(date,gender);
        if (cursor.getCount() > 0){
            textView.setText(String.valueOf(cursor.getCount()));
        }
    }
    void getDiagnosisMonth(int date, TextView textView){
        Cursor cursor = databaseHelper.readDiagnosisMonth(date);
        if (cursor.getCount() > 0){
            textView.setText(String.valueOf(cursor.getCount()));
        }
    }
    void getMonthTyphoid(int date){
        Cursor cursor = databaseHelper.readReportMonth(date,"Typhoid");
        if (cursor.getCount() > 0){
            monthTyphoid.setText(String.valueOf(cursor.getCount()));
        }
    }
    //
    void getMonthPid(int date){
        Cursor cursor = databaseHelper.readReportMonth(date,"Pelvic Inflammatory Disease");
        if (cursor.getCount() > 0){
            monthPID.setText(String.valueOf(cursor.getCount()));
        }
    }
    //
    void getMonthPud(int date){
        Cursor cursor = databaseHelper.readReportMonth(date,"Peptic Ulcer Disease");
        if (cursor.getCount() > 0){
            monthPUD.setText(String.valueOf(cursor.getCount()));
        }
    }
    //
    void getMonthOther(int date){
        Cursor cursor = databaseHelper.readReportMonth(date,"Others");
        if (cursor.getCount() > 0){
            monthOther.setText(String.valueOf(cursor.getCount()));
        }
    }
    //
    public void exitApp(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("EXIT");
        builder.setMessage("Are you sure you want to exit?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                finishAffinity();
                System.exit(0);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // TODO: 9/11/2020 Cancel submission
            }
        });
        builder.show();
    }
    public void logoutApp(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("LOGOUT");
        builder.setMessage("Are you sure you want to logout?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                Intent intentLogin = new Intent(Statistics.this, MainActivity.class);
                startActivity(intentLogin);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // TODO: 9/11/2020 Cancel submission
            }
        });
        builder.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.account:
                Intent intent = new Intent(this, SignupActivity.class);
                startActivity(intent);
                break;
            case R.id.add_report_menu:
                Toast.makeText(this, "You are here already", Toast.LENGTH_SHORT).show();
                break;
            case R.id.all_users_menu:
                Intent intentUsers = new Intent(this, StudentActivity.class);
                startActivity(intentUsers);
                break;
            case R.id.report_menu:
                Intent intentReports = new Intent(this, AllReportActivity.class);
                startActivity(intentReports);
                break;
            case R.id.search_menu:
                Intent intentSearch = new Intent(this, SearchActivity.class);
                startActivity(intentSearch);
                break;
            case R.id.update_menu:
                Intent intentUpdate = new Intent(this, UpdateStudentActivity.class);
                startActivity(intentUpdate);
                break;
            case R.id.update_report:
                Intent updateReport = new Intent(this, UpdateReportActivity.class);
                startActivity(updateReport);
                break;
            case R.id.record_details:
                Toast.makeText(this,"You are here Already", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout_menu:
                logoutApp();
                break;
            case R.id.exit_menu:
                exitApp();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
