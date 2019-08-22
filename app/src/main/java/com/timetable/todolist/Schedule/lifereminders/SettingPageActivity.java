package com.timetable.todolist.Schedule.lifereminders;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SettingPageActivity extends AppCompatActivity {


    private String TAG = "TAG_setting_page";

    private String only_show_fourteen_days = "";
    private String do_not_show_negative_days = "";
    private String sorting_order = "";
    private String turn_on_notification = "";


    private String original_only_show_fourteen_days = "";
    private String original_do_not_show_negative_days = "";
    private String original_sorting_order = "";
    private String original_turn_on_notification = "";


    private int selected_hour;
    private int selected_minute;


    private int original_selected_hour;
    private int original_selected_minute;


    private String currentUser = "";

    private Spinner sorting_mode_spinner;
    private List<String> list = new ArrayList<String>();
    private ArrayAdapter adapter;

    private List<String> colour_list = new ArrayList<String>();
    private ArrayAdapter colour_adapter;

    private Switch switch_bar;
    private Switch switch_bar_non_negative;
    private Switch switch_bar_turn_on_notification;

    private TimePicker notification_time_picker;

    private Button setting_apply_button;
    private Button setting_discard_button;

    private String page_redirection = "";

    private MyDatabaseHelper dbHelper;

    private Spinner colour_spinner;

    private String colour = "";
    private String original_colour = "";

    private String LANGUAGE = "";

    private int databaseVersion = 6;
    private String databaseName = "information2.db";


    public void initLocaleLanguage ()
    {
        Resources resource = getApplicationContext().getResources();
        Configuration configuration = resource.getConfiguration();
        DisplayMetrics displayMetrics = resource.getDisplayMetrics();
        switch (LANGUAGE)
        {
            case "English (EN-GB)":configuration.locale = Locale.UK;break;
            case "Simplified Chinese (CH-ZN)":configuration.locale = Locale.CHINA;break;
            case "Traditional Chinese (CH-TW)":configuration.locale = Locale.TAIWAN;break;
            case "Japanese (JA-JP)":configuration.locale = Locale.JAPAN;break;
            case "French (FR-FA)":configuration.locale = Locale.FRANCE;break;

            case "English (EN_GB)":configuration.locale = Locale.UK;break;
            case "Simplified Chinese (CH_ZN)":configuration.locale = Locale.CHINA;break;
            case "Traditional Chinese (CH_TW)":configuration.locale = Locale.TAIWAN;break;
            case "Japanese (JA_JP)":configuration.locale = Locale.JAPAN;break;
            case "French (FR_FA)":configuration.locale = Locale.FRANCE;break;

            default:configuration.locale = Locale.UK;break;
        }
        resource.updateConfiguration(configuration, displayMetrics);
        getBaseContext().getResources().updateConfiguration(configuration, null);
    }



    public void init_theme ()
    {
        switch (colour)
        {
            case "Blue": setTheme(R.style.AppThemeBlue);break;
            case "blue": setTheme(R.style.AppThemeBlue);break;
            case "Green": setTheme(R.style.AppThemeGreen);break;
            case "green": setTheme(R.style.AppThemeGreen);break;
            case "Pink": setTheme(R.style.AppThemePink);break;
            case "pink": setTheme(R.style.AppThemePink);break;
            case "Purple": setTheme(R.style.AppThemePurple);break;
            case "purple": setTheme(R.style.AppThemePurple);break;
            case "Orange": setTheme(R.style.AppThemeOrange);break;
            case "orange": setTheme(R.style.AppThemeOrange);break;
            case "Light Green":setTheme(R.style.AppThemeLightGreen);break;
            case "light green":setTheme(R.style.AppThemeLightGreen);break;
            case "Light green":setTheme(R.style.AppThemeLightGreen);break;
            case "Lime":setTheme(R.style.AppThemeLime);break;
            case "lime":setTheme(R.style.AppThemeLime);break;
            case "Amber":setTheme(R.style.AppThemeAmber);break;
            case "amber":setTheme(R.style.AppThemeAmber);break;
            case "Yellow":setTheme(R.style.AppThemeYellow);break;
            case "yellow":setTheme(R.style.AppThemeYellow);break;
            case "Grey":setTheme(R.style.AppThemeGrey);break;
            case "grey":setTheme(R.style.AppThemeGrey);break;
            case "Light Blue":setTheme(R.style.AppThemeLightBlue);break;
            case "Light blue":setTheme(R.style.AppThemeLightBlue);break;
            case "light blue":setTheme(R.style.AppThemeLightBlue);break;
            case "Teal":setTheme(R.style.AppThemeTeal);break;
            case "teal":setTheme(R.style.AppThemeTeal);break;
            case "Red":setTheme(R.style.AppThemeRed);break;
            case "red":setTheme(R.style.AppThemeRed);break;

            case "Green1":setTheme(R.style.AppThemeGreen1);break;
            case "green1":setTheme(R.style.AppThemeGreen1);break;
            case "Green2":setTheme(R.style.AppThemeGreen2);break;
            case "green2":setTheme(R.style.AppThemeGreen2);break;
            case "Teal1":setTheme(R.style.AppThemeTeal1);break;
            case "teal1":setTheme(R.style.AppThemeTeal1);break;
            case "Purple1":setTheme(R.style.AppThemePurple1);break;
            case "purple1":setTheme(R.style.AppThemePurple1);break;
            case "Pink1":setTheme(R.style.AppThemePink1);break;
            case "pink1":setTheme(R.style.AppThemePink1);break;
            case "Grey1":setTheme(R.style.AppThemeGrey1);break;
            case "grey1":setTheme(R.style.AppThemeGrey1);break;
            case "Purple2":setTheme(R.style.AppThemePurple2);break;
            case "purple2":setTheme(R.style.AppThemePurple2);break;
            case "Pink2":setTheme(R.style.AppThemePink2);break;
            case "pink2":setTheme(R.style.AppThemePink2);break;
            case "Green3":setTheme(R.style.AppThemeGreen3);break;
            case "green3":setTheme(R.style.AppThemeGreen3);break;
            case "Orange1":setTheme(R.style.AppThemeOrange1);break;
            case "orange1":setTheme(R.style.AppThemeOrange1);break;

            case "Purple3":setTheme(R.style.AppThemePurple3);break;
            case "purple3":setTheme(R.style.AppThemePurple3);break;
            case "Purple4":setTheme(R.style.AppThemePurple4);break;
            case "purple4":setTheme(R.style.AppThemePurple4);break;
            case "Blue1":setTheme(R.style.AppThemeBlue1);break;
            case "blue1":setTheme(R.style.AppThemeBlue1);break;
            case "Cyan":setTheme(R.style.AppThemeCyan);break;
            case "cyan":setTheme(R.style.AppThemeCyan);break;
            case "Green4":setTheme(R.style.AppThemeGreen4);break;
            case "green4":setTheme(R.style.AppThemeGreen4);break;
            case "Pink3":setTheme(R.style.AppThemePink3);break;
            case "pink3":setTheme(R.style.AppThemePink3);break;
            case "Orange2":setTheme(R.style.AppThemeOrange2);break;
            case "orange2":setTheme(R.style.AppThemeOrange2);break;


            default: setTheme(R.style.AppThemeBlue);break;
        }
        return;
    }



    public void changeStatusBarColour (Activity activity, int colourResId)
    {
        try
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(activity.getResources().getColor(colourResId));

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    public void setUpNotification ()
    {
        String title = getString(R.string.lucky_day_notification_title);
        String message = "";
        int today_task_count = 0;
        today_task_count = task_count();
        message = getString(R.string.you_have) + " " + String.valueOf(today_task_count) + " " + getString(R.string.due_on);
        /*
        //SimpleDateFormat format  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //intent.putExtra("time" , );
        Intent intent = new Intent (SettingPageActivity.this , AlarmService.class);
        intent.putExtra("title" , title);
        intent.putExtra("content" , message);
        intent.putExtra("selected_hour" , selected_hour);
        intent.putExtra("selected_minute" , selected_minute);

        Log.d("selected_hour" , String.valueOf(selected_hour));
        Log.d("selected_minute" , String.valueOf(selected_minute));
        Log.d("selected_title" , title);
        Log.d("selected_content" , message);
        */

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY , selected_hour);
        calendar.set(Calendar.MINUTE , selected_minute);
        calendar.set(Calendar.SECOND , 0);

        Intent intent = new Intent (getApplicationContext() , NotificationReceiver.class);
        intent.putExtra("title" , title);
        intent.putExtra("content" , message);

        intent.putExtra("currentUser" , currentUser);
        intent.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
        intent.putExtra("sorting_order" , sorting_order);
        intent.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
        intent.putExtra("turn_on_notification" , turn_on_notification);
        intent.putExtra("notification_hour" , selected_hour);
        intent.putExtra("notification_minute" , selected_minute);
        intent.putExtra("colour" , colour);
        intent.putExtra("language" , LANGUAGE);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext() , 100 , intent , PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP , calendar.getTimeInMillis() , 1000*60*60*24, pendingIntent);




    }


    public void cancelNotification ()
    {

        Intent intent = new Intent (SettingPageActivity.this , NotificationReceiver.class);
        intent.putExtra("currentUser" , currentUser);
        intent.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
        intent.putExtra("sorting_order" , sorting_order);
        intent.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
        intent.putExtra("turn_on_notification" , turn_on_notification);
        intent.putExtra("notification_hour" , selected_hour);
        intent.putExtra("notification_minute" , selected_minute);
        intent.putExtra("colour" , colour);
        intent.putExtra("language" , LANGUAGE);
        AlarmManager am = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        PendingIntent cancel_sender = PendingIntent.getBroadcast(SettingPageActivity.this , 100 , intent , 0);
        am.cancel(cancel_sender);

    }


    public int task_count ()
    {
        Calendar calendar = Calendar.getInstance();
        int current_year = calendar.get(Calendar.YEAR);
        int current_month = calendar.get(Calendar.MONTH) + 1;
        int current_day = calendar.get(Calendar.DAY_OF_MONTH);
        String current_year_s = String.valueOf(current_year);
        if (current_year_s.length() < 2)
        {
            current_year_s = "0" + current_year_s;
        }
        String current_month_s = String.valueOf(current_month);
        if (current_month_s.length() < 2)
        {
            current_month_s = "0" + current_month_s;
        }
        String current_day_s = String.valueOf(current_day);
        if (current_day_s.length() < 2)
        {
            current_day_s = "0" + current_day_s;
        }
        String current_date = current_year_s + "-" + current_month_s + "-" + current_day_s + " 00:00:00";

        dbHelper = new MyDatabaseHelper(this, databaseName, null, databaseVersion);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM Item WHERE date_finished = ? ";
        String [] parameter = new String [] {current_date};
        Cursor cursor = db.rawQuery(query , parameter);
        int result = 0;
        while (cursor.moveToNext())
        {
            result = Integer.parseInt(cursor.getString(0).toString());
        }
        if (cursor != null)
        {
            cursor.close();
        }
        db.close();

        return result;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (colour.equalsIgnoreCase(""))
        {
            colour = "Blue";
        }
        if (getIntent().getStringExtra("colour") != null)
        {
            colour = getIntent().getStringExtra("colour").toString();
        }

        if (LANGUAGE.equalsIgnoreCase(""))
        {
            LANGUAGE = "English (EN-GB)";
        }
        if (getIntent().getStringExtra("language") != null)
        {
            LANGUAGE = getIntent().getStringExtra("language").toString();
        }



        initLocaleLanguage();
        original_colour = colour;
        init_theme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Log.d(TAG, "onCreate");

        //changeStatusBarColour(SettingPageActivity.this, R.color.colorPrimaryDark);

        if (getIntent().getStringExtra("page_redirection") != null)
        {
            if (!getIntent().getStringExtra("page_redirection").equalsIgnoreCase(""))
            {
                page_redirection = getIntent().getStringExtra("page_redirection");
            }
        }


        selected_hour = getIntent().getIntExtra("notification_hour" , -10);

        selected_minute = getIntent().getIntExtra("notification_minute" , -10);
        original_selected_hour = selected_hour;
        original_selected_minute = selected_minute;





        currentUser = getIntent().getStringExtra("currentUser");
        sorting_order = getIntent().getStringExtra("sorting_order");
        only_show_fourteen_days = getIntent().getStringExtra("only_show_fourteen_days");
        do_not_show_negative_days = getIntent().getStringExtra("do_not_show_negative_days");
        turn_on_notification = getIntent().getStringExtra("turn_on_notification");

        original_do_not_show_negative_days = do_not_show_negative_days;
        original_only_show_fourteen_days = only_show_fourteen_days;
        original_sorting_order = sorting_order;
        original_turn_on_notification = turn_on_notification;
        // set up the sorting_mode_spinner
        sorting_mode_spinner = (Spinner) findViewById(R.id.setting_mode_spinner);
        list.add("Sort by creating date ASC");
        list.add("Sort by creating date DESC");
        list.add("Sort by remain days");

        String [] mList = getResources().getStringArray(R.array.sorting_order_spinner_options);

        adapter = new ArrayAdapter<String>(this , android.R.layout.simple_spinner_dropdown_item, mList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sorting_mode_spinner.setAdapter(adapter);
        switch (sorting_order)
        {
            case "item_id ASC": sorting_mode_spinner.setSelection(0);break;
            case "item_id DESC": sorting_mode_spinner.setSelection(1);break;
            case "date_finished ASC": sorting_mode_spinner.setSelection(2);break;
            default:sorting_mode_spinner.setSelection(2);break;
        }

        sorting_mode_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                {
                    sorting_order = "item_id ASC";
                }
                if (position == 1)
                {
                    sorting_order = "item_id DESC";
                }
                if (position == 2)
                {
                    sorting_order = "date_finished ASC";
                }
                Log.d("sorting_order" , sorting_order);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        switch_bar = (Switch) findViewById(R.id.setting_switch_bar);
        switch (only_show_fourteen_days)
        {
            case "true":switch_bar.setChecked(true);break;
            case "false":switch_bar.setChecked(false);break;
            default:switch_bar.setChecked(false);break;
        }
        switch_bar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    only_show_fourteen_days = "true";
                }
                else
                {
                    only_show_fourteen_days = "false";
                }
                Log.d("only_show" , only_show_fourteen_days);
            }
        });

        switch_bar_non_negative = (Switch) findViewById(R.id.negative_days_switch_bar);
        switch (do_not_show_negative_days)
        {
            case "true":switch_bar_non_negative.setChecked(true);break;
            case "false":switch_bar_non_negative.setChecked(false);break;
            default:switch_bar_non_negative.setChecked(false);break;
        }
        switch_bar_non_negative.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    do_not_show_negative_days = "true";
                }
                else
                {
                    do_not_show_negative_days = "false";
                }
                Log.d("do_not_show" , do_not_show_negative_days);
            }
        });

        switch_bar_turn_on_notification = (Switch) findViewById(R.id.notification_switch_bar);
        switch (turn_on_notification)
        {
            case "true":switch_bar_turn_on_notification.setChecked(true);break;
            case "false":switch_bar_turn_on_notification.setChecked(false);break;
            default:switch_bar_turn_on_notification.setChecked(false);break;
        }
        switch_bar_turn_on_notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    turn_on_notification = "true";
                }
                else
                {
                    turn_on_notification = "false";
                }
                Log.d("turn_on_N" , turn_on_notification);
            }
        });

        notification_time_picker = (TimePicker) findViewById(R.id.setting_notification_time_picker);
        if (selected_hour != -10)
        {
            notification_time_picker.setCurrentHour(selected_hour);
        }
        if (selected_minute != -10)
        {
            notification_time_picker.setCurrentMinute(selected_minute);
        }


        notification_time_picker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                selected_hour = hourOfDay;
                selected_minute = minute;
                Log.d("selected_hour" , String.valueOf(selected_hour));
                Log.d("selected_minute" , String.valueOf(selected_minute));
            }
        });


        /*
        if (turn_on_notification.equalsIgnoreCase("true"))
        {
            // turn on the notification
            // send notification at certain time everyday
            setUpNotification();
        }
        else
        {

            // cancel the notification service
            // FINISHED WAITING FOR CHECKING
            cancelNotification();
        }
        */



        colour_spinner = (Spinner) findViewById(R.id.colour_spinner);

        colour_list.add("Blue");
        colour_list.add("Green");
        colour_list.add("Pink");
        colour_list.add("Purple");
        colour_list.add("Orange");
        colour_list.add("Light Green");
        colour_list.add("Lime");
        colour_list.add("Amber");
        colour_list.add("Yellow");
        colour_list.add("Grey");
        colour_list.add("Light Blue");
        colour_list.add("Teal");
        colour_list.add("Red");
        colour_list.add("Green1");
        colour_list.add("Green2");
        colour_list.add("Teal1");
        colour_list.add("Purple1");
        colour_list.add("Pink1");
        colour_list.add("Grey1");
        colour_list.add("Purple2");
        colour_list.add("Pink2");
        colour_list.add("Green3");
        colour_list.add("Orange1");
        colour_list.add("Purple3");
        colour_list.add("Purple4");
        colour_list.add("Blue1");
        colour_list.add("Cyan");
        colour_list.add("Green4");
        colour_list.add("Pink3");
        colour_list.add("Orange2");

        String [] mColour = getResources().getStringArray(R.array.colour_array);

        colour_adapter = new ArrayAdapter<String>(this , android.R.layout.simple_spinner_dropdown_item, mColour);
        colour_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        colour_spinner.setAdapter(colour_adapter);
        switch (colour)
        {
            case "Blue": colour_spinner.setSelection(0);break;
            case "Green": colour_spinner.setSelection(1);break;
            case "Pink": colour_spinner.setSelection(2);break;
            case "Purple": colour_spinner.setSelection(3);break;
            case "Orange": colour_spinner.setSelection(4);break;
            case "Light Green": colour_spinner.setSelection(5);break;
            case "Lime": colour_spinner.setSelection(6);break;
            case "Amber": colour_spinner.setSelection(7);break;
            case "Yellow": colour_spinner.setSelection(8);break;
            case "Grey": colour_spinner.setSelection(9);break;
            case "Light Blue": colour_spinner.setSelection(10);break;
            case "Teal": colour_spinner.setSelection(11);break;
            case "Red": colour_spinner.setSelection(12);break;
            case "Green1": colour_spinner.setSelection(13);break;
            case "Green2": colour_spinner.setSelection(14);break;
            case "Teal1": colour_spinner.setSelection(15);break;
            case "Purple1": colour_spinner.setSelection(16);break;
            case "Pink1": colour_spinner.setSelection(17);break;
            case "Grey1": colour_spinner.setSelection(18);break;
            case "Purple2": colour_spinner.setSelection(19);break;
            case "Pink2": colour_spinner.setSelection(20);break;
            case "Green3": colour_spinner.setSelection(21);break;
            case "Orange1": colour_spinner.setSelection(22);break;
            case "Purple3": colour_spinner.setSelection(23);break;
            case "Purple4": colour_spinner.setSelection(24);break;
            case "Blue1": colour_spinner.setSelection(25);break;
            case "Cyan": colour_spinner.setSelection(26);break;
            case "Green4": colour_spinner.setSelection(27);break;
            case "Pink3": colour_spinner.setSelection(28);break;
            case "Orange2": colour_spinner.setSelection(29);break;



            default:colour_spinner.setSelection(0);break;
        }

        colour_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0: colour = "Blue";break;
                    case 1: colour = "Green";break;
                    case 2: colour = "Pink";break;
                    case 3: colour = "Purple";break;
                    case 4: colour = "Orange";break;
                    case 5: colour = "Light Green";break;
                    case 6: colour = "Lime";break;
                    case 7: colour = "Amber";break;
                    case 8: colour = "Yellow";break;
                    case 9: colour = "Grey";break;
                    case 10: colour = "Light Blue";break;
                    case 11: colour = "Teal";break;
                    case 12: colour = "Red";break;
                    case 13: colour = "Green1";break;
                    case 14: colour = "Green2";break;
                    case 15: colour = "Teal1"; break;
                    case 16: colour = "Purple1";break;
                    case 17: colour = "Pink1";break;
                    case 18: colour = "Grey1";break;
                    case 19: colour = "Purple2";break;
                    case 20: colour = "Pink2";break;
                    case 21: colour = "Green3";break;
                    case 22: colour = "Orange1";break;
                    case 23: colour = "Purple3";break;
                    case 24: colour = "Purple4";break;
                    case 25: colour = "Blue1";break;
                    case 26: colour = "Cyan";break;
                    case 27: colour = "Green4";break;
                    case 28: colour = "Pink3";break;
                    case 29: colour = "Orange2";break;


                    default:colour = "Blue";break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });







        setting_apply_button = (Button) findViewById(R.id.setting_apply_button);
        setting_discard_button = (Button) findViewById(R.id.setting_discard_button);
        setting_apply_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (SettingPageActivity.this , NavigationActivity.class);
                if (page_redirection.equalsIgnoreCase("calendar_mode"))
                {
                    intent = new Intent(SettingPageActivity.this , CalendarViewNavigationActivity.class);
                }
                if (turn_on_notification.equalsIgnoreCase("true"))
                {
                    setUpNotification();
                    NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);;
                    Notification notification = new NotificationCompat.Builder(getApplicationContext())
                            .setSmallIcon(R.drawable.ic_small_icon1)
                            .setTicker(getString(R.string.turn_on_content))
                            .setContentInfo(getString(R.string.lucky_day_n))
                            .setContentTitle(getString(R.string.turn_on_n))
                            .setContentText(getString(R.string.turn_on_content))
                            .setAutoCancel(true)
                            .setDefaults(Notification.DEFAULT_ALL)
                            .build();
                    manager.notify(0, notification);
                }
                else
                {
                    cancelNotification();
                    NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                    Notification notification = new NotificationCompat.Builder(getApplicationContext())
                            .setSmallIcon(R.drawable.ic_small_icon1)
                            .setTicker(getString(R.string.turn_off_content))
                            .setContentInfo(getString(R.string.lucky_day_n))
                            .setContentTitle(getString(R.string.turn_off_n))
                            .setContentText(getString(R.string.turn_off_content))
                            .setAutoCancel(true)
                            .setDefaults(Notification.DEFAULT_ALL)
                            .build();
                    manager.notify(0, notification);
                }


                intent.putExtra("sorting_order" , sorting_order);
                intent.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
                intent.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
                intent.putExtra("turn_on_notification" , turn_on_notification);
                Log.d("setting_final1" , sorting_order);
                Log.d("setting_final2" , only_show_fourteen_days);
                intent.putExtra("currentUser" , currentUser);
                intent.putExtra("notification_hour" , selected_hour);
                intent.putExtra("notification_minute" , selected_minute);
                intent.putExtra("colour" , colour);
                intent.putExtra("language", LANGUAGE);


                MyDatabaseHelper dbHelper1 = new MyDatabaseHelper(SettingPageActivity.this, databaseName, null, databaseVersion);
                SQLiteDatabase db5 = dbHelper1.getWritableDatabase();


                ContentValues values = new ContentValues();
                values.put("sorting_order" , sorting_order);
                values.put("only_show_fourteen_days" , only_show_fourteen_days);
                values.put("do_not_show_negative_days" , do_not_show_negative_days);
                values.put("turn_on_notification" , turn_on_notification);
                values.put("notification_hour" , selected_hour);
                values.put("notification_minute" , selected_minute);
                values.put("colour" , colour);

                db5.update("User" , values, "user_id = ? ", new String [] {currentUser});
                if (db5 != null)
                {
                    db5.close();
                }


                Log.d("null_ptr" , String.valueOf(dbHelper1 == null));
                startActivity(intent);
                finish();
            }
        });

        setting_discard_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (SettingPageActivity.this , NavigationActivity.class);
                if (page_redirection.equalsIgnoreCase("calendar_mode"))
                {
                    intent = new Intent(SettingPageActivity.this , CalendarViewNavigationActivity.class);
                }
                intent.putExtra("sorting_order" , original_sorting_order);
                intent.putExtra("only_show_fourteen_days" , original_only_show_fourteen_days);
                intent.putExtra("do_not_show_negative_days" , original_do_not_show_negative_days);
                intent.putExtra("turn_on_notification" , original_turn_on_notification);
                Log.d("setting_final3" , "discard");
                Log.d("setting_final4" , "discard");
                intent.putExtra("currentUser" , currentUser);
                intent.putExtra("notification_hour" , original_selected_hour);
                intent.putExtra("notification_minute" , original_selected_minute);
                intent.putExtra("colour" , original_colour);
                intent.putExtra("language", LANGUAGE);
                startActivity(intent);
                finish();
            }
        });

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
    }



    // control the go home option.

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home)
        {
            Intent intent = new Intent (SettingPageActivity.this , NavigationActivity.class);
            if (page_redirection.equalsIgnoreCase("calendar_mode"))
            {
                intent = new Intent(SettingPageActivity.this , CalendarViewNavigationActivity.class);
            }
            intent.putExtra("currentUser" , currentUser);
            intent.putExtra("only_show_fourteen_days" , original_only_show_fourteen_days);
            intent.putExtra("sorting_order" , original_sorting_order);
            intent.putExtra("do_not_show_negative_days" , original_do_not_show_negative_days);
            intent.putExtra("turn_on_notification" , original_turn_on_notification);
            intent.putExtra("notification_hour" , original_selected_hour);
            intent.putExtra("notification_minute" , original_selected_minute);
            intent.putExtra("colour" , original_colour);
            intent.putExtra("language", LANGUAGE);
            startActivity(intent);
            finish();
            return true;
        }
        return true;
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






