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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authListener;
    private EditText etLoginEmail;
    private EditText etLoginPassword;
    private Button btnLogin;
    private TextView tvRegistration;
    private ProgressDialog progressDialog;

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


        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null){ //User loged in

                    //////////////////////// its a test/////////////////////////////
//                    String email = user.getEmail();
//                    Student curruser = new Student("Chen", "Ostrovski", "22", "Tel-Aviv", email);
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    intent.putExtra("User", curruser);
//                    startActivity(intent);
//                    finish();
                }
            }
        };

        btnLogin.setOnClickListener(this);
        tvRegistration.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.signOut();
        firebaseAuth.removeAuthStateListener(authListener);
    }

    @Override
    public void onClick(View view) {
        if (view == btnLogin){
            loginUser();

        }
        if (view == tvRegistration){
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
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this,
                                    "Login failed", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            progressDialog.cancel();
                        }
                    }
                });

    }
}
