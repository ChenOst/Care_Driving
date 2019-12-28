package com.example.caredriving.ui.navigationBar.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caredriving.R;
import com.example.caredriving.firebase.model.dataObject.StudentObj;

import java.util.ArrayList;

public class RecyclerViewAdapterLessonsToday extends RecyclerView.Adapter<RecyclerViewAdapterLessonsToday.ViewHolder> {

    private ArrayList<String> hours = new ArrayList<>();
    //private ArrayList<StudentObj> students = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapterLessonsToday(Context context, ArrayList<String> hours){
        this.hours = hours;
        //this.students = students;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterLessonsToday.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem_lessons_today, parent, false);
        return new RecyclerViewAdapterLessonsToday.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterLessonsToday.ViewHolder holder, int position) {
        holder.tvHour.setText(hours.get(position));
        /*
        holder.tvStudentFirstName.setText(students.get(position).getFirstName());
        holder.tvStudentLastName.setText(students.get(position).getLastName());
        //holder.tvStudentStreet.setText(students.get(position));
        holder.tvStudentCity.setText(students.get(position).getCity());
        holder.tvStudentsPhone.setText(students.get(position).getPhoneNumber());

         */
    }

    @Override
    public int getItemCount() {
        return hours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvHour;
        TextView tvStudentFirstName;
        TextView tvStudentLastName;
        TextView tvStudentStreet;
        TextView tvStudentCity;
        TextView tvStudentsPhone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHour = itemView.findViewById(R.id.tvHour);
            tvStudentFirstName = itemView.findViewById(R.id.tvStudentFirstName);
            tvStudentLastName  = itemView.findViewById(R.id.tvStudentLastName);
            tvStudentStreet  = itemView.findViewById(R.id.tvStudentStreet);
            tvStudentCity  = itemView.findViewById(R.id.tvStudentCity);
            tvStudentsPhone  = itemView.findViewById(R.id.tvStudentsPhone);
        }
    }
}
