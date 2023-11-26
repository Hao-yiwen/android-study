package com.example.chapter05;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.chapter05.util.ViewUtil;

public class HideTextWatcher implements TextWatcher {
    private final Activity mcActivity;
    private EditText mEditText; // 声明一个编辑框对象
    private int maxLength; // 声明一个最大长度


    public HideTextWatcher(EditText etPhone, int maxLength, Activity mActivity) {
        this.mEditText = etPhone;
        this.maxLength = maxLength;
        this.mcActivity = mActivity;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    // 在编辑框输入文本变化后触发
    @Override
    public void afterTextChanged(Editable s) {
        String str = s.toString();
        if(str.length() == maxLength){
            ViewUtil.HideOneInputMethod(mcActivity, mEditText);
        }
    }
}
