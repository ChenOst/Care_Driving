package com.example.caredriving;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

public class Teacher extends User {

    private String carType;
    private String carYear;
    private String experience;
    private String transmission; //גיר
    private String lessonPrice;

//    private ArrayList<String> students;

    public Teacher(){
        super();
//        students = new ArrayList<>();
    }

    @Override
    public void sideBarVisibility() {}

    public void setCarBrand(String carBrand) {this.carType = carBrand;}
    public void setCarYear(String carYear) {this.carYear = carYear;}
    public void setExperience(String experience) {this.experience = experience;}
    public void setTransmission(String transmission) {this.transmission = transmission;}
    public void setLessonPrice(String price) {this.lessonPrice = price;}

//    public void loadStudents(){}
//    public void addStudent(String studentId){}

    public Intent getIntent(Context from, Class<?> to) {
        return super.getIntent(from, to);
    }

    public String getCarType() {
        return carType;
    }

    public String getCarYear() {
        return carYear;
    }

    public String getExperience() {
        return experience;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getLessonPrice() {
        return lessonPrice;
    }
}
