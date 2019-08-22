package com.timetable.todolist.Schedule.lifereminders;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class EventDetailsScrollingActivity extends AppCompatActivity {



    private int item_id;
    private String event_classification = "";
    private String event_content = "";
    private String date_created = "";
    private String date_started = "";
    private String date_finished = "";
    private String event_name = "";
    private String creator_id = "";
    private int item_days;
    private String currentUser = "";

    private MyDatabaseHelper dbHelper;

    private SQLiteDatabase db;

    private String sorting_order = "";
    private String do_not_show_negative_days = "";
    private String only_show_fourteen_days = "";

    private String page_redirection = "";

    private String TAG = "TAG_event_detail_scroll";

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

    /*
        int item_id = Integer.parseInt(String.valueOf(event_detail.get("item_id")));
        Log.d("Testing" , String.valueOf(item_id));
        intent.putExtra("item_id" , item_id);

        String classification = String.valueOf(event_detail.get("classification"));
        Log.d("Testing" , classification);
        intent.putExtra("classification" , classification);

        String item_content = String.valueOf(event_detail.get("item_content1"));
        Log.d("Testing" , item_content);
        intent.putExtra("item_content" , item_content);

        String date_created = (String)event_detail.get("date_created");
        Log.d("Testing" , date_created);
        intent.putExtra("date_created" , date_created);

        String date_started = (String)event_detail.get("date_started");
        Log.d("Testing", date_started);
        intent.putExtra("item_start_date" , date_started);

        String item_finish_date = (String)event_detail.get("item_finish_date1");
        Log.d("Testing", item_finish_date);
        intent.putExtra("item_finish_date" , item_finish_date);

        String item_name = (String)event_detail.get("item_name1");
        Log.d("Testing" , item_name);
        intent.putExtra("item_name" , item_name);

        String creator_id = (String)event_detail.get("creator_id");
        Log.d("Testing" , creator_id);
        intent.putExtra("creator_id" , creator_id);

        int item_days = Integer.parseInt(String.valueOf(event_detail.get("item_days1")));
        Log.d("Testing" , String.valueOf(item_days));
        intent.putExtra("item_days" , item_days);

        intent.putExtra("current_user" , currentUser);
        Log.d("Testing" , currentUser);
    */

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
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_event_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        dbHelper = new MyDatabaseHelper(this, databaseName, null, databaseVersion);
        dbHelper.getWritableDatabase();
        db = dbHelper.getReadableDatabase();




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

        if (getIntent().getStringExtra("page_redirection") != null)
        {
            if (!getIntent().getStringExtra("page_redirection").equalsIgnoreCase(""))
            {
                page_redirection = getIntent().getStringExtra("page_redirection");
            }
        }

        Log.d(TAG, "onCreate");

        // getting intent data from intent
        currentUser = getIntent().getStringExtra("current_user");
        Log.d("currentUser_detail" , currentUser);

        item_id = getIntent().getIntExtra("item_id" , -1);
        Log.d("detail_Item_Id" , String.valueOf(item_id));

        creator_id = getIntent().getStringExtra("creator_id");
        Log.d("detail_Creator_Id" , String.valueOf(creator_id));

        event_classification = getIntent().getStringExtra("classification");
        Log.d("detail_classifi" , event_classification);

        event_content = getIntent().getStringExtra("item_content");
        Log.d("detail_Item_Content" , event_content);

        date_created = getIntent().getStringExtra("date_created");
        Log.d("detail_Date_Created" , date_created);

        date_started = getIntent().getStringExtra("item_start_date");
        Log.d("detail_Date_Started" , date_started);

        date_finished = getIntent().getStringExtra("item_finish_date");
        Log.d("detail_Date_Finished" , date_finished);

        event_name = getIntent().getStringExtra("item_name");
        Log.d("detail_Item_Name" , event_name);

        item_days = getIntent().getIntExtra("item_days" , -1000000);
        //getIntent().getIntExtra("item_days" , -1000000);
        Log.d("detail_Item_Days" , String.valueOf(item_days));

        TextView event_detail_name_field = (TextView) findViewById(R.id.event_detail_name_field);
        event_detail_name_field.setText(event_name);

        TextView event_detail_classification_field = (TextView) findViewById(R.id.event_detail_classification_field);
        //event_detail_classification_field.setText(event_classification);
        switch (event_classification)
        {
            case "Home": event_detail_classification_field.setText(getString(R.string.home));break;
            case "home": event_detail_classification_field.setText(getString(R.string.home));break;
            case "Work": event_detail_classification_field.setText(getString(R.string.work));break;
            case "work": event_detail_classification_field.setText(getString(R.string.work));break;
            case "Others": event_detail_classification_field.setText(getString(R.string.others));break;
            case "others": event_detail_classification_field.setText(getString(R.string.others));break;
        }

        TextView event_detail_starting_date_field = (TextView) findViewById(R.id.event_detail_starting_date_field);
        String put_date_started = "";
        put_date_started = date_started.split(" ")[0];
        event_detail_starting_date_field.setText(put_date_started);

        TextView event_detail_finishing_date_field = (TextView) findViewById(R.id.event_detail_finishing_date_field);
        event_detail_finishing_date_field.setText(date_finished);

        TextView event_detail_creating_date_field = (TextView) findViewById(R.id.event_detail_creating_date_field);
        event_detail_creating_date_field.setText(date_created);

        TextView event_detail_days_remain_field = (TextView) findViewById(R.id.event_detail_days_remain_field);
        event_detail_days_remain_field.setText(String.valueOf(item_days));

        TextView event_detail_content_field = (TextView) findViewById(R.id.event_detail_content_field);
        event_detail_content_field.setText(event_content);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, getString(R.string.edit_your_event), Snackbar.LENGTH_LONG)
                        .setAction(getString(R.string.edit), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(EventDetailsScrollingActivity.this, EventEditScrollingActivity.class);
                                intent.putExtra("item_id" , item_id);
                                intent.putExtra("classification" , event_classification);
                                intent.putExtra("item_content" , event_content);
                                intent.putExtra("date_created" , date_created);
                                intent.putExtra("item_start_date" , date_started);
                                intent.putExtra("item_finish_date" , date_finished);
                                intent.putExtra("item_name" , event_name);
                                intent.putExtra("creator_id" , creator_id);
                                intent.putExtra("item_days" , item_days);
                                intent.putExtra("current_user" , currentUser);
                                intent.putExtra("sorting_order" , sorting_order);
                                intent.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
                                intent.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
                                intent.putExtra("page_redirection", page_redirection);
                                intent.putExtra("colour" , colour);
                                intent.putExtra("language", LANGUAGE);
                                startActivity(intent);

                            }
                        }).show();
            }
        });



    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_edit)
        {
            Intent intent = new Intent(EventDetailsScrollingActivity.this, EventEditScrollingActivity.class);
            intent.putExtra("item_id" , item_id);
            intent.putExtra("classification" , event_classification);
            intent.putExtra("item_content" , event_content);
            intent.putExtra("date_created" , date_created);
            intent.putExtra("item_start_date" , date_started);
            intent.putExtra("item_finish_date" , date_finished);
            intent.putExtra("item_name" , event_name);
            intent.putExtra("creator_id" , creator_id);
            intent.putExtra("item_days" , item_days);
            intent.putExtra("current_user" , currentUser);
            intent.putExtra("sorting_order" , sorting_order);
            intent.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
            intent.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
            intent.putExtra("page_redirection" , page_redirection);
            intent.putExtra("colour" , colour);
            intent.putExtra("language", LANGUAGE);
            startActivity(intent);
            return true;
        }
        else if (id == android.R.id.home)
        {
            Intent intent = new Intent (EventDetailsScrollingActivity.this , NavigationActivity.class);
            if (page_redirection.equalsIgnoreCase("calendar_mode"))
            {
                intent = new Intent (EventDetailsScrollingActivity.this , CalendarViewNavigationActivity.class);
            }
            intent.putExtra("currentUser" , currentUser);
            intent.putExtra("sorting_order" , sorting_order);
            intent.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
            intent.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
            intent.putExtra("colour" , colour);
            intent.putExtra("language", LANGUAGE);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_delete)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(EventDetailsScrollingActivity.this);
            builder.setTitle(getString(R.string.confirm));
            builder.setCancelable(false);
            builder.setMessage(getString(R.string.delete_event));
            builder.setNegativeButton(getString(R.string.go_back), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Should do nothing here
                    // Null
                }
            });

            builder.setPositiveButton(getString(R.string.delete), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    db = dbHelper.getWritableDatabase();
                    db.delete("item" , "item_id = ?" , new String [] {String.valueOf(item_id)});
                    Intent intent = new Intent (EventDetailsScrollingActivity.this , NavigationActivity.class);
                    if (page_redirection.equalsIgnoreCase("calendar_mode"))
                    {
                        intent = new Intent (EventDetailsScrollingActivity.this , CalendarViewNavigationActivity.class);
                    }
                    intent.putExtra("sorting_order" , sorting_order);
                    intent.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
                    intent.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
                    intent.putExtra("currentUser" , currentUser);
                    intent.putExtra("colour", colour);
                    intent.putExtra("language", LANGUAGE);
                    startActivity(intent);
                    finish();
                }
            });

            builder.create();
            builder.show();

            return true;
        }
        else if (id == R.id.action_email)
        {
            Intent intent = new Intent (EventDetailsScrollingActivity.this, SendEmailActivity.class);
            intent.putExtra("current_user", currentUser);
            intent.putExtra("item_id", item_id);
            intent.putExtra("creator_id", creator_id);
            intent.putExtra("classification", event_classification);
            intent.putExtra("item_content", event_content);
            intent.putExtra("date_created", date_created);
            intent.putExtra("item_start_date", date_started);
            intent.putExtra("item_finish_date", date_finished);
            intent.putExtra("item_name", event_name);
            intent.putExtra("item_days", item_days);
            intent.putExtra("language", LANGUAGE);
            intent.putExtra("colour", colour);
            intent.putExtra("only_show_fourteen_days", only_show_fourteen_days);
            intent.putExtra("sorting_order", sorting_order);
            intent.putExtra("do_not_show_negative_days", do_not_show_negative_days);
            intent.putExtra("page_redirection", page_redirection);

            startActivity(intent);
            finish();

        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_toolbar_event_detail , menu);
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
