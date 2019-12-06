package com.example.caredriving;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class InformationActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText firstName;
    private EditText lastName;
    private EditText age;
    private Spinner city;

    private Button nextInfo;
    private Button cancelInfo;
    private RadioGroup radioUserGroup;
    private RadioButton radioStudentButton;
    private RadioButton radioTeacherButton;

    private User user;
    private String nextActivity = "student";
    private Intent startIntent;

    private DatabaseReference myRef;
    private FirebaseAuth firebaseAuth;
    private String currentId;
    private String cityFromSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        firebaseAuth = FirebaseAuth.getInstance();
//        myRef = FirebaseDatabase.getInstance().getReference();
        currentId = firebaseAuth.getCurrentUser().getUid();

        firstName = findViewById(R.id.etInformationFirstName);
        lastName = findViewById(R.id.etInformationLastName);
        age = findViewById(R.id.etInformationAge);
        city = findViewById(R.id.spnInformationCity);

        startIntent = getIntent();

        if (startIntent.hasExtra("User")) {

            String type = startIntent.getStringExtra("Type");
            startIntent.removeExtra("Type");

            if (type.equals("Student")) {
                user = (Student) startIntent.getSerializableExtra("User");
                setCreatedFields();
            } else {
                nextActivity = "teacher";
                user = (Teacher) startIntent.getSerializableExtra("User");
                setCreatedFields();

                radioTeacherButton = findViewById(R.id.radioTeacher);
                radioTeacherButton.setChecked(true);
            }
        }

        nextInfo = findViewById(R.id.btnInformationNext);
        cancelInfo = findViewById(R.id.btnInformationCancelInfo);

        radioUserGroup = findViewById(R.id.radioUser);


        spinnerCityListener();
        nextInfo.setOnClickListener(this);
        cancelInfo.setOnClickListener(this);

        radioUserGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = radioUserGroup.getCheckedRadioButtonId();
                radioStudentButton = findViewById(selectedId);
                nextActivity = "student";

                if (radioStudentButton.getText().toString().toLowerCase().equals(nextActivity)) {
                    nextActivity = "student";
                } else {
                    nextActivity = "teacher";
                }
            }
        });
    }

    private void setCreatedFields(){
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        age.setText(user.getAge());
        city.setSelection(getIndex(city, user.getCity()));
    }

    private int getIndex(Spinner spinner, String myString) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)) {
                return i;
            }
        }
        return 0;
    }


    @Override
    public void onClick(View view) {
        if (view == nextInfo) {
            continueRegistration();
        }
        if (view == cancelInfo) {
            submitCancelRegistration();
        }
    }

    private void submitCancelRegistration() {
        AlertDialog.Builder cancelRegistration = new AlertDialog.Builder(this);
        cancelRegistration.setTitle("Registration");

        cancelRegistration.setMessage("Do you want to cancel this registration?");
        cancelRegistration.setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        firebaseAuth.getCurrentUser().delete();
                        Intent intent = new Intent(InformationActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.cancel();
                        Toast.makeText(InformationActivity.this, "Continue", Toast.LENGTH_SHORT).show();
                    }
                });
        cancelRegistration.create().show();
    }

    private void spinnerCityListener() {
        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cityFromSpinner = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                cityFromSpinner = adapterView.getItemAtPosition(0).toString();
            }
        });
    }

    private void continueRegistration() {

        Validation validation = new Validation();
        validation.checkFirstName(firstName.getText().toString().trim());
        validation.checkLastName(lastName.getText().toString().trim());
        validation.checkAge(age.getText().toString().trim());

        if (validation.hasErrors()) {
            for (String error : validation.getErrors()) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
            return;
        }

        Intent intent;
        if (nextActivity.equals("student")) {
            user = new Student();
            intent = new Intent(InformationActivity.this, InformationStudentActivity.class);
        } else {
            user = new Teacher();
            intent = new Intent(InformationActivity.this, InformationTeacherActivity.class);
        }

        user.setFirstName(firstName.getText().toString().trim());
        user.setLastName(lastName.getText().toString().trim());
        user.setAge(age.getText().toString().trim());
        user.setCity(cityFromSpinner);

        intent.putExtra("User", user);
        intent.putExtra("uid", currentId);
        startActivity(intent);
    }
}
