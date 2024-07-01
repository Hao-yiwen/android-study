package io.github.haoyiwen.test.core.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

public class BaseApp extends Application {

    private static Context mContext;

    private static Thread mMainThread;

    private static Handler mHandler;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // 对全局对象赋值
        mContext = getApplicationContext();
        mMainThread = Thread.currentThread();
        mHandler = new Handler();
    }

    public static void restart(){
        Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(intent);
    }

    public static Context getContext() {
        return mContext;
    }

    public static void setContext(Context mContext) {
        BaseApp.mContext = mContext;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static void setMainThread(Thread mMainThread) {
        BaseApp.mMainThread = mMainThread;
    }

    public static Handler getMainHandler() {
        return mHandler;
    }

    public static void setMainHandler(Handler mHandler) {
        BaseApp.mHandler = mHandler;
    }
}
