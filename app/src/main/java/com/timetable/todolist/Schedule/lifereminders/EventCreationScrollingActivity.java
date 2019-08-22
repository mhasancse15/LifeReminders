package com.timetable.todolist.Schedule.lifereminders;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EventCreationScrollingActivity extends AppCompatActivity {

    private Calendar calendar;
    private int year, month, day;
    private int hour, minute, second;

    private int start_year, start_month, start_day;
    private int finish_year, finish_month, finish_day;

    private String event_name = "";
    private String event_content = "";

    private String event_classification;

    private Button finish_button;
    private Button cancel_button;

    private DatePicker starting_date_picker;
    private DatePicker finishing_date_picker;

    private EditText event_name_edittext;
    private EditText event_content_edittext;

    private Spinner event_classification_spinner;
    private List<String> list = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    private MyDatabaseHelper dbHelper;

    private String currentUser;

    private int item_id;

    private String page_redirection = "";

    private String sorting_order = "";
    private String do_not_show_negative_days = "";
    private String only_show_fourteen_days = "";

    private String TAG = "TAG_event_create_scroll";

    //private String only_show_class = "";

    private String colour = "";

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

    public void showMessage (String message , Context context)
    {
        //Log.d("Show Message" , message);
        AlertDialog.Builder messageBox = new AlertDialog.Builder (context);
        messageBox.setTitle(getString(R.string.invalid_event_information));
        messageBox.setMessage(message);
        messageBox.setPositiveButton(getString(R.string.ok) , null);
        messageBox.setCancelable(false);
        messageBox.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        if (LANGUAGE.equalsIgnoreCase(""))
        {
            LANGUAGE = "English (EN-GB)";
        }
        if (getIntent().getStringExtra("language") != null)
        {
            LANGUAGE = getIntent().getStringExtra("language").toString();
        }
        initLocaleLanguage();


        if (colour.equalsIgnoreCase(""))
        {
            colour = "Blue";
        }
        if (getIntent().getStringExtra("colour") != null)
        {
            colour = getIntent().getStringExtra("colour").toString();
        }
        init_theme();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_creation_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        if (getIntent().getStringExtra("page_redirection") != null)
        {
            if (!getIntent().getStringExtra("page_redirection").equalsIgnoreCase(""))
            {
                page_redirection = getIntent().getStringExtra("page_redirection");
            }
        }


        if (sorting_order.equalsIgnoreCase(""))
        {
            sorting_order = "date_finished ASC";
        }
        if (only_show_fourteen_days.equalsIgnoreCase(""))
        {
            only_show_fourteen_days = "false";
        }
        if (do_not_show_negative_days.equalsIgnoreCase(""))
        {
            do_not_show_negative_days = "false";
        }




        if (getIntent().getStringExtra("only_show_fourteen_days") != null)
        {
            if (!getIntent().getStringExtra("only_show_fourteen_days").equalsIgnoreCase("discard"))
            {
                only_show_fourteen_days = getIntent().getStringExtra("only_show_fourteen_days");
            }
        }

        if (getIntent().getStringExtra("sorting_order") != null)
        {
            if (!getIntent().getStringExtra("sorting_order").equalsIgnoreCase("discard"))
            {
                sorting_order = getIntent().getStringExtra("sorting_order");
            }
        }

        if (getIntent().getStringExtra("do_not_show_negative_days") != null)
        {
            if (!getIntent().getStringExtra("do_not_show_negative_days").equalsIgnoreCase("discard"))
            {
                do_not_show_negative_days = getIntent().getStringExtra("do_not_show_negative_days");
            }
        }


        currentUser = getIntent().getStringExtra("currentUser");
        Log.d("currentUser" , currentUser);

        Log.d(TAG, "onCreate");

        //only_show_class = getIntent().getStringExtra("only_show_class");
        //Log.d("only_show_class" , only_show_class);

        dbHelper = new MyDatabaseHelper (this, databaseName, null, databaseVersion);
        //DatabaseVersion.version = DatabaseVersion.version + 1;
        dbHelper.getWritableDatabase();

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        finish_button = (Button) findViewById(R.id.event_finish_button);
        cancel_button = (Button) findViewById(R.id.event_cancel_button);

        starting_date_picker = (DatePicker) findViewById(R.id.event_creation_starting_date_picker);
        finishing_date_picker = (DatePicker) findViewById(R.id.event_creation_finishing_date_picker);

        event_name_edittext = (EditText) findViewById(R.id.event_name_editview) ;
        event_content_edittext = (EditText) findViewById(R.id.event_content_editview);

        event_classification_spinner = (Spinner) findViewById(R.id.event_classification_spinner);

        calendar = Calendar.getInstance();

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);

        starting_date_picker.init(year, calendar.get(Calendar.MONTH), day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                start_year = year;
                start_month = monthOfYear + 1;
                start_day = dayOfMonth;
                Log.d("Starting Date Picker Y" , String.valueOf(start_year));
                Log.d("Starting Date Picker M" , String.valueOf(start_month));
                Log.d("Starting Date Picker D" , String.valueOf(start_day));
            }
        });


        finishing_date_picker.init(year, calendar.get(Calendar.MONTH), day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                finish_year = year;
                finish_month = monthOfYear + 1;
                finish_day = dayOfMonth;
                Log.d("Finishing Date Picker Y" , String.valueOf(finish_year));
                Log.d("Finishing Date Picker M" , String.valueOf(finish_month));
                Log.d("Finishing Date Picker D" , String.valueOf(finish_day));
            }

        });

        if (start_year == 0)
        {
            start_year = year;
        }
        if (finish_year == 0)
        {
            finish_year = year;
        }
        if (start_month == 0)
        {
            start_month = month;
        }
        if (finish_month == 0)
        {
            finish_month = month;
        }
        if (start_day == 0)
        {
            start_day = day;
        }
        if (finish_day == 0)
        {
            finish_day = day;
        }










        list.add(getString(R.string.home));
        list.add(getString(R.string.work));
        list.add(getString(R.string.others));

        String [] mList = getResources().getStringArray(R.array.event_classification_spinner_options);

        adapter = new ArrayAdapter<String>(this , android.R.layout.simple_spinner_dropdown_item, mList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        event_classification_spinner.setAdapter(adapter);

        event_classification_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //String [] classification = getResources().getStringArray(R.array.event_classification_spinner_options);
                // Position tells you which one was selected
                //event_classification = classification[position];
                switch (position)
                {
                    case 0: event_classification = "Home";break;
                    case 1: event_classification = "Work";break;
                    case 2: event_classification = "Others";break;
                    default: event_classification = "Home";break;
                }
                Log.d("eventclass" , event_classification);
                // TODO
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Log.d("eventclass" , event_classification);

        event_name_edittext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Log.d("Event_name" , event_name);
                return true;
            }
        });

        /*
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */


        finish_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
                event_name = event_name_edittext.getText().toString();
                event_content = event_content_edittext.getText().toString();
                int name_length = event_name.length();
                int content_length = event_content.length();
                //Log.d("finish button" , "100");
                boolean finish = true;
                if (name_length <= 0 && (content_length < 0 || content_length > 10000))
                {
                    showMessage(getString(R.string.event_name_content_invalid) , EventCreationScrollingActivity.this);
                    finish = false;
                }
                if (name_length <= 0 && (content_length >= 0 && content_length <= 10000))
                {
                    showMessage(getString(R.string.event_name_invalid) , EventCreationScrollingActivity.this);
                    finish = false;
                }
                if (name_length > 0 && (content_length < 0 || content_length > 10000))
                {
                    showMessage(getString(R.string.event_content_invalid) , EventCreationScrollingActivity.this);
                    finish = false;
                }

                String finish_month_s0 = String.valueOf(finish_month);
                if (finish_month_s0.length() < 2)
                {
                    finish_month_s0 = "0" + finish_month_s0;
                }
                String finish_day_s0 = String.valueOf(finish_day);
                if (finish_day_s0.length() < 2)
                {
                    finish_day_s0 = "0" + finish_day_s0;
                }
                String start_month_s0 = String.valueOf(start_month);
                if (start_month_s0.length() < 2)
                {
                    start_month_s0 = "0" + start_month_s0;
                }
                String start_day_s0 = String.valueOf(start_day);
                if (start_day_s0.length() < 2)
                {
                    start_day_s0 = "0" + start_day_s0;
                }
                String finishing_time0 = finish_year + "-" + finish_month_s0 + "-" + finish_day_s0 + " 00:00:00";
                String starting_time0 = start_year + "-" + start_month_s0 + "-" + start_day_s0 + " 00:00:00";

                if (starting_time0.compareToIgnoreCase(finishing_time0) > 0)
                {
                    showMessage(getString(R.string.date_decisioin) , EventCreationScrollingActivity.this);
                    finish = false;
                }





                if (finish == true)
                {

                    AlertDialog.Builder dialog = new AlertDialog.Builder (EventCreationScrollingActivity.this);
                    String event_classification_lan = "";
                    switch (event_classification)
                    {
                        case "Home": event_classification_lan = getString(R.string.home);break;
                        case "home": event_classification_lan = getString(R.string.home);break;
                        case "Work": event_classification_lan = getString(R.string.work);break;
                        case "work": event_classification_lan = getString(R.string.work);break;
                        case "Others": event_classification_lan = getString(R.string.others);break;
                        case "others": event_classification_lan = getString(R.string.others);break;
                    }
                    dialog.setTitle(getString(R.string.confirm_saving));
                    dialog.setCancelable(false);
                    dialog.setMessage(getString(R.string.event_name) + " : " + event_name + "\n" +
                            getString(R.string.event_starting_date) + " : " + start_year + "/" + start_month + "/" + start_day + "\n" +
                            getString(R.string.event_finishing_date) + " : " + finish_year + "/" + finish_month + "/" + finish_day + "\n" +
                            getString(R.string.event_classification) + " : " + event_classification_lan + "\n" +
                            getString(R.string.event_content) + " : " + event_content + "\n" );
                    //only one dialog.setMessage () is allowed.
                    //dialog.setMessage("Event Starting Date : " + start_year + "/" + start_month + "/" + start_day);
                    //dialog.setMessage("Event Finishing Date : " + finish_year + "/" + finish_month + "/" + finish_day);
                    //dialog.setMessage("Event Classification : " + event_classification);
                    //dialog.setMessage("Event Content : " + event_content);
                    dialog.setNegativeButton(getString(R.string.go_back_to_change), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                            // null
                        }
                    });
                    dialog.setPositiveButton(getString(R.string.finish), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Use the database to save this new event
                            // go back to event list page
                            SQLiteDatabase db = dbHelper.getWritableDatabase();
                            ContentValues values = new ContentValues();
                            values.put("creator_id", currentUser);
                            values.put("name", event_name);
                            values.put("content", event_content);
                            values.put("classification" , event_classification);
                            Log.d("dataclass" , event_classification);
                            String finish_month_s = String.valueOf(finish_month);
                            if (finish_month_s.length() < 2)
                            {
                                finish_month_s = "0" + finish_month_s;
                            }
                            String finish_day_s = String.valueOf(finish_day);
                            if (finish_day_s.length() < 2)
                            {
                                finish_day_s = "0" + finish_day_s;
                            }
                            String start_month_s = String.valueOf(start_month);
                            if (start_month_s.length() < 2)
                            {
                                start_month_s = "0" + start_month_s;
                            }
                            String start_day_s = String.valueOf(start_day);
                            if (start_day_s.length() < 2)
                            {
                                start_day_s = "0" + start_day_s;
                            }
                            String finishing_time = finish_year + "-" + finish_month_s + "-" + finish_day_s + " 00:00:00";
                            String starting_time = start_year + "-" + start_month_s + "-" + start_day_s + " 00:00:00";
                            //SimpleDateFormat finishing_sdf = new SimpleDateFormat("yyyy:mm:dd:hh:mm:ss");
                            //SimpleDateFormat starting_sdf = new SimpleDateFormat("yyyy:mm:dd:hh:mm:ss");

                            // overwrite the current time setting
                            year = calendar.get(Calendar.YEAR);
                            month = calendar.get(Calendar.MONTH) + 1;
                            day = calendar.get(Calendar.DAY_OF_MONTH);
                            hour = calendar.get(Calendar.HOUR_OF_DAY);
                            minute = calendar.get(Calendar.MINUTE);
                            second = calendar.get(Calendar.SECOND);

                            String month_s = String.valueOf(month);
                            if (month_s.length() < 2)
                            {
                                month_s = "0" + month_s;
                            }
                            String day_s = String.valueOf(day);
                            if (day_s.length() < 2)
                            {
                                day_s = "0" + day_s;
                            }
                            String hour_s = String.valueOf(hour);
                            if (hour_s.length() < 2)
                            {
                                hour_s = "0" + hour_s;
                            }
                            String minute_s = String.valueOf(minute);
                            if (minute_s.length() < 2)
                            {
                                minute_s = "0" + minute_s;
                            }
                            String second_s = String.valueOf(second);
                            if (second_s.length() < 2)
                            {
                                second_s = "0" + second_s;
                            }

                            String current_time = year + "-" + month_s + "-" + day_s + " " + hour_s + ":" + minute_s + ":" + second_s;
                            values.put("date_started" , starting_time);
                            values.put("date_finished" , finishing_time);
                            values.put("date_created" , current_time);
                            Log.d("Time_Storing_Side" , "Datebase Storing Side Test");
                            Log.d("starting_time" , starting_time);
                            Log.d("finishing_time" , finishing_time);
                            Log.d("current_time" , current_time);

                            /*
                            try
                            {
                                Date finishing_timestamp0 = finishing_sdf.parse(finishing_time);
                                Date starting_timestamp0 = starting_sdf.parse(starting_time);
                                SimpleDateFormat current_sdf = new SimpleDateFormat("yyyy:mm:dd:hh:mm:ss");
                                java.util.Date current_date = new java.util.Date ();
                                String formatted_current_date = current_sdf.format(current_date);
                                Date current_time = current_sdf.parse(formatted_current_date);
                                //java.sql.Timestamp current_timestamp = new Timestamp(current_time.getTime());
                                //java.sql.Timestamp finishing_timestamp = new Timestamp(finishing_timestamp0.getTime());
                                //java.sql.Timestamp starting_timestamp = new Timestamp(starting_timestamp0.getTime());
                                Log.d("starting_timestamp" , String.valueOf(starting_timestamp0.getTime()));
                                values.put("date_started" , starting_timestamp0.getTime());
                                values.put("date_finished" , finishing_timestamp0.getTime());
                                values.put("date_created" , current_time.getTime());
                            }
                            catch (ParseException pe)
                            {
                                pe.printStackTrace();
                            }
                            */


                            db.insert("Item" , null , values);
                            Log.d("finish_adding_data" , "database");

                            // testing
                            db = dbHelper.getReadableDatabase();
                            String query = "SELECT item_id FROM Item";
                            String [] parameter = new String [] {};
                            Cursor cursor = db.rawQuery(query , parameter);
                            while (cursor.moveToNext()) {
                                item_id = Integer.parseInt(cursor.getString(0).toString());
                                Log.d("We_have_data", String.valueOf(item_id));
                            }
                            if (cursor != null)
                            {
                                cursor.close();
                            }


                            Intent intent = new Intent (EventCreationScrollingActivity.this , NavigationActivity.class);
                            if (page_redirection.equalsIgnoreCase("calendar_mode"))
                            {
                                intent = new Intent(EventCreationScrollingActivity.this , CalendarViewNavigationActivity.class);
                            }
                            intent.putExtra("item_id" , String.valueOf(item_id));
                            intent.putExtra("create_entry" , String.valueOf(true));
                            intent.putExtra("currentUser" , currentUser);
                            intent.putExtra("sorting_order" , sorting_order);
                            intent.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
                            intent.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
                            intent.putExtra("colour" , colour);
                            intent.putExtra("language", LANGUAGE);
                            //intent.putExtra("only_show_class" , only_show_class);
                            startActivity(intent);
                            finish();

                        }
                    });
                    dialog.show();



                }

            }
        });


        cancel_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder (EventCreationScrollingActivity.this);
                dialog.setTitle(getString(R.string.confirm_exit));
                dialog.setCancelable(false);
                dialog.setMessage(getString(R.string.exit_without_saving2));
                dialog.setNegativeButton(getString(R.string.exit_without_saving1), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO
                        Intent intent = new Intent(EventCreationScrollingActivity.this , NavigationActivity.class);
                        if (page_redirection.equalsIgnoreCase("calendar_mode"))
                        {
                            intent = new Intent(EventCreationScrollingActivity.this , CalendarViewNavigationActivity.class);
                        }
                        intent.putExtra("currentUser" , currentUser);
                        intent.putExtra("sorting_order" , sorting_order);
                        intent.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
                        intent.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
                        intent.putExtra("colour" , colour);
                        intent.putExtra("language", LANGUAGE);
                        //intent.putExtra("only_show_class" , only_show_class);
                        startActivity(intent);
                        finish();
                    }
                });
                dialog.setPositiveButton(getString(R.string.go_back), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // should do nothing
                        // null
                    }
                });
                dialog.show();

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
