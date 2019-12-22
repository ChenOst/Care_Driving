package com.example.caredriving;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Entity;
import android.content.Intent;
import android.os.Bundle;

import com.example.caredriving.firebase.model.FirebaseDBEntity;
import com.example.caredriving.firebase.model.FirebaseDBUser;
import com.example.caredriving.firebase.model.dataObject.StudentObj;
import com.example.caredriving.firebase.model.dataObject.TeacherObj;
import com.example.caredriving.firebase.model.dataObject.UserObj;
import com.example.caredriving.ui.navigationBar.requests.RequestsFragment;
import com.example.caredriving.ui.navigationBar.searchTeachers.SearchTeachersFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener,
        View.OnClickListener {

    private AppBarConfiguration mAppBarConfiguration;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private ImageButton imgButton;
    private View header;
    private TextView nameHeader;
    private TextView emailHeader;

    public UserObj user;
    public FirebaseDBUser fb_user;

    private int day, month, year, hour, minute;
    private int dayFinal, monthFinal, yearFinal, hourFinal, minuteFinal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Calendar calendar = Calendar.getInstance();

                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new
                        DatePickerDialog(MainActivity.this, MainActivity.this, year, month, day);

                datePickerDialog.show();
            }
        });

        navigationView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer_layout);

        fb_user = new FirebaseDBUser();
        findUser();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_search_teachers) {
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.containerId, SearchTeachersFragment.newInstance()).commit();
        }

        if (id == R.id.nav_requests){
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.containerId, RequestsFragment.newInstance()).commit();
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("UserObj", user);
//            RequestsFragment fragobj = new RequestsFragment();
//            fragobj.setArguments(bundle);
        }
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        if(view == imgButton)
            headerImageButtonPressed();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        yearFinal = i;
        monthFinal = i1 + 1;
        dayFinal = i2;

        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, MainActivity.this,
                hour, minute, true);
        timePickerDialog.setTitle("Set the date for next lesson");
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        hourFinal = i;
        minuteFinal = i1;
    }

    private void findUser(){
        fb_user.getUserRefFromDB().addListenerForSingleValueEvent(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Create local instance of UserObj(TeacherObj/StudentObj) from Entity object(HashMap, String)
                FirebaseDBEntity entity = dataSnapshot.getValue(FirebaseDBEntity.class);
                assert entity != null;
                user = entity.getUserObj();
                if (user instanceof TeacherObj){
                    setTeachersNavigationView();
                }
                else if (user instanceof StudentObj){
                    setStudentsNavigationView();
                }
                displayHeaderDetailsToUser();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void headerImageButtonPressed(){
        //What happen when click on image view in navigation bar
        Intent intent = new Intent(MainActivity.this, PersonalAreaActivity.class);
        intent.putExtra("UserObj", user);
        startActivity(intent);
    }

    private void displayHeaderDetailsToUser(){
        String hello = "Welcome, "+user.getFirstName()+" !";
        nameHeader.setText(hello);
        emailHeader.setText(user.getEmail());
    }

    // Add to the NavigationView restrictions according to the Teacher object
    private void setTeachersNavigationView(){
        navigationView.getMenu().findItem(R.id.nav_search_teachers).setVisible(false);
        setNavigationView();
    }

    // Add to the NavigationView restrictions according to the Student object
    private void setStudentsNavigationView(){
       setNavigationView();
    }

    // Set the navigation view
    private void setNavigationView(){
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_search_teachers, R.id.nav_requests,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send, R.id.imageButton)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        header = navigationView.getHeaderView(0);
        nameHeader = header.findViewById(R.id.nameHeader);
        emailHeader = header.findViewById(R.id.emailHeader);
        imgButton = header.findViewById(R.id.imageButton);
        imgButton.setOnClickListener(this);
    }
}
