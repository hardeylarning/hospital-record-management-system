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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SignupActivity extends AppCompatActivity {
    private EditText firstName, lastName, studentId,phoneNo,age;
    private Spinner department, faculty, level,gender;
    private Button signup, clear;
    DatabaseHelper databaseHelper;
    ArrayList<String> depts,facts,levels,sex;
    String deptsHolder, factsHolder, levelsHolder,genderHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        depts = new ArrayList<>();
        facts = new ArrayList<>();
        levels = new ArrayList<>();
        sex = new ArrayList<>();
        initValue();
        getDept();
        getFact();
        getLevel();
        getGender();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUser();
            }
        });

        //
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAll();
            }
        });

    }

    private void getGender() {
        sex.add("Gender");
        sex.add("Male");
        sex.add("Female");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(SignupActivity.this,
                android.R.layout.simple_spinner_dropdown_item, sex);
        gender.setAdapter(spinnerAdapter);
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                genderHolder = sex.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    void initValue(){
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        studentId = findViewById(R.id.studentId);
        department = findViewById(R.id.department);
        faculty = findViewById(R.id.faculty);
        level = findViewById(R.id.level);
        gender = findViewById(R.id.gender);
        age = findViewById(R.id.age);
        phoneNo = findViewById(R.id.phoneNo);
        signup = findViewById(R.id.signup);
        clear = findViewById(R.id.clear);
    }

    void clearAll(){
        firstName.setText("");
        lastName.setText("");
        studentId.setText("");
        age.setText("");
        phoneNo.setText("");
    }

    void getUser(){
        databaseHelper = new DatabaseHelper(SignupActivity.this);
        Cursor cursor = databaseHelper.readUser(studentId.getText().toString());
        String first,last,matric,dept,fact,level1,phone,sAge,sGender;
        first = firstName.getText().toString().trim().toLowerCase();
        last = lastName.getText().toString().trim().toLowerCase();
        matric = studentId.getText().toString().trim().toLowerCase();
        phone = phoneNo.getText().toString().trim().toLowerCase();
        sAge = age.getText().toString().trim().toLowerCase();
        sGender = genderHolder;
        dept = deptsHolder;
        fact = factsHolder;
        level1 = levelsHolder;
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a dd-MMM-yyyy");
        String currentTime = dateFormat.format(new Date());
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMd");
        int currentDate = Integer.parseInt(dateFormat1.format(new Date()));

        if (first.length() < 2 || last.length() < 2 || matric.length() < 6 || phone.length() < 8 ){
            Toast.makeText(SignupActivity.this, "All Fields are required", Toast.LENGTH_LONG).show();
        }
       else if (cursor.getCount() > 0) {
            Toast.makeText(SignupActivity.this, "Account Already in use", Toast.LENGTH_LONG).show();
            cursor.close();
        }
       else {
            databaseHelper.addUser(first,last,matric,dept,fact,level1,phone, sGender,sAge,currentTime, currentDate);
            clearAll();
            cursor.close();
        }
    }
    void getDept(){
        depts.add("Computer Science");
        depts.add("Biochemistry");
        depts.add("Physiology");
        depts.add("Anatomy");
        depts.add("Civil Engineering");
        depts.add("Mechanical Engineering");
        depts.add("Electrical Engineering");
        depts.add("Mathematics");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(SignupActivity.this, android.R.layout.simple_spinner_dropdown_item, depts);
        department.setAdapter(spinnerAdapter);
        department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                deptsHolder = depts.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    void getFact(){
        facts.add("Basic and Applied Science");
        facts.add("Engineering");
        facts.add("Health Scientist");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(SignupActivity.this, android.R.layout.simple_spinner_dropdown_item, facts);
        faculty.setAdapter(spinnerAdapter);
        faculty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                factsHolder = facts.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    void getLevel(){
        levels.add("100L");
        levels.add("200L");
        levels.add("300L");
        levels.add("400L");
        levels.add("500L");
        levels.add("600L");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(SignupActivity.this, android.R.layout.simple_spinner_dropdown_item, levels);
        level.setAdapter(spinnerAdapter);
        level.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                levelsHolder = levels.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
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
                Intent intentLogin = new Intent(SignupActivity.this, MainActivity.class);
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
                Toast.makeText(this, "You are here already", Toast.LENGTH_SHORT).show();
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
