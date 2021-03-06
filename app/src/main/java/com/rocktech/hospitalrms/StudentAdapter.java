package com.rocktech.hospitalrms;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private Context context;
    Activity activity;
    private ArrayList userId,first,last,studentId,dept,faculty,level,phoneNo,gender,age;

    public StudentAdapter(Context context, Activity activity, ArrayList userId, ArrayList first,
                          ArrayList last, ArrayList studentId, ArrayList dept, ArrayList faculty,
                          ArrayList level, ArrayList phoneNo, ArrayList gender, ArrayList age) {
        this.context = context;
        this.activity = activity;
        this.userId = userId;
        this.first = first;
        this.last = last;
        this.studentId = studentId;
        this.dept = dept;
        this.faculty = faculty;
        this.level = level;
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.age = age;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.student_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.txtStudentId.setText(String.valueOf(studentId.get(position)));
        holder.txtFirst.setText(String.valueOf(last.get(position)).toUpperCase()
                +" "+ String.valueOf(first.get(position)));
        holder.txtDept.setText(dept.get(position).toString());
        holder.txtFaculty.setText(faculty.get(position).toString());
        holder.txtLevel.setText(level.get(position).toString());
        holder.txtGender.setText(gender.get(position).toString());
        holder.txtPhone.setText(phoneNo.get(position).toString());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DescriptionActivity.class);
                intent.putExtra("studentId", String.valueOf(studentId.get(position)));
                intent.putExtra("first", String.valueOf(first.get(position)));
                intent.putExtra("last", String.valueOf(last.get(position)).toUpperCase());
                intent.putExtra("dept", String.valueOf(dept.get(position)));
                intent.putExtra("faculty", String.valueOf(faculty.get(position)));
                intent.putExtra("level", String.valueOf(level.get(position)));
                intent.putExtra("name", String.valueOf(last.get(position)).toUpperCase()
                        +" "+ String.valueOf(first.get(position)));
                intent.putExtra("gender", String.valueOf(gender.get(position)));
                intent.putExtra("age", String.valueOf(age.get(position)));
                intent.putExtra("phone", String.valueOf(phoneNo.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }


    @Override
    public int getItemCount() {
        return studentId.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtPhone,txtFirst,txtGender,txtStudentId,txtDept,txtFaculty,txtLevel;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDept = itemView.findViewById(R.id.txtDept);
            txtFaculty = itemView.findViewById(R.id.txtFaculty);
            txtFirst = itemView.findViewById(R.id.txtName);
            txtGender = itemView.findViewById(R.id.txtGender);
            txtLevel = itemView.findViewById(R.id.txtLevel);
            txtStudentId = itemView.findViewById(R.id.txtMatric);
            txtPhone = itemView.findViewById(R.id.txtPhone);
            linearLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
