package com.example.caredriving.ui.navigationBar.teacher;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.caredriving.R;
import com.example.caredriving.firebase.model.FirebaseDBUser;
import com.example.caredriving.firebase.model.dataObject.TeacherObj;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class ShareFragment extends Fragment {

    private TextView tvTeachersFirstName;
    private TextView tvTeachersLastName;
    private TextView tvTeachersExperience;
    private TextView tvTeacherslocations;
    private TextView tvCarType;
    private TextView tvCarYear;
    private TextView tvGearType;
    private TextView tvLessonPrice;
    private TextView tvTeachersPhoneNumber;
    private ImageView imgPhone;
    private TextView txtNoTeacher;
    RelativeLayout layoutAllTeacherInfo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_teacher, container, false);
        tvTeachersFirstName = root.findViewById(R.id.tvTeachersFirstName);
        tvTeachersLastName = root.findViewById(R.id.tvTeachersLastName);
        tvTeachersExperience = root.findViewById(R.id.tvTeachersExperience);
        tvTeacherslocations = root.findViewById(R.id.tvTeachersLoction);
        tvCarType = root.findViewById(R.id.tvTeachersCar);
        tvCarYear = root.findViewById(R.id.tvTeachersCarYear);
        tvGearType = root.findViewById(R.id.tvTeachersGear);
        tvLessonPrice = root.findViewById(R.id.tvLessonPrice);
        tvTeachersPhoneNumber = root.findViewById(R.id.tvTeachersPhone);
        imgPhone = root.findViewById(R.id.imgPhone);
        txtNoTeacher = root.findViewById(R.id.txtNoTeacher);
        layoutAllTeacherInfo = root.findViewById(R.id.layoutAllTeacherInfo);

        layoutAllTeacherInfo.setVisibility(View.INVISIBLE);
        txtNoTeacher.setVisibility(View.INVISIBLE);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String id = currentUser.getUid();
        downloadInfoFromDatabase(root,id);
        return root;
    }

    // Download the teacher's information from the Database
    private void downloadInfoFromDatabase(final View root, final String id){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users").child(id);
        reference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if ((dataSnapshot.child("info").child("teacherId").getValue()).toString().equals("null")) {
                    txtNoTeacher.setVisibility(View.VISIBLE);
                }
                else {
                    layoutAllTeacherInfo.setVisibility(View.VISIBLE);
                    String teachersId = dataSnapshot.child("info").child("teacherId").getValue().toString();
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users").child(teachersId);
                    reference.addValueEventListener(new ValueEventListener() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String firstName = Objects.requireNonNull(dataSnapshot.child("info").child("firstName").getValue()).toString();
                            String lastName = Objects.requireNonNull(dataSnapshot.child("info").child("lastName").getValue()).toString();
                            String city = Objects.requireNonNull(dataSnapshot.child("info").child("city").getValue()).toString();
                            String phone = Objects.requireNonNull(dataSnapshot.child("info").child("phoneNumber").getValue()).toString();
                            String carType = Objects.requireNonNull(dataSnapshot.child("info").child("carType").getValue()).toString();
                            String carYear = Objects.requireNonNull(dataSnapshot.child("info").child("carYear").getValue()).toString();
                            String experience = Objects.requireNonNull(dataSnapshot.child("info").child("experience").getValue()).toString();
                            String transmission = Objects.requireNonNull(dataSnapshot.child("info").child("transmission").getValue()).toString();
                            String lessonPrice = Objects.requireNonNull(dataSnapshot.child("info").child("lessonPrice").getValue()).toString();

                            tvTeachersFirstName.setText(firstName);
                            tvTeachersLastName.setText(lastName);
                            tvTeachersExperience.setText(experience);
                            tvTeacherslocations.setText(city);
                            tvCarType.setText(carType);
                            tvCarYear.setText(carYear);
                            tvGearType.setText(transmission);
                            tvLessonPrice.setText(lessonPrice);
                            tvTeachersPhoneNumber.setText(phone);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(root.getContext(), "Oops, something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(root.getContext(), "Oops, something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
}