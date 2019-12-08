package com.example.caredriving;

import android.content.Context;
import android.content.Intent;

import java.io.Serializable;

abstract class User extends Entity implements Serializable {

    private String firstName;
    private String lastName;
    private String age;
    private String city;
    private String email;
    private String phoneNumber;

    public User(){}

    public User(String firstName, String lastName, String age, String city, String email, String phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.city = city;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setAge(String age) {this.age = age;}
    public void setCity(String city) {this.city = city;}
    public void setEmail(String email){this.email = email;}
    public void setPhoneNumber(String phoneNumber){this.phoneNumber = phoneNumber;}

    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getAge() {return age;}
    public String getCity() {return city;}
    public String getEmail() {return email;}
    public String getPhoneNumber() {return phoneNumber;}

    public Intent getIntent(Context from, Class<?> to){
        Intent intent = new Intent(from, to);
        intent.putExtra("firstname", getFirstName());
        intent.putExtra("lastname", getLastName());
        intent.putExtra("age", getAge());
        intent.putExtra("city", getCity());
        return intent;
    }
}