package com.example.caredriving.ui.searchTeachers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caredriving.MainActivity;
import com.example.caredriving.R;
import com.google.android.material.navigation.NavigationView;

public class ContactTeacherActivity extends AppCompatActivity {

    private static final String TAG = "ContactTeacherActivity";
    private TextView teachersFirstName;
    private TextView teachersLastName;
    private TextView teacherslocations;
    private TextView teachersPhoneNumber;
    private TextView lessonPrice;
    private TextView gearType;
    private ImageView imgPhone;
    private static String number = "052-8559958"; // Checks if the number is valid
    private static final int REQUEST_CALL = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_teacher);
        getSupportActionBar().setTitle("Contact Teacher");
        Log.d(TAG, "ContactTeacherActivity: started.");

        teachersFirstName = findViewById(R.id.tvDetailsTeachersFirstName);
        teachersLastName = findViewById(R.id.tvDetailsTeachersLastName);
        teacherslocations = findViewById(R.id.tvDetailsTeachersLoction);
        gearType = findViewById(R.id.tvDetailsTeachersGear);
        lessonPrice = findViewById(R.id.tvDetailsLessonPrice);
        teachersPhoneNumber = findViewById(R.id.tvDetailsTeachersPhone);
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
        if(getIntent().hasExtra("TeachersFirstName")){
            Log.d(TAG, "getIncomingIntent: get and set incoming intents.");
            String firstName = getIntent().getStringExtra("TeachersFirstName");
            teachersFirstName.setText(firstName);
        }
    }

    // Call to the teacher
    private void makePhoneCall(){
        Log.d(TAG, "makePhoneCall: call the teacher " + teachersFirstName + " " + teachersLastName);
        // Checks if the app have permission to make a phone call
        if(ContextCompat.checkSelfPermission(ContactTeacherActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(ContactTeacherActivity.this,
                    new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        }
        else{
            if(number.contains("-")) {
                number.replace("-", "");
            }

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
