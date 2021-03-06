package com.rocktech.hospitalrms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class DashboardActivity extends AppCompatActivity {
    ImageView addUser,home,addReport,dashReport,searchUser,updateUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.abs_layout);

        addUser = findViewById(R.id.addUser);
        home = findViewById(R.id.home);
        addReport = findViewById(R.id.addReport);
        dashReport = findViewById(R.id.dashReport);
        searchUser =findViewById(R.id.searchUser);
        updateUser = findViewById(R.id.about);
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, StudentActivity.class);
                startActivity(intent);

            }
        });

        addReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, NewReportActivity.class);
                startActivity(intent);
            }
        });
        dashReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, AllReportActivity.class);
                startActivity(intent);
            }
        });
        searchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        updateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, UpdateStudentActivity.class);
                startActivity(intent);
            }
        });


    }
    public void exitApp(){
        AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);
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
                Intent intentLogin = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(intentLogin);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // TODO: 9/11/2020 Cancel submission
                Intent intentLogin = new Intent(DashboardActivity.this, Statistics.class);
                startActivity(intentLogin);
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
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.account:
                Intent intent = new Intent(DashboardActivity.this, SignupActivity.class);
                startActivity(intent);
                break;
            case R.id.add_report_menu:
                Intent intentReport = new Intent(DashboardActivity.this, NewReportActivity.class);
                startActivity(intentReport);
                break;
            case R.id.all_users_menu:
                Intent intentUsers = new Intent(DashboardActivity.this, StudentActivity.class);
                startActivity(intentUsers);
                break;
            case R.id.report_menu:
                Intent intentReports = new Intent(DashboardActivity.this, AllReportActivity.class);
                startActivity(intentReports);
                break;
            case R.id.search_menu:
                Intent intentSearch = new Intent(DashboardActivity.this, SearchActivity.class);
                startActivity(intentSearch);
                break;
            case R.id.update_menu:
                Intent intentUpdate = new Intent(DashboardActivity.this, UpdateStudentActivity.class);
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
