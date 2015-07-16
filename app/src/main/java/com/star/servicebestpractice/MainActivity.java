package com.star.servicebestpractice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button mStartServiceButton;
    private Button mStopServiceButton;

    private Button mBindServiceButton;
    private Button mUnbindServiceButton;

    private Button mStartIntentServiceButton;

    private Button mStartLongRunServiceButton;

    private MyService.MyBinder mMyBinder;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMyBinder = (MyService.MyBinder) service;

            MyService myService = (MyService) mMyBinder.getService();

            myService.startDownload();
            myService.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStartServiceButton = (Button) findViewById(R.id.start_service);
        mStopServiceButton = (Button) findViewById(R.id.stop_service);

        mBindServiceButton = (Button) findViewById(R.id.bind_service);
        mUnbindServiceButton = (Button) findViewById(R.id.unbind_service);

        mStartIntentServiceButton = (Button) findViewById(R.id.start_intentservice);

        mStartLongRunServiceButton = (Button) findViewById(R.id.start_long_run_service);

        mStartServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(MainActivity.this, MyService.class);
                startService(startIntent);
            }
        });

        mStopServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stopIntent = new Intent(MainActivity.this, MyService.class);
                stopService(stopIntent);
            }
        });

        mBindServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bindIntent = new Intent(MainActivity.this, MyService.class);
                bindService(bindIntent, mServiceConnection, BIND_AUTO_CREATE);
            }
        });

        mUnbindServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(mServiceConnection);
            }
        });

        mStartIntentServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("MainActivity", "Thread id is " + Thread.currentThread().getId());

                Intent startIntent = new Intent(MainActivity.this, MyIntentService.class);
                startService(startIntent);
            }
        });

        mStartLongRunServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(MainActivity.this, LongRunningService.class);
                startService(startIntent);
            }
        });
    }


}
