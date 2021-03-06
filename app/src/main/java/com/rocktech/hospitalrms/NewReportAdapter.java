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

public class NewReportAdapter extends RecyclerView.Adapter<NewReportAdapter.ViewHolder> {
    private final Activity activity;
    private Context context;
    private DatabaseHelper databaseHelper;
    ArrayList<Report> reports = new ArrayList<>();

    public NewReportAdapter(Activity activity, Context context) {
        this.context = context;
        this.activity = activity;
    }

    public void setReports(ArrayList<Report> reports) {
        this.reports = reports;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.report_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
      //  holder.txtDesc.setText(String.valueOf(desc.get(position)));
      //  holder.txtDate.setText(date.get(position).toString());
        holder.txtDate.setText(reports.get(position).getReportDate());
        holder.txtDesc.setText(reports.get(position).getReportDesc());
        holder.txtAttendance.setText(reports.get(position).getAttendance());
        holder.txtDiagnosis.setText(reports.get(position).getDiagnosis());
        holder.txtTest.setText(reports.get(position).getTest());
        holder.txtDrug.setText(reports.get(position).getDrug());
        holder.txtOutcome.setText(reports.get(position).getOutcome());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateReportActivity.class);
                intent.putExtra("desc", reports.get(position).getReportDesc());
                intent.putExtra("id", reports.get(position).getReportId());
                intent.putExtra("matric", reports.get(position).getReportMatric());
                intent.putExtra("name", reports.get(position).getName());
                intent.putExtra("phone", reports.get(position).getPhone());
                intent.putExtra("gender", reports.get(position).getGender());
                intent.putExtra("age", reports.get(position).getAge());
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
                        activity.recreate();

//                        Intent intent = new Intent(context, DescriptionActivity.class);
//                        activity.startActivityForResult(intent,2);
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
        TextView txtDate,txtDesc,txtAttendance,txtDiagnosis,txtTest,txtDrug,txtOutcome;
        ImageView delete;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            databaseHelper = new DatabaseHelper(context);
            txtDesc = itemView.findViewById(R.id.textDesc);
            txtDate = itemView.findViewById(R.id.textDate);
            txtAttendance = itemView.findViewById(R.id.textAttendance);
            txtDiagnosis = itemView.findViewById(R.id.textDiagnosis);
            txtTest = itemView.findViewById(R.id.textTest);
            txtDrug = itemView.findViewById(R.id.textDrug);
            txtOutcome = itemView.findViewById(R.id.textOutcome);
            delete = itemView.findViewById(R.id.rptDelete);
            linearLayout = itemView.findViewById(R.id.reportLayout);
        }
    }
}
