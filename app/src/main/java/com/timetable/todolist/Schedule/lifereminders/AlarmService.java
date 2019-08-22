package com.timetable.todolist.Schedule.lifereminders;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Calendar;

/**
 * Created by David Wu on 15/12/2017.
 */

public class AlarmService extends Service {

    private AlarmManager am;
    private PendingIntent pi;
    private String title = "";
    private String content = "";


    private int databaseVersion = 6;
    private String databaseName = "information2.db";


    private int selected_hour;
    private int selected_minute;

    @Nullable
    @Override
    public IBinder onBind (Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate ()
    {
        super.onCreate();
    }

    @Override
    public int onStartCommand (Intent intent, int flags, int startId)
    {
        if (intent.getStringExtra("title") != null)
        {
            title = intent.getStringExtra("title");
        }
        if (intent.getStringExtra("content") != null)
        {
            content = intent.getStringExtra("content");
        }
        selected_hour = intent.getIntExtra("selected_hour" , -10000);
        selected_minute = intent.getIntExtra("selected_minute" , -10000);
        getAlarmTime ();
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy ()
    {
        super.onDestroy();
    }

    public void getAlarmTime()
    {
        // TODO
        // HERE WE SHOULD GET THE TITLE, CONTENT, TIME FROM THE DATABASE OR WHEREVER

        Intent startNotification = new Intent (this, AlarmReceiver.class);
        startNotification.putExtra("title", title);
        startNotification.putExtra("content" , content);
        am = (AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, selected_hour);
        calendar.set(Calendar.MINUTE, selected_minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        pi = PendingIntent.getBroadcast(this, 0, startNotification, PendingIntent.FLAG_UPDATE_CURRENT);

        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000*60*60*24, pi);

    }


}
