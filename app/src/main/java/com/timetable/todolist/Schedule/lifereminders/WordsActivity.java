package com.timetable.todolist.Schedule.lifereminders;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class WordsActivity extends AppCompatActivity {

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

    private String turn_on_notification = "";

    private int notification_hour;
    private int notification_minute;


    private String TAG = "TAG_event_detail_scroll";

    private String colour = "";

    private String LANGUAGE = "";

    private EditText word_edittext;
    private EditText word_email_edittext;

    private Button choose_time_button;
    private Button send_button;

    private CheckBox new_word_checkbox;

    private TextView word_time_textview;

    private String words = "";

    private String email_address = "";

    private boolean is_new_word = true;

    private int year;
    private int month;
    private int day;

    private DatePicker word_choose_time_datepicker;

    private boolean readyToSend;

    private boolean confirm_to_send;

    private String email_subject = "";





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

        if (getIntent().getStringExtra("page_redirection") != null)
        {
            if (!getIntent().getStringExtra("page_redirection").equalsIgnoreCase(""))
            {
                page_redirection = getIntent().getStringExtra("page_redirection");
            }
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



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

        currentUser = getIntent().getStringExtra("currentUser");
        Log.d("currentUser_detail" , currentUser);


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


        if (turn_on_notification.equalsIgnoreCase(""))
        {
            turn_on_notification = "false";
        }

        if (getIntent().getStringExtra("turn_on_notification") != null)
        {
            if (!getIntent().getStringExtra("turn_on_notification").equalsIgnoreCase("discard"))
            {
                turn_on_notification = getIntent().getStringExtra("turn_on_notification");
            }
        }
        Log.d("turn_on" , turn_on_notification);

        notification_hour = getIntent().getIntExtra("notification_hour" , -10);
        notification_minute = getIntent().getIntExtra("notification_minute" , -10);

        // FINISHING GETTING ALL DATA

        // START IMPLEMENTING THIS PAGE

        word_edittext = (EditText) findViewById(R.id.word_edittext);
        word_email_edittext = (EditText) findViewById(R.id.word_email_edittext);

        choose_time_button = (Button) findViewById(R.id.word_time_button);
        send_button = (Button) findViewById(R.id.word_send_button);

        new_word_checkbox = (CheckBox) findViewById(R.id.word_new_checkbox);

        word_time_textview = (TextView) findViewById(R.id.word_time_textView);

        is_new_word = true;

        new_word_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                {
                    is_new_word = true;
                }
                if (!isChecked)
                {
                    is_new_word = false;
                }
            }
        });

        Calendar calendar = Calendar.getInstance();

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);

        word_time_textview.setText(year + "/" + month + "/" + day);


        choose_time_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(WordsActivity.this);
                View view = inflater.inflate(R.layout.word_page_choose_time_alertdialog_layout , null);

                AlertDialog.Builder dialog = new AlertDialog.Builder(WordsActivity.this);
                dialog.setTitle(getString(R.string.word_choose_time));
                dialog.setCancelable(false);
                dialog.setView(view);
                word_choose_time_datepicker = (DatePicker) view.findViewById(R.id.word_choose_time_datepicker);
                if (word_choose_time_datepicker == null)
                {
                    Log.d("i_am_null" , "null_null_null");
                }
                dialog.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        year = word_choose_time_datepicker.getYear();
                        month = word_choose_time_datepicker.getMonth() + 1;
                        day = word_choose_time_datepicker.getDayOfMonth();
                        word_time_textview.setText(year + "/" + month + "/" + day);
                        }

                });
                dialog.create();
                dialog.show();
            }
        });


        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                words = word_edittext.getText().toString();
                email_address = word_email_edittext.getText().toString();

                readyToSend = true;
                boolean network_available = false;

                Context context = getApplicationContext();
                final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager != null && connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isAvailable() && connectivityManager.getActiveNetworkInfo().isConnected())
                {
                    network_available = true;
                }


                if (network_available == false)
                {
                    readyToSend = false;
                    android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder (WordsActivity.this);
                    dialog.setTitle(getString(R.string.network_error));
                    dialog.setCancelable(false);
                    dialog.setMessage(getString(R.string.network_not_available));
                    dialog.setNegativeButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // don't need to do anything here
                            // leave it as empty block
                        }
                    });

                    dialog.show();
                }
                else if (email_address.isEmpty())
                {
                    readyToSend = false;
                    android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder (WordsActivity.this);
                    dialog.setTitle(getString(R.string.invalid_information));
                    dialog.setCancelable(false);
                    dialog.setMessage(getString(R.string.email_is_empty));
                    dialog.setNegativeButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // don't need to do anything here
                            // leave it as empty block
                        }
                    });

                    dialog.show();
                }
                else if (!email_address.contains("@") || !email_address.contains("."))
                {
                    readyToSend = false;
                    android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder (WordsActivity.this);
                    dialog.setTitle(getString(R.string.invalid_information));
                    dialog.setCancelable(false);
                    dialog.setMessage(getString(R.string.email_address_invalid));
                    dialog.setNegativeButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // don't need to do anything here
                            // leave it as empty block
                        }
                    });

                    dialog.show();
                }
                else if (words.isEmpty())
                {
                    readyToSend = false;
                    android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder (WordsActivity.this);
                    dialog.setTitle(getString(R.string.invalid_information));
                    dialog.setCancelable(false);
                    dialog.setMessage(getString(R.string.word_is_empty));
                    dialog.setNegativeButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // don't need to do anything here
                            // leave it as empty block
                        }
                    });

                    dialog.show();
                }


                confirm_to_send = false;

                if (readyToSend)
                {
                    android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder (WordsActivity.this);
                    dialog.setTitle(getString(R.string.confirm));
                    dialog.setCancelable(false);
                    dialog.setMessage(getString(R.string.confirm_sending_email));
                    dialog.setNegativeButton(getString(R.string.go_back), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // don't need to do anything here
                            // leave it as empty block
                            confirm_to_send = false;
                        }
                    });

                    dialog.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            confirm_to_send = true;
                            Log.d("email_sig", String.valueOf(confirm_to_send));

                            String chosen_time = year + "/" + month + "/" + day;
                            String new_word = "New Word";
                            String word = "Word";
                            if (is_new_word)
                            {
                                email_subject = chosen_time + " " + new_word + " " + words;
                                words = chosen_time + "\n" + new_word + "\n" + words;
                            }
                            else
                            {
                                email_subject = chosen_time + " " + word + " " + words;
                                words = chosen_time + "\n" + word + "\n" + words;
                            }


                            if (readyToSend && confirm_to_send) {
                                try {
                                    Log.d("start_sending", "start_sending");
                                    emailSender.sendMessage("smtp.gmail.com", "luckydaysemailservice@gmail.com", "Ab1099511627776", email_address, email_subject, words);
                                    // BOTH emailSender and emailSender1 can be used
                                    //emailSender.sendMessage("smtp.live.com", "ldsservice1099611527776@outlook.com", "Ab1099611527776", email_final_sendTo, email_final_subject, email_final_content);
                                    Log.d("email_to", email_address);
                                    Log.d("email_subject", email_subject);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                Intent intent = new Intent(WordsActivity.this,  NavigationActivity.class);
                                if (page_redirection.equalsIgnoreCase("calendar_mode"))
                                {
                                    intent = new Intent(WordsActivity.this , CalendarViewNavigationActivity.class);
                                }
                                intent.putExtra("current_user", currentUser);
                                //intent.putExtra("item_id", item_id);
                                //intent.putExtra("creator_id", creator_id);
                                //intent.putExtra("classification", event_classification);
                                //intent.putExtra("item_content", event_content);
                                //intent.putExtra("date_created", date_created);
                                //intent.putExtra("item_start_date", date_started);
                                //intent.putExtra("item_finish_date", date_finished);
                                //intent.putExtra("item_name", event_name);
                                //intent.putExtra("item_days", item_days);
                                intent.putExtra("language", LANGUAGE);
                                intent.putExtra("colour", colour);
                                intent.putExtra("only_show_fourteen_days", only_show_fourteen_days);
                                intent.putExtra("sorting_order", sorting_order);
                                intent.putExtra("do_not_show_negative_days", do_not_show_negative_days);
                                intent.putExtra("page_redirection", page_redirection);
                                intent.putExtra("turn_on_notification", turn_on_notification);
                                intent.putExtra("notification_hour", notification_hour);
                                intent.putExtra("notification_minute", notification_minute);
                                intent.putExtra("currentUser", currentUser);
                                startActivity(intent);
                                finish();

                            }

                        }
                    });

                    dialog.show();
                }


            }
        });










    }

}
