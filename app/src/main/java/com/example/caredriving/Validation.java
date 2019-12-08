package com.example.caredriving;

import android.widget.Toast;

import java.util.ArrayList;

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

    public void checkNumber(String number){
        if(number.length() != 7){
            errors.add("Number is incorrect");
        }
    }
}
