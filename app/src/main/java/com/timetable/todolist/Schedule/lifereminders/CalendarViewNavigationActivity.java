package com.timetable.todolist.Schedule.lifereminders;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CalendarViewNavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String original_sorting_order = "";
    private String original_do_not_show_negative_days = "";
    private String original_only_show_fourteen_days = "";
    private String original_turn_on_notification = "";

    private String sorting_order = "";
    private String do_not_show_negative_days = "";
    private String only_show_fourteen_days = "";
    private String turn_on_notification = "";

    private String currentUser = "";

    private Calendar calendar;

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;

    private int current_selected_year;
    private int current_selected_month;
    private int current_selected_day;
    private int current_selected_hour;
    private int current_selected_minute;
    private int current_selected_second;

    private String current_selected_year_s = "";
    private String current_selected_month_s = "";
    private String current_selected_day_s = "";

    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private SQLiteDatabase db1;

    private ListView calendar_mode_listview;

    private SimpleAdapter simpleAdapter;

    private String only_show_class_00 = "";

    private String current_selected_finish_date = "";

    private DatePicker date_picker;

    public int counter = 0;

    private int count_all;
    private int count_home;
    private int count_work;
    private int count_others;

    private String page_redirection = "";

    private int original_notification_hour = -10;
    private int original_notification_minute = -10;

    private String TAG = "TAG_calendar_mode_navi";

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



    public int different_day_int (Date date1 , Date date2)
    {
        int days = (int) ((date1.getTime() - date2.getTime()) / (1000*3600*24));
        return days;
    }







    public double different_day_double (Date date1 , Date date2)
    {
        double days = (double) ((date1.getTime() - date2.getTime()) / (1000*3600*24));
        return days;
    }






    public void setCounter0 (String currentUser)
    {
        String query = "SELECT COUNT(*) " +
                "FROM Item " +
                "WHERE creator_id = ? COLLATE NOCASE AND classification = ? COLLATE NOCASE ";

        String query0 = "SELECT COUNT(*) " +
                "FROM Item " +
                "WHERE creator_id = ? COLLATE NOCASE ";

        String [] parameter1 = new String [] {currentUser};
        String [] parameter2 = new String [] {currentUser, "Home"};
        String [] parameter3 = new String [] {currentUser, "Work"};
        String [] parameter4 = new String [] {currentUser, "Others"};

        Cursor cursor;


        cursor = db.rawQuery(query0, parameter1);
        while (cursor.moveToNext())
        {
            int length = Integer.parseInt(cursor.getString(0).toString());
            //counter = counter + length;
            //setMenuCounter(R.id.nav_all_event , length);
            count_all = length;
            //String classs = cursor.getString(0).toString();
            Log.d("classs" , String.valueOf(length));
        }


        cursor = db.rawQuery(query, parameter2);
        while (cursor.moveToNext())
        {
            int length = Integer.parseInt(cursor.getString(0).toString());
            //counter = counter + length;
            //setMenuCounter(R.id.nav_home_event , length);
            count_home = length;
        }


        cursor = db.rawQuery(query, parameter3);
        while (cursor.moveToNext())
        {
            int length = Integer.parseInt(cursor.getString(0).toString());
            //counter = counter + length;
            //setMenuCounter(R.id.nav_work_event , length);
            count_work = length;
        }


        //cursor = db.query("Item" , new String [] {"COUNT(*)"} , );
        cursor = db.rawQuery(query, parameter4);
        while (cursor.moveToNext())
        {
            int length = Integer.parseInt(cursor.getString(0).toString());
            //counter = counter + length;

            Log.d("lengthhh" , String.valueOf(length));
            //setMenuCounter(R.id.nav_others_event , length);

            count_others = length;
        }

    }






    public ArrayList <Map<String, Object>> cursorHandler (Cursor cursor, ArrayList<Map<String, Object>> result)
    {
        while (cursor.moveToNext()) {

            //long starting_date = new SimpleDateFormat("yyyy:mm:dd:hh:mm:ss").parse(cursor.getString(0).toString()).getTime();
            //long finishing_date = new SimpleDateFormat("yyyy:mm:dd:hh:mm:ss").parse(cursor.getString(1).toString()).getTime();
            //long current_date = new SimpleDateFormat("yyyy:mm:dd:hh:mm:ss").parse(cursor.getString(2).toString()).getTime();
            String date_created = cursor.getString(0).toString();
            String starting_date = cursor.getString(1).toString();
            String finishing_date = cursor.getString(2).toString();
            String creator_id = cursor.getString(3).toString();
            String event_name = cursor.getString(4).toString();
            String classification = cursor.getString(5).toString();
            String content = cursor.getString(6).toString();
            int itemId = Integer.parseInt(cursor.getString(7).toString());
            //long remain_days = Integer.parseInt(cursor.getString(8).toString());
            //Log.d("remain_days" , String.valueOf(remain_days));

            String put_finishing_date = finishing_date.split(" ")[0];
            SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");

            calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH) + 1;
            day = calendar.get(Calendar.DAY_OF_MONTH);
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            minute = calendar.get(Calendar.MINUTE);
            second = calendar.get(Calendar.SECOND);

            Map<String, Object> entry = new HashMap<String, Object>();

            String classification_lan = "";
            switch (classification)
            {
                case "Home": classification_lan = getString(R.string.home);break;
                case "home": classification_lan = getString(R.string.home);break;
                case "Work": classification_lan = getString(R.string.work);break;
                case "work": classification_lan = getString(R.string.work);break;
                case "Others": classification_lan = getString(R.string.others);break;
                case "others": classification_lan = getString(R.string.others);break;
            }

            entry.put("item_name1", event_name);
            entry.put("item_content1", content);
            entry.put("item_finish_date1" , put_finishing_date);
            Log.d("entry_name" , event_name);
            Log.d("entry_content" , content);

            entry.put("date_created", date_created);
            entry.put("classification", classification);
            entry.put("classification_lan", classification_lan);
            int image_id = 0;
            switch (classification)
            {
                case "Home": image_id = R.drawable.home; break;
                case "home": image_id = R.drawable.home; break;
                case "Work": image_id = R.drawable.work; break;
                case "work": image_id = R.drawable.work; break;
                case "Others": image_id = R.drawable.others; break;
                case "others":image_id = R.drawable.others;break;
                default:image_id = R.drawable.others;break;
            }
            entry.put("image_id" , image_id);
            entry.put("date_started", starting_date);
            entry.put("creator_id", creator_id);
            entry.put("item_id", itemId);
            int days_diff = 0;
            try
            {
                Date date1 = format.parse(finishing_date);

                String current_day = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day) + " " + "00:00:00";
                Date date2 = format.parse(current_day);

                Log.d("days_diff_int" , String.valueOf(different_day_int(date1 , date2)));
                Log.d("days_diff_double" , String.valueOf(different_day_double(date1 , date2)));
                Log.d("what_is_today" , current_day);
                Log.d("what_is_finish_day" , finishing_date);
                days_diff = different_day_int(date1 , date2);
                entry.put("item_days1" , days_diff);
            }
            catch (ParseException pe)
            {
                pe.printStackTrace();
            }



            //entry.put("starting_date", starting_date);
            //entry.put("finishing_date", finishing_date);
            //entry.put("current_date", current_date);
            //entry.put("creator_id", creator_id);
            //entry.put("classification", classification);
            //entry.put("item_id", itemId);



            if (only_show_fourteen_days.equalsIgnoreCase("true") && days_diff >14)
            {
                // should not add this.
            }
            else if (do_not_show_negative_days.equalsIgnoreCase("true") && days_diff <0)
            {
                // should not add this
            }
            else
            {
                result.add(entry);
            }
            //entry.clear();


            Log.d("Added a new data entry", String.valueOf(itemId));
        }
        if (cursor != null)
        {
            cursor.close();
        }
        return result;
    }




    // get the data for currentUser
    public ArrayList <Map <String , Object> > getData (String currentUser, String only_show_class, String sorting_order, String only_show_fourteen_days, String current_selected_finish_date)
    {
        ArrayList<Map<String, Object>> result = new ArrayList<Map<String, Object>> ();
        Cursor cursor;
        if (!only_show_class.equalsIgnoreCase("") && only_show_class.equalsIgnoreCase("All"))
        {

            String query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                    "FROM Item " +
                    "WHERE creator_id = ? " + "AND date_finished = ? " +
                    "ORDER BY CAST(item_id AS REAL) ASC";


            if (sorting_order.equalsIgnoreCase("item_id ASC"))
            {
                query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                        "FROM Item " +
                        "WHERE creator_id = ? " + "AND date_finished = ? " +
                        "ORDER BY CAST(item_id AS REAL) ASC";
            }
            if (sorting_order.equalsIgnoreCase("item_id DESC"))
            {
                query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                        "FROM Item " +
                        "WHERE creator_id = ? " + "AND date_finished = ? " +
                        "ORDER BY CAST(item_id AS REAL) DESC";
            }
            if (sorting_order.equalsIgnoreCase("date_finished ASC"))
            {
                query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                        "FROM Item " +
                        "WHERE creator_id = ? " + "AND date_finished = ? " +
                        "ORDER BY DATETIME(date_finished) ASC";
            }
            String [] parameter = new String [] {currentUser, current_selected_finish_date};
            cursor = db.rawQuery(query , parameter);
            result = cursorHandler(cursor, result);
            Log.d("N_Order_Info" , sorting_order);

        }
        else if (!only_show_class.equalsIgnoreCase("") && !only_show_class.equalsIgnoreCase("All"))
        {

            String query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                    "FROM Item " +
                    "WHERE creator_id = ? AND classification = ? COLLATE NOCASE " + "AND date_finished = ? " +
                    "ORDER BY CAST(item_id AS REAL) ASC";


            if (sorting_order.equalsIgnoreCase("item_id ASC"))
            {
                query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                        "FROM Item " +
                        "WHERE creator_id = ? AND classification = ? COLLATE NOCASE " + "AND date_finished = ? " +
                        "ORDER BY CAST(item_id AS REAL) ASC";
            }
            if (sorting_order.equalsIgnoreCase("item_id DESC"))
            {
                query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                        "FROM Item " +
                        "WHERE creator_id = ? AND classification = ? COLLATE NOCASE " + "AND date_finished = ? " +
                        "ORDER BY CAST(item_id AS REAL) DESC";
            }
            if (sorting_order.equalsIgnoreCase("date_finished ASC"))
            {
                query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                        "FROM Item " +
                        "WHERE creator_id = ? AND classification = ? COLLATE NOCASE " + "AND date_finished = ? " +
                        "ORDER BY DATETIME(date_finished) ASC";
            }
            String [] parameter = new String [] {currentUser, only_show_class, current_selected_finish_date};
            cursor = db.rawQuery(query , parameter);
            result = cursorHandler(cursor, result);
        }

        //cursor.close();
        Log.d("length_of_result" , String.valueOf(result.size()));

        return result;
    }




    public ArrayList <Map <String , Object> > getDataLike (String currentUser, String only_show_class, String sorting_order, String only_show_fourteen_days, String like_query, String current_selected_finish_date)
    {
        ArrayList<Map<String, Object>> result = new ArrayList<Map<String, Object>> ();
        Cursor cursor;
        if (!only_show_class.equalsIgnoreCase("") && only_show_class.equalsIgnoreCase("All"))
        {

            String query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                    "FROM Item " +
                    "WHERE creator_id = ? " + " AND ( name LIKE '%" + like_query + "%' OR content LIKE '%" + like_query + "%' ) " + "AND date_finished = ? " +
                    "ORDER BY CAST(item_id AS REAL) ASC";


            if (sorting_order.equalsIgnoreCase("item_id ASC"))
            {
                query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                        "FROM Item " +
                        "WHERE creator_id = ? " + " AND ( name LIKE '%" + like_query + "%' OR content LIKE '%" + like_query + "%' ) " + "AND date_finished = ? " +
                        "ORDER BY CAST(item_id AS REAL) ASC";
            }
            if (sorting_order.equalsIgnoreCase("item_id DESC"))
            {
                query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                        "FROM Item " +
                        "WHERE creator_id = ? " + " AND ( name LIKE '%" + like_query + "%' OR content LIKE '%" + like_query + "%' ) " + "AND date_finished = ? " +
                        "ORDER BY CAST(item_id AS REAL) DESC";
            }
            if (sorting_order.equalsIgnoreCase("date_finished ASC"))
            {
                query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                        "FROM Item " +
                        "WHERE creator_id = ? " + " AND ( name LIKE '%" + like_query + "%' OR content LIKE '%" + like_query + "%' ) " + "AND date_finished = ? " +
                        "ORDER BY DATETIME(date_finished) ASC";
            }
            String [] parameter = new String [] {currentUser, current_selected_finish_date};
            cursor = db.rawQuery(query , parameter);
            result = cursorHandler(cursor, result);
            Log.d("N_Order_Info" , sorting_order);

        }
        else if (!only_show_class.equalsIgnoreCase("") && !only_show_class.equalsIgnoreCase("All"))
        {

            String query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                    "FROM Item " +
                    "WHERE creator_id = ? AND classification = ? COLLATE NOCASE " + " AND ( name LIKE '%" + like_query + "%' OR content LIKE '%" + like_query + "%' ) " + "AND date_finished = ? " +
                    "ORDER BY CAST(item_id AS REAL) ASC";


            if (sorting_order.equalsIgnoreCase("item_id ASC"))
            {
                query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                        "FROM Item " +
                        "WHERE creator_id = ? AND classification = ? COLLATE NOCASE " + " AND ( name LIKE '%" + like_query + "%' OR content LIKE '%" + like_query + "%' ) " + "AND date_finished = ? " +
                        "ORDER BY CAST(item_id AS REAL) ASC";
            }
            if (sorting_order.equalsIgnoreCase("item_id DESC"))
            {
                query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                        "FROM Item " +
                        "WHERE creator_id = ? AND classification = ? COLLATE NOCASE " + " AND ( name LIKE '%" + like_query + "%' OR content LIKE '%" + like_query + "%' ) " + "AND date_finished = ? " +
                        "ORDER BY CAST(item_id AS REAL) DESC";
            }
            if (sorting_order.equalsIgnoreCase("date_finished ASC"))
            {
                query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                        "FROM Item " +
                        "WHERE creator_id = ? AND classification = ? COLLATE NOCASE " + " AND ( name LIKE '%" + like_query + "%' OR content LIKE '%" + like_query + "%' ) " + "AND date_finished = ? " +
                        "ORDER BY DATETIME(date_finished) ASC";
            }
            String [] parameter = new String [] {currentUser, only_show_class, current_selected_finish_date};
            cursor = db.rawQuery(query , parameter);
            result = cursorHandler(cursor, result);
        }

        //cursor.close();
        Log.d("length_of_result" , String.valueOf(result.size()));

        return result;
    }






    public void clear_event_list ()
    {

        setCounter0(currentUser);


        ArrayList<Map<String, Object>> item_data = new ArrayList<Map<String, Object>>();
        if (only_show_class_00.equalsIgnoreCase("")) {
            only_show_class_00 = "All";
        }

        current_selected_year_s = String.valueOf(current_selected_year);
        if (current_selected_year_s.length() < 2)
        {
            current_selected_year_s = "0" + current_selected_year_s;
        }
        current_selected_month_s = String.valueOf(current_selected_month);
        if (current_selected_month_s.length() < 2)
        {
            current_selected_month_s = "0" + current_selected_month_s;
        }
        current_selected_day_s = String.valueOf(current_selected_day);
        if (current_selected_day_s.length() < 2)
        {
            current_selected_day_s = "0" + current_selected_day_s;
        }

        current_selected_finish_date = current_selected_year_s + "-" + current_selected_month_s + "-" + current_selected_day_s + " 00:00:00";
        Log.d("curr_select_FD" , current_selected_finish_date);
        item_data = getData(currentUser , only_show_class_00, sorting_order, only_show_fourteen_days, current_selected_finish_date);
        if (getData(currentUser , only_show_class_00, sorting_order, only_show_fourteen_days, current_selected_finish_date) == null)
        {
            item_data = new ArrayList<Map<String, Object>>();
        }
        simpleAdapter = new SimpleAdapter(CalendarViewNavigationActivity.this, item_data, R.layout.calendar_mode_listview_item, new String [] {"item_name1" , "item_content1" , "item_finish_date1" , "item_days1" , "classification_lan", "image_id"}, new int [] {R.id.item_name_c , R.id.item_content_c , R.id.item_finish_date_c , R.id.item_days_c , R.id.item_classification_c, R.id.item_class_image_c});
        calendar_mode_listview.setAdapter(simpleAdapter);

        //event_listview.setBackgroundResource(R.drawable.customshape);
        //event_listview.setTextFilterEnabled(true);
        Log.d("Simple_Adapter" , "Finished setting adapter");
        Log.d("get_Data" , String.valueOf(getData(currentUser, only_show_class_00, sorting_order, only_show_fourteen_days, current_selected_finish_date).size()));

    }





    public void setIntentData (Intent intent, HashMap<String, Object> event_detail)
    {
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

        intent.putExtra("only_show_class" , only_show_class_00);
        Log.d("Testing" , only_show_class_00);

        intent.putExtra("page_redirection" , page_redirection);

        intent.putExtra("turn_on_notification" , turn_on_notification);

        intent.putExtra("notification_hour" , original_notification_hour);
        intent.putExtra("notification_minute" , original_notification_minute);

        intent.putExtra("colour" , colour);

        intent.putExtra("language", LANGUAGE);

        return;
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
        setContentView(R.layout.activity_calendar_view_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        Log.d(TAG, "onCreate");
        original_do_not_show_negative_days = getIntent().getStringExtra("do_not_show_negative_days");
        original_only_show_fourteen_days = getIntent().getStringExtra("only_show_fourteen_days");
        original_sorting_order = getIntent().getStringExtra("sorting_order");
        original_turn_on_notification = getIntent().getStringExtra("turn_on_notification");
        currentUser = getIntent().getStringExtra("currentUser");
        //Log.d("original_do_not_show" , original_do_not_show_negative_days);
        //Log.d("original_only_show" , original_only_show_fourteen_days);
        //Log.d("original_sorting" , original_sorting_order);
        Log.d("currentUser" , currentUser);


        original_notification_hour = getIntent().getIntExtra("notification_hour" , -10);
        original_notification_minute = getIntent().getIntExtra("notification_minute" , -10);


        calendar = Calendar.getInstance();

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);



        current_selected_year = year;
        current_selected_month = month;
        current_selected_day = day;
        current_selected_hour = hour;
        current_selected_minute = minute;
        current_selected_second = second;

        counter = 0;
        Log.d("curr_Y" , String.valueOf(current_selected_year));
        Log.d("curr_M" , String.valueOf(current_selected_month));
        Log.d("curr_D" , String.valueOf(current_selected_day));
        date_picker = (DatePicker) findViewById(R.id.calendar_mode_calendar);
        date_picker.init(current_selected_year, current_selected_month - 1, current_selected_day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                current_selected_year = year;
                current_selected_month = monthOfYear + 1;
                current_selected_day = dayOfMonth;
                Log.d("curr_select_Y" , String.valueOf(current_selected_year));
                Log.d("curr_select_M" , String.valueOf(current_selected_month));
                Log.d("curr_select_D" , String.valueOf(current_selected_day));
                counter = counter + 1;
                Log.d("curr_counter" , String.valueOf(counter));
                clear_event_list();

            }
        });



        dbHelper = new MyDatabaseHelper(this, databaseName, null, databaseVersion);
        dbHelper.getWritableDatabase();
        db = dbHelper.getReadableDatabase();


        calendar_mode_listview = (ListView) findViewById(R.id.calendar_mode_listview);




        if (page_redirection.equalsIgnoreCase(""))
        {
            page_redirection = "calendar_mode";
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
        if (turn_on_notification.equalsIgnoreCase(""))
        {
            turn_on_notification = "false";
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

        if (getIntent().getStringExtra("turn_on_notification") != null)
        {
            if (!getIntent().getStringExtra("turn_on_notification").equalsIgnoreCase("discard"))
            {
                turn_on_notification = getIntent().getStringExtra("turn_on_notification");
            }
        }
        Log.d("turn_on" , turn_on_notification);

        /*
        if (getIntent().getStringExtra("page_redirection") != null)
        {
            page_redirection = getIntent().getStringExtra("page_redirection");
        }
        */



        clear_event_list();



        calendar_mode_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Direct the user to a new page to view their event,
                // and in this page there should have a BACK DELETE and EDIT button
                final String content = calendar_mode_listview.getItemAtPosition(position) + "";
                final HashMap<String, Object> event_detail = (HashMap<String, Object>) calendar_mode_listview.getItemAtPosition(position);
                Intent intent = new Intent (CalendarViewNavigationActivity.this, EventDetailsScrollingActivity.class);

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
                setIntentData(intent, event_detail);

                startActivity(intent);
            }
        });




        calendar_mode_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String content = calendar_mode_listview.getItemAtPosition(position) + "";
                final HashMap<String, Object> event_detail = (HashMap<String, Object>) calendar_mode_listview.getItemAtPosition(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(CalendarViewNavigationActivity.this);
                builder.setTitle(getString(R.string.make_changes));
                builder.setMessage(getString(R.string.change_event));
                builder.setCancelable(false);
                builder.setPositiveButton(getString(R.string.go_back_without_saving) , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        // Should do nothing here
                        // null
                    }
                });
                builder.setNegativeButton(getString(R.string.delete), new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick (DialogInterface dialog, int which)
                    {
                        // TODO
                        // Should delete the entry

                        // Testing the details of the content
                        Log.d("item_content" , content);

                        int event_tobe_deleted = (int)event_detail.get("item_id");
                        Log.d("to_be_deleted" , String.valueOf(event_tobe_deleted));

                        db1 = dbHelper.getWritableDatabase();


                        //String query = "DELETE FROM Item WHERE item_id = ? ";
                        //String [] parameter = new String [] {String.valueOf(event_tobe_deleted)};
                        //Cursor cursor = db.rawQuery(query , parameter);

                        db1.delete("item" , "item_id = ?" , new String [] {String.valueOf(event_tobe_deleted)});

                        clear_event_list();

                    }
                });
                builder.setNeutralButton(getString(R.string.edit), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO
                        // Should let the user to edit this event without creating a new one
                        Intent intent = new Intent (CalendarViewNavigationActivity.this, EventEditScrollingActivity.class);

                        Log.d("Testing" , content);

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

                        setIntentData(intent, event_detail);

                        startActivity(intent);
                    }
                });
                //builder.getButton();
                builder.create();
                builder.show();
                return true;
            }
        });





        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        /*
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        /*
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add New Event", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent (CalendarViewNavigationActivity.this , EventCreationScrollingActivity.class);
                                intent.putExtra("currentUser" , currentUser);
                                intent.putExtra("page_redirection" , page_redirection);
                                intent.putExtra("turn_on_notification" , turn_on_notification);
                                intent.putExtra("notification_hour" , original_notification_hour);
                                intent.putExtra("notification_minute" , original_notification_minute);
                                intent.putExtra("colour" , colour);
                                startActivity(intent);
                            }
                        })
                        .setActionTextColor(Color.GREEN).show();
            }
        });
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.calendar_mode_nav_page, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView mySearchView = (SearchView) menu.findItem(R.id.action_searching_c).getActionView();
        mySearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        mySearchView.setSubmitButtonEnabled(true);
        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!TextUtils.isEmpty(query))
                {
                    //event_listview.setFilterText(newText);
                    ArrayList<Map<String, Object>> item_data = new ArrayList<Map<String, Object>>();
                    item_data = getDataLike(currentUser , only_show_class_00, sorting_order, only_show_fourteen_days, query, current_selected_finish_date);
                    if (getDataLike(currentUser , only_show_class_00, sorting_order, only_show_fourteen_days, query, current_selected_finish_date) == null)
                    {
                        item_data = new ArrayList<Map<String, Object>>();
                    }
                    simpleAdapter = new SimpleAdapter(CalendarViewNavigationActivity.this, item_data, R.layout.calendar_mode_listview_item, new String [] {"item_name1" , "item_content1" , "item_finish_date1" , "item_days1" , "classification_lan", "image_id"}, new int [] {R.id.item_name_c , R.id.item_content_c , R.id.item_finish_date_c , R.id.item_days_c, R.id.item_classification_c, R.id.item_class_image_c});
                    calendar_mode_listview.setAdapter(simpleAdapter);
                }
                else
                {
                    //event_listview.clearTextFilter();
                    ArrayList<Map<String, Object>> item_data = new ArrayList<Map<String, Object>>();
                    item_data = getData(currentUser , only_show_class_00, sorting_order, only_show_fourteen_days, current_selected_finish_date);
                    if (getData(currentUser , only_show_class_00, sorting_order, only_show_fourteen_days, current_selected_finish_date) == null)
                    {
                        item_data = new ArrayList<Map<String, Object>>();
                    }
                    simpleAdapter = new SimpleAdapter(CalendarViewNavigationActivity.this, item_data, R.layout.calendar_mode_listview_item, new String [] {"item_name1" , "item_content1" , "item_finish_date1" , "item_days1" , "classification_lan", "image_id"}, new int [] {R.id.item_name_c , R.id.item_content_c , R.id.item_finish_date_c , R.id.item_days_c, R.id.item_classification_c, R.id.item_class_image_c});
                    calendar_mode_listview.setAdapter(simpleAdapter);
                }
                Log.d("only_show_class" , only_show_class_00);
                Log.d("only_show_days" , only_show_fourteen_days);
                Log.d("only_order" , sorting_order);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText))
                {
                    //event_listview.setFilterText(newText);
                    ArrayList<Map<String, Object>> item_data = new ArrayList<Map<String, Object>>();
                    item_data = getDataLike(currentUser , only_show_class_00, sorting_order, only_show_fourteen_days, newText, current_selected_finish_date);
                    if (getDataLike(currentUser , only_show_class_00, sorting_order, only_show_fourteen_days, newText, current_selected_finish_date) == null)
                    {
                        item_data = new ArrayList<Map<String, Object>>();
                    }
                    simpleAdapter = new SimpleAdapter(CalendarViewNavigationActivity.this, item_data, R.layout.calendar_mode_listview_item, new String [] {"item_name1" , "item_content1" , "item_finish_date1" , "item_days1" , "classification_lan", "image_id"}, new int [] {R.id.item_name_c , R.id.item_content_c , R.id.item_finish_date_c , R.id.item_days_c, R.id.item_classification_c, R.id.item_class_image_c});
                    calendar_mode_listview.setAdapter(simpleAdapter);
                }
                else
                {
                    //event_listview.clearTextFilter();
                    ArrayList<Map<String, Object>> item_data = new ArrayList<Map<String, Object>>();
                    item_data = getData(currentUser , only_show_class_00, sorting_order, only_show_fourteen_days, current_selected_finish_date);
                    if (getData(currentUser , only_show_class_00, sorting_order, only_show_fourteen_days, current_selected_finish_date) == null)
                    {
                        item_data = new ArrayList<Map<String, Object>>();
                    }
                    simpleAdapter = new SimpleAdapter(CalendarViewNavigationActivity.this, item_data, R.layout.calendar_mode_listview_item, new String [] {"item_name1" , "item_content1" , "item_finish_date1" , "item_days1" , "classification_lan", "image_id"}, new int [] {R.id.item_name_c , R.id.item_content_c , R.id.item_finish_date_c , R.id.item_days_c, R.id.item_classification_c, R.id.item_class_image_c});
                    calendar_mode_listview.setAdapter(simpleAdapter);
                }
                Log.d("only_show_class" , only_show_class_00);
                Log.d("only_show_days" , only_show_fourteen_days);
                Log.d("only_order" , sorting_order);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_event_c)
        {
            Intent intent = new Intent(CalendarViewNavigationActivity.this, EventCreationScrollingActivity.class);
            intent.putExtra("currentUser" , currentUser);
            intent.putExtra("page_redirection" , page_redirection);
            intent.putExtra("colour" , colour);
            intent.putExtra("language", LANGUAGE);
            startActivity(intent);
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings_c) {
            // goto another avtivity
            Intent intent = new Intent (CalendarViewNavigationActivity.this , SettingPageActivity.class);
            intent.putExtra("currentUser" , currentUser);
            intent.putExtra("sorting_order" , sorting_order);
            intent.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
            intent.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
            intent.putExtra("turn_on_notification" , turn_on_notification);
            intent.putExtra("page_redirection" , page_redirection);
            intent.putExtra("notification_hour" , original_notification_hour);
            intent.putExtra("notification_minute" , original_notification_minute);
            intent.putExtra("colour" , colour);
            intent.putExtra("language", LANGUAGE);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_display_listmode)
        {
            Intent intent = new Intent (CalendarViewNavigationActivity.this , NavigationActivity.class);
            intent.putExtra("currentUser" , currentUser);
            intent.putExtra("sorting_order" , sorting_order);
            intent.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
            intent.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
            intent.putExtra("page_redirection" , page_redirection);
            intent.putExtra("turn_on_notification" , turn_on_notification);
            intent.putExtra("notification_hour" , original_notification_hour);
            intent.putExtra("notification_minute" , original_notification_minute);
            intent.putExtra("colour" , colour);
            intent.putExtra("language", LANGUAGE);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.nav_settings_c)
        {
            Intent intent = new Intent (CalendarViewNavigationActivity.this , SettingPageActivity.class);
            intent.putExtra("currentUser" , currentUser);
            intent.putExtra("sorting_order" , sorting_order);
            intent.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
            intent.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
            intent.putExtra("page_redirection" , page_redirection);
            intent.putExtra("turn_on_notification" , turn_on_notification);
            intent.putExtra("notification_hour" , original_notification_hour);
            intent.putExtra("notification_minute" , original_notification_minute);
            intent.putExtra("colour" , colour);
            intent.putExtra("language", LANGUAGE);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.nav_about_c)
        {
            Intent intent = new Intent (CalendarViewNavigationActivity.this , AboutPageMain.class);
            intent.putExtra("currentUser" , currentUser);
            intent.putExtra("count_all" , count_all);
            intent.putExtra("count_home" , count_home);
            intent.putExtra("count_work" , count_work);
            intent.putExtra("count_others" , count_others);
            intent.putExtra("page_redirection" , page_redirection);
            intent.putExtra("turn_on_notification" , turn_on_notification);
            intent.putExtra("notification_hour" , original_notification_hour);
            intent.putExtra("notification_minute" , original_notification_minute);
            intent.putExtra("colour" , colour);
            intent.putExtra("language", LANGUAGE);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.nav_add_event_c)
        {
            Intent intent = new Intent (CalendarViewNavigationActivity.this , EventCreationScrollingActivity.class);
            intent.putExtra("currentUser" , currentUser);
            intent.putExtra("page_redirection" , page_redirection);
            intent.putExtra("colour" , colour);
            intent.putExtra("language", LANGUAGE);
            startActivity(intent);
            Log.d("Action New Event Button" , "Go to new page");
            return true;
        }
        else if (id == R.id.nav_log_out_c)
        {
            Log.d("logout_button" , "logout_button");
            AlertDialog.Builder dialogL = new AlertDialog.Builder(CalendarViewNavigationActivity.this);
            dialogL.setTitle(getString(R.string.confirm_logout1));
            dialogL.setMessage(R.string.confirm_logout2);
            dialogL.setCancelable(false);
            dialogL.setNegativeButton(getString(R.string.go_back), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Should do nothing here
                    // null
                }
            });
            dialogL.setPositiveButton(getString(R.string.log_out), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent (CalendarViewNavigationActivity.this , LoginActivity.class);
                    intent.putExtra("page_redirection" , page_redirection);
                    intent.putExtra("language", LANGUAGE);
                    startActivity(intent);
                    finish();
                }
            });
            dialogL.create();
            dialogL.show();
        }
        else if (id == R.id.nav_function_word)
        {
            Intent intent = new Intent (CalendarViewNavigationActivity.this, WordsActivity.class);

            intent.putExtra("currentUser" , currentUser);
            intent.putExtra("sorting_order" , sorting_order);
            intent.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
            intent.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
            intent.putExtra("page_redirection" , page_redirection);
            intent.putExtra("turn_on_notification" , turn_on_notification);
            intent.putExtra("notification_hour" , original_notification_hour);
            intent.putExtra("notification_minute" , original_notification_minute);
            intent.putExtra("colour" , colour);
            intent.putExtra("language", LANGUAGE);

            startActivity(intent);
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
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
