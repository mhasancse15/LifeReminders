package com.timetable.todolist.Schedule.lifereminders;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static android.provider.ContactsContract.Intents.Insert.ACTION;

/**
 * Created by David Wu on 15/12/2017.
 */

public class BootReceiver extends BroadcastReceiver {

    public BootReceiver ()
    {

    }

    @Override
    public void onReceive (Context context, Intent intent)
    {
        if (intent.getAction().equals(ACTION))
        {
            Intent intent2 = new Intent (context, AlarmService.class);
            context.startService(intent2);
        }
        boolean isServiseRunning = false;
        if (intent.getAction().equalsIgnoreCase(Intent.ACTION_TIME_TICK))
        {
            ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE))
            {
                if (".AlarmService".equals(service.service.getClassName()))
                {
                    isServiseRunning = true;
                }
            }
        }
        if (!isServiseRunning)
        {
            Intent intent3 = new Intent (context, AlarmService.class);
            context.startService(intent3);
        }
    }


}




















