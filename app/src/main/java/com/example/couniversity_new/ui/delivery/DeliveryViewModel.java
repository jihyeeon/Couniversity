package com.example.couniversity_new.ui.delivery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DeliveryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DeliveryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is delivery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}