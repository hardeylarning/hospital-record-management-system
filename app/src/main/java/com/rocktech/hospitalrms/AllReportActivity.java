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
import java.util.List;

public class AllReportActivity extends AppCompatActivity {
    ArrayList<Report> reports;
    DatabaseHelper databaseHelper;
    RecyclerView recyclerView;
    AllReportAdapter adapter;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_report);
        recyclerView = findViewById(R.id.reportRecycler);
        floatingActionButton = findViewById(R.id.fabAddReport);
        databaseHelper = new DatabaseHelper(this);
        reports = new ArrayList<>();
        //
        getReport();
        adapter = new AllReportAdapter(AllReportActivity.this, reports,AllReportActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AllReportActivity.this));

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllReportActivity.this, NewReportActivity.class);
                startActivity(intent);
            }
        });

    }

    void getReport(){
        Cursor cursor = databaseHelper.readAllReport();
       // reports = new ArrayList<>();
        String id,matric,desc,date,name,phone,gender,age,attendance,diagnosis,test,drug,outcome;
        if (cursor.getCount() == 0){
            Toast.makeText(AllReportActivity.this, "No Data Found", Toast.LENGTH_LONG).show();
        }
        else {
            while (cursor.moveToNext()) {
                id = cursor.getString(0);
                matric = cursor.getString(1);
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
                reports.add(new Report(id, matric, desc, date,name,phone,gender,age,attendance,diagnosis,test,drug,outcome));
            }
            cursor.close();
        }
    }
    public void exitApp(){
        AlertDialog.Builder builder = new AlertDialog.Builder(AllReportActivity.this);
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
                Intent intentLogin = new Intent(AllReportActivity.this, MainActivity.class);
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
                Intent intent = new Intent(AllReportActivity.this, SignupActivity.class);
                startActivity(intent);
                break;
            case R.id.add_report_menu:
                Intent intentReport = new Intent(AllReportActivity.this, NewReportActivity.class);
                startActivity(intentReport);
                break;
            case R.id.all_users_menu:
                Intent intentUsers = new Intent(AllReportActivity.this, StudentActivity.class);
                startActivity(intentUsers);
                break;
            case R.id.report_menu:
                Toast.makeText(this, "You are here already", Toast.LENGTH_SHORT).show();
                break;
            case R.id.search_menu:
                Intent intentSearch = new Intent(AllReportActivity.this, SearchActivity.class);
                startActivity(intentSearch);
                break;
            case R.id.update_menu:
                Intent intentUpdate = new Intent(AllReportActivity.this, UpdateStudentActivity.class);
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
