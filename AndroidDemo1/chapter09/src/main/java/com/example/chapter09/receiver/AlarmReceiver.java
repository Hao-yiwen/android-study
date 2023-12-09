package com.example.chapter09.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

    private static final String ALARM_ACTION = "com.example.chapter09.ALARM_ACTION";
    private final Context mContent;

    public AlarmReceiver(Context context) {
        this.mContent = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            Log.d("ning", "收到闹钟广播");
        }
    }

    public void sendAlarm() {
        Intent intent = new Intent(ALARM_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContent, 0, intent, PendingIntent.FLAG_MUTABLE);
        AlarmManager alarmManager = (AlarmManager) mContent.getSystemService(Context.ALARM_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, 1000, pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, 1000, pendingIntent);
        }
    }


}
