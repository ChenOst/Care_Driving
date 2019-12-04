package com.example.caredriving.ui.searchTeachers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.caredriving.R;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> teachersFirstNames = new ArrayList<>();
    private ArrayList<String> teachersLastNames = new ArrayList<>();
    private ArrayList<String> locations = new ArrayList<>();
    private ArrayList<String> lessonPrice = new ArrayList<>();
    private ArrayList<String> gearTypes = new ArrayList<>();
    private ArrayList<String> phoneNumbers = new ArrayList<>();

    private Context context;

    public RecyclerViewAdapter(Context context, ArrayList<String> teachersFirstNames, ArrayList<String> teachersLastNames, ArrayList<String> locations,
            ArrayList<String> lessonPrice, ArrayList<String> gearTypes, ArrayList<String> phoneNumbers) {
        this.teachersFirstNames = teachersFirstNames;
        this.teachersLastNames = teachersLastNames;
        this.locations = locations;
        this.lessonPrice = lessonPrice;
        this.gearTypes = gearTypes;
        this.phoneNumbers = phoneNumbers;
        this.context = context;
    }

    // Recycling the ViewHolder - put things into position
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    // Called every single time when a new item is added to the list
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        holder.tvTeachersFirstName.setText(teachersFirstNames.get(position));
        holder.tvTeachersLastName.setText(teachersLastNames.get(position));
        holder.tvLocation.setText(locations.get(position));
        holder.tvPrice.setText(lessonPrice.get(position));
        holder.tvGearType.setText(gearTypes.get(position));
        holder.tvPhoneNumber.setText(phoneNumbers.get(position));

        //OnClickListener to each item
        holder.layoutTeachersDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked." + teachersFirstNames.get(position));
                Toast.makeText(context, teachersFirstNames.get(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ContactTeacherActivity.class);
                intent.putExtra("TeachersFirstName", teachersFirstNames.get(position));
                context.startActivity(intent);
            }
        });
    }

    // Tells how many items are on the list
    @Override
    public int getItemCount() {
        return teachersFirstNames.size();
    }

    // Holds the information in memory of each individual entry
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvTeachersFirstName;
        TextView tvTeachersLastName;
        TextView tvLocation;
        TextView tvGearType;
        TextView tvPhoneNumber;
        TextView tvPrice;
        RelativeLayout layoutTeachersDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTeachersFirstName = itemView.findViewById(R.id.tvTeachersFirstName);
            tvTeachersLastName = itemView.findViewById(R.id.tvTeachersLastName);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvPhoneNumber = itemView.findViewById(R.id.tvPhoneNumber);
            tvGearType = itemView.findViewById(R.id.tvGearType);
            layoutTeachersDetails = itemView.findViewById(R.id.layoutTeachersDetails);
        }
    }

}
