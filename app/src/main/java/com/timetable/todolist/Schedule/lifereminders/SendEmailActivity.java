package com.timetable.todolist.Schedule.lifereminders;

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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendEmailActivity extends AppCompatActivity {




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


    private EditText email_recipient;
    private EditText email_subject;
    private EditText email_content;

    private Button send_button;
    private Button cancel_button;

    private String email_final_sendTo = "";
    private String email_final_subject = "";
    private String email_final_content = "";

    private boolean confirm_to_send = false;

    private boolean readyToSend = true;







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



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);
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
        Log.d("detail_classification" , event_classification);

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

        email_recipient = (EditText) findViewById(R.id.email_send_to_edittext);
        email_subject = (EditText) findViewById(R.id.email_subject_edittext);
        email_content = (EditText) findViewById(R.id.email_content_edittext);

        String initial_subject = event_name + ", classified as " + event_classification +
                ", will due on " + date_finished + ", " + String.valueOf(item_days) + " days left.";

        String initial_content = event_content;

        email_subject.setText(initial_subject);
        email_content.setText(initial_content);

        send_button = (Button) findViewById(R.id.email_send_button);
        cancel_button = (Button) findViewById(R.id.email_cancel_button);

        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email_final_sendTo = email_recipient.getText().toString();
                email_final_subject = email_subject.getText().toString();
                email_final_content = email_content.getText().toString();

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
                    android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder (SendEmailActivity.this);
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
                else if (email_final_sendTo.isEmpty())
                {
                    readyToSend = false;
                    android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder (SendEmailActivity.this);
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
                else if (!email_final_sendTo.contains("@") || !email_final_sendTo.contains("."))
                {
                    readyToSend = false;
                    android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder (SendEmailActivity.this);
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
                else if (email_final_subject.isEmpty())
                {
                    readyToSend = false;
                    android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder (SendEmailActivity.this);
                    dialog.setTitle(getString(R.string.invalid_information));
                    dialog.setCancelable(false);
                    dialog.setMessage(getString(R.string.subject_is_empty));
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
                    android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder (SendEmailActivity.this);
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

                            if (readyToSend && confirm_to_send) {
                                try {
                                    emailSender.sendMessage("smtp.gmail.com", "luckydaysemailservice@gmail.com", "Ab1099511627776", email_final_sendTo, email_final_subject, email_final_content);
                                    //emailSender.sendMessage("smtp.live.com", "ldsservice1099611527776@outlook.com", "Ab1099611527776", email_final_sendTo, email_final_subject, email_final_content);
                                    Log.d("email_to", email_final_sendTo);
                                    Log.d("email_subject", email_final_subject);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                Intent intent = new Intent(SendEmailActivity.this, EventDetailsScrollingActivity.class);
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

                        }
                    });

                    dialog.show();
                }

                /*
                Log.d("email_send_signal", String.valueOf(readyToSend));
                Log.d("email_send_signal", String.valueOf(confirm_to_send));
                Log.d("email_send_signal", String.valueOf(network_available));
                */





            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SendEmailActivity.this);
                builder.setTitle(getString(R.string.confirm));
                builder.setCancelable(false);
                builder.setMessage(getString(R.string.cancel_sending));
                builder.setNegativeButton(getString(R.string.go_back), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Should do nothing here
                        // Null
                    }
                });

                builder.setPositiveButton(getString(R.string.exit_without_sending), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent (SendEmailActivity.this, EventDetailsScrollingActivity.class);
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
                });
                builder.create();
                builder.show();


            }
        });









    }

}
