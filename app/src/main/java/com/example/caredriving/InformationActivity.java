package com.example.caredriving;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class InformationActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText firstName;
    private EditText lastName;
    private EditText age;
    private EditText city;

    private Button nextInfo;
    private Button cancelInfo;
    private RadioGroup radioUserGroup;
    private RadioButton radioUserButton;

    private User user;
    private String nextActivity = "student";

    private DatabaseReference myRef;
    private FirebaseAuth firebaseAuth;
    private String currentId;

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
        city = findViewById(R.id.etInformationCity);

        nextInfo = findViewById(R.id.btnInformationNext);
        cancelInfo = findViewById(R.id.btnInformationCancelInfo);

        radioUserGroup = findViewById(R.id.radioUser);

        nextInfo.setOnClickListener(this);
        cancelInfo.setOnClickListener(this);

        radioUserGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = radioUserGroup.getCheckedRadioButtonId();
                radioUserButton = findViewById(selectedId);

                if (radioUserButton.getText().toString().toLowerCase().equals(nextActivity)) {
                    nextActivity = "student";
                } else {
                    nextActivity = "teacher";
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view == nextInfo) {
            continueRegistration();
        }
        if(view == cancelInfo) {
            //window "Cancel registration?" Yes -> go to first page / No -> stay in this page
        }
    }

    private void continueRegistration() {

        Intent intent;
        if(nextActivity.equals("student")){
            user = new Student();
            intent = new Intent(InformationActivity.this, InformationStudentActivity.class);
        } else {
            user = new Teacher();
            intent = new Intent(InformationActivity.this, InformationTeacherActivity.class);
        }

        user.setFirstName(firstName.getText().toString().trim());
        user.setLastName(lastName.getText().toString().trim());
        user.setAge(age.getText().toString().trim());
        user.setCity(city.getText().toString().trim());

        intent.putExtra("User", user);
        intent.putExtra("uid", currentId);
        startActivity(intent);
    }
}
