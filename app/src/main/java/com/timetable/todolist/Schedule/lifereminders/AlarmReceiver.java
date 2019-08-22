package com.timetable.todolist.Schedule.lifereminders;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by David Wu on 15/12/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {

    private int databaseVersion = 6;
    private String databaseName = "information2.db";

    private NotificationManager manager;
    private static final int NOTIFICATION_ID_1 = 0x00113;
    private String title = ""; // title of the notification
    private String content = ""; // content of the notification


    // DEFAULT CONSTRUCTOR
    public AlarmReceiver ()
    {

    }

    @Override
    public void onReceive (Context context, Intent intent)
    {
        if (intent.getStringExtra("title")  != null)
        {
            title = intent.getStringExtra("title");
        }
        if (intent.getStringExtra("content") != null)
        {
            content = intent.getStringExtra("content");
        }

        showNotification (context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClass(context, AlarmService.class);
        context.startService(intent);

    }

    public void showNotification (Context context)
    {
        Intent [] intent = new Intent [] {new Intent (context, CalendarViewNavigationActivity.class) } ;
        PendingIntent pi = PendingIntent.getActivities(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.icon_1_1)
                .setTicker(content)
                .setContentInfo("Notebook Notification")
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(pi)
                .build();
        manager.notify(NOTIFICATION_ID_1, notification);
    }

}


















