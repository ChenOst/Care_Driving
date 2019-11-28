package com.example.caredriving;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InformationStudentActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner transmission;
    private Spinner greenForm;
    private Spinner theory;

    private Button save;
    private Button previous;

    private Student student;
    private final String STUDENT = "student";
    private String userId;

    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_student);

        myRef = FirebaseDatabase.getInstance().getReference();

        transmission = findViewById(R.id.spnInformationStudentTransmission);
        greenForm = findViewById(R.id.spnInformationStudentGreenForm);
        theory = findViewById(R.id.spnInformationStudentTheory);

        Intent intent = getIntent();
        student = (Student) intent.getSerializableExtra("User");
        userId = (String) intent.getSerializableExtra("uid");

        save = findViewById(R.id.btnInformationStudentSaveInfo);
        previous = findViewById(R.id.btnInformationStudentPreviousInfo);

        spinnerGreenFormListener();
        spinnerTheoryListener();
        spinnerTransmissionListener();

        save.setOnClickListener(this);
        previous.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == save) {
            saveUserInformation();
        }
        if (view == previous) {
            openPreviousPage();
        }
    }

    private void saveUserInformation() {
        //create user on database by currentId
        //create teacher in database by currentId
        myRef.child("users").child(userId).child("info").setValue(student);
        myRef.child("users").child(userId).child("type").setValue(STUDENT);
        Intent intent = new Intent(InformationStudentActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void openPreviousPage() {
        //add chosen data to intent
        //go to information activity
        Intent intent = new Intent(InformationStudentActivity.this, InformationActivity.class);
        startActivity(intent);
    }

    private void spinnerTransmissionListener() {
        transmission.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            String tempTransmission;

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tempTransmission = adapterView.getItemAtPosition(i).toString();
                student.setTransmission(tempTransmission);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                tempTransmission = adapterView.getItemAtPosition(0).toString();
                student.setTransmission(tempTransmission);
            }
        });
    }

    private void spinnerGreenFormListener() {
        greenForm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            String tempGreenForm;

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tempGreenForm = adapterView.getItemAtPosition(i).toString();
                student.setGreenForm(tempGreenForm);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                tempGreenForm = adapterView.getItemAtPosition(0).toString();
                student.setGreenForm(tempGreenForm);
            }
        });
    }

    private void spinnerTheoryListener() {
        theory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            String tempTheory;

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tempTheory = adapterView.getItemAtPosition(i).toString();
                student.setTheory(tempTheory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                tempTheory = adapterView.getItemAtPosition(0).toString();
                student.setTheory(tempTheory);
            }
        });
    }
}
