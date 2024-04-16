package com.yiwen.java_view_other.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private MutableLiveData<Integer> number;

    public LiveData<Integer> getNumber() {
        if (number == null) {
            number = new MutableLiveData<>();
            ;
            number.setValue(10);
        }
        return number;
    }

    public void incrementNumber() {
        if (number != null) {
            number.setValue(number.getValue() + 1);
        }
    }
}
