package com.timetable.todolist.Schedule.lifereminders;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

/**
 * Created by David Wu on 12/12/2017.
 */

public class MySimpleAdapter extends SimpleAdapter {

    // Constructor

    public MySimpleAdapter (Context context, List<Map<String, ?>> items)
    {
        super(context, items, android.R.layout.simple_list_item_1, null, null);
    }


    @Override
    public View getView (int position, View convertView, ViewGroup parent)
    {
        return null;
    }

}
