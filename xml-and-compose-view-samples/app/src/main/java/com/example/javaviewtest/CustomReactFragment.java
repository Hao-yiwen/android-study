package com.example.javaviewtest;

import androidx.annotation.Nullable;

import com.facebook.react.BuildConfig;
import com.facebook.react.ReactFragment;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;

import java.util.Arrays;
import java.util.List;

import io.github.haoyiwen.react_native_container.MyReactNativeApplication;

public class CustomReactFragment extends ReactFragment {
    final ReactNativeHost mReactNativeHost = new ReactNativeHost(this.getActivity().getApplication()) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage()
            );
        }

        @Override
        protected String getJSMainModuleName() {
            return "index"; // 指定入口文件为 index.android
        }

        @Nullable
        @Override
        protected String getBundleAssetName() {
            return "home.android.bundle";
        }
    };

    @Override
    protected ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }
}
