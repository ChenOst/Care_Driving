package com.example.caredriving.ui.navigationBar.requests;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RequestsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RequestsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is requests fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}