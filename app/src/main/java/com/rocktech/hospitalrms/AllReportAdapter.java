package com.rocktech.hospitalrms;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllReportAdapter extends RecyclerView.Adapter<AllReportAdapter.ViewHolder> {
    private Context context;
    private DatabaseHelper databaseHelper;
    private ArrayList <Report> reports;
    private Activity activity;


    public AllReportAdapter(Context context, ArrayList<Report> reports, Activity activity) {
        this.context = context;
        this.reports = reports;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.all_report_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtMatric.setText(reports.get(position).getReportMatric());
        holder.txtDesc.setText(reports.get(position).getReportDesc());
        holder.txtDate.setText(reports.get(position).getReportDate());
        holder.txtName.setText(reports.get(position).getName());
        holder.txtPhone.setText(reports.get(position).getPhone());
        holder.txtGender.setText(reports.get(position).getGender());
        holder.txtAttendance.setText(reports.get(position).getAttendance());
        holder.txtDiagnosis.setText(reports.get(position).getDiagnosis());
        holder.txtTest.setText(reports.get(position).getTest());
        holder.txtDrug.setText(reports.get(position).getDrug());
        holder.txtOutcome.setText(reports.get(position).getOutcome());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateReportActivity.class);
                intent.putExtra("name", reports.get(position).getName());
                intent.putExtra("id", reports.get(position).getReportId());
                intent.putExtra("matric", reports.get(position).getReportMatric());
                intent.putExtra("desc", reports.get(position).getReportDesc());
                intent.putExtra("phone", reports.get(position).getPhone());
                intent.putExtra("attendance", reports.get(position).getAttendance());
                intent.putExtra("diagnosis", reports.get(position).getDiagnosis());
                intent.putExtra("test", reports.get(position).getTest());
                intent.putExtra("drug", reports.get(position).getDrug());
                intent.putExtra("outcome", reports.get(position).getOutcome());
                activity.startActivityForResult(intent, 1);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("DELETE");
                builder.setMessage("Are you sure you want to Delete report?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        databaseHelper.deleteReport(reports.get(position).getReportId());
                        Intent intent = new Intent(context, AllReportActivity.class);
                        context.startActivity(intent);

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
        });
    }

    @Override
    public int getItemCount() {
        return reports.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDate,txtDesc,txtMatric,txtName,txtPhone,txtGender,txtAttendance;
        TextView txtDiagnosis,txtTest,txtDrug,txtOutcome;
        ImageView delete;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            databaseHelper = new DatabaseHelper(context);
            txtMatric = itemView.findViewById(R.id.reportMatric);
            txtDesc = itemView.findViewById(R.id.reportDesc);
            txtDate = itemView.findViewById(R.id.reportDate);
            txtName = itemView.findViewById(R.id.reportName);
            txtPhone = itemView.findViewById(R.id.reportPhone);
            txtGender = itemView.findViewById(R.id.reportGender);
            txtAttendance = itemView.findViewById(R.id.reportAttendance);
            txtDiagnosis = itemView.findViewById(R.id.reportDiagnosis);
            txtTest = itemView.findViewById(R.id.reportTest);
            txtDrug = itemView.findViewById(R.id.reportDrug);
            txtOutcome = itemView.findViewById(R.id.reportOutcome);
            linearLayout = itemView.findViewById(R.id.reportLayer);
            delete = itemView.findViewById(R.id.rptDelete);
        }
    }
}
