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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class EventEditScrollingActivity extends AppCompatActivity {



    private Calendar calendar;
    private int year, month, day;
    private int hour, minute, second;

    private int start_year_previous, start_month_previous, start_day_previous;
    private int finish_year_previous, finish_month_previous, finish_day_previous;

    private int start_year_new, start_month_new, start_day_new;
    private int finish_year_new, finish_month_new, finish_day_new;

    private String new_event_name = "";
    private String new_event_content = "";

    private String old_event_name = "";
    private String old_event_content = "";

    private String old_date_created = "";
    private String new_date_created = "";

    private String old_date_started = "";
    private String new_date_started = "";

    private String old_date_finished = "";
    private String new_date_finished = "";


    private String event_classification = "";

    private Button update_button;
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

    private int old_item_days;

    private String old_creator_id = "";

    private String finish_month_s  ="";
    private String finish_day_s = "";

    private String start_month_s = "";
    private String start_day_s = "";

    private String only_show_class = "";

    private String page_redirection = "";

    private String sorting_order = "";
    private String only_show_fourteen_days = "";
    private String do_not_show_negative_days = "";

    private String TAG = "TAG_event_edit_scroll";

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
        messageBox.setCancelable(false);
        messageBox.setMessage(message);
        messageBox.setPositiveButton(getString(R.string.okay) , null);
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
            colour = getIntent().getStringExtra("colour");
        }

        init_theme();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit_scrolling);
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


        Log.d(TAG, "onCreate");


        // get the old data
        currentUser = getIntent().getStringExtra("current_user");
        Log.d("currentUser_edit" , currentUser);

        item_id = getIntent().getIntExtra("item_id" , -1);
        Log.d("Old_Item_Id" , String.valueOf(item_id));

        old_creator_id = getIntent().getStringExtra("creator_id");
        Log.d("Old_Creator_Id" , String.valueOf(old_creator_id));

        event_classification = getIntent().getStringExtra("classification");
        Log.d("Old_classifi" , event_classification);

        old_event_content = getIntent().getStringExtra("item_content");
        Log.d("Old_Item_Content" , old_event_content);

        old_date_created = getIntent().getStringExtra("date_created");
        Log.d("Old_Date_Created" , old_date_created);

        old_date_started = getIntent().getStringExtra("item_start_date");
        Log.d("Old_Date_Started" , old_date_started);

        old_date_finished = getIntent().getStringExtra("item_finish_date");
        Log.d("Old_Date_Finished" , old_date_finished);

        old_event_name = getIntent().getStringExtra("item_name");
        Log.d("Old_Item_Name" , old_event_name);

        old_item_days = getIntent().getIntExtra("item_days" , -1000000);
        //getIntent().getIntExtra("item_days" , -1000000);
        Log.d("Old_Item_Days" , String.valueOf(old_item_days));

        // get old starting date
        Log.d("old_date_started" , old_date_started);
        String [] old_start_1 = old_date_started.split(" ");
        String old_start_2 = old_start_1[0];
        Log.d("old_start_2" , old_start_2);
        String [] old_start_3 = old_start_2.split("-");
        if (old_start_3[2].contains(":") == true)
        {
            old_start_3[2] = old_start_3[2].substring(0 , old_start_3[2].length() - 1);
        }
        start_year_previous = Integer.parseInt(old_start_3[0]);
        start_month_previous = Integer.parseInt(old_start_3[1]);
        Log.d("Start_Month_P" , String.valueOf(start_month_previous));
        start_day_previous = Integer.parseInt(old_start_3[2]);

        // get old finishing date
        String [] old_finish_1 = old_date_finished.split(" ");
        String old_finish_2 = old_finish_1[0];
        String [] old_finish_3 = old_finish_2.split("-");
        finish_year_previous = Integer.parseInt(old_finish_3[0]);
        finish_month_previous = Integer.parseInt(old_finish_3[1]);
        Log.d("Finish_Month_P" , String.valueOf(finish_month_previous));
        finish_day_previous = Integer.parseInt(old_finish_3[2]);




        // set up the database connection
        dbHelper = new MyDatabaseHelper (this, databaseName, null, databaseVersion);
        //DatabaseVersion.version = DatabaseVersion.version + 1;
        dbHelper.getWritableDatabase();

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

        update_button = (Button) findViewById(R.id.event_update_button_edit);
        cancel_button = (Button) findViewById(R.id.event_cancel_button_edit);

        starting_date_picker = (DatePicker) findViewById(R.id.event_edit_starting_date_picker);
        finishing_date_picker = (DatePicker) findViewById(R.id.event_edit_finishing_date_picker);

        event_name_edittext = (EditText) findViewById(R.id.event_name_editview_edit) ;
        event_content_edittext = (EditText) findViewById(R.id.event_content_editview_edit);

        event_classification_spinner = (Spinner) findViewById(R.id.event_classification_spinner_edit);

        calendar = Calendar.getInstance();

        // get the current date
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);


        starting_date_picker.init(start_year_previous, start_month_previous - 1, start_day_previous, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                start_year_new = year;
                start_month_new = monthOfYear + 1;
                start_day_new = dayOfMonth;
                Log.d("Starting Date Y New" , String.valueOf(start_year_new));
                Log.d("Starting Date M New" , String.valueOf(start_month_new));
                Log.d("Starting Date D New" , String.valueOf(start_day_new));
            }
        });



        finishing_date_picker.init(finish_year_previous, finish_month_previous - 1, finish_day_previous, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                finish_year_new = year;
                finish_month_new = monthOfYear + 1;
                finish_day_new = dayOfMonth;
                Log.d("Finishing Date Y New" , String.valueOf(finish_year_new));
                Log.d("Finishing Date M New" , String.valueOf(finish_month_new));
                Log.d("Finishing Date D New" , String.valueOf(finish_day_new));
            }
        });



        if (start_year_new == 0)
        {
            start_year_new = start_year_previous;
        }
        if (finish_year_new == 0)
        {
            finish_year_new = finish_year_previous;
        }
        if (start_month_new == 0)
        {
            start_month_new = start_month_previous;
        }
        if (finish_month_new == 0)
        {
            finish_month_new = finish_month_previous;
        }
        if (start_day_new == 0)
        {
            start_day_new = start_day_previous;
        }
        if (finish_day_new == 0)
        {
            finish_day_new = finish_day_previous;
        }



        list.add(getString(R.string.home));
        list.add(getString(R.string.work));
        list.add(getString(R.string.others));

        String [] mList = getResources().getStringArray(R.array.event_classification_spinner_options);

        adapter = new ArrayAdapter<String>(this , android.R.layout.simple_spinner_dropdown_item, mList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        event_classification_spinner.setAdapter(adapter);

        switch (event_classification)
        {
            case "Home" : event_classification_spinner.setSelection(0);break;
            case "Work" : event_classification_spinner.setSelection(1);break;
            case "Others" : event_classification_spinner.setSelection(2);break;
            default:event_classification_spinner.setSelection(0);break;
        }

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
                Log.d("Event Classification" , event_classification);
                // TODO
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        event_name_edittext.setText(old_event_name.toCharArray(), 0, old_event_name.length());
        event_content_edittext.setText(old_event_content.toCharArray(), 0,  old_event_content.length());

        event_name_edittext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Log.d("Event_name" , new_event_name);
                return true;
            }
        });

        event_content_edittext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Log.d("Event_content" , new_event_content);
                return true;
            }
        });


        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
                new_event_name = event_name_edittext.getText().toString();
                new_event_content = event_content_edittext.getText().toString();
                int name_length = new_event_name.length();
                int content_length = new_event_content.length();
                //Log.d("finish button" , "100");
                boolean finish = true;
                if (name_length <= 0 && (content_length < 0 || content_length > 10000))
                {
                    showMessage(getString(R.string.event_name_content_invalid) , EventEditScrollingActivity.this);
                    finish = false;
                }
                if (name_length <= 0 && (content_length >= 0 && content_length <= 10000))
                {
                    showMessage(getString(R.string.event_name_invalid) , EventEditScrollingActivity.this);
                    finish = false;
                }
                if (name_length > 0 && (content_length < 0 || content_length > 10000))
                {
                    showMessage(getString(R.string.event_content_invalid) , EventEditScrollingActivity.this);
                    finish = false;
                }



                String finish_month_s0 = String.valueOf(finish_month_new);
                if (finish_month_s0.length() < 2)
                {
                    finish_month_s0 = "0" + finish_month_s0;
                }
                String finish_day_s0 = String.valueOf(finish_day_new);
                if (finish_day_s0.length() < 2)
                {
                    finish_day_s0 = "0" + finish_day_s0;
                }
                String start_month_s0 = String.valueOf(start_month_new);
                if (start_month_s0.length() < 2)
                {
                    start_month_s0 = "0" + start_month_s0;
                }
                String start_day_s0 = String.valueOf(start_day_new);
                if (start_day_s0.length() < 2)
                {
                    start_day_s0 = "0" + start_day_s0;
                }
                String finishing_time0 = finish_year_new + "-" + finish_month_s0 + "-" + finish_day_s0 + " 00:00:00";
                String starting_time0 = start_year_new + "-" + start_month_s0 + "-" + start_day_s0 + " 00:00:00";

                if (starting_time0.compareToIgnoreCase(finishing_time0) > 0)
                {
                    showMessage(getString(R.string.date_decisioin) , EventEditScrollingActivity.this);
                    finish = false;
                }





                if (finish == true)
                {
                    finish_month_s = String.valueOf(finish_month_new);
                    if (finish_month_s.length() < 2)
                    {
                        finish_month_s = "0" + finish_month_s;
                    }
                    finish_day_s = String.valueOf(finish_day_new);
                    if (finish_day_s.length() < 2)
                    {
                        finish_day_s = "0" + finish_day_s;
                    }
                    start_month_s = String.valueOf(start_month_new);
                    if (start_month_s.length() < 2)
                    {
                        start_month_s = "0" + start_month_s;
                    }
                    start_day_s = String.valueOf(start_day_new);
                    if (start_day_s.length() < 2)
                    {
                        start_day_s = "0" + start_day_s;
                    }

                    AlertDialog.Builder dialog = new AlertDialog.Builder (EventEditScrollingActivity.this);
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
                    dialog.setMessage(getString(R.string.event_name) + " : " + new_event_name + "\n" +
                            getString(R.string.event_starting_date) + " : " + start_year_new + "/" + start_month_new + "/" + start_day_new + "\n" +
                            getString(R.string.event_finishing_date) + " : " + finish_year_new + "/" + finish_month_new + "/" + finish_day_new + "\n" +
                            getString(R.string.event_classification) + " : " + event_classification_lan + "\n" +
                            getString(R.string.event_content) + " : " + new_event_content + "\n" );
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
                            values.put("name", new_event_name);
                            values.put("content", new_event_content);
                            values.put("classification" , event_classification);

                            String new_finishing_time = finish_year_new + "-" + finish_month_s + "-" + finish_day_s + " 00:00:00";
                            String new_starting_time = start_year_new + "-" + start_month_s + "-" + start_day_s + " 00:00:00";
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

                            String new_current_time = year + "-" + month_s + "-" + day_s + " " + hour_s + ":" + minute_s + ":" + second_s;
                            values.put("date_started" , new_starting_time);
                            values.put("date_finished" , new_finishing_time);
                            values.put("date_created" , new_current_time);
                            Log.d("Time_Storing_Side" , "Datebase Storing Side Test");
                            Log.d("starting_time" , new_starting_time);
                            Log.d("finishing_time" , new_finishing_time);
                            Log.d("current_time" , new_current_time);

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


                            db.update("Item" , values , "item_id = ? " , new String [] {String.valueOf(item_id)});
                            Log.d("finish_adding_data" , "database");

                            // testing
                            /*
                            db = dbHelper.getReadableDatabase();
                            String query = "SELECT item_id FROM Item";
                            String [] parameter = new String [] {};
                            Cursor cursor = db.rawQuery(query , parameter);
                            while (cursor.moveToNext()) {
                                item_id = Integer.parseInt(cursor.getString(0).toString());
                                Log.d("We_have_data", String.valueOf(item_id));
                            }
                            */

                            Intent intent = new Intent (EventEditScrollingActivity.this , NavigationActivity.class);
                            if (page_redirection.equalsIgnoreCase("calendar_mode"))
                            {
                                intent = new Intent(EventEditScrollingActivity.this , CalendarViewNavigationActivity.class);
                            }
                            intent.putExtra("item_id" , String.valueOf(item_id));
                            intent.putExtra("create_entry" , String.valueOf(true));
                            intent.putExtra("currentUser" , currentUser);
                            intent.putExtra("sorting_order" , sorting_order);
                            intent.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
                            intent.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
                            intent.putExtra("colour" , colour);
                            intent.putExtra("language", LANGUAGE);

                            startActivity(intent);
                            finish();

                        }
                    });
                    dialog.show();



                }

            }
        });


        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder (EventEditScrollingActivity.this);
                dialog.setTitle(getString(R.string.confirm_exit));
                dialog.setCancelable(false);
                dialog.setMessage(getString(R.string.exit_without_saving2));
                dialog.setNegativeButton(getString(R.string.exit_without_saving1), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO
                        Intent intent = new Intent(EventEditScrollingActivity.this , NavigationActivity.class);
                        if (page_redirection.equalsIgnoreCase("calendar_mode"))
                        {
                            intent = new Intent(EventEditScrollingActivity.this , CalendarViewNavigationActivity.class);

                        }
                        intent.putExtra("currentUser" , currentUser);
                        intent.putExtra("sorting_order" , sorting_order);
                        intent.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
                        intent.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
                        intent.putExtra("colour" , colour);
                        intent.putExtra("language", LANGUAGE);
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
