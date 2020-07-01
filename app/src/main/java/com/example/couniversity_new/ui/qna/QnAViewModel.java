package com.example.couniversity_new.ui.qna;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QnAViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public QnAViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is qna fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}