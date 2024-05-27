package io.github.haoyiwen.react_native_container;

import android.app.Application;
import android.content.Context;

import com.facebook.hermes.reactexecutor.HermesExecutorFactory;
import com.facebook.react.BuildConfig;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.shell.MainReactPackage;
import com.reactnativecommunity.webview.RNCWebViewPackage;
import com.swmansion.rnscreens.RNScreensPackage;
import com.th3rdwave.safeareacontext.SafeAreaContextPackage;
import com.facebook.soloader.SoLoader;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

public class MyReactNativeApplication extends Application {
    private ConcurrentHashMap<String, ReactInstanceManager> mReactInstanceManagers = new ConcurrentHashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, false);
        initializeFlipper(this);
    }

    public synchronized ReactInstanceManager createReactInstanceManager(String bundlePath) {
        ReactInstanceManager instanceManager = mReactInstanceManagers.get(bundlePath);
        if (instanceManager == null) {
            instanceManager = ReactInstanceManager.builder()
                    .setApplication(this)
                    .setBundleAssetName(bundlePath)
                    .setJSMainModulePath("index")
                    .addPackages(Arrays.asList(
                            new MyReactPackage(),
                            new MainReactPackage(),
                            new RNCWebViewPackage(),
                            new RNScreensPackage(),
                            new SafeAreaContextPackage()))
                    .setUseDeveloperSupport(BuildConfig.DEBUG)
                    .setInitialLifecycleState(LifecycleState.BEFORE_CREATE)
                    .setJavaScriptExecutorFactory(new HermesExecutorFactory())
                    .build();
            instanceManager.createReactContextInBackground();
            mReactInstanceManagers.put(bundlePath, instanceManager);
        }
        return instanceManager;
    }

    public static void initializeFlipper(Context context) {
        if (BuildConfig.DEBUG) {
            try {
                Class<?> aClass = Class.forName("io.github.haoyiwen.react_native_container.ReactNativeFlipper");
                aClass.getMethod("initializeFlipper", Context.class, ReactInstanceManager.class).invoke(null, context);
            } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                     InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}