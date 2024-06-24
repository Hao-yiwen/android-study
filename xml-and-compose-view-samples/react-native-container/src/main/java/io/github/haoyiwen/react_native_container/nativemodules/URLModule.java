package io.github.haoyiwen.react_native_container.nativemodules;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import io.github.haoyiwen.react_native_container.ReactNativeActivity;
import io.github.haoyiwen.test.core.router.URLRouter;

public class URLModule extends ReactContextBaseJavaModule {
    final static String name = "URL";

    @NonNull
    @Override
    public String getName() {
        return name;
    }

    public URLModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @ReactMethod
    public void openURL(String url) {
        try {
            Activity currentActivity = getCurrentActivity();
            URLRouter.openURL(currentActivity, url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
