package com.example.caredriving.ui.navigationBar.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caredriving.R;
import com.example.caredriving.firebase.model.dataObject.StudentObj;

import java.util.ArrayList;

public class RecyclerViewAdapterAllDates extends RecyclerView.Adapter<RecyclerViewAdapterAllDates.ViewHolder> {

    private ArrayList<String> dates = new ArrayList<>();
    //private ArrayList<UserObj> contacts = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapterAllDates(Context context, ArrayList<String> dates){
        this.dates = dates;
        //this.students = students;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem_lessons, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvDate.setText(dates.get(position));
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        RecyclerViewAdapterLessonsToday adapter = new RecyclerViewAdapterLessonsToday(context, dates);
        holder.recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        holder.imgArrowRight.setVisibility(View.VISIBLE);
        holder.imgArrowLeft.setVisibility(View.VISIBLE);
        if(position == dates.size()-1){
            holder.imgArrowRight.setVisibility(View.INVISIBLE);
        }
        if(position == 0){
            holder.imgArrowLeft.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvDate;
        ImageView imgArrowRight;
        ImageView imgArrowLeft;
        RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            imgArrowRight = itemView.findViewById(R.id.imgArrowRight);
            imgArrowLeft  = itemView.findViewById(R.id.imgArrowLeft);
            recyclerView = itemView.findViewById(R.id.recyclerviewLessonsToday);
        }
    }
}
