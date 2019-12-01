package com.example.caredriving.ui.searchTeachers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.caredriving.R;

public class ContactTeacherActivity extends AppCompatActivity {

    private static final String TAG = "ContactTeacherActivity";
    private static TextView name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_teacher);
        Log.d(TAG, "OnCreate: started.");
        name = findViewById(R.id.tvDetailsTeachersName);
        getIncomingIntent();
    }

    // Get the incoming intents
    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        // Checks if the intent have any extras before trying to get the extras
        if(getIntent().hasExtra("TeachesName")){
            Log.d(TAG, "getIncomingIntent: get and set incoming intents.");
            String TeachesName = getIntent().getStringExtra("TeachesName");

            name.setText(TeachesName);
        }
    }

}
