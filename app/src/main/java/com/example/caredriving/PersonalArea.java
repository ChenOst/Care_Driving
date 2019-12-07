package com.example.caredriving;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PersonalArea extends AppCompatActivity implements View.OnClickListener {

    User user;
    String userType;
    String userUid;

    private ProgressDialog progressDialog;

    EditText fnameEditText;
    EditText lnameEditText;
    EditText ageEditText;
    EditText cityEditText;
    EditText emailEditText;

    // Teacher info
    EditText carTypeEditText;
    EditText carYearEditText;
    EditText experienceEditText;
    EditText teacher_transmissionEditText;
    EditText lessonPriceEditText;
    LinearLayout carTypeLayout;
    LinearLayout carYearLayout;
    LinearLayout experienceLayout;
    LinearLayout teacher_transmissionLayout;
    LinearLayout lessonPriceLayout;

    // Student info
    EditText greenFormEditText;
    EditText student_transmissionEditText;
    EditText theoryEditText;
    EditText teacherIdEditText;
    LinearLayout greenFormLayout;
    LinearLayout student_transmissionLayout;
    LinearLayout theoryLayout;
    LinearLayout teacherIdLayout;

    ArrayList<View> userViews;
    ArrayList<View> teacherViews;
    ArrayList<View> studentViews;
    ArrayList<View> teacherLayouts;
    ArrayList<View> studentLayouts;

    Button editBtn;
    Button saveBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_area);


        //take the user from login activity and put it in "user" variable
        user = (User) getIntent().getSerializableExtra("User");
        userType = getIntent().getStringExtra("type");
        userUid = getIntent().getStringExtra("Uid");


        userViews = new ArrayList<View>();
        teacherViews = new ArrayList<View>();
        studentViews = new ArrayList<View>();
        teacherLayouts = new ArrayList<View>();
        studentLayouts = new ArrayList<View>();


        fnameEditText = findViewById(R.id.fillFirstName);
        lnameEditText = findViewById(R.id.fillLastName);
        ageEditText = findViewById(R.id.fillAge);
        cityEditText = findViewById(R.id.fillCity);
        emailEditText = findViewById(R.id.fillemail);
        carTypeEditText = findViewById(R.id.fillcarType);
        carYearEditText = findViewById(R.id.fillcarYear);
        experienceEditText = findViewById(R.id.fillexperience);
        teacher_transmissionEditText = findViewById(R.id.fillteacher_transmission);
        lessonPriceEditText = findViewById(R.id.filllessonPrice);
        greenFormEditText = findViewById(R.id.fillgreenForm);
        student_transmissionEditText = findViewById(R.id.fillstudent_transmission);
        theoryEditText = findViewById(R.id.filltheory);
        teacherIdEditText = findViewById(R.id.fillteacherId);
        userViews.add(fnameEditText);
        userViews.add(lnameEditText);
        userViews.add(ageEditText);
        userViews.add(cityEditText);
        userViews.add(emailEditText);
        teacherViews.add(carTypeEditText);
        teacherViews.add(carYearEditText);
        teacherViews.add(experienceEditText);
        teacherViews.add(teacher_transmissionEditText);
        teacherViews.add(lessonPriceEditText);
        studentViews.add(greenFormEditText);
        studentViews.add(student_transmissionEditText);
        studentViews.add(theoryEditText);
        studentViews.add(teacherIdEditText);


        carTypeLayout = findViewById(R.id.carType);
        carYearLayout = findViewById(R.id.carYear);
        experienceLayout = findViewById(R.id.experience);
        teacher_transmissionLayout = findViewById(R.id.teacher_transmission);
        lessonPriceLayout = findViewById(R.id.lessonPrice);
        greenFormLayout = findViewById(R.id.greenForm);
        student_transmissionLayout = findViewById(R.id.student_transmission);
        theoryLayout = findViewById(R.id.theory);
        teacherIdLayout = findViewById(R.id.teacherId);
        teacherLayouts.add(carTypeLayout);
        teacherLayouts.add(carYearLayout);
        teacherLayouts.add(experienceLayout);
        teacherLayouts.add(teacher_transmissionLayout);
        teacherLayouts.add(lessonPriceLayout);
        studentLayouts.add(greenFormLayout);
        studentLayouts.add(student_transmissionLayout);
        studentLayouts.add(theoryLayout);
        studentLayouts.add(teacherIdLayout);


        editBtn = findViewById(R.id.editButton);
        saveBtn = findViewById(R.id.saveButton);

        progressDialog = new ProgressDialog(this);

        //Fill hint details in all fields according the "user" and "type" from intent
        fillHintDetails();

        editBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == editBtn)
            editButtonClicked();
        if(view == saveBtn)
            saveButtonClicked();
    }

    private void setTextViewsNotEditable(){
        for(View view: userViews){
            view.setEnabled(false);
        }
        if(userType.equals("teacher")){
            for(View view: teacherViews){
                view.setEnabled(false);
            }
        }
        if(userType.equals("student")){
            for(View view: studentViews){
                view.setEnabled(false);
            }
        }
    }

    private void setEditTextsEditable(){
        for(View view: userViews){
            if(view != emailEditText) {
                view.setEnabled(true);
            }
        }
        if(userType.equals("teacher")){
            for(View view: teacherViews){
                view.setEnabled(true);
            }
        }
        if(userType.equals("student")){
            for(View view: studentViews){
                view.setEnabled(true);
            }
        }
    }

    private void fillHintDetails(){
        // User need basic hint details
        fnameEditText.setHint(user.getFirstName());
        lnameEditText.setHint(user.getLastName());
        ageEditText.setHint(user.getAge());
        cityEditText.setHint(user.getCity());
        emailEditText.setHint(user.getEmail());

        // Teacher hint details
        if(userType.equals("teacher")){
            setStudentViewsGone();
            fillHintTeacherViews();
        }
        // Student hint details
        if(userType.equals("student")){
            setTeacherViewsGone();
            fillHintStudentViews();
        }
    }

    private void setStudentViewsGone(){
        for(View view : studentLayouts){
            view.setVisibility(View.GONE);
        }
    }

    private void setTeacherViewsGone(){
        for(View view : teacherLayouts){
            view.setVisibility(View.GONE);
        }
    }

    private void fillHintTeacherViews(){
        Teacher teacher = (Teacher) user;
        carTypeEditText.setHint(teacher.getCarType());
        carYearEditText.setHint(teacher.getCarYear());
        experienceEditText.setHint(teacher.getExperience());
        teacher_transmissionEditText.setHint(teacher.getTransmission());
        lessonPriceEditText.setHint(teacher.getLessonPrice());
    }

    private void fillHintStudentViews(){
        Student student = (Student) user;
        greenFormEditText.setHint(student.getGreenForm());
        student_transmissionEditText.setHint(student.getTransmission());
        theoryEditText.setHint(student.getTheory());
        teacherIdEditText.setHint(student.getTeacherId());
    }

    private void fillTextDetails(){
        // User need basic text details
        fnameEditText.setText(user.getFirstName());
        lnameEditText.setText(user.getLastName());
        ageEditText.setText(user.getAge());
        cityEditText.setText(user.getCity());
        emailEditText.setText(user.getEmail());

        // Teacher text details
        if(userType.equals("teacher")){
            fillTextTeacherViews();
        }
        // Student text details
        if(userType.equals("student")){
            fillTextStudentViews();
        }
    }

    private void fillTextTeacherViews(){
        Teacher teacher = (Teacher) user;
        carTypeEditText.setText(teacher.getCarType());
        carYearEditText.setText(teacher.getCarYear());
        experienceEditText.setText(teacher.getExperience());
        teacher_transmissionEditText.setText(teacher.getTransmission());
        lessonPriceEditText.setText(teacher.getLessonPrice());
    }

    private void fillTextStudentViews(){
        Student student = (Student) user;
        greenFormEditText.setText(student.getGreenForm());
        student_transmissionEditText.setText(student.getTransmission());
        theoryEditText.setText(student.getTheory());
        teacherIdEditText.setText(student.getTeacherId());
    }

    private void editButtonClicked(){
        setEditTextsEditable();
        fillTextDetails();
    }

    private void saveButtonClicked(){
        DatabaseReference userReference = FirebaseDatabase.getInstance().getReference().child("users").child(userUid).child("info");
        if(userType.equals("teacher")){
            userReference.child("firstName").setValue(fnameEditText.getText().toString());
            userReference.child("lastName").setValue(lnameEditText.getText().toString());
            userReference.child("age").setValue(ageEditText.getText().toString());
            userReference.child("city").setValue(cityEditText.getText().toString());
            userReference.child("carType").setValue(carTypeEditText.getText().toString());
            userReference.child("carYear").setValue(carYearEditText.getText().toString());
            userReference.child("experience").setValue(experienceEditText.getText().toString());
            userReference.child("lessonPrice").setValue(lessonPriceEditText.getText().toString());
            userReference.child("transmission").setValue(teacher_transmissionEditText.getText().toString());
            ///////////////////////////////////////////////
            ////////חסר שינוי של הUSER המקומי
        }
        if(userType.equals("student")){

        }
        setTextViewsNotEditable();
    }
}
