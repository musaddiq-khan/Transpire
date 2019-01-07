package com.example.junaid.transpire;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import android.support.v4.app.NotificationCompat;

public class AlarmNotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"");

      //  NotificationCompat.Builder mBuilder = (android.support.v7.app.NotificationCompat.Builder)


        builder.setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_fit)
                .setContentTitle("Its Time")
                .setContentText("Time to training")
                .setContentInfo("Info");
        NotificationManager notificationManager=(NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());


    }
}
