package com.example.caredriving;

public class Teacher extends User {

    private String carType;
    private String carYear;
    private String workExpirience;
    private String transmissionType; //גיר

    @Override
    public void sideBarVisibility() {}

    public void setCarBrand(String carBrand) {this.carType = carBrand;}
    public void setCarYear(String carYear) {this.carYear = carYear;}
    public void setWorkExpirience(String workExpirience) {this.workExpirience = workExpirience;}
    public void setTransmissionType(String transmissionType) {this.transmissionType = transmissionType;}
}
