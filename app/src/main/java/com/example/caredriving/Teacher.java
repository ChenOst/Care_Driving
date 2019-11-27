package com.example.caredriving;

import android.content.Context;
import android.content.Intent;

public class Teacher extends User {

    private String carType;
    private String carYear;
    private String workExpirience;
    private String transmissionType; //גיר

    public Teacher(){
        super();
    }

    @Override
    public void sideBarVisibility() {}

    public void setCarBrand(String carBrand) {this.carType = carBrand;}
    public void setCarYear(String carYear) {this.carYear = carYear;}
    public void setWorkExpirience(String workExpirience) {this.workExpirience = workExpirience;}
    public void setTransmissionType(String transmissionType) {this.transmissionType = transmissionType;}

    public Intent getIntent(Context from, Class<?> to) {
        return super.getIntent(from, to);
    }
}
