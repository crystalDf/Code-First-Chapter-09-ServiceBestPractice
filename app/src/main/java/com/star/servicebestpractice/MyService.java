package com.star.servicebestpractice;


import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    public static final int REQUEST_CODE = 0;
    public static final int NOTIFICATION_ID = 1;

    private Binder mBinder = new MyBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                REQUEST_CODE,
                new Intent(this, MainActivity.class),
                0);

        Notification notification = new Notification.Builder(this)
                .setContentIntent(pendingIntent)
                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setContentInfo("info")
                .setAutoCancel(true)
                .setSound(Uri.parse(
                        "file:///storage/emulated/0/BaiduNetdisk/" +
                                "Taylor Swift-Safe And Sound.mp3"))
                .setVibrate(new long[]{0, 1000, 1000, 1000})
                .setLights(Color.GREEN, 1000, 1000)
                .build();

        startForeground(NOTIFICATION_ID, notification);

        Log.d("MyService", "onCreate executed");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService", "onStartCommand executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService", "onDestroy executed");
    }

    class MyBinder extends Binder {

        public Service getService() {
            return MyService.this;
        }
    }

    public void startDownload() {
        Log.d("MyService", "startDownload executed");
    }

    public int getProgress() {
        Log.d("MyService", "getProgress executed");
        return 0;
    }
}
