package com.example.caredriving;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class InformationActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText firstName;
    private EditText lastName;
    private EditText age;
    private EditText city;

    private Spinner spnCarBrand;
    private Spinner spnCarYear;
    private Spinner spnExperience;
    private Spinner spnTransmission;

    private LinearLayout linearLayout;

    private Button saveInfo;
    private RadioGroup radioUserGroup;
    private RadioButton radioUserButton;

    private User user;
    private String chosenUser; //when save the user

    private DatabaseReference myRef;
    private FirebaseAuth firebaseAuth;
    private String currentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        firebaseAuth = FirebaseAuth.getInstance();
        currentId = firebaseAuth.getCurrentUser().getUid();

        spnCarYear = findViewById(R.id.spinnerInformationCarYears);
        spnExperience = findViewById(R.id.spinnerInformationExperience);
        spnCarBrand = findViewById(R.id.spinnerInformationCarBrands);
        spnTransmission = findViewById(R.id.spinnerInformationTransmission);

        addItemsToSpinnerTransmission();
        addItemsToSpinnerCarBrands();
        addItemsToSpinnerExperience();
        addItemsToSpinnerCarYear();

        firstName = findViewById(R.id.fillFirstName);
        lastName = findViewById(R.id.fillLastName);
        age = findViewById(R.id.fillAge);
        city = findViewById(R.id.fillCity);
        saveInfo = findViewById(R.id.btnSaveInfo);

        radioUserGroup = findViewById(R.id.radioUser);

        myRef = FirebaseDatabase.getInstance().getReference();
        saveInfo.setOnClickListener(this);
        radioUserGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = radioUserGroup.getCheckedRadioButtonId();
                radioUserButton = findViewById(selectedId);

                chosenUser = "students";

                if(radioUserButton.getText().toString().toLowerCase().equals(chosenUser.substring(0, chosenUser.length() - 1))){
                    user = new Student();
                } else {
                    user = new Teacher();
                    chosenUser = "teachers";
                }
            }
        });
    }

    //fix on selected
    private void addItemsToSpinnerCarBrands() {
        spnCarBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //fix on selected
    private void addItemsToSpinnerTransmission() {
        spnTransmission.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void addItemsToSpinnerCarYear() {
        int startFrom = 2000;
        List<String> list = new ArrayList<>();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for(;startFrom < year; startFrom++){
            list.add(startFrom + "");
        }

        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCarYear.setAdapter(yearAdapter);
    }


    private void addItemsToSpinnerExperience() {
        List<String> list = new ArrayList<>();
        for(int i = 0; i < 25; i++){
            list.add(i + "");
        }

        list.add("25+");

        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnExperience.setAdapter(yearAdapter);
    }

    @Override
    public void onClick(View view) {
        if(view == saveInfo){
            saveInfoInFirebase();
        }
    }

    private void saveInfoInFirebase() {

        user.setFirstName(firstName.getText().toString().trim());
        user.setLastName(lastName.getText().toString().trim());
        user.setAge(age.getText().toString().trim());
        user.setCity(city.getText().toString().trim());

//        myRef.child(chosenUser).push().setValue(user);
        myRef.child(chosenUser).child(currentId).setValue(user);

        finish();
        startActivity(new Intent(this, MainActivity.class));
        Toast.makeText(this, "Save Info", Toast.LENGTH_SHORT).show();
    }
}
