package com.timetable.todolist.Schedule.lifereminders;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AboutPageMain extends AppCompatActivity {

    //private TextView mTextMessage;

    private String currentUser = "";
    private int count_all;
    private int count_home;
    private int count_work;
    private int count_others;

    private View view_about;
    private View view_developer;
    private View view_statistics;

    private ViewPager viewPager;

    private List<View> viewList;

    private PagerAdapter pageAdapter;

    private MenuItem menuItem;

    private MyDatabaseHelper dbHelper;

    private Button logout_button;
    private Button change_password_button;

    private Button licence_button;
    private Button version_button;

    private EditText edit1;
    private EditText edit2;

    private SQLiteDatabase db;

    private TextView count_all_field;
    private TextView count_home_field;
    private TextView count_work_field;
    private TextView count_others_field;

    private TextView username_textview;

    private TextView about_this_app;

    private TextView about_developer;

    private String page_redirection = "";

    private String sorting_order = "";
    private String do_not_show_negative_days = "";
    private String only_show_fourteen_days = "";

    private BottomNavigationView navigation;

    private String TAG = "TAG_about_page";

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

    //private BottomNavigationView.OnCreateContextMenuListener

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_about:
                    //mTextMessage.setText(R.string.title_About);
                    viewPager.setCurrentItem(0);
                    licence_button = (Button) findViewById(R.id.about_page_licence_button);
                    licence_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent (AboutPageMain.this, DisplayLicencesActivity.class);
                            Log.d("licence_button" , "licence_button");
                            intent.putExtra("currentUser" , currentUser);
                            intent.putExtra("count_all" , count_all);
                            intent.putExtra("count_home" , count_home);
                            intent.putExtra("count_work" , count_work);
                            intent.putExtra("count_others" , count_others);
                            intent.putExtra("sorting_order" , sorting_order);
                            intent.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
                            intent.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
                            intent.putExtra("page_redirection" , page_redirection);
                            intent.putExtra("colour" , colour);
                            intent.putExtra("language", LANGUAGE);
                            startActivity(intent);
                        }
                    });

                    version_button = (Button) findViewById(R.id.version_info_page_button);
                    version_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent (AboutPageMain.this, VersionInfoActivity.class);
                            Log.d("version_button" , "version_button");
                            intent.putExtra("currentUser" , currentUser);
                            intent.putExtra("count_all" , count_all);
                            intent.putExtra("count_home" , count_home);
                            intent.putExtra("count_work" , count_work);
                            intent.putExtra("count_others" , count_others);
                            intent.putExtra("sorting_order" , sorting_order);
                            intent.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
                            intent.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
                            intent.putExtra("page_redirection" , page_redirection);
                            intent.putExtra("colour" , colour);
                            intent.putExtra("language", LANGUAGE);
                            startActivity(intent);
                        }
                    });

                    break;
                case R.id.navigation_developer:
                    //mTextMessage.setText(R.string.title_Develpoer);
                    viewPager.setCurrentItem(1);

                    break;
                case R.id.navigation_statistics:
                    //mTextMessage.setText(R.string.title_Statistic);
                    viewPager.setCurrentItem(2);

                    username_textview = (TextView) findViewById(R.id.username_field);

                    count_all_field = (TextView) findViewById(R.id.count_all_field);
                    count_home_field = (TextView) findViewById(R.id.count_home_field);
                    count_work_field = (TextView) findViewById(R.id.count_work_field);
                    count_others_field = (TextView) findViewById(R.id.count_others_field);

                    username_textview.setText(currentUser);


                    count_all_field.setText(String.valueOf(count_all));
                    count_home_field.setText(String.valueOf(count_home));
                    count_work_field.setText(String.valueOf(count_work));
                    count_others_field.setText(String.valueOf(count_others));


                    change_password_button = (Button) findViewById(R.id.change_password_button);
                    change_password_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d("change_pass_button" , "change_pass_button");
                            LayoutInflater inflater = LayoutInflater.from(AboutPageMain.this);
                            View view = inflater.inflate(R.layout.about_page_change_password_alertdialog_layout , null);

                            AlertDialog.Builder dialog = new AlertDialog.Builder(AboutPageMain.this);
                            dialog.setTitle(getString(R.string.change_password));
                            dialog.setCancelable(false);
                            dialog.setView(view);
                            edit1 = (EditText) view.findViewById(R.id.about_page_change_password_alertdialog_enter_password_edittext);
                            edit2 = (EditText) view.findViewById(R.id.about_page_change_password_alertdialog_reenter_password_edittext);
                            if (edit1 == null)
                            {
                                Log.d("i_am_null" , "nullnullnull");
                            }
                            dialog.setPositiveButton("Finish", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.d("finish_pass_reset" , "pass_reset");
                                    String password1 = "";
                                    password1 = edit1.getText().toString();
                                    String password2 = "";
                                    password2 = edit2.getText().toString();
                                    if (password1.length() < 6 || password2.length() < 6 || !password1.equals(password2))
                                    {
                                        AlertDialog.Builder warning_dialog = new AlertDialog.Builder(AboutPageMain.this);
                                        warning_dialog.setTitle(getString(R.string.incorrect_password_setting));
                                        warning_dialog.setMessage(getString(R.string.incorrect_password_setting));
                                        warning_dialog.setPositiveButton(getString(R.string.ok) , null);
                                        warning_dialog.setCancelable(false);
                                        warning_dialog.create();
                                        warning_dialog.show();
                                    }
                                    else
                                    {
                                        db = dbHelper.getWritableDatabase();
                                        ContentValues values = new ContentValues();
                                        values.put("password" , password2);
                                        db.update("User" , values , "user_id = ?" , new String [] {currentUser});
                                        Toast toast = Toast.makeText(getApplicationContext() , getString(R.string.password_reset_successfully) , Toast.LENGTH_LONG);
                                        toast.show();
                                    }
                                }
                            });
                            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.d("cancel_pass_reset" , "pass_reset");
                                    // should do nothing
                                    // null
                                }
                            });
                            dialog.create();
                            dialog.show();

                        }
                    });
                    logout_button = (Button) findViewById(R.id.logout_button);
                    logout_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d("logout_button" , "logout_button");
                            AlertDialog.Builder dialog = new AlertDialog.Builder(AboutPageMain.this);
                            dialog.setTitle(getString(R.string.confirm_logout1));
                            dialog.setMessage(getString(R.string.confirm_logout2));
                            dialog.setCancelable(false);
                            dialog.setNegativeButton(getString(R.string.go_back), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Should do nothing here
                                    // null
                                }
                            });
                            dialog.setPositiveButton(getString(R.string.log_out), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent (AboutPageMain.this , LoginActivity.class);
                                    intent.putExtra("colour" , colour);
                                    intent.putExtra("language", LANGUAGE);
                                    startActivity(intent);
                                }
                            });
                            dialog.create();
                            dialog.show();
                        }
                    });
                    break;
            }
            return true;
        }
    };

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
        setContentView(R.layout.activity_about_page_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        Log.d(TAG, "onCreate");

        //changeStatusBarColour(AboutPageMain.this, R.color.colorPrimaryDark);


        if (getIntent().getStringExtra("page_redirection") != null)
        {
            if (!getIntent().getStringExtra("page_redirection").equalsIgnoreCase(""))
            {
                page_redirection = getIntent().getStringExtra("page_redirection");
            }
        }
        Log.d("redirection" , page_redirection);


        if (only_show_fourteen_days.equalsIgnoreCase(""))
        {
            only_show_fourteen_days = "false";
        }
        if (sorting_order.equalsIgnoreCase(""))
        {
            sorting_order = "date_finished ASC";
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


        // create new databasE
        dbHelper = new MyDatabaseHelper (this, databaseName, null, databaseVersion);
        //DatabaseVersion.version = DatabaseVersion.version + 1;
        dbHelper.getWritableDatabase();

        db = dbHelper.getWritableDatabase();

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        viewPager = (ViewPager) findViewById(R.id.about_page_view_pager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                menuItem = navigation.getMenu().getItem(position);
            }

            @Override
            public void onPageSelected(int position) {



                if (menuItem != null)
                {
                    menuItem.setChecked(false);
                }
                else
                {
                    navigation.getMenu().getItem(0).setChecked(false);
                }


                menuItem = navigation.getMenu().getItem(position);
                menuItem.setChecked(true);


                /*
                switch (position)
                {
                    case 1: navigation.setSelectedItemId(R.id.navigation_about);break;
                    case 2: navigation.setSelectedItemId(R.id.navigation_developer);break;
                    case 3: navigation.setSelectedItemId(R.id.navigation_statistics);break;
                    default:break;
                }
                */
                return;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                if (state == 2 || state == 0)
                {
                    menuItem = navigation.getMenu().getItem(viewPager.getCurrentItem());
                    menuItem.setChecked(true);
                }

            }

        });

        // control the SLIDING
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //menuItem = navigation.getMenu().getItem(viewPager.getCurrentItem());
                //menuItem.setChecked(true);
                return false;
                // false means scroll enabled
            }
        });





        LayoutInflater inflater = getLayoutInflater();
        view_about = inflater.inflate(R.layout.about_page_layout_1 , null);
        view_developer = inflater.inflate(R.layout.about_page_layout_2 , null);
        view_statistics = inflater.inflate(R.layout.about_page_layout_3 , null);

        viewList = new ArrayList<View> ();
        viewList.add(view_about);
        viewList.add(view_developer);
        viewList.add(view_statistics);

        pageAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem (ViewGroup container, int position, Object object)
            {
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem (ViewGroup container, int position)
            {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }
        };



        viewPager.setAdapter(pageAdapter);

        //menuItem = navigation.getMenu().getItem(viewPager.getCurrentItem());
        //menuItem.setChecked(true);

        currentUser = getIntent().getStringExtra("currentUser");
        count_all = getIntent().getIntExtra("count_all" , -1000000);
        count_home = getIntent().getIntExtra("count_home" , -1000000);
        count_work = getIntent().getIntExtra("count_work" , -1000000);
        count_others = getIntent().getIntExtra("count_others" , -1000000);
        Log.d("currentUser" , currentUser);
        Log.d("count_all" , String.valueOf(count_all));
        Log.d("count_home" , String.valueOf(count_home));
        Log.d("count_work" , String.valueOf(count_work));
        Log.d("count_others" , String.valueOf(count_others));

        Log.d("current_item" , String.valueOf(viewPager.getCurrentItem()));
        //mTextMessage = (TextView) findViewById(R.id.message);


        // set up the first page

        about_this_app = (TextView) view_about.findViewById(R.id.layout_1_about_content);

        String chinese_about = "LuckyDays Version 1.1 (2018.01)\n" +
                "\n" +
                "一个能显示倒计时的记事本\n" +
                "\n" +
                "新增发送邮件提醒功能，可将添加的事件发送至邮箱\n" +
                "\n" +
                "修复语言更改功能在一些设备上不兼容的情况\n" +
                "\n" +
                "退出时功能优化\n" +
                "\n" +
                "\n" +
                "\n" +
                "LuckyDays Version 1.0 (2017.12)\n" +
                "\n" +
                "一个能显示倒计时的记事本\n" +
                "\n" +
                "可记录事件名称、起始、截止和创建日期、事件内容及分类，并自动计算剩余天数\n" +
                "\n" +
                "支持显示事件的倒计时\n" +
                "\n" +
                "支持三种分类(生活、工作和其他)\n" +
                "\n" +
                "支持两种事件显示模式(列表模式、日历模式)\n" +
                "\n" +
                "支持30种界面颜色\n" +
                "\n" +
                "支持五种语言(英文、简体中文、繁体中文、日语、法语)\n" +
                "\n" +
                "支持事件排序(以创建日期升序排列、以创建日期降序排列、以剩余日数排列)\n" +
                "\n" +
                "支持按事件名称和内容进行事件搜索\n" +
                "\n" +
                "支持显示特定事件(未来14天内截止的事件、不显示已截止的事件)\n" +
                "\n" +
                "支持定时事件截止数提醒\n" +
                "\n" +
                "系统自动保存用户设置(排序设置、提醒设置、界面颜色设置)\n" +
                "\n" +
                "系统自动保留上次退出时的语言设置";

        String english_about = "LuckyDays Version 1.1 (2018.01)\n" +
                "\n" +
                "A note-taking tool combined with remain days functionality\n" +
                "\n" +
                "Added new functionality: send email notification\n" +
                "\n" +
                "Fixed a compatibility issue on language setting on some devices\n" +
                "\n" +
                "\n" +
                "\n" +
                "LuckyDays Version 1.0 (2017.12)\n" +
                "\n" +
                "A note-taking tool combined with remain days functionality\n" +
                "\n" +
                "Record event name, event starting date, finishing date, creating date, event content and event classification, and calculate the remain days automatically.\n" +
                "\n" +
                "Display the days remain for each event\n" +
                "\n" +
                "Support three classifications for events (Home, Work and Others)\n" +
                "\n" +
                "Support two display modes (Event-list Mode, Calendar Mode)\n" +
                "\n" +
                "Customise the app with 30 pre-defined theme colours.\n" +
                "\n" +
                "Support five languages (English (GB), Simplified Chinese, Traditional Chinese, Japanese, French)\n" +
                "\n" +
                "Allow sorting the events in specified order (Order by creating time in ascending or descending order, order by remain days in descending order)\n" +
                "\n" +
                "Allow searching by event name and event content.\n" +
                "\n" +
                "Allow displaying events with certain constraints (Due in next fourteen days, already passed the due date)\n" +
                "\n" +
                "Support notifications of the number of event due on each day at a fixed time.\n" +
                "\n" +
                "Automatically save the settings for user (Sorting order setting, notification setting, colour setting)\n" +
                "\n" +
                "Automatically save the language setting at the last shut down of the app";



        String tradition_about = "LuckyDays Version 1.1 (2018.01)\n" +
                "\n" +
                "一個能顯示倒計時的記事本\n" +
                "\n" +
                "新增發送郵件提醒功能，可將添加的事件發送至郵箱\n" +
                "\n" +
                "修復語言更改功能在一些設備上不兼容的情況\n" +
                "\n" +
                "退出時功能優化\n" +
                "\n" +
                "\n" +
                "\n" +
                "LuckyDays Version 1.0 (2017.12)\n" +
                "\n" +
                "一個能顯示倒計時的記事本\n" +
                "\n" +
                "可記錄事件名稱、起始、截止和創建日期、事件內容及分類，并自動計算剩餘天數\n" +
                "\n" +
                "支持顯示事件的倒計時天數\n" +
                "\n" +
                "支持三種分類(生活、工作和其他)\n" +
                "\n" +
                "支持兩種事件顯示模式(列表模式、日曆模式)\n" +
                "\n" +
                "支持30種界面顏色\n" +
                "\n" +
                "支持五種語言(英文、簡體中文、繁體中文、日語、法語)\n" +
                "\n" +
                "支持事件排序(以創建日期升序排列、以創建日期降序排列、以剩餘日數排列)\n" +
                "\n" +
                "支持按事件名稱和內容進行事件搜索\n" +
                "\n" +
                "只是顯示特定事件(未來14天內截止的事件、不顯示已截止的事件)\n" +
                "\n" +
                "支持定時事件截止數提醒\n" +
                "\n" +
                "系統自動保存用戶設置(排序設置、提醒設置、界面顏色設置)\n" +
                "\n" +
                "系統自動保留上次退出時的語言設置";



        switch (LANGUAGE)
        {
            case "English (EN-GB)": about_this_app.setText(english_about);break;
            case "Simplified Chinese (CH-ZN)": about_this_app.setText(chinese_about);break;
            case "Traditional Chinese (CH-TW)": about_this_app.setText(tradition_about);break;
            case "Japanese (JA-JP)": about_this_app.setText(chinese_about + "\n\n\n\n\n\n" + english_about + "\n\n\n\n\n\n" + tradition_about);break;
            case "French (FR-FA)": about_this_app.setText(chinese_about + "\n\n\n\n\n\n" + english_about + "\n\n\n\n\n\n" + tradition_about);break;
            default: about_this_app.setText(chinese_about + "\n\n\n\n\n\n" + english_about + "\n\n\n\n\n\n" + tradition_about);break;
        }



        licence_button = (Button) view_about.findViewById(R.id.about_page_licence_button);
        licence_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (AboutPageMain.this, DisplayLicencesActivity.class);
                Log.d("licence_button" , "licence_button");
                intent.putExtra("currentUser" , currentUser);
                intent.putExtra("count_all" , count_all);
                intent.putExtra("count_home" , count_home);
                intent.putExtra("count_work" , count_work);
                intent.putExtra("count_others" , count_others);
                intent.putExtra("sorting_order" , sorting_order);
                intent.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
                intent.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
                intent.putExtra("page_redirection" , page_redirection);
                intent.putExtra("colour" , colour);
                intent.putExtra("language", LANGUAGE);

                startActivity(intent);

            }
        });


        version_button = (Button) view_about.findViewById(R.id.version_info_page_button);
        version_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (AboutPageMain.this, VersionInfoActivity.class);
                Log.d("version_button" , "version_button");
                intent.putExtra("currentUser" , currentUser);
                intent.putExtra("count_all" , count_all);
                intent.putExtra("count_home" , count_home);
                intent.putExtra("count_work" , count_work);
                intent.putExtra("count_others" , count_others);
                intent.putExtra("sorting_order" , sorting_order);
                intent.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
                intent.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
                intent.putExtra("page_redirection" , page_redirection);
                intent.putExtra("colour" , colour);
                intent.putExtra("language", LANGUAGE);
                startActivity(intent);
            }
        });



        // set up the second page

        about_developer = (TextView) view_developer.findViewById(R.id.layout_2_about_content);

        String english_developer = "Hey, I am Jiashu Wu, a uni student, studying Computer Science and Applied Maths." +
                "\n\n\n" +
                "Thank you for supporting my application!";

        about_developer.setText(english_developer);



        // set up the third page

        //username_textview = (TextView) findViewById(R.id.username_field);

        count_all_field = (TextView) view_statistics.findViewById(R.id.count_all_field);
        count_home_field = (TextView) view_statistics.findViewById(R.id.count_home_field);
        count_work_field = (TextView) view_statistics.findViewById(R.id.count_work_field);
        count_others_field = (TextView) view_statistics.findViewById(R.id.count_others_field);


        count_all_field.setText(String.valueOf(count_all));
        count_home_field.setText(String.valueOf(count_home));
        count_work_field.setText(String.valueOf(count_work));
        count_others_field.setText(String.valueOf(count_others));

        username_textview = (TextView) view_statistics.findViewById(R.id.username_field);

        username_textview.setText(currentUser);


        change_password_button = (Button) view_statistics.findViewById(R.id.change_password_button);
        change_password_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("change_pass_button" , "change_pass_button");
                LayoutInflater inflater = LayoutInflater.from(AboutPageMain.this);
                View view = inflater.inflate(R.layout.about_page_change_password_alertdialog_layout , null);

                AlertDialog.Builder dialog = new AlertDialog.Builder(AboutPageMain.this);
                dialog.setTitle(getString(R.string.change_password));
                dialog.setCancelable(false);
                dialog.setView(view);
                edit1 = (EditText) view.findViewById(R.id.about_page_change_password_alertdialog_enter_password_edittext);
                edit2 = (EditText) view.findViewById(R.id.about_page_change_password_alertdialog_reenter_password_edittext);
                if (edit1 == null)
                {
                    Log.d("i_am_null" , "nullnullnull");
                }
                dialog.setPositiveButton(getString(R.string.finish), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("finish_pass_reset" , "pass_reset");
                        String password1 = "";
                        password1 = edit1.getText().toString();
                        String password2 = "";
                        password2 = edit2.getText().toString();
                        if (password1.length() < 6 || password2.length() < 6 || !password1.equals(password2))
                        {
                            AlertDialog.Builder warning_dialog = new AlertDialog.Builder(AboutPageMain.this);
                            warning_dialog.setTitle(getString(R.string.incorrect_password_setting));
                            warning_dialog.setMessage(getString(R.string.incorrect_password_setting));
                            warning_dialog.setCancelable(false);
                            warning_dialog.setPositiveButton(getString(R.string.ok) , null);
                            warning_dialog.create();
                            warning_dialog.show();
                        }
                        else
                        {
                            db = dbHelper.getWritableDatabase();
                            ContentValues values = new ContentValues();
                            values.put("password" , password2);
                            db.update("User" , values , "user_id = ?" , new String [] {currentUser});
                            Toast toast = Toast.makeText(getApplicationContext() , getString(R.string.password_reset_successfully) , Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                });
                dialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("cancel_pass_reset" , "pass_reset");
                        // should do nothing
                        // null
                    }
                });
                dialog.create();
                dialog.show();

            }
        });
        logout_button = (Button) view_statistics.findViewById(R.id.logout_button);
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("logout_button" , "logout_button");
                AlertDialog.Builder dialog = new AlertDialog.Builder(AboutPageMain.this);
                dialog.setTitle(getString(R.string.confirm_logout1));
                dialog.setMessage(getString(R.string.confirm_logout2));
                dialog.setCancelable(false);
                dialog.setNegativeButton(getString(R.string.go_back), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Should do nothing here
                        // null
                    }
                });
                dialog.setPositiveButton(getString(R.string.log_out), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent (AboutPageMain.this , LoginActivity.class);
                        intent.putExtra("language" , LANGUAGE);
                        startActivity(intent);
                    }
                });
                dialog.create();
                dialog.show();
            }
        });

    }






    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home)
        {
            Intent intent = new Intent (AboutPageMain.this , NavigationActivity.class);
            if (page_redirection.equalsIgnoreCase("calendar_mode"))
            {
                intent = new Intent(AboutPageMain.this , CalendarViewNavigationActivity.class);
            }
            intent.putExtra("currentUser" , currentUser);
            intent.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
            intent.putExtra("sorting_order" , sorting_order);
            intent.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
            intent.putExtra("colour" , colour);
            intent.putExtra("language", LANGUAGE);
            startActivity(intent);
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
