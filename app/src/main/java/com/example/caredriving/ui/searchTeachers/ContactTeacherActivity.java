package com.example.caredriving.ui.searchTeachers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caredriving.R;

public class ContactTeacherActivity extends AppCompatActivity {

    private static final String TAG = "ContactTeacherActivity";
    private static TextView name; //change later
    private TextView phone; //change later
    private ImageView imgPhone;
    private String number = "+972528559958";
    private static final int REQUEST_CALL = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_teacher);
        Log.d(TAG, "ContactTeacherActivity: started.");

        name = findViewById(R.id.tvDetailsTeachersName);
        phone = findViewById(R.id.tvDetailsTeachersPhone);
        phone.setText(number);
        imgPhone = findViewById(R.id.imgPhone);
        getIncomingIntent();

        // Call to the teacher by clicking the phone image
        imgPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });
    }

    // Get the incoming intents
    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        // Checks if the intent have any extras before trying to get the extras
        if(getIntent().hasExtra("TeachersName")){
            Log.d(TAG, "getIncomingIntent: get and set incoming intents.");
            String TeachersName = getIntent().getStringExtra("TeachersName");
            name.setText(TeachersName);
        }
    }

    // Call to the teacher
    private void makePhoneCall(){
        Log.d(TAG, "makePhoneCall: call the teacher " + name);
        // Checks if the app have permission to make a phone call
        if(ContextCompat.checkSelfPermission(ContactTeacherActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(ContactTeacherActivity.this,
                    new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        }
        else{
            String dial = "tel:" + number;
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(dial));
            startActivity(intent);
        }

    }

    // Ask for permission to make the phone call
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }
            else{
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
