package com.example.caredriving.firebase.model.dataObject.validation;

import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Validation {

    ArrayList<String> errors;

    public Validation() {
        errors = new ArrayList<>();
    }

    public void checkFirstName(String firstName) {
        if (firstName.length() == 0) {
            errors.add("Please enter your name");
        }

        for (int i = 0; i < firstName.length(); i++) {
            if (firstName.charAt(i) >= '0' && firstName.charAt(i) <= '9') {
                errors.add("Name is incorrect");
                break;
            }
        }
    }

    public void checkLastName(String firstName) {
        if (firstName.length() == 0) {
            errors.add("Please enter your last name");
        }

        for (int i = 0; i < firstName.length(); i++) {
            if (firstName.charAt(i) >= '0' && firstName.charAt(i) <= '9') {
                errors.add("Last name is incorrect");
                break;
            }
        }
    }

    public void checkAge(String sAge) {

        if (sAge.isEmpty()) {
            errors.add("Please enter your age");
        } else {
            try {
                int age = Integer.parseInt(sAge);
                if (age <= 1 || age >= 100) {
                    errors.add("Age is incorrect");
                }
            } catch (Exception e) {
                errors.add("Age is incorrect");
            }
        }
    }

    public void checkPrice(String sPrice) {
        try {
            int price = Integer.parseInt(sPrice);
            if (price <= 0) {
                errors.add("Price is incorrect");
            }
        } catch (Exception e) {
            errors.add("Price is incorrect");
        }
    }

    public ArrayList<String> getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        return errors.size() != 0;
    }

    public void checkNumber(String number) {
        if (number.length() != 7) {
            errors.add("Number is incorrect");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void checkLessonDate(String date) {
        String[] currentDate = java.time.LocalDate.now().toString().split("-");
        String[] checkDate = date.split("/");

        if (Integer.parseInt(currentDate[0]) <= Integer.parseInt(checkDate[2])) {
            if (Integer.parseInt(currentDate[1]) <= Integer.parseInt(checkDate[1])) {
                if (Integer.parseInt(currentDate[2]) > Integer.parseInt(checkDate[0])) {
                    errors.add("Date is incorrect");
                }
            } else {
                errors.add("Date is incorrect");
            }
        } else {
            errors.add("Date is incorrect");
        }
    }

    public void checkTime(String time){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String [] fullCurrentTime = formatter.format(date).toString().split(" ");
        String [] currentTime = fullCurrentTime[1].split(":");
        String [] checkedTime = time.split(":");

        if(Integer.parseInt(currentTime[0]) <= Integer.parseInt(checkedTime[0])){
            if(Integer.parseInt(currentTime[1]) > Integer.parseInt(checkedTime[1])){
                errors.add("Time is incorrect");
            }
        } else {
            errors.add("Time is incorrect");
        }
    }

    public void clearErrors(){
        errors.clear();
    }
}
