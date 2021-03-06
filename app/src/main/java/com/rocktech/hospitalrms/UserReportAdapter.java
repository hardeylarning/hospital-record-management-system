package com.rocktech.hospitalrms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserReportAdapter extends RecyclerView.Adapter<UserReportAdapter.ViewHolder> {
    private Context context;
    private ArrayList <Report> reports;

    public UserReportAdapter(Context context, ArrayList<Report> reports) {
        this.context = context;
        this.reports = reports;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.user_report, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtDate.setText(reports.get(position).getReportDate());
        holder.txtDesc.setText(reports.get(position).getReportDesc());
        holder.txtAttendance.setText(reports.get(position).getAttendance());
        holder.txtDiagnosis.setText(reports.get(position).getDiagnosis());
        holder.txtTest.setText(reports.get(position).getTest());
        holder.txtDrug.setText(reports.get(position).getDrug());
        holder.txtOutcome.setText(reports.get(position).getOutcome());
    }

    @Override
    public int getItemCount() {
        return reports.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDate,txtDesc,txtAttendance,txtDiagnosis,txtTest,txtDrug,txtOutcome;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDesc = itemView.findViewById(R.id.textDesc);
            txtDate = itemView.findViewById(R.id.textDate);
            txtAttendance = itemView.findViewById(R.id.textAttendance);
            txtDiagnosis = itemView.findViewById(R.id.textDiagnosis);
            txtTest = itemView.findViewById(R.id.textTest);
            txtDrug = itemView.findViewById(R.id.textDrug);
            txtOutcome = itemView.findViewById(R.id.textOutcome);
        }
    }
}
