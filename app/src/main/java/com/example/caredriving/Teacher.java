package com.example.caredriving;

import android.content.Context;
import android.content.Intent;

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

    public Teacher(String firstName, String lastName,  String age, String city, String email, String phoneNumber,
                   String carType, String carYear, String experience, String transmission, String lessonPrice){
        super(firstName, lastName, age, city, email, phoneNumber);
        this.carType = carType;
        this.carYear = carYear;
        this.experience = experience;
        this.transmission = transmission;
        this.lessonPrice = lessonPrice;
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

    @Override
    public String toString(){
        return getFirstName() + " " + getLastName();
    }
}
