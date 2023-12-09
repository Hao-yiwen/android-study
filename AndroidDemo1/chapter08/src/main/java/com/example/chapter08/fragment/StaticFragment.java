package com.example.chapter08.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chapter08.R;
public class StaticFragment extends Fragment {

    private static final String TAG = "fragment";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "StaticFragment onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "StaticFragment onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "StaticFragment onCreateView");
        return inflater.inflate(R.layout.fragment_static, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "StaticFragment onStart");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "StaticFragment onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "StaticFragment onResume");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG,"StaticFragment onViewCreated");
    }
}