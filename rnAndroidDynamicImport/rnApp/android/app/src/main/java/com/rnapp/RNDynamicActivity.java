package com.rnapp;

import android.app.Activity;

import androidx.annotation.Nullable;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;

import java.util.Arrays;
import java.util.List;

//RNDynamicActivity
public class RNDynamicActivity extends ReactActivity {
    public static String bundleName;

    @Override
    protected ReactActivityDelegate createReactActivityDelegate() {

        DispatchDelegate delegate = new DispatchDelegate(this, bundleName);

        return delegate;
    }
}

