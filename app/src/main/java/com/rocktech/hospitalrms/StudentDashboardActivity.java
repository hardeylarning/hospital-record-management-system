package com.rocktech.hospitalrms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentDashboardActivity extends AppCompatActivity {
    ArrayList<Report> reports;
    DatabaseHelper databaseHelper;
    RecyclerView reportView;
    UserReportAdapter userReportAdapter;
    TextView matricNo,name,level,dept,faculty,tPhone,tGender;
    String desc,date,rptMatric,rptId,gender,age,name1,phone,attendance,diagnosis,test,drug,outcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);
        reportView = findViewById(R.id.userView);
        matricNo = findViewById(R.id.stdTxtMatric);
        name = findViewById(R.id.stdTxtName);
        level = findViewById(R.id.stdTxtLevel);
        dept = findViewById(R.id.stdTxtDept);
        tPhone = findViewById(R.id.stdTxtPhone);
        tGender = findViewById(R.id.stdTxtGender);
        faculty = findViewById(R.id.stdTxtFaculty);
        reports = new ArrayList<>();
        databaseHelper = new DatabaseHelper(this);
        getUserDetails();
        getReport();
        userReportAdapter = new UserReportAdapter(this, reports);
        reportView.setAdapter(userReportAdapter);
        reportView.setLayoutManager(new LinearLayoutManager(StudentDashboardActivity.this));


    }

    public void exitApp(){
        AlertDialog.Builder builder = new AlertDialog.Builder(StudentDashboardActivity.this);
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
    //
    public void logoutApp(){
        AlertDialog.Builder builder = new AlertDialog.Builder(StudentDashboardActivity.this);
        builder.setTitle("LOGOUT");
        builder.setMessage("Are you sure you want to logout?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                Intent intentLogin = new Intent(StudentDashboardActivity.this, MainActivity.class);
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
    public void onBackPressed() {
        //super.onBackPressed();
        // Toast.makeText(DashboardActivity.this, "Good Bye!", Toast.LENGTH_LONG).show();
        exitApp();
        return;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.student_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.std_logout_menu:
                logoutApp();
                break;
            case R.id.std_exit_menu:
                exitApp();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
    void getUserDetails(){
        String matric;
        if (getIntent().hasExtra("matric")) {
            matric = getIntent().getStringExtra("matric");
            Cursor cursor = databaseHelper.readUser(matric);
            if (cursor.getCount() == -1){
                Toast.makeText(StudentDashboardActivity.this, "No Data Found", Toast.LENGTH_LONG).show();
            }
            else {
                if (cursor.moveToNext()){
                    name.setText(cursor.getString(2).toUpperCase()+" "+cursor.getString(1));
                    matricNo.setText(cursor.getString(3));
                    dept.setText(cursor.getString(4));
                    faculty.setText(cursor.getString(5));
                    level.setText(cursor.getString(6));
                    tPhone.setText(cursor.getString(7));
                    tGender.setText(cursor.getString(8));
                    cursor.close();
                }

            }


        }
    }

    void getReport(){
        String matric ;
        if (getIntent().hasExtra("matric")){
            matric = getIntent().getStringExtra("matric");

            Cursor cursor = databaseHelper.readUserReport(matric);
            if (cursor.getCount() == 0){
                Toast.makeText(StudentDashboardActivity.this, "No Data Found", Toast.LENGTH_LONG).show();
            }
            else {
                while (cursor.moveToNext()) {
                    rptId = cursor.getString(0);
                    rptMatric = cursor.getString(1);
                    desc = cursor.getString(2);
                    date = cursor.getString(3);
                    name1 = cursor.getString(4);
                    phone = cursor.getString(5);
                    gender = cursor.getString(6);
                    age = cursor.getString(7);
                    attendance = cursor.getString(8);
                    diagnosis = cursor.getString(9);
                    test = cursor.getString(10);
                    drug = cursor.getString(11);
                    outcome = cursor.getString(12);
                    reports.add(new Report(rptId, rptMatric, desc, date,name1,phone,gender,age,attendance,diagnosis,test,drug,outcome));
                }
                cursor.close();
            }
        }
        else {
            Toast.makeText(StudentDashboardActivity.this, "No Data Found in Intent", Toast.LENGTH_LONG).show();
        }


    }
}
