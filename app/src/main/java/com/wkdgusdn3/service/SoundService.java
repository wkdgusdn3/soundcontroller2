package com.wkdgusdn3.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.widget.RemoteViews;

import com.wkdgusdn3.broadcastreceiver.ReceiverVolume0;
import com.wkdgusdn3.soundcontroller2.R;

public class SoundService extends Service {

    Context context;

    @Override
    public void onCreate() {

        context = getApplicationContext();

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            notificationJellyBean();
        } else {
            notificationIceCreamSandwich();
        }
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    void notificationIceCreamSandwich() {
        NotificationManager notificationManager;
        Notification notification;
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notification = new Notification(com.wkdgusdn3.soundcontroller2.R.drawable.sound_icon, "soundcontroller2", System.currentTimeMillis());

        notification.priority = Notification.PRIORITY_MIN;
        notification.flags = Notification.FLAG_NO_CLEAR;

        RemoteViews views = new RemoteViews(this.getPackageName(), com.wkdgusdn3.soundcontroller2.R.layout.sound_notification);
//        views.setImageViewResource(com.wkdgusdn3.soundcontroller2.R.id.sound_icon, com.wkdgusdn3.soundcontroller2.R.drawable.sound_icon);

        for (int i = 0; i < 16; i++) {
            String className = "com.wkdgusdn3.broadcastreceiver.ReceiverVolume" + i;
            String id = "volume" + i;
            try {
                Intent intent = new Intent(getApplicationContext(), Class.forName(className));
                PendingIntent pedingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
                views.setOnClickPendingIntent(getResources().getIdentifier(id, "id", getPackageName()), pedingIntent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Intent intent = new Intent(getApplicationContext(), ReceiverVolume0.class);
        PendingIntent pedingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.volume0, pedingIntent);

        notification.contentView = views;
        notificationManager.notify(3, notification);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    void notificationJellyBean() {
        NotificationManager notificationManager;
        Notification notification;
        Notification.Builder builder = new Notification.Builder(getApplicationContext());

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        builder.setSmallIcon(R.drawable.sound_icon);
        builder.setTicker("SoundController");
        builder.setPriority(Notification.PRIORITY_MIN);
        builder.setOngoing(true);
        notification = builder.build();

        RemoteViews views = new RemoteViews(this.getPackageName(), com.wkdgusdn3.soundcontroller2.R.layout.sound_notification);
//        views.setImageViewResource(com.wkdgusdn3.soundcontroller2.R.id.sound_icon, com.wkdgusdn3.soundcontroller2.R.drawable.sound_icon);

        for (int i = 0; i < 16; i++) {
            String className = "com.wkdgusdn3.broadcastreceiver.ReceiverVolume" + i;
            String id = "volume" + i;
            try {
                Intent intent = new Intent(getApplicationContext(), Class.forName(className));
                PendingIntent pedingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
                views.setOnClickPendingIntent(getResources().getIdentifier(id, "id", getPackageName()), pedingIntent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        notification.contentView = views;
        notificationManager.notify(3, notification);
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
}
