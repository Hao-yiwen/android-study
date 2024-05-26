package io.github.haoyiwen.react_native_container;

import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.facebook.react.BuildConfig;
import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.defaults.DefaultReactActivityDelegate;
import com.facebook.react.shell.MainReactPackage;
//import com.swmansion.rnscreens.RNScreensPackage;

import java.util.Arrays;
import java.util.List;


public class ReactNativeActivity extends ReactActivity {
    private ReactInstanceManager mReactInstanceManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    protected String getMainComponentName() {
        return "HelloWorld";
    }


    @Override
    protected ReactActivityDelegate createReactActivityDelegate() {
        return new DefaultReactActivityDelegate(this, getMainComponentName(), false) {
            @Nullable
            @Override
            protected Bundle getLaunchOptions() {
                Bundle initialProperties = new Bundle();
                initialProperties.putString("initParam", "value");
                return initialProperties;
            }

            @Override
            protected ReactNativeHost getReactNativeHost() {

                return super.getReactNativeHost();
            }
        };
    }

    @Override
    public AssetManager getAssets() {
        return super.getAssets();
    }
}
