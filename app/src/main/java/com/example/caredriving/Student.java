package com.example.caredriving;

import android.content.Context;
import android.content.Intent;

public class Student extends User {

    @Override
    public void sideBarVisibility(){}

    public Intent getIntent(Context from, Class<?> to){
        return super.getIntent(from, to);
    }
}
