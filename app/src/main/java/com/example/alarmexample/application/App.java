package com.example.alarmexample.application;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class App extends Application
{
    public static final String CHANNEL_ID="ALARM_SERVICE_CHANNEL";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    //@RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel()
    {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O)
        {
            NotificationChannel serviceChannel=new NotificationChannel(CHANNEL_ID,"Alarm Service Channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }
}
