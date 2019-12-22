package com.example.caredriving.ui.navigationBar.requests;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caredriving.R;
import com.example.caredriving.firebase.model.dataObject.RequestObj;
import com.example.caredriving.firebase.model.dataObject.StudentObj;
import com.example.caredriving.firebase.model.dataObject.TeacherObj;
import com.example.caredriving.ui.navigationBar.searchTeachers.RecyclerViewAdapter;

import java.util.ArrayList;

public class RequestsViewAdapterModel extends RecyclerView.Adapter<RequestsViewAdapterModel.ViewHolder> {

    private static final String TAG = "RequestViewAdapterModel";
    private MutableLiveData<String> mText;
    private ArrayList<StudentObj> students;
    private Context context;

    public RequestsViewAdapterModel(Context context, ArrayList<StudentObj> students) {
//        mText = new MutableLiveData<>();
//        mText.setValue("This is requests fragment");
        this.context = context;
        this.students = students;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_request_item, parent, false);
        ViewHolder holder = new RequestsViewAdapterModel.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        holder.tvStudentFirstName.setText(students.get(position).getFirstName());
        holder.tvStudentLastName.setText(students.get(position).getLastName());
        holder.tvCity.setText(students.get(position).getCity());
        holder.tvGreenForm.setText(students.get(position).getGreenForm());
        holder.tvPhoneNumber.setText(students.get(position).getPhoneNumber());
        holder.tvTheory.setText(students.get(position).getTheory());
        holder.tvStudentFirstName.setText(students.get(position).getFirstName());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public LiveData<String> getText() {
        return mText;
    }







    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvStudentFirstName;
        TextView tvStudentLastName;
        TextView tvCity;
        TextView tvGreenForm;
        TextView tvTheory;
        TextView tvPhoneNumber;
        Button btnAccept;
        Button btnReject;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStudentFirstName = itemView.findViewById(R.id.tvStudentFirstName);
            tvStudentLastName = itemView.findViewById(R.id.tvStudentLastName);
            tvCity = itemView.findViewById(R.id.tvCityValue);
            tvGreenForm = itemView.findViewById(R.id.tvGreenFormValue);
            tvTheory = itemView.findViewById(R.id.tvTheoryValue);
            tvPhoneNumber = itemView.findViewById(R.id.tvPhoneNumberValue);
            btnAccept = itemView.findViewById(R.id.btnAccept);
            btnReject = itemView.findViewById(R.id.btnReject);

            btnAccept.setOnClickListener(this);
            btnReject.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view == btnAccept){
                System.out.println("Send accept (status 1)");
            }
            else if (view == btnReject){
                System.out.println("Send reject (status -1)");
            }
        }
    }
}