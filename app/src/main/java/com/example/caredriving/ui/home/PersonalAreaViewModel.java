package com.example.caredriving.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PersonalAreaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PersonalAreaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is personal area fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}