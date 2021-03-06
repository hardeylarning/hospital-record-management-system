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
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UpdateReportActivity extends AppCompatActivity {
    EditText txtDesc, txtLab, txtDrug;
    Button saveButton;
    DatabaseHelper databaseHelper;
    String reportId;
    TextView matric;

    private Spinner diagnosisSpinner, attendanceSpinner, outcomeSpinner;
    String attendanceHolder, diagnosisHolder,outcomeHolder;
    ArrayList<String> attendances,diagnoses,outcomes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_report);
        txtDesc = findViewById(R.id.txtDesc);
        matric = findViewById(R.id.rptMatric);
        saveButton = findViewById(R.id.rptBtn);
        databaseHelper = new DatabaseHelper(this);
        txtDesc = findViewById(R.id.txtDesc);
        txtDrug = findViewById(R.id.txtDrug);
        txtLab = findViewById(R.id.txtLab);
        diagnosisSpinner = findViewById(R.id.txtDiagnosis);
        attendanceSpinner = findViewById(R.id.txtAttendance);
        outcomeSpinner = findViewById(R.id.txtOutcome);
        attendances = new ArrayList<>();
        diagnoses = new ArrayList<>();
        outcomes = new ArrayList<>();
        databaseHelper = new DatabaseHelper(this);
        getIntentData();
        getAttendance();
        getDiagnosis();
        getOutcome();
        //
        getIntentData();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a dd-MMM-yyyy");
                String currentTime = dateFormat.format(new Date());
                SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMd");
                int currentDate = Integer.parseInt(dateFormat1.format(new Date()));
                databaseHelper.updateReport(reportId, txtDesc.getText().toString().trim(), currentTime,
                        attendanceHolder,diagnosisHolder,txtLab.getText().toString().trim(),
                        txtDrug.getText().toString().trim(),outcomeHolder,currentDate);
                clear();
            }
        });
    }
    //
    void getDiagnosis(){
        diagnoses.add("Diagnosis");
        diagnoses.add("Malaria");
        diagnoses.add("Typhoid");
        diagnoses.add("Pelvic Inflammatory Disease");
        diagnoses.add("Peptic Ulcer Disease");
        diagnoses.add("Others");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, diagnoses);
        diagnosisSpinner.setAdapter(spinnerAdapter);
        diagnosisSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                diagnosisHolder = diagnoses.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    //
    void getOutcome(){
        outcomes.add("Outcome of Visits");
        outcomes.add("NT");
        outcomes.add("T");
        outcomes.add("A");
        outcomes.add("RO");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, outcomes);
        outcomeSpinner.setAdapter(spinnerAdapter);
        outcomeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                outcomeHolder = outcomes.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    //
    void getAttendance(){
        attendances.add("Types of Attendance");
        attendances.add("New");
        attendances.add("Following Up");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, attendances);
        attendanceSpinner.setAdapter(spinnerAdapter);
        attendanceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                attendanceHolder = attendances.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    void getIntentData(){
        if (getIntent().hasExtra("id")){
            //getter
            reportId = getIntent().getStringExtra("id");
            matric.setText(getIntent().getStringExtra("matric"));
            txtDesc.setText(getIntent().getStringExtra("desc"));
            txtLab.setText(getIntent().getStringExtra("test"));
            txtDrug.setText(getIntent().getStringExtra("drug"));
        }
        else {
            Toast.makeText(this, "No data found.", Toast.LENGTH_LONG).show();
        }
    }
    //
    void clear(){
        matric.setText("");
        txtDesc.setText("");
        txtLab.setText("");
        txtDrug.setText("");
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
                Intent intentLogin = new Intent(UpdateReportActivity.this, MainActivity.class);
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
