package com.rocktech.hospitalrms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText userId, userPassword;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);
        Button btn = findViewById(R.id.btn);
        databaseHelper = new DatabaseHelper(this);
        userId = findViewById(R.id.user_id);
        userPassword = findViewById(R.id.user_password);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               loginValidation();
            }
        });
    }
    public void exitApp(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MainActivity.this, HomepageActivity.class);
        startActivity(intent);
       // super.onBackPressed();
    }

    void loginValidation(){
      String  user_id = userId.getText().toString().trim().toLowerCase();
      String  user_password = userPassword.getText().toString();
        Cursor cursor = databaseHelper.loginUser(user_id, user_password);
        if (cursor.getCount() > 0){
           // Toast.makeText(MainActivity.this, "Login is Successful", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, StudentDashboardActivity.class);
            intent.putExtra("matric", user_id);
            startActivity(intent);
            cursor.close();
        }
        else if (user_id.equalsIgnoreCase("admin") && user_password.equalsIgnoreCase("pass")){
            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(MainActivity.this, "User id or Password is incorrect", Toast.LENGTH_LONG).show();
            }
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.login_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                Intent intent = new Intent(this, HomepageActivity.class);
                startActivity(intent);
                break;
            case R.id.about:
                intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.exit:
                exitApp();
                break;
            default: return super.onOptionsItemSelected(item);
        }
        return true;
    }
    }

