package com.rocktech.hospitalrms;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DescriptionActivity extends AppCompatActivity {
    TextView textName, textMatric, textLevel, textDept, textFaculty,textDate,textDesc,textPhone,textGender;
    String name,last,matric,level,dept,faculty,id;
    String desc,date,rptMatric,rptId,gender,age,phone,attendance,diagnosis,test,drug,outcome;
    ArrayList<Report> reports;
    RecyclerView reportView;
    NewReportAdapter newReportAdapter;
    DatabaseHelper databaseHelper;
    FloatingActionButton floatingActionButton;
    private static final String TAG = "DescriptionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        textName = findViewById(R.id.textName);
        textMatric = findViewById(R.id.textMatric);
        textLevel = findViewById(R.id.textLevel);
        textDept= findViewById(R.id.textDept);
        textFaculty = findViewById(R.id.textFaculty);
        textDesc = findViewById(R.id.textDesc);
        textDate= findViewById(R.id.textDate);
        textPhone = findViewById(R.id.textPhone);
        textGender = findViewById(R.id.textGender);
        floatingActionButton = findViewById(R.id.fabReport);
        reportView = findViewById(R.id.recyclerReport);
        databaseHelper = new DatabaseHelper(this);
        reports = new ArrayList<>();
        getIntentData();
        getReport();
        newReportAdapter = new NewReportAdapter(DescriptionActivity.this,DescriptionActivity.this);
        newReportAdapter.setReports(reports);
        reportView.setAdapter(newReportAdapter);
        reportView.setLayoutManager(new LinearLayoutManager(DescriptionActivity.this));

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DescriptionActivity.this, FabReportActivity.class);
                intent.putExtra("matric", matric);
                intent.putExtra("name", textName.getText());
                intent.putExtra("phone", textPhone.getText());
                intent.putExtra("gender", textGender.getText());
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
        else if (requestCode == 2){
            recreate();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        recreate();
    }
    //

    void getIntentData(){
        if (getIntent().hasExtra("studentId") && getIntent().hasExtra("first")
                && getIntent().hasExtra("last") && getIntent().hasExtra("dept")){
            //getter
            matric = getIntent().getStringExtra("studentId");
            name = getIntent().getStringExtra("last")+" "+getIntent().getStringExtra("first");
            dept = getIntent().getStringExtra("dept");
            faculty = getIntent().getStringExtra("faculty");
            level = getIntent().getStringExtra("level");
            phone = getIntent().getStringExtra("phone");
            gender = getIntent().getStringExtra("gender");
            age = getIntent().getStringExtra("age");
            // setter
            textName.setText(name);
            textMatric.setText(matric);
            textDept.setText(dept);
            textFaculty.setText(faculty);
            textLevel.setText(level);
            textPhone.setText(phone);
            textGender.setText(gender);


        }
        else {
            Toast.makeText(this, "No data found.", Toast.LENGTH_LONG).show();
        }
    }

    //
    void getReport(){
        try {
            Cursor cursor = databaseHelper.readUserReport(matric);
            if (cursor.getCount() == 0){
                Toast.makeText(DescriptionActivity.this, "No Data Found", Toast.LENGTH_LONG).show();
            }
            else {
                while (cursor.moveToNext()) {
                    rptId = cursor.getString(0);
                    rptMatric = cursor.getString(1);
                    desc = cursor.getString(2);
                    date = cursor.getString(3);
                    name = cursor.getString(4);
                    phone = cursor.getString(5);
                    gender = cursor.getString(6);
                    age = cursor.getString(7);
                    attendance = cursor.getString(8);
                    diagnosis = cursor.getString(9);
                    test = cursor.getString(10);
                    drug = cursor.getString(11);
                    outcome = cursor.getString(12);
                    reports.add(new Report(rptId, rptMatric, desc, date,name,phone,gender,age,attendance,diagnosis,test,drug,outcome));

                }
                cursor.close();
            }
        } catch (SQLException e){
            Log.d(TAG, "getReport: "+e.getMessage());
        }

    }
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
                Intent intentLogin = new Intent(DescriptionActivity.this, MainActivity.class);
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
                Intent intentReport = new Intent(this, NewReportActivity.class);
                startActivity(intentReport);
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
                Intent statistics = new Intent(this, Statistics.class);
                startActivity(statistics);
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
