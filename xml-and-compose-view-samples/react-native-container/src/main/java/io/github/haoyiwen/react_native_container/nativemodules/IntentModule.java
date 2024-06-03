package io.github.haoyiwen.react_native_container.nativemodules;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import io.github.haoyiwen.react_native_container.ReactNativeActivity;

public class IntentModule extends ReactContextBaseJavaModule {
    final static String name = "IntentModule";

    @NonNull
    @Override
    public String getName() {
        return name;
    }

    public IntentModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @ReactMethod
    public void startActivityFromJS(String name, String param) {
        try {
            Activity currentActivity = getCurrentActivity();
            if (null != currentActivity) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ReactMethod
    public void startActivityFromJS(String name, String path, Boolean isRN) {
        try {
            Activity currentActivity = getCurrentActivity();
            if (null != currentActivity) {
                Intent intent = null;
                if(isRN) {
                    intent = ReactNativeActivity.createIntent(currentActivity, name, path, null);
                } else {
                    Class toActivity = Class.forName(name);
                    intent = new Intent(currentActivity, toActivity);
                    intent.putExtra("param", path);
                    currentActivity.startActivity(intent);
                }
                currentActivity.startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ReactMethod
    public void dataToJS(Callback successCallback, Callback errorCallback) {
        try {
            Activity currentActivity = getCurrentActivity();
            if (null != currentActivity) {
                String data = currentActivity.getIntent().getStringExtra("url");
                successCallback.invoke(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorCallback.invoke(e.getMessage());
        }
    }
}
