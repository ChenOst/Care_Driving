package com.example.caredriving;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PersonalArea extends AppCompatActivity implements View.OnClickListener{

    User user;

    TextView fnameTextView;
    TextView lnameTextView;
    TextView ageTextView;
    TextView cityTextView;
    TextView emailTextView;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_area);


//        //take the user from login activity and put it in "user" variable
        user = (User) getIntent().getSerializableExtra("User");


        fnameTextView = (TextView) findViewById(R.id.fillFirstName);
        lnameTextView = (TextView) findViewById(R.id.fillLastName);
        ageTextView = (TextView) findViewById(R.id.fillAge);
        cityTextView = (TextView) findViewById(R.id.fillCity);
        emailTextView = (TextView) findViewById(R.id.fillemail);

        progressDialog = new ProgressDialog(this);

        //change details in all fields according the "user" from intent
        fnameTextView.setHint(user.getFirstName());
        lnameTextView.setHint(user.getLastName());
        ageTextView.setHint(user.getAge());
        cityTextView.setHint(user.getCity());
        emailTextView.setHint(user.getEmail());

    }

    @Override
    public void onClick(View view) {

    }
}
