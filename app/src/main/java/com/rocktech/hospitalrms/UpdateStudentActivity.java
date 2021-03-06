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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateStudentActivity extends AppCompatActivity {
    private EditText firstName, lastName, department, faculty, level, matric;
    private String firstName1, lastName1, studentId1, department1, faculty1, level1;
    private Button signup, search;
    DatabaseHelper databaseHelper;
    private TextView studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);
        initValue();
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retreiveData();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser();
            }
        });

    }

    void initValue(){
        databaseHelper = new DatabaseHelper(this);
        firstName = findViewById(R.id.upFirstName);
        lastName = findViewById(R.id.upLastName);
        studentId = findViewById(R.id.upStudentId);
        department = findViewById(R.id.upDepartment);
        faculty = findViewById(R.id.upFaculty);
        level = findViewById(R.id.upLevel);
        signup = findViewById(R.id.upSignup);
        matric = findViewById(R.id.upSearch);
        search = findViewById(R.id.upBtnSearch);
    }

    void retreiveData(){
        Cursor cursor = databaseHelper.readUser(matric.getText().toString().trim().toLowerCase());
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No Data Found", Toast.LENGTH_LONG).show();
        }
        else {
            while (cursor.moveToNext()){
              firstName1 = cursor.getString(1);
              lastName1 = cursor.getString(2);
              studentId1 = cursor.getString(3);
              department1 = cursor.getString(4);
              faculty1 = cursor.getString(5);
              level1 = cursor.getString(6);
            }
            cursor.close();
            firstName.setText(firstName1);
            lastName.setText(lastName1);
            studentId.setText(studentId1);
            department.setText(department1);
            faculty.setText(faculty1);
            level.setText(level1);
        }
    }
    void updateUser(){
        String first,last,matric,dept,fact,level1;
        first = firstName.getText().toString().trim().toLowerCase();
        last =  lastName.getText().toString().trim().toLowerCase();
        matric =  studentId.getText().toString().trim();
        dept = department.getText().toString().trim();
        fact = faculty.getText().toString().trim();
        level1 = level.getText().toString().trim();

        if (first.length() < 2 || last.length() < 2 || matric.length() < 6 ){
            Toast.makeText(this, "All Fields are required", Toast.LENGTH_LONG).show();
        }
        else {
            databaseHelper.updateData(first,last,matric,dept,fact,level1);

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
                Intent intentLogin = new Intent(UpdateStudentActivity.this, MainActivity.class);
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
                Intent intent = new Intent(UpdateStudentActivity.this, SignupActivity.class);
                startActivity(intent);
                break;
            case R.id.add_report_menu:
                Intent intentReport = new Intent(UpdateStudentActivity.this, NewReportActivity.class);
                startActivity(intentReport);
                break;
            case R.id.all_users_menu:
                Intent intentUsers = new Intent(UpdateStudentActivity.this, StudentActivity.class);
                startActivity(intentUsers);
                break;
            case R.id.report_menu:
                Intent intentReports = new Intent(this, AllReportActivity.class);
                startActivity(intentReports);
                break;
            case R.id.search_menu:
                Intent intentSearch = new Intent(UpdateStudentActivity.this, SearchActivity.class);
                startActivity(intentSearch);
                break;
            case R.id.update_menu:
                Toast.makeText(this, "You are here already", Toast.LENGTH_SHORT).show();
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
