package com.timetable.todolist.Schedule.lifereminders;

import android.app.Dialog;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.app.ListActivity;
import android.provider.ContactsContract;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String currentUser;

    private ListView event_listview;

    private SimpleAdapter simpleAdapter;

    private MyDatabaseHelper dbHelper;

    private SQLiteDatabase db;
    private SQLiteDatabase db1;

    private int item_id;
    private boolean do_create;

    private Calendar calendar;
    private int year, month, day;
    private int hour, minute, second;

    private static String only_show_class = "";

    private String event_name = "";
    private String event_content = "";
    private String event_finish_date = "";
    private String event_start_date = "";
    private String event_create_date = "";
    private String event_classification = "";
    private String event_creator_id = "";
    private int event_id;
    private int event_days_remain;

    private String sorting_order = "";
    private String only_show_fourteen_days = "";
    private String do_not_show_negative_days = "";
    private String turn_on_notification = "";


    private int count_all;
    private int count_home;
    private int count_work;
    private int count_others;

    private int original_notification_hour = -10;
    private int original_notification_minute = -10;

    private String TAG = "TAG_navi_acticity";

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




    private void setMenuCounter (@IdRes int menu_item_id , int count)
    {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        TextView view = (TextView) navigationView.getMenu().findItem(menu_item_id).getActionView();
        view.setText(String.valueOf(count));
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
            setMenuCounter(R.id.nav_all_event , length);
            count_all = length;
            //String classs = cursor.getString(0).toString();
            Log.d("class" , String.valueOf(length));
        }


        cursor = db.rawQuery(query, parameter2);
        while (cursor.moveToNext())
        {
            int length = Integer.parseInt(cursor.getString(0).toString());
            //counter = counter + length;
            setMenuCounter(R.id.nav_home_event , length);
            count_home = length;
        }


        cursor = db.rawQuery(query, parameter3);
        while (cursor.moveToNext())
        {
            int length = Integer.parseInt(cursor.getString(0).toString());
            //counter = counter + length;
            setMenuCounter(R.id.nav_work_event , length);
            count_work = length;
        }


        //cursor = db.query("Item" , new String [] {"COUNT(*)"} , );
        cursor = db.rawQuery(query, parameter4);
        while (cursor.moveToNext())
        {
            int length = Integer.parseInt(cursor.getString(0).toString());
            //counter = counter + length;

            Log.d("lengthhh" , String.valueOf(length));
            setMenuCounter(R.id.nav_others_event , length);

            count_others = length;
        }
        if (cursor != null)
        {
            cursor.close();
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

            SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
            String put_finishing_date = finishing_date.split(" ")[0];

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
            entry.put("classification_lan", classification_lan);
            entry.put("classification" , classification);
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
    public ArrayList <Map <String , Object> > getData (String currentUser, String only_show_class, String sorting_order, String only_show_fourteen_days)
    {
        ArrayList<Map<String, Object>> result = new ArrayList<Map<String, Object>> ();
        Cursor cursor;
        if (!only_show_class.equalsIgnoreCase("") && only_show_class.equalsIgnoreCase("All"))
        {

            String query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                    "FROM Item " +
                    "WHERE creator_id = ? " +
                    "ORDER BY CAST(item_id AS REAL) ASC";


            if (sorting_order.equalsIgnoreCase("item_id ASC"))
            {
                query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                        "FROM Item " +
                        "WHERE creator_id = ? " +
                        "ORDER BY CAST(item_id AS REAL) ASC";
            }
            if (sorting_order.equalsIgnoreCase("item_id DESC"))
            {
                query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                        "FROM Item " +
                        "WHERE creator_id = ? " +
                        "ORDER BY CAST(item_id AS REAL) DESC";
            }
            if (sorting_order.equalsIgnoreCase("date_finished ASC"))
            {
                query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                        "FROM Item " +
                        "WHERE creator_id = ? " +
                        "ORDER BY DATETIME(date_finished) ASC";
            }
            String [] parameter = new String [] {currentUser};
            cursor = db.rawQuery(query , parameter);
            result = cursorHandler(cursor, result);
            Log.d("N_Order_Info" , sorting_order);

        }
        else if (!only_show_class.equalsIgnoreCase("") && !only_show_class.equalsIgnoreCase("All"))
        {

            String query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                    "FROM Item " +
                    "WHERE creator_id = ? AND classification = ? COLLATE NOCASE " +
                    "ORDER BY CAST(item_id AS REAL) ASC";


            if (sorting_order.equalsIgnoreCase("item_id ASC"))
            {
                query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                        "FROM Item " +
                        "WHERE creator_id = ? AND classification = ? COLLATE NOCASE " +
                        "ORDER BY CAST(item_id AS REAL) ASC";
            }
            if (sorting_order.equalsIgnoreCase("item_id DESC"))
            {
                query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                        "FROM Item " +
                        "WHERE creator_id = ? AND classification = ? COLLATE NOCASE " +
                        "ORDER BY CAST(item_id AS REAL) DESC";
            }
            if (sorting_order.equalsIgnoreCase("date_finished ASC"))
            {
                query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                        "FROM Item " +
                        "WHERE creator_id = ? AND classification = ? COLLATE NOCASE " +
                        "ORDER BY DATETIME(date_finished) ASC";
            }
            String [] parameter = new String [] {currentUser, only_show_class};
            cursor = db.rawQuery(query , parameter);
            result = cursorHandler(cursor, result);
        }

        //cursor.close();
        Log.d("length_of_result" , String.valueOf(result.size()));

        return result;
    }






    public ArrayList <Map <String , Object> > getDataLike (String currentUser, String only_show_class, String sorting_order, String only_show_fourteen_days, String like_query)
    {
        ArrayList<Map<String, Object>> result = new ArrayList<Map<String, Object>> ();
        Cursor cursor;
        if (!only_show_class.equalsIgnoreCase("") && only_show_class.equalsIgnoreCase("All"))
        {

            String query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                    "FROM Item " +
                    "WHERE creator_id = ? " + " AND ( name LIKE '%" + like_query + "%' OR content LIKE '%" + like_query + "%' ) " +
                    "ORDER BY CAST(item_id AS REAL) ASC";


            if (sorting_order.equalsIgnoreCase("item_id ASC"))
            {
                query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                        "FROM Item " +
                        "WHERE creator_id = ? " + " AND ( name LIKE '%" + like_query + "%' OR content LIKE '%" + like_query + "%' ) " +
                        "ORDER BY CAST(item_id AS REAL) ASC";
            }
            if (sorting_order.equalsIgnoreCase("item_id DESC"))
            {
                query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                        "FROM Item " +
                        "WHERE creator_id = ? " + " AND ( name LIKE '%" + like_query + "%' OR content LIKE '%" + like_query + "%' ) " +
                        "ORDER BY CAST(item_id AS REAL) DESC";
            }
            if (sorting_order.equalsIgnoreCase("date_finished ASC"))
            {
                query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                        "FROM Item " +
                        "WHERE creator_id = ? " + " AND ( name LIKE '%" + like_query + "%' OR content LIKE '%" + like_query + "%' ) " +
                        "ORDER BY DATETIME(date_finished) ASC";
            }
            String [] parameter = new String [] {currentUser};
            cursor = db.rawQuery(query , parameter);
            result = cursorHandler(cursor, result);
            Log.d("N_Order_Info" , sorting_order);

        }
        else if (!only_show_class.equalsIgnoreCase("") && !only_show_class.equalsIgnoreCase("All"))
        {

            String query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                    "FROM Item " +
                    "WHERE creator_id = ? AND classification = ? COLLATE NOCASE " + " AND ( name LIKE '%" + like_query + "%' OR content LIKE '%" + like_query + "%' ) " +
                    "ORDER BY CAST(item_id AS REAL) ASC";


            if (sorting_order.equalsIgnoreCase("item_id ASC"))
            {
                query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                        "FROM Item " +
                        "WHERE creator_id = ? AND classification = ? COLLATE NOCASE " + " AND ( name LIKE '%" + like_query + "%' OR content LIKE '%" + like_query + "%' ) " +
                        "ORDER BY CAST(item_id AS REAL) ASC";
            }
            if (sorting_order.equalsIgnoreCase("item_id DESC"))
            {
                query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                        "FROM Item " +
                        "WHERE creator_id = ? AND classification = ? COLLATE NOCASE " + " AND ( name LIKE '%" + like_query + "%' OR content LIKE '%" + like_query + "%' ) " +
                        "ORDER BY CAST(item_id AS REAL) DESC";
            }
            if (sorting_order.equalsIgnoreCase("date_finished ASC"))
            {
                query = "SELECT date_created , date_started , date_finished , creator_id , name , classification , content , item_id " +
                        "FROM Item " +
                        "WHERE creator_id = ? AND classification = ? COLLATE NOCASE " + " AND ( name LIKE '%" + like_query + "%' OR content LIKE '%" + like_query + "%' ) " +
                        "ORDER BY DATETIME(date_finished) ASC";
            }
            String [] parameter = new String [] {currentUser, only_show_class};
            cursor = db.rawQuery(query , parameter);
            result = cursorHandler(cursor, result);
        }

        //cursor.close();
        Log.d("length_of_result" , String.valueOf(result.size()));

        return result;
    }






    // clear the event array list
    public void clear_event_list ()
    {
        /*
        if (eventList != null && eventList.size() > 0)
        {
            eventList.removeAll(eventList);
            simpleAdapter.notifyDataSetChanged();
            event_listview.setAdapter(simpleAdapter);
        }
        */

        setCounter0(currentUser);
        ArrayList<Map<String, Object>> item_data = new ArrayList<Map<String, Object>>();
        item_data = getData(currentUser , only_show_class, sorting_order, only_show_fourteen_days);
        if (getData(currentUser , only_show_class, sorting_order, only_show_fourteen_days) == null)
        {
            item_data = new ArrayList<Map<String, Object>>();
        }
        simpleAdapter = new SimpleAdapter(NavigationActivity.this, item_data, R.layout.listview_item, new String [] {"item_name1" , "item_content1" , "item_finish_date1" , "item_days1" , "classification_lan", "image_id"}, new int [] {R.id.item_name , R.id.item_content , R.id.item_finish_date , R.id.item_days , R.id.item_classification, R.id.item_class_image});
        event_listview.setAdapter(simpleAdapter);

        //event_listview.setBackgroundResource(R.drawable.customshape);
        //event_listview.setTextFilterEnabled(true);
        Log.d("Simple_Adapter" , "Finished setting adapter");
        Log.d("get_Data" , String.valueOf(getData(currentUser, only_show_class, sorting_order, only_show_fourteen_days).size()));
        /*
        Log.d("text_text" , String.valueOf(event_listview.getChildCount()));
        for (int i = 0 ; i < event_listview.getCount() ; i++)
        {
            long item = event_listview.getItemIdAtPosition(i);
            LinearLayout linearLayout = (LinearLayout) findViewById((int)item);
            TextView text_class = (TextView) linearLayout.findViewById(R.id.item_classification);
            Log.d("text_text" , String.valueOf(text_class.getText()));

        }
        */

        /*
        TextView text_class = (TextView) findViewById(R.id.item_classification);
        switch (String.valueOf(text_class.getText()))
        {
            case "Home" : Log.d("text_text" , "Home");
            case "Work" : Log.d("text_text" , "Work");
            case "Others" : Log.d("text_text" , "Others");
        }
        */

        //Utility.setListViewHeightBasedOnChildren(event_listview);
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

        intent.putExtra("only_show_class" , only_show_class);
        Log.d("Testing" , only_show_class);

        intent.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
        intent.putExtra("sorting_order" , sorting_order);
        intent.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
        intent.putExtra("turn_on_notification" , turn_on_notification);

        intent.putExtra("notification_hour" , original_notification_hour);
        intent.putExtra("notification_minute" , original_notification_minute);

        intent.putExtra("colour" , colour);

        intent.putExtra("language", LANGUAGE);

        return;
    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {


        // set up

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
        setContentView(R.layout.activity_navigation);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d(TAG, "onCreate");

        /*
        SearchView searchView = (SearchView) findViewById(R.id.search_view);
        searchView.setIconifiedByDefault(true);
        searchView.onActionViewExpanded();
        searchView.requestFocus();
        searchView.setSubmitButtonEnabled(true);
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.requestFocusFromTouch();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //SnackbarUtil.show(toolbar , query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        */

        currentUser = getIntent().getStringExtra("currentUser");

        // database set up
        dbHelper = new MyDatabaseHelper(this, databaseName, null, databaseVersion);
        dbHelper.getWritableDatabase();
        db = dbHelper.getReadableDatabase();

        //searchView = (SearchView) findViewById(R.id.action_search);
        /*
        searchView.setIconifiedByDefault(true);
        searchView.setSubmitButtonEnabled(true);
        searchView.onActionViewExpanded();
        searchView.setIconifiedByDefault(true);
        */
        /*
        mCursor =

        mAdapter = new SimpleCursorAdapter(this , android.R.layout.simple_list_item_1 , mCursor , new String [] {ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY} , new int [] {android.R.id.text1});
        */
        event_listview = (ListView) findViewById(R.id.event_listview);

        //event_listview.setAdapter(mAdapter);


        // get the current user
        if (only_show_fourteen_days.equalsIgnoreCase(""))
        {
            only_show_fourteen_days = "false";
        }
        if (sorting_order.equalsIgnoreCase(""))
        {
            sorting_order = "date_finished ASC";
        }
        if (only_show_class.equalsIgnoreCase(""))
        {
            only_show_class = "All";
        }
        if (onlyShowClass.only_show_class_global.equalsIgnoreCase(""))
        {
            onlyShowClass.only_show_class_global = "All";
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
        if (getIntent().getStringExtra("only_show_class") != null)
        {
            only_show_class = getIntent().getStringExtra("only_show_class");
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

        original_notification_hour = getIntent().getIntExtra("notification_hour" , -10);
        original_notification_minute = getIntent().getIntExtra("notification_minute" , -10);


        // set up the event list view

        //only_show_class = "all";
        //only_show_class = getIntent().getStringExtra("only_show_class");
        clear_event_list();

        event_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Direct the user to a new page to view their event,
                // and in this page there should have a BACK DELETE and EDIT button
                final String content = event_listview.getItemAtPosition(position) + "";
                final HashMap<String, Object> event_detail = (HashMap<String, Object>) event_listview.getItemAtPosition(position);
                Intent intent = new Intent (NavigationActivity.this, EventDetailsScrollingActivity.class);

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

        event_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String content = event_listview.getItemAtPosition(position) + "";
                final HashMap<String, Object> event_detail = (HashMap<String, Object>) event_listview.getItemAtPosition(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(NavigationActivity.this);
                builder.setTitle(getString(R.string.make_changes));
                builder.setCancelable(false);
                builder.setMessage(getString(R.string.change_event));
                builder.setPositiveButton(getString(R.string.go_back_without_saving), new DialogInterface.OnClickListener() {
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
                        Intent intent = new Intent (NavigationActivity.this, EventEditScrollingActivity.class);

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
                        finish();
                    }
                });
                //builder.getButton();
                builder.create();
                builder.show();
                return true;
            }
        });





        // set up the fab
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, getString(R.string.add_new_event), Snackbar.LENGTH_LONG)
                        .setAction(getString(R.string.add), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent (NavigationActivity.this , EventCreationScrollingActivity.class);
                                intent.putExtra("currentUser" , currentUser);
                                intent.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
                                intent.putExtra("sorting_order" , sorting_order);
                                intent.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
                                intent.putExtra("turn_on_notification" , turn_on_notification);
                                intent.putExtra("colour" , colour);
                                intent.putExtra("language", LANGUAGE);

                                startActivity(intent);
                                finish();
                            }
                        })
                        .show();
            }
        });


        // start handling that search bar

        //searchView = (SearchView) findViewById(R.id.action_search);

        /*
        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setSubmitButtonEnabled(true);
        Log.d("hello_null" , String.valueOf(searchView == null));


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!TextUtils.isEmpty(query))
                {
                    event_listview.setFilterText(query);
                }
                else
                {
                    event_listview.clearTextFilter();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText))
                {
                    event_listview.setFilterText(newText);
                }
                else
                {
                    event_listview.clearTextFilter();
                }
                return true;
            }
        });
        */




        // Navigation Controller
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
        getMenuInflater().inflate(R.menu.navigation_nav_page, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView mySearchView = (SearchView) menu.findItem(R.id.action_searching).getActionView();
        mySearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        mySearchView.setSubmitButtonEnabled(true);
        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!TextUtils.isEmpty(query))
                {
                    //event_listview.setFilterText(newText);
                    ArrayList<Map<String, Object>> item_data = new ArrayList<Map<String, Object>>();
                    item_data = getDataLike(currentUser , only_show_class, sorting_order, only_show_fourteen_days, query);
                    if (getDataLike(currentUser , only_show_class, sorting_order, only_show_fourteen_days, query) == null)
                    {
                        item_data = new ArrayList<Map<String, Object>>();
                    }
                    simpleAdapter = new SimpleAdapter(NavigationActivity.this, item_data, R.layout.listview_item, new String [] {"item_name1" , "item_content1" , "item_finish_date1" , "item_days1" , "classification_lan", "image_id"}, new int [] {R.id.item_name , R.id.item_content , R.id.item_finish_date , R.id.item_days, R.id.item_classification, R.id.item_class_image});
                    event_listview.setAdapter(simpleAdapter);
                }
                else
                {
                    //event_listview.clearTextFilter();
                    ArrayList<Map<String, Object>> item_data = new ArrayList<Map<String, Object>>();
                    item_data = getData(currentUser , only_show_class, sorting_order, only_show_fourteen_days);
                    if (getData(currentUser , only_show_class, sorting_order, only_show_fourteen_days) == null)
                    {
                        item_data = new ArrayList<Map<String, Object>>();
                    }
                    simpleAdapter = new SimpleAdapter(NavigationActivity.this, item_data, R.layout.listview_item, new String [] {"item_name1" , "item_content1" , "item_finish_date1" , "item_days1" , "classification_lan", "image_id"}, new int [] {R.id.item_name , R.id.item_content , R.id.item_finish_date , R.id.item_days, R.id.item_classification, R.id.item_class_image});
                    event_listview.setAdapter(simpleAdapter);
                }
                Log.d("only_show_class" , only_show_class);
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
                    item_data = getDataLike(currentUser , only_show_class, sorting_order, only_show_fourteen_days, newText);
                    if (getDataLike(currentUser , only_show_class, sorting_order, only_show_fourteen_days, newText) == null)
                    {
                        item_data = new ArrayList<Map<String, Object>>();
                    }
                    simpleAdapter = new SimpleAdapter(NavigationActivity.this, item_data, R.layout.listview_item, new String [] {"item_name1" , "item_content1" , "item_finish_date1" , "item_days1" , "classification_lan", "image_id"}, new int [] {R.id.item_name , R.id.item_content , R.id.item_finish_date , R.id.item_days, R.id.item_classification, R.id.item_class_image});
                    event_listview.setAdapter(simpleAdapter);
                }
                else
                {
                    //event_listview.clearTextFilter();
                    ArrayList<Map<String, Object>> item_data = new ArrayList<Map<String, Object>>();
                    item_data = getData(currentUser , only_show_class, sorting_order, only_show_fourteen_days);
                    if (getData(currentUser , only_show_class, sorting_order, only_show_fourteen_days) == null)
                    {
                        item_data = new ArrayList<Map<String, Object>>();
                    }
                    simpleAdapter = new SimpleAdapter(NavigationActivity.this, item_data, R.layout.listview_item, new String [] {"item_name1" , "item_content1" , "item_finish_date1" , "item_days1" , "classification_lan", "image_id"}, new int [] {R.id.item_name , R.id.item_content , R.id.item_finish_date , R.id.item_days, R.id.item_classification, R.id.item_class_image});
                    event_listview.setAdapter(simpleAdapter);
                }
                Log.d("only_show_class" , only_show_class);
                Log.d("only_show_days" , only_show_fourteen_days);
                Log.d("only_order" , sorting_order);
                return true;
            }
        });




        return super.onCreateOptionsMenu(menu);
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar listview_item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Menu on the top right corner

        if (id == R.id.action_add_event)
        {
            Intent intent = new Intent(NavigationActivity.this, EventCreationScrollingActivity.class);
            intent.putExtra("currentUser" , currentUser);
            intent.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
            intent.putExtra("sorting_order" , sorting_order);
            intent.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
            intent.putExtra("turn_on_notification" , turn_on_notification);
            intent.putExtra("notification_hour" , original_notification_hour);
            intent.putExtra("notification_minute" , original_notification_minute);
            intent.putExtra("colour" , colour);
            intent.putExtra("language", LANGUAGE);

            startActivity(intent);
            finish();
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            // goto another avtivity
            Intent intent = new Intent (NavigationActivity.this , SettingPageActivity.class);
            intent.putExtra("currentUser" , currentUser);
            intent.putExtra("sorting_order" , sorting_order);
            intent.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
            intent.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
            intent.putExtra("turn_on_notification" , turn_on_notification);
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
        // Handle navigation_nav_page view listview_item clicks here.
        // Menu on the left hand side
        int id = item.getItemId();

        if (id == R.id.nav_all_event)
        {
            // Handle the all event action
            only_show_class = "All";
            clear_event_list();
        }
        else if (id == R.id.nav_home_event)
        {
            only_show_class = "Home";
            clear_event_list();
        }
        else if (id == R.id.nav_work_event)
        {
            only_show_class = "Work";
            clear_event_list();
        }
        else if (id == R.id.nav_others_event)
        {
            only_show_class = "Others";
            clear_event_list();
        }
        else if (id == R.id.nav_settings)
        {
            Intent intent = new Intent (NavigationActivity.this , SettingPageActivity.class);
            intent.putExtra("currentUser" , currentUser);
            intent.putExtra("sorting_order" , sorting_order);
            intent.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
            intent.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
            intent.putExtra("turn_on_notification" , turn_on_notification);
            intent.putExtra("notification_hour" , original_notification_hour);
            intent.putExtra("notification_minute" , original_notification_minute);
            intent.putExtra("colour" , colour);
            intent.putExtra("language", LANGUAGE);

            startActivity(intent);
            return true;
        }
        else if (id == R.id.nav_about)
        {
            Intent intent = new Intent (NavigationActivity.this , AboutPageMain.class);
            intent.putExtra("currentUser" , currentUser);
            intent.putExtra("count_all" , count_all);
            intent.putExtra("count_home" , count_home);
            intent.putExtra("count_work" , count_work);
            intent.putExtra("count_others" , count_others);
            intent.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
            intent.putExtra("sorting_order" , sorting_order);
            intent.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
            intent.putExtra("turn_on_notification" , turn_on_notification);
            intent.putExtra("notification_hour" , original_notification_hour);
            intent.putExtra("notification_minute" , original_notification_minute);
            intent.putExtra("colour" , colour);
            intent.putExtra("language", LANGUAGE);

            startActivity(intent);
            return true;
        }
        else if (id == R.id.nav_add_event)
        {
            Intent intent = new Intent (NavigationActivity.this , EventCreationScrollingActivity.class);
            intent.putExtra("currentUser" , currentUser);
            intent.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
            intent.putExtra("sorting_order" , sorting_order);
            intent.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
            intent.putExtra("turn_on_notification" , turn_on_notification);
            intent.putExtra("notification_hour" , original_notification_hour);
            intent.putExtra("notification_minute" , original_notification_minute);
            intent.putExtra("colour" , colour);
            intent.putExtra("language", LANGUAGE);

            startActivity(intent);
            finish();
            Log.d("Action New Event Button" , "Go to new page");
            return true;
        }
        else if (id == R.id.nav_log_out)
        {
            Log.d("logout_button" , "logout_button");
            AlertDialog.Builder dialogL = new AlertDialog.Builder(NavigationActivity.this);
            dialogL.setTitle(getString(R.string.confirm_logout1));
            dialogL.setCancelable(false);
            dialogL.setMessage(getString(R.string.confirm_logout2));
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
                    Intent intent = new Intent (NavigationActivity.this , LoginActivity.class);
                    intent.putExtra("language" , LANGUAGE);
                    startActivity(intent);
                    finish();
                }
            });
            dialogL.create();
            dialogL.show();
        }
        else if (id == R.id.nav_display_calendar)
        {
            Log.d("calendar_page_trigger" , "go_to_calendar_page_now");
            Intent intent = new Intent (NavigationActivity.this , CalendarViewNavigationActivity.class);
            intent.putExtra("currentUser" , currentUser);
            intent.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
            intent.putExtra("sorting_order" , sorting_order);
            intent.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
            intent.putExtra("turn_on_notification" , turn_on_notification);
            intent.putExtra("notification_hour" , original_notification_hour);
            intent.putExtra("notification_minute" , original_notification_minute);
            intent.putExtra("colour" , colour);
            intent.putExtra("language", LANGUAGE);

            startActivity(intent);

        }
        else if (id == R.id.nav_function_word)
        {
            Log.d("word_page_trigger", "go_to_word_page");
            Intent intent = new Intent (NavigationActivity.this, WordsActivity.class);

            intent.putExtra("currentUser" , currentUser);
            intent.putExtra("sorting_order" , sorting_order);
            intent.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
            intent.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
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
