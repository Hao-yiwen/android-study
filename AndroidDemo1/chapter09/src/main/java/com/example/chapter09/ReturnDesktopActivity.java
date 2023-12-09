package com.example.chapter09;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PictureInPictureParams;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Picture;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Rational;

public class ReturnDesktopActivity extends AppCompatActivity {

    private DesktopReceiver desktopReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_desktop);
        desktopReceiver = new DesktopReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        registerReceiver(desktopReceiver, filter, RECEIVER_EXPORTED);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(desktopReceiver);
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
        if (isInPictureInPictureMode) {
            Log.d("ning", "进入画中画模式");
        } else {
            Log.d("ning", "退出画中画模式");
        }
    }

    private class DesktopReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra("reason");
                if (!TextUtils.isEmpty(reason) && (reason.equals("homekey") || reason.equals("recentapps"))) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && !isInPictureInPictureMode()) {
                        PictureInPictureParams.Builder builder = new PictureInPictureParams.Builder();
                        Rational ratio = new Rational(10, 5);
                        builder.setAspectRatio(ratio);
                        enterPictureInPictureMode(builder.build());
                    }
                }
            }
        }
    }
}