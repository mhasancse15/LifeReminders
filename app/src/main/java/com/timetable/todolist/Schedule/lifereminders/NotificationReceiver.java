package com.timetable.todolist.Schedule.lifereminders;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

/**
 * Created by David Wu on 15/12/2017.
 */

public class NotificationReceiver extends BroadcastReceiver{

    private String title = "";
    private String content = "";

    private String currentUser = "";
    private String only_show_fourteen_days = "";
    private String sorting_order = "";
    private String do_not_show_negative_days = "";
    private String turn_on_notification = "";
    private int selected_hour;
    private int selected_minute;
    private String colour = "";
    private String LANGUAGE = "";

    private int databaseVersion = 6;
    private String databaseName = "information2.db";

    @Override
    public void onReceive (Context context, Intent intent)
    {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent repeating_intent = new Intent (context, StartUpActivity.class);
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        if (intent.getStringExtra("title").toString() != null)
        {
            title = intent.getStringExtra("title").toString();
        }
        if (intent.getStringExtra("content").toString() != null)
        {
            content = intent.getStringExtra("content");
        }
        if (intent.getStringExtra("currentUser") != null)
        {
            currentUser = intent.getStringExtra("currentUser").toString();
            repeating_intent.putExtra("currentUser" , currentUser);
        }
        if (intent.getStringExtra("only_show_fourteen_days") != null)
        {
            only_show_fourteen_days = intent.getStringExtra("only_show_fourteen_days").toString();
            repeating_intent.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
        }
        if (intent.getStringExtra("do_not_show_negative_days") != null)
        {
            do_not_show_negative_days = intent.getStringExtra("do_not_show_negative_days").toString();
            repeating_intent.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
        }
        if (intent.getStringExtra("turn_on_notification") != null)
        {
            turn_on_notification = intent.getStringExtra("turn_on_notification").toString();
            repeating_intent.putExtra("turn_on_notification" , turn_on_notification);
        }
        if (intent.getStringExtra("sorting_order") != null)
        {
            sorting_order = intent.getStringExtra("sorting_order").toString();
            repeating_intent.putExtra("sorting_order" , sorting_order);
        }
        if (intent.getStringExtra("notification_hour") != null)
        {
            selected_hour = intent.getIntExtra("notification_hour" , -10);
            repeating_intent.putExtra("notification_hour" , selected_hour);
        }
        if (intent.getStringExtra("notification_minute") != null)
        {
            selected_minute = intent.getIntExtra("notification_minute" , -10);
            repeating_intent.putExtra("notification_minute" , selected_minute);
        }



        if (intent.getStringExtra("colour") != null)
        {
            colour = intent.getStringExtra("colour").toString();
            repeating_intent.putExtra("colour" , colour);
        }




        if (intent.getStringExtra("language") != null)
        {
            LANGUAGE = intent.getStringExtra("language").toString();
            repeating_intent.putExtra("language" , LANGUAGE);
        }



        PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, repeating_intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_small_icon1)
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true);

        notificationManager.notify(100 , builder.build());
    }
}
