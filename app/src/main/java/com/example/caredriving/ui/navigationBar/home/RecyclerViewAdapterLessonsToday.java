package com.example.caredriving.ui.navigationBar.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.caredriving.R;
import java.util.ArrayList;

public class RecyclerViewAdapterLessonsToday extends RecyclerView.Adapter<RecyclerViewAdapterLessonsToday.ViewHolder> {

    private ArrayList<String> hours = new ArrayList<>();
    //private ArrayList<StudentObj> students = new ArrayList<>();
    //private ArrayList<TeacherObj> teacher = new ArrayList<>();
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
        TextView tvFirstName;
        TextView tvLastName;
        TextView tvStreet;
        TextView tvCity;
        TextView tvPhone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHour = itemView.findViewById(R.id.tvHour);
            tvFirstName = itemView.findViewById(R.id.tvFirstName);
            tvLastName  = itemView.findViewById(R.id.tvLastName);
            tvStreet  = itemView.findViewById(R.id.tvStreet);
            tvCity  = itemView.findViewById(R.id.tvCity);
            tvPhone  = itemView.findViewById(R.id.tvPhone);
        }
    }
}
