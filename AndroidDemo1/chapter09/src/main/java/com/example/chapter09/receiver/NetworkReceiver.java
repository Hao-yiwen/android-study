package com.example.chapter09.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.util.Log;

public class NetworkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            NetworkInfo networkInfo = intent.getParcelableExtra("networkInfo");
            networkInfo.getTypeName();
            String text = String.format("网络发生变化，类型：%s，名称：%s",
                    networkInfo.getTypeName(), networkInfo.getExtraInfo());
            Log.d("NetworkReceiver", text);
        }
    }
}
