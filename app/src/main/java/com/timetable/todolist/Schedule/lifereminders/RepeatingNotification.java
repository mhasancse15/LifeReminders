package com.timetable.todolist.Schedule.lifereminders;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by David Wu on 15/12/2017.
 */

public class RepeatingNotification extends BroadcastReceiver {

    @Override
    public void onReceive (Context context, Intent intent)
    {
        if (intent.getAction() != null && intent.getAction().equals("com.example.davidwu.notebook"))
        {
            intent = new Intent (context, CalendarViewNavigationActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
