package com.timetable.todolist.Schedule.lifereminders; /**
 * Created by David Wu on 14/12/2017.
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class daysDifference {




    public int days_diff (Date date1, Date date2)
    {
        int result = 0;
        result = (int) ((date1.getTime() - date2.getTime()) / (1000*3600*24));

        return result;
    }


}

