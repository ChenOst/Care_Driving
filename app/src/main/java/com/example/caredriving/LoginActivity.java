package com.example.caredriving;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caredriving.firebase.model.FirebaseDBEntity;
import com.example.caredriving.firebase.model.FirebaseDBUser;
import com.example.caredriving.firebase.model.dataObject.StudentObj;
import com.example.caredriving.firebase.model.dataObject.TeacherObj;
import com.example.caredriving.firebase.model.dataObject.UserObj;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private EditText etLoginEmail;
    private EditText etLoginPassword;
    private Button btnLogin;
    private TextView tvRegistration;
    private ProgressDialog progressDialog;

    private UserObj user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        etLoginEmail = findViewById(R.id.etLoginEmail);
        etLoginPassword = findViewById(R.id.etLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegistration = findViewById(R.id.tvRegister);

        progressDialog = new ProgressDialog(this);

        // get the login status of the app - user loged in or not
        firebaseAuth = FirebaseAuth.getInstance();


        btnLogin.setOnClickListener(this);
        tvRegistration.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnLogin){
            loginUser();

        }
        if (view == tvRegistration){
            // Go to registration activity
            Intent intent = new Intent(this, RegistrationActivity.class);
            startActivity(intent);
        }
    }

    private void loginUser() {
        String email = etLoginEmail.getText().toString().trim();
        String password = etLoginPassword.getText().toString().trim();

        if(email.length() == 0){
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(password.length() == 0){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Login user...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
//                            findUser();
                        } else {
                            Toast.makeText(LoginActivity.this,
                                    "Login failed", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            progressDialog.cancel();
                        }
                    }
                });
    }

    private void findUser(){
        final FirebaseDBUser fb_user = new FirebaseDBUser();
        fb_user.getUserRefFromDB().addListenerForSingleValueEvent(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Find type of FirebaseUser(TeacherObj/StudentObj)
                String usertype = dataSnapshot.child("type").getValue().toString();
                // Create local instance of UserObj(TeacherObj/StudentObj)
                user = dataSnapshot.getValue(FirebaseDBEntity.class).getUserObj();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("UserObj", user);
                intent.putExtra("type", usertype);
                intent.putExtra("Uid", fb_user.getMyUid());
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private TeacherObj fillDataForTeacherUser(DataSnapshot dataSnapshot){
        String firstName = dataSnapshot.child("info").child("firstName").getValue().toString();
        String lastName = dataSnapshot.child("info").child("lastName").getValue().toString();
        String age = dataSnapshot.child("info").child("age").getValue().toString();
        String city = dataSnapshot.child("info").child("city").getValue().toString();
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        String phoneNumber = dataSnapshot.child("info").child("phoneNumber").getValue().toString();
//        String phoneNumber = "052-******";
        String lessonPrice = dataSnapshot.child("info").child("lessonPrice").getValue().toString();
        String experience = dataSnapshot.child("info").child("experience").getValue().toString();
        String carType = dataSnapshot.child("info").child("carType").getValue().toString();
        String carYear = dataSnapshot.child("info").child("carYear").getValue().toString();
        String transmission = dataSnapshot.child("info").child("transmission").getValue().toString();
        return new TeacherObj(firstName, lastName, age, city, email, phoneNumber,
                carType, carYear, experience, transmission, lessonPrice);
    }

    private StudentObj fillDataForStudentUser(DataSnapshot dataSnapshot){
        String firstName = dataSnapshot.child("info").child("firstName").getValue().toString();
        String lastName = dataSnapshot.child("info").child("lastName").getValue().toString();
        String age = dataSnapshot.child("info").child("age").getValue().toString();
        String city = dataSnapshot.child("info").child("city").getValue().toString();
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        String phoneNumber = dataSnapshot.child("info").child("phoneNumber").getValue().toString();
//        String phoneNumber = "052-******";
        String greenForm = dataSnapshot.child("info").child("greenForm").getValue().toString();
        String transmission = dataSnapshot.child("info").child("transmission").getValue().toString();
        String theory = dataSnapshot.child("info").child("theory").getValue().toString();
        String teacherId = dataSnapshot.child("info").child("teacherId").getValue().toString();
        return new StudentObj(firstName, lastName, age, city, email, phoneNumber, greenForm,
                transmission, theory, teacherId);
    }
}
