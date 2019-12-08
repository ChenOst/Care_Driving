package com.example.caredriving;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

public class Student extends User {

    private String greenForm;
    private String transmission;
    private String theory;
    private String teacherId;

//    private ArrayList<Lesson> lessons;

    public Student(){
        super();
    }

    public Student(String firstName, String lastName, String age, String city, String email, String phoneNumber,
                   String greenForm, String transmission, String theory, String teacherId) {
        super(firstName, lastName, age, city, email, phoneNumber);
        this.greenForm = greenForm;
        this.transmission = transmission;
        this.theory = theory;
        this.teacherId = teacherId;
    }

    @Override
    public void sideBarVisibility(){}

    public Intent getIntent(Context from, Class<?> to){
        return super.getIntent(from, to);
    }

    public void setGreenForm(String greenForm) {
        this.greenForm = greenForm;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public void setTheory(String theory) {
        this.theory = theory;
    }

    public String getGreenForm() {
        return greenForm;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getTheory() {
        return theory;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherId() {
        return teacherId;
    }

}
