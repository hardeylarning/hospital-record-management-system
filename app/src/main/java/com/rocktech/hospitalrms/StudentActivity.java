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
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton actionButton;

    TextView txtName, txtMatric, txtLevel, txtDept, txtFaculty;
   private DatabaseHelper databaseHelper;
   private ArrayList<String> name,last,matric,level,dept,faculty,id, phone,gender,age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        init();
        retreiveAllData();

        StudentAdapter studentAdapter = new StudentAdapter(StudentActivity.this,
                StudentActivity.this, id, name, last, matric, dept, faculty,level,phone,gender,age);
        recyclerView.setAdapter(studentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(StudentActivity.this));

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

    }
    //

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = new Intent(StudentActivity.this, StudentActivity.class);
        startActivity(intent);
    }

    //
    void retreiveAllData(){
        Cursor cursor = databaseHelper.readAll();

        if (cursor.getCount() == 0){
            Toast.makeText(StudentActivity.this, "No Data Found", Toast.LENGTH_LONG).show();
        }
        else {
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                last.add(cursor.getString(2));
                matric.add(cursor.getString(3));
                dept.add(cursor.getString(4));
                faculty.add(cursor.getString(5));
                level.add(cursor.getString(6));
                phone.add(cursor.getString(7));
                gender.add(cursor.getString(8));
                age.add(cursor.getString(9));
            }
        }
    }

    void init (){
        databaseHelper = new DatabaseHelper(StudentActivity.this);
        txtName = findViewById(R.id.txtName);
        txtMatric = findViewById(R.id.txtMatric);
        txtLevel = findViewById(R.id.txtLevel);
        txtDept= findViewById(R.id.txtDept);
        txtFaculty = findViewById(R.id.txtFaculty);
        recyclerView = findViewById(R.id.recyclerStd);
        actionButton = findViewById(R.id.floatingActionButton);
        name = new ArrayList<>();
        last = new ArrayList<>();
        matric = new ArrayList<>();
        level = new ArrayList<>();
        dept = new ArrayList<>();
        faculty = new ArrayList<>();
        id = new ArrayList<>();
        phone = new ArrayList<>();
        gender = new ArrayList<>();
        age = new ArrayList<>();
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
                Intent intentLogin = new Intent(StudentActivity.this, MainActivity.class);
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
                Toast.makeText(this, "You are here already", Toast.LENGTH_SHORT).show();
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
