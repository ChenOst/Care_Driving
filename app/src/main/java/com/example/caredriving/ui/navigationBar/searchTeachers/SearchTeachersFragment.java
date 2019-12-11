package com.example.caredriving.ui.navigationBar.searchTeachers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.caredriving.R;
import com.example.caredriving.firebase.model.dataObject.TeacherObj;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class SearchTeachersFragment extends Fragment {

    private static ArrayList<TeacherObj> teachers = new ArrayList<>();
    private static ArrayList<TeacherObj> allTeachers = new ArrayList<>();
    private static ArrayList<String> newLocations = new ArrayList<>();
    private static ArrayList<String> newCarBrands = new ArrayList<>();
    private static ArrayList<String> newGears = new ArrayList<>();
    private static ArrayList<String> newPrices = new ArrayList<>();
    private RecyclerViewAdapter adapter;
    private static RecyclerView recyclerView;

    // Locations Filters
    private String[] listLocations;
    private boolean[] checkedLocations;
    private ArrayList<Integer> usersLocations = new ArrayList<>();

    // Car Brands Filters
    private String[] listCarBrands;
    private boolean[] checkedCarBrands;
    private ArrayList<Integer> usersCarBrands = new ArrayList<>();

    // Gear Types Filters
    private String[] listGears;
    private boolean[] checkedGears;
    private ArrayList<Integer> usersGears = new ArrayList<>();

    // Price Filters
    private String[] listPriceRange;
    private boolean[] checkedPriceRange;
    private ArrayList<Integer> usersPriceRange = new ArrayList<>();

    public static SearchTeachersFragment newInstance() {
        return new SearchTeachersFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_search_teachers, container, false);
        recyclerView = root.findViewById(R.id.recyclerviewTeachers);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");
        reference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                teachers.clear();
                if (teachers.isEmpty()) {
                    for (DataSnapshot dataSnapshotl : dataSnapshot.getChildren()) {
                        if (dataSnapshotl.child("type").getValue().equals("teacher")) {
                            String firstName = Objects.requireNonNull(dataSnapshotl.child("info").child("firstName").getValue()).toString();
                            String lastName = Objects.requireNonNull(dataSnapshotl.child("info").child("lastName").getValue()).toString();
                            String age = Objects.requireNonNull(dataSnapshotl.child("info").child("age").getValue()).toString();
                            String city = Objects.requireNonNull(dataSnapshotl.child("info").child("city").getValue()).toString();
                            String email = Objects.requireNonNull(dataSnapshotl.child("info").child("email").getValue()).toString();
                            String phone = Objects.requireNonNull(dataSnapshotl.child("info").child("phoneNumber").getValue()).toString();
                            String carType = Objects.requireNonNull(dataSnapshotl.child("info").child("carType").getValue()).toString();
                            String carYear = Objects.requireNonNull(dataSnapshotl.child("info").child("carYear").getValue()).toString();
                            String experience = Objects.requireNonNull(dataSnapshotl.child("info").child("experience").getValue()).toString();
                            String transmission = Objects.requireNonNull(dataSnapshotl.child("info").child("transmission").getValue()).toString();
                            String lessonPrice = Objects.requireNonNull(dataSnapshotl.child("info").child("lessonPrice").getValue()).toString();

                            TeacherObj teacher = new TeacherObj(firstName, lastName, age, city, email, phone, carType, carYear, experience, transmission, lessonPrice);
                            teachers.add(teacher);

                        }
                    }
                    allTeachers.addAll(teachers);
                    adapter = new RecyclerViewAdapter(getActivity(), teachers);
                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(root.getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });


        // Locations Filters
        Button btnLocationsFilter = root.findViewById(R.id.btnLocationsFilter);
        listLocations = getResources().getStringArray(R.array.cities);
        checkedLocations = new boolean[listLocations.length];
        btnLocationsFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(root.getContext());
                mBuilder.setTitle(getString(R.string.title_location_filter));
                mBuilder.setMultiChoiceItems(listLocations, checkedLocations, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if (isChecked) {
                            if (!usersLocations.contains(position)) {
                                usersLocations.add(position);
                            }
                        } else if (usersLocations.contains(position)) {
                            usersLocations.remove((Object) position);
                        }
                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("Filter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        newLocations.clear();
                        ArrayList<String> items = new ArrayList<>();
                        items.clear();
                        for (int i = 0; i < usersLocations.size(); i++) {
                            items.add(listLocations[usersLocations.get(i)]);
                        }
                        newLocations.addAll(items);
                        setNotificationInList(root);
                    }
                });
                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

        // Car Brands Filters
        Button btnCarBrandsFilter = root.findViewById(R.id.btnCarBrandsFilter);
        listCarBrands = getResources().getStringArray(R.array.car_brands);
        checkedCarBrands = new boolean[listCarBrands.length];
        btnCarBrandsFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(root.getContext());
                mBuilder.setTitle(getString(R.string.title_car_brand_filter));
                mBuilder.setMultiChoiceItems(listCarBrands, checkedCarBrands, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if (isChecked) {
                            if (!usersCarBrands.contains(position)) {
                                usersCarBrands.add(position);
                            }
                        } else if (usersCarBrands.contains(position)) {
                            usersCarBrands.remove((Object) position);
                        }
                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("Filter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        newCarBrands.clear();
                        ArrayList<String> items = new ArrayList<>();
                        items.clear();
                        for (int i = 0; i < usersCarBrands.size(); i++) {
                            items.add(listCarBrands[usersCarBrands.get(i)]);
                        }
                        newCarBrands.addAll(items);
                        setNotificationInList(root);
                    }
                });
                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

        // Gear Types Filters
        Button btnGearTypesFilter = root.findViewById(R.id.btnGearTypesFilter);
        listGears = getResources().getStringArray(R.array.transmission_type);
        checkedGears = new boolean[listGears.length];
        btnGearTypesFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(root.getContext());
                mBuilder.setTitle(getString(R.string.title_gear_filter));
                mBuilder.setMultiChoiceItems(listGears, checkedGears, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if (isChecked) {
                            if (!usersGears.contains(position)) {
                                usersGears.add(position);
                            }
                        } else if (usersGears.contains(position)) {
                            usersGears.remove((Object) position);
                        }
                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("Filter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        newGears.clear();
                        ArrayList<String> items = new ArrayList<>();
                        items.clear();
                        for (int i = 0; i < usersGears.size(); i++) {
                            items.add(listGears[usersGears.get(i)]);
                        }
                        newGears.addAll(items);
                        setNotificationInList(root);
                    }
                });
                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

        // Price Filters
        Button btnPriceFilter = root.findViewById(R.id.btnPriceFilter);
        listPriceRange = getResources().getStringArray(R.array.price_range);
        checkedPriceRange = new boolean[listPriceRange.length];
        btnPriceFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(root.getContext());
                mBuilder.setTitle(getString(R.string.title_price_filter));
                mBuilder.setMultiChoiceItems(listPriceRange, checkedPriceRange, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if (isChecked) {
                            if (!usersPriceRange.contains(position)) {
                                usersPriceRange.add(position);
                            }
                        } else if (usersPriceRange.contains(position)) {
                            usersPriceRange.remove((Object) position);
                        }
                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("Filter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        newPrices.clear();
                        ArrayList<String> items = new ArrayList<>();
                        items.clear();
                        for (int i = 0; i < usersPriceRange.size(); i++) {
                            items.add(listPriceRange[usersPriceRange.get(i)]);
                        }
                        newPrices.addAll(items);
                        setNotificationInList(root);
                    }
                });
                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

        return root;
    }

    // taking care of all the filter options - 16 options
    private void setNotificationInList(final View root) {
        // 0 0 0 0
        if (newLocations.isEmpty() && newCarBrands.isEmpty() && newGears.isEmpty() && newPrices.isEmpty()) {
            teachers.clear();
            teachers.addAll(allTeachers);
            adapter.notifyDataSetChanged();
        }
        // 0 0 0 1
        else if (newLocations.isEmpty() && newCarBrands.isEmpty() && newGears.isEmpty() && !newPrices.isEmpty()) {
            teachers.clear();
            for (TeacherObj teacher : allTeachers) {
                for (String price : newPrices) {
                    String[] parts = price.split("\\–");
                    int lower = Integer.parseInt(parts[0]);
                    int upper = Integer.parseInt(parts[1]);
                    if (lower <= Integer.parseInt(teacher.getLessonPrice()) && upper >= Integer.parseInt(teacher.getLessonPrice())) {
                        teachers.add(teacher);
                    }
                }
            }
            adapter.notifyDataSetChanged();
        }
        // 0 0 1 0
        else if (newLocations.isEmpty() && newCarBrands.isEmpty() && !newGears.isEmpty() && newPrices.isEmpty()) {
            teachers.clear();
            for (TeacherObj teacher : allTeachers) {
                if (newGears.contains(teacher.getTransmission())) {
                    teachers.add(teacher);
                }
            }
            adapter.notifyDataSetChanged();
        }
        // 0 0 1 1
        else if (newLocations.isEmpty() && newCarBrands.isEmpty() && !newGears.isEmpty() && !newPrices.isEmpty()) {
            teachers.clear();
            for (TeacherObj teacher : allTeachers) {
                if (newGears.contains(teacher.getTransmission())) {
                    for (String price : newPrices) {
                        String[] parts = price.split("\\–");
                        int lower = Integer.parseInt(parts[0]);
                        int upper = Integer.parseInt(parts[1]);
                        if (lower <= Integer.parseInt(teacher.getLessonPrice()) && upper >= Integer.parseInt(teacher.getLessonPrice())) {
                            teachers.add(teacher);
                        }
                    }
                }
            }
            adapter.notifyDataSetChanged();
        }
        // 0 1 0 0
        else if (newLocations.isEmpty() && !newCarBrands.isEmpty() && newGears.isEmpty() && newPrices.isEmpty()) {
            teachers.clear();
            for (TeacherObj teacher : allTeachers) {
                if (newCarBrands.contains(teacher.getCarType())) {
                    teachers.add(teacher);
                }
            }
            adapter.notifyDataSetChanged();
        }
        // 0 1 0 1
        else if (newLocations.isEmpty() && !newCarBrands.isEmpty() && newGears.isEmpty() && !newPrices.isEmpty()) {
            teachers.clear();
            for (TeacherObj teacher : allTeachers) {
                if (newCarBrands.contains(teacher.getCarType())) {
                    for (String price : newPrices) {
                        String[] parts = price.split("\\–");
                        int lower = Integer.parseInt(parts[0]);
                        int upper = Integer.parseInt(parts[1]);
                        if (lower <= Integer.parseInt(teacher.getLessonPrice()) && upper >= Integer.parseInt(teacher.getLessonPrice())) {
                            teachers.add(teacher);
                        }
                    }
                }
            }
            adapter.notifyDataSetChanged();
        }
        // 0 1 1 0
        else if (newLocations.isEmpty() && !newCarBrands.isEmpty() && !newGears.isEmpty() && newPrices.isEmpty()) {
            teachers.clear();
            for (TeacherObj teacher : allTeachers) {
                if (newCarBrands.contains(teacher.getCarType()) && newGears.contains(teacher.getTransmission())) {
                    teachers.add(teacher);
                }
            }
            adapter.notifyDataSetChanged();
        }
        // 0 1 1 1
        else if (newLocations.isEmpty() && !newCarBrands.isEmpty() && !newGears.isEmpty() && !newPrices.isEmpty()) {
            teachers.clear();
            for (TeacherObj teacher : allTeachers) {
                if (newCarBrands.contains(teacher.getCarType()) && newGears.contains(teacher.getTransmission())) {
                    for (String price : newPrices) {
                        String[] parts = price.split("\\–");
                        int lower = Integer.parseInt(parts[0]);
                        int upper = Integer.parseInt(parts[1]);
                        if (lower <= Integer.parseInt(teacher.getLessonPrice()) && upper >= Integer.parseInt(teacher.getLessonPrice())) {
                            teachers.add(teacher);
                        }
                    }
                }
            }
            adapter.notifyDataSetChanged();
        }
        // 1 0 0 0
        else if (!newLocations.isEmpty() && newCarBrands.isEmpty() && newGears.isEmpty() && newPrices.isEmpty()) {
            teachers.clear();
            for (TeacherObj teacher : allTeachers) {
                if (newLocations.contains(teacher.getCity())) {
                    teachers.add(teacher);
                }
            }
            adapter.notifyDataSetChanged();
        }
        // 1 0 0 1
        else if (!newLocations.isEmpty() && newCarBrands.isEmpty() && newGears.isEmpty() && !newPrices.isEmpty()) {
            teachers.clear();
            for (TeacherObj teacher : allTeachers) {
                if (newLocations.contains(teacher.getCity())) {
                    for (String price : newPrices) {
                        String[] parts = price.split("\\–");
                        int lower = Integer.parseInt(parts[0]);
                        int upper = Integer.parseInt(parts[1]);
                        if (lower <= Integer.parseInt(teacher.getLessonPrice()) && upper >= Integer.parseInt(teacher.getLessonPrice())) {
                            teachers.add(teacher);
                        }
                    }
                }
            }
            adapter.notifyDataSetChanged();
        }
        // 1 0 1 0
        else if (!newLocations.isEmpty() && newCarBrands.isEmpty() && !newGears.isEmpty() && newPrices.isEmpty()) {
            teachers.clear();
            for (TeacherObj teacher : allTeachers) {
                if (newLocations.contains(teacher.getCity()) && newGears.contains(teacher.getTransmission())) {
                    teachers.add(teacher);
                }
            }
            adapter.notifyDataSetChanged();
        }
        // 1 0 1 1
        else if (!newLocations.isEmpty() && newCarBrands.isEmpty() && !newGears.isEmpty() && !newPrices.isEmpty()) {
            teachers.clear();
            for (TeacherObj teacher : allTeachers) {
                if (newLocations.contains(teacher.getCity()) && newGears.contains(teacher.getTransmission())) {
                    for (String price : newPrices) {
                        String[] parts = price.split("\\–");
                        int lower = Integer.parseInt(parts[0]);
                        int upper = Integer.parseInt(parts[1]);
                        if (lower <= Integer.parseInt(teacher.getLessonPrice()) && upper >= Integer.parseInt(teacher.getLessonPrice())) {
                            teachers.add(teacher);
                        }
                    }
                }
            }
            adapter.notifyDataSetChanged();
        }
        // 1 1 0 0
        else if (!newLocations.isEmpty() && !newCarBrands.isEmpty() && newGears.isEmpty() && newPrices.isEmpty()) {
            teachers.clear();
            for (TeacherObj teacher : allTeachers) {
                if (newLocations.contains(teacher.getCity()) && newCarBrands.contains(teacher.getCarType())) {
                    teachers.add(teacher);
                }
            }
            adapter.notifyDataSetChanged();
        }
        // 1 1 0 1
        else if (!newLocations.isEmpty() && !newCarBrands.isEmpty() && newGears.isEmpty() && !newPrices.isEmpty()) {
            teachers.clear();
            for (TeacherObj teacher : allTeachers) {
                if (newLocations.contains(teacher.getCity()) && newCarBrands.contains(teacher.getCarType())) {
                    for (String price : newPrices) {
                        String[] parts = price.split("\\–");
                        int lower = Integer.parseInt(parts[0]);
                        int upper = Integer.parseInt(parts[1]);
                        if (lower <= Integer.parseInt(teacher.getLessonPrice()) && upper >= Integer.parseInt(teacher.getLessonPrice())) {
                            teachers.add(teacher);
                        }
                    }
                }
            }
            adapter.notifyDataSetChanged();
        }
        // 1 1 1 0
        else if (!newLocations.isEmpty() && !newCarBrands.isEmpty() && !newGears.isEmpty() && newPrices.isEmpty()) {
            teachers.clear();
            for (TeacherObj teacher : allTeachers) {
                if (newLocations.contains(teacher.getCity()) && newCarBrands.contains(teacher.getCarType())
                        && newGears.contains(teacher.getTransmission())) {
                    teachers.add(teacher);
                }
            }
            adapter.notifyDataSetChanged();
        }
        // 1 1 1 1
        else if (!newLocations.isEmpty() && !newCarBrands.isEmpty() && !newGears.isEmpty() && !newPrices.isEmpty()) {
            teachers.clear();
            for (TeacherObj teacher : allTeachers) {
                if (newLocations.contains(teacher.getCity()) && newCarBrands.contains(teacher.getCarType())
                        && newGears.contains(teacher.getTransmission())) {
                    for (String price : newPrices) {
                        String[] parts = price.split("\\–");
                        int lower = Integer.parseInt(parts[0]);
                        int upper = Integer.parseInt(parts[1]);
                        if (lower <= Integer.parseInt(teacher.getLessonPrice()) && upper >= Integer.parseInt(teacher.getLessonPrice())) {
                            teachers.add(teacher);
                        }
                    }
                }
            }
            adapter.notifyDataSetChanged();

        } else {
            Toast.makeText(root.getContext(), "Something went wrong...", Toast.LENGTH_SHORT).show();
        }
    }
}