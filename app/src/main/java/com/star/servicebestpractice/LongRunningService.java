package com.star.servicebestpractice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import java.util.Date;


public class LongRunningService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("LongRunningService", "executed at " + new Date().toString());

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                long triggerAtTime = SystemClock.elapsedRealtime() + 10000;
                Intent i = new Intent(LongRunningService.this, AlarmReceiver.class);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(LongRunningService.this, 0, i, 0);

                alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);

                stopSelf();
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }
}
