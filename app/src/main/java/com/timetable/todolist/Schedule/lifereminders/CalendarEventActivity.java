package com.timetable.todolist.Schedule.lifereminders;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ListView;

import java.util.Calendar;

public class CalendarEventActivity extends AppCompatActivity {

    private String original_sorting_order = "";
    private String original_do_not_show_negative_days = "";
    private String original_only_show_fourteen_days = "";
    private String currentUser = "";

    private int databaseVersion = 6;
    private String databaseName = "information2.db";

    private ListView calendar_page_listview;
    private CalendarView calendarView;

    private Calendar calendar;

    private int current_year;
    private int current_month;
    private int current_day;
    private int current_hour;
    private int current_minute;
    private int current_second;

    private int current_selected_year;
    private int current_selected_month;
    private int current_selected_day;
    private int current_selected_hour;
    private int current_selected_minute;
    private int current_selected_second;

    private String TAG = "TAG_calendarevent";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        original_do_not_show_negative_days = getIntent().getStringExtra("do_not_show_negative_days");
        original_only_show_fourteen_days = getIntent().getStringExtra("only_show_fourteen_days");
        original_sorting_order = getIntent().getStringExtra("sorting_order");
        currentUser = getIntent().getStringExtra("currentUser");
        Log.d("original_do_not_show" , original_do_not_show_negative_days);
        Log.d("original_only_show" , original_only_show_fourteen_days);
        Log.d("original_sorting" , original_sorting_order);
        Log.d("currentUser" , currentUser);

        calendar = Calendar.getInstance();

        current_year = calendar.get(Calendar.YEAR);
        current_month = calendar.get(Calendar.MONTH) + 1;
        current_day = calendar.get(Calendar.DAY_OF_MONTH);
        current_hour = calendar.get(Calendar.HOUR_OF_DAY);
        current_minute = calendar.get(Calendar.MINUTE);
        current_second = calendar.get(Calendar.SECOND);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Log.d(TAG , "onStart");
    }

    @Override
    public void onResume ()
    {
        super.onResume();
        Log.d(TAG , "onResume");
    }

    @Override
    public void onPause ()
    {
        super.onPause();
        Log.d(TAG , "onPause");
    }

    @Override
    public void onStop ()
    {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroy ()
    {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onRestart ()
    {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

}
