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
//        lessons = new ArrayList<>();
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
}
