package com.yiwen.java_view_other.rxjava;

import android.widget.Button;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {
    @BindingAdapter(value = {"viewModel", "userId"}, requireAll = false)
    public static void setUpdateUser(Button button, UserViewModel viewModel, Integer userId) {
        if (viewModel != null && userId != null) {

        }
    }
}
