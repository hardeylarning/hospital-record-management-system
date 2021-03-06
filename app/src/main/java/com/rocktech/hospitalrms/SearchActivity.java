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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText searchUser;
    Button btnSearch;
    private DatabaseHelper databaseHelper;
    private ArrayList<User> users;
    private SearchAdapter searchAdapter;
    String userId,first,last,studentId,dept,faculty,level,phone,gender,age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        init();
        users = new ArrayList<>();
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (searchAdapter != null){
                    searchAdapter.clear();
                }
                retreiveAllData();
                searchAdapter = new SearchAdapter(SearchActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                searchAdapter.setUsers(users);
                recyclerView.setAdapter(searchAdapter);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        searchAdapter.clear();
    }

    //
    void retreiveAllData(){
        Cursor cursor = databaseHelper.searchUser(searchUser.getText().toString().trim());
        if (cursor.getCount() <= 0){
            Toast.makeText(SearchActivity.this, "No Data Found", Toast.LENGTH_LONG).show();
        }
        else {
            while (cursor.moveToNext()){
                first =cursor.getString(1);
                last =cursor.getString(2);
                studentId =cursor.getString(3);
                dept =cursor.getString(4);
                faculty =cursor.getString(5);
                level =cursor.getString(6);
                userId =cursor.getString(0);
                phone =cursor.getString(7);
                gender =cursor.getString(8);
                age =cursor.getString(9);
                users.add(new User(Integer.valueOf(userId), first,last,studentId,dept,faculty,level,
                        phone,gender,age));

            }
        }
        cursor.close();
    }
    void init (){
        databaseHelper = new DatabaseHelper(SearchActivity.this);
        searchUser = findViewById(R.id.textSearch);
        btnSearch = findViewById(R.id.btnSearch);
        recyclerView = findViewById(R.id.searchReport);
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
                Intent intentLogin = new Intent(SearchActivity.this, MainActivity.class);
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
                Toast.makeText(this, "You are here already", Toast.LENGTH_SHORT).show();
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
