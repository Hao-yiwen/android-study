package com.yiwen.java_view_other.databinding;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DataViewModel extends ViewModel {
    // 使用泛型定义 MutableLiveData
    private MutableLiveData<String> _name = new MutableLiveData<>("yiwen");
    private MutableLiveData<String> _email = new MutableLiveData<>("yiwen@qq.com");

    // 将 MutableLiveData 转换为 LiveData，确保封装性
    public LiveData<String> name = _name;
    public LiveData<String> email = _email;

    public LiveData<String> getName() {
        return name;
    }

    public void setName(String name) {
        _name.setValue(name);
    }

    public LiveData<String> getEmail() {
        return email;
    }

    public void setEmail(String email) {
        _email.setValue(email);
    }

    public void update(){
        setName("test");
        setEmail("test@qq.com");
    }

}
