package com.example.caredriving;

import android.content.Context;
import android.content.Intent;

abstract class User extends Entity {

    private String firstName;
    private String lastName;
    private String age;
    private String city;

    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setAge(String age) {this.age = age;}
    public void setCity(String city) {this.city = city;}

    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getAge() {return age;}
    public String getCity() {return city;}

    public Intent getIntent(Context from, Class<?> to){
        Intent intent = new Intent(from, to);
        intent.putExtra("firstname", getFirstName());
        intent.putExtra("lastname", getLastName());
        intent.putExtra("age", getAge());
        intent.putExtra("city", getCity());
        return intent;
    }
}