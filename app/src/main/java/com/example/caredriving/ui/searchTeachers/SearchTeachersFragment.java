package com.example.caredriving.ui.searchTeachers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.caredriving.R;
import com.example.caredriving.Teacher;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class SearchTeachersFragment extends Fragment {

    private static ArrayList<Teacher> teachers = new ArrayList<>();
    private static ArrayList<String> items = new ArrayList<>();
    private DatabaseReference reference;

    // Location Filters
    private Button btnLocationsFilter;
    private String[] listLocations; // Contains all the locations
    private boolean[] checkedLocations; // Contains all the locations that the user checked
    private ArrayList<Integer> selectedLocations = new ArrayList<>(); // The locations the user selected - helps to avoid duplicated data

    // Car brands Filters
    private Button btnCarBrandsFilter;
    private String[] listCarBrands; // Contains all the locations
    private boolean[] checkedCarBrands; // Contains all the locations that the user checked
    private ArrayList<Integer> selectedCarBrands = new ArrayList<>(); // The locations the user selected - helps to avoid duplicated data

    // Gear Types Filters
    private Button btnGearTypesFilter;
    private String[] listGearTypes; // Contains all the locations
    private boolean[] checkedGearTypes; // Contains all the locations that the user checked
    private ArrayList<Integer> selectedGearTypes = new ArrayList<>(); // The locations the user selected - helps to avoid duplicated data

    // Price Filters
    private Button btnPriceFilter;
    private String[] listPrice; // Contains all the locations
    private boolean[] checkedPrice; // Contains all the locations that the user checked
    private ArrayList<Integer> selectedPrice = new ArrayList<>(); // The locations the user selected - helps to avoid duplicated data

    public static SearchTeachersFragment newInstance(){
        SearchTeachersFragment searchTeachersFragment = new SearchTeachersFragment();
        return searchTeachersFragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_search_teachers, container, false);
        teachers.clear();

        // Location Filters
        btnLocationsFilter = root.findViewById(R.id.btnLocationsFilter);
        listLocations = getResources().getStringArray(R.array.cities);
        checkedLocations = new boolean[listLocations.length];
        btnLocationsFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creates Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(root.getContext());
                builder.setTitle("Select Locations:");
                builder.setMultiChoiceItems(listLocations, checkedLocations, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if (isChecked){
                            if (!selectedLocations.contains(position)){
                                selectedLocations.add(position);
                            }
                            else{
                                selectedLocations.remove(position);
                            }
                        }
                    }
                });
                builder.setCancelable(false);
                builder.setPositiveButton("Filter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i=0 ; i<selectedLocations.size(); i++){
                            items.add(listLocations[selectedLocations.get(i)]);
                        }
                    }
                });
                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNeutralButton("Clear all", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i=0; i< checkedLocations.length; i++){
                            checkedLocations[i] = false;
                            selectedLocations.clear();
                        }
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        // Car brands Filters
        btnCarBrandsFilter = root.findViewById(R.id.btnCarBrandsFilter);
        listCarBrands = getResources().getStringArray(R.array.car_brands);
        checkedCarBrands = new boolean[listCarBrands.length];
        btnCarBrandsFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creates Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(root.getContext());
                builder.setTitle("Select Car Brands:");
                builder.setMultiChoiceItems(listCarBrands, checkedCarBrands, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if (isChecked){
                            if (!selectedCarBrands.contains(position)){
                                selectedCarBrands.add(position);
                            }
                            else{
                                selectedCarBrands.remove(position);
                            }
                        }
                    }
                });
                builder.setCancelable(false);
                builder.setPositiveButton("Filter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i=0 ; i<selectedCarBrands.size(); i++){
                            items.add(listCarBrands[selectedCarBrands.get(i)]);
                        }
                    }
                });
                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNeutralButton("Clear all", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i=0; i< checkedCarBrands.length; i++){
                            checkedCarBrands[i] = false;
                            selectedCarBrands.clear();
                        }
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        // Gear Types Filters
        btnGearTypesFilter = root.findViewById(R.id.btnGearTypesFilter);
        listGearTypes = getResources().getStringArray(R.array.transmission_type);
        checkedGearTypes = new boolean[listGearTypes.length];
        btnGearTypesFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creates Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(root.getContext());
                builder.setTitle("Select Gear:");
                builder.setMultiChoiceItems(listGearTypes, checkedGearTypes, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if (isChecked){
                            if (!selectedGearTypes.contains(position)){
                                selectedGearTypes.add(position);
                            }
                            else{
                                selectedGearTypes.remove(position);
                            }
                        }
                    }
                });
                builder.setCancelable(false);
                builder.setPositiveButton("Filter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i=0 ; i<selectedGearTypes.size(); i++){
                            items.add(listGearTypes[selectedGearTypes.get(i)]);
                        }
                    }
                });
                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNeutralButton("Clear all", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i=0; i< checkedGearTypes.length; i++){
                            checkedGearTypes[i] = false;
                            selectedGearTypes.clear();
                        }
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        // Price Filters
        btnPriceFilter = root.findViewById(R.id.btnPriceFilter);
        listPrice = getResources().getStringArray(R.array.price_range);
        checkedPrice = new boolean[listPrice.length];
        btnPriceFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creates Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(root.getContext());
                builder.setTitle("Select Price:");
                builder.setMultiChoiceItems(listPrice, checkedPrice, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if (isChecked){
                            if (!selectedPrice.contains(position)){
                                selectedPrice.add(position);
                            }
                            else{
                                selectedPrice.remove(position);
                            }
                        }
                    }
                });
                builder.setCancelable(false);
                builder.setPositiveButton("Filter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i=0 ; i<selectedPrice.size(); i++){
                            items.add(listPrice[selectedPrice.get(i)]);
                        }
                    }
                });
                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNeutralButton("Clear all", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i=0; i< checkedPrice.length; i++){
                            checkedPrice[i] = false;
                            selectedPrice.clear();
                        }
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        final RecyclerView recyclerView = root.findViewById(R.id.recyclerviewTeachers);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Get all teachers information from the Firebase
            reference = FirebaseDatabase.getInstance().getReference().child("users");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshotl : dataSnapshot.getChildren()) {
                        if (dataSnapshotl.child("type").getValue().equals("teacher")) {
                                String firstName = dataSnapshotl.child("info").child("firstName").getValue().toString();
                                String lastName = dataSnapshotl.child("info").child("lastName").getValue().toString();
                                String age = dataSnapshotl.child("info").child("age").getValue().toString();
                                String city = dataSnapshotl.child("info").child("city").getValue().toString();
                                String email = dataSnapshotl.child("info").child("email").getValue().toString();
                                String phone = dataSnapshotl.child("info").child("phoneNumber").getValue().toString();
                                String carType = dataSnapshotl.child("info").child("carType").getValue().toString();
                                String carYear = dataSnapshotl.child("info").child("carYear").getValue().toString();
                                String experience = dataSnapshotl.child("info").child("experience").getValue().toString();
                                String transmission = dataSnapshotl.child("info").child("transmission").getValue().toString();
                                String lessonPrice = dataSnapshotl.child("info").child("lessonPrice").getValue().toString();

                                Teacher t = new Teacher(firstName, lastName, age, city, email, phone, carType, carYear, experience, transmission, lessonPrice);
                                teachers.add(t);
                                RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), teachers);
                                recyclerView.setAdapter(adapter);
                            }

                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(root.getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });
        return root;
    }
}
