package com.rocktech.hospitalrms;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    ArrayList<User> users = new ArrayList<>();

    public SearchAdapter(Context context) {
        this.context = context;
    }

    public void setUsers(ArrayList<User> users){
       // clear();
        this.users = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // clear();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.student_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtStudentId.setText(String.valueOf(users.get(position).getStudentId()));
        holder.txtFirst.setText(String.valueOf(users.get(position).getFirstName()).toUpperCase()+" "+ String.valueOf(users.get(position).getLastName()));
        holder.txtDept.setText(users.get(position).getDepartment());
        holder.txtFaculty.setText(users.get(position).getFaculty());
        holder.txtLevel.setText(users.get(position).getLevel());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DescriptionActivity.class);
                intent.putExtra("studentId", String.valueOf(users.get(position).getStudentId()));
                intent.putExtra("first", String.valueOf(users.get(position).getFirstName()));
                intent.putExtra("last", String.valueOf(users.get(position).getLastName()));
                intent.putExtra("dept", String.valueOf(users.get(position).getDepartment()));
                intent.putExtra("faculty", String.valueOf(users.get(position).getFaculty()));
                intent.putExtra("level", String.valueOf(users.get(position).getLevel()));
                intent.putExtra("name", String.valueOf(users.get(position).getLastName()).toUpperCase()+" "+ String.valueOf(users.get(position).getFirstName()));
                intent.putExtra("gender", String.valueOf(users.get(position).getGender()));
                intent.putExtra("age", String.valueOf(users.get(position).getAge()));
                intent.putExtra("phone", String.valueOf(users.get(position).getPhone()));
                context.startActivity(intent);
            }
        });
      // clear();
    }

    public void clear(){
        int size = users.size();
            users.clear();
            notifyItemRangeRemoved(0, size);
      //  }
    }


    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtUserId,txtFirst,txtLast,txtStudentId,txtDept,txtFaculty,txtLevel;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDept = itemView.findViewById(R.id.txtDept);
            txtFaculty = itemView.findViewById(R.id.txtFaculty);
            txtFirst = itemView.findViewById(R.id.txtName);
          //  txtLast = itemView.findViewById(R.id.department);
            txtLevel = itemView.findViewById(R.id.txtLevel);
            txtStudentId = itemView.findViewById(R.id.txtMatric);
            linearLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
