package com.example.caredriving;

import android.content.Context;
import android.content.Intent;

public class Student extends User {


    public Student(){
        super();
    }

    public Student(String firstName, String lastName, String age, String city, String email){
        super(firstName, lastName, age, city, email);
    }

    @Override
    public void sideBarVisibility(){}

    public Intent getIntent(Context from, Class<?> to){
        return super.getIntent(from, to);
    }
}
