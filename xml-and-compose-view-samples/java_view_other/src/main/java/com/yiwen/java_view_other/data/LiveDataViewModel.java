package com.yiwen.java_view_other.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yiwen.java_view_other.model.Car;

public class LiveDataViewModel extends ViewModel {
    private MutableLiveData<Car> car;

    public LiveData<Car> getCar() {
        if (car == null) {
            car = new MutableLiveData<>();
            Car tmp = new Car();
            tmp.setNumber(1);
            tmp.setTextData("1");
            car.setValue(tmp);
        }
        return car;
    }

    public void update(Car tmp) {
        car.setValue(tmp);
    }

}
