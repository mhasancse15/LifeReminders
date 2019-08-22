package com.timetable.todolist.Schedule.lifereminders;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Intent;
import android.widget.Toast;

import static android.Manifest.permission.READ_CONTACTS;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */


    private static final int REQUEST_READ_CONTACTS = 0;

    private String LANGUAGE = "";

    private String TAG = "TAG_login_page";

    // SQL HELPER
    private MyDatabaseHelper dbHelper;
    private MyDatabaseHelper dbHelper1;


    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };


    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    private Spinner language_spinner;
    private ArrayAdapter adapter;
    private ArrayList<String> list;

    private String sorting_order = "";
    private String only_show_fourteen_days = "";
    private String do_not_show_negative_days = "";
    private String turn_on_notification = "";
    private int notification_hour;
    private int notification_minute;
    private int judge = 0;

    private String colour = "";

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
        //init_theme();

        if (getIntent().getStringExtra("language") != null)
        {
            LANGUAGE = getIntent().getStringExtra("language").toString();
            //initLocaleLanguage();
        }

        if (!LANGUAGE.equalsIgnoreCase("English (EN-GB)") && !LANGUAGE.equalsIgnoreCase("Simplified Chinese (CH-ZN)") && !LANGUAGE.equalsIgnoreCase("Traditional Chinese (CH-TW)") && !LANGUAGE.equalsIgnoreCase("Japanese (JA-JP)") && !LANGUAGE.equalsIgnoreCase("French (FR-FA)"))
        {
            LANGUAGE = "English (EN-GB)";
        }

        initLocaleLanguage();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        Log.d(TAG, "onCreate");

        //populateAutoComplete();





        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        // create new database
        dbHelper = new MyDatabaseHelper (this, databaseName, null, databaseVersion);
        //DatabaseVersion.version = DatabaseVersion.version + 1;
        dbHelper.getWritableDatabase();


        SQLiteDatabase db2 = dbHelper.getReadableDatabase();
        SQLiteDatabase db3 = dbHelper.getWritableDatabase();

        String [] params = new String[] {};
        String query = "SELECT COUNT(*), language FROM Lang";
        Cursor cursor2 = db2.rawQuery(query , params);
        int anon_count = 0;
        while (cursor2.moveToNext())
        {
            anon_count = Integer.parseInt(cursor2.getString(0).toString());
            if (anon_count == 0)
            {
                ContentValues values = new ContentValues();
                values.put("anon_user_id" , "anonymous_language_holder");
                values.put("language" , "English (EN-GB)");
                db3.insert("Lang" , null, values);
            }
            else
            {
                LANGUAGE = cursor2.getString(1).toString();
            }
        }
        if (!LANGUAGE.equalsIgnoreCase("English (EN-GB)") && !LANGUAGE.equalsIgnoreCase("Simplified Chinese (CH-ZN)") && !LANGUAGE.equalsIgnoreCase("Traditional Chinese (CH-TW)") && !LANGUAGE.equalsIgnoreCase("Japanese (JA-JP)") && !LANGUAGE.equalsIgnoreCase("French (FR-FA)"))
        {
            LANGUAGE = "English (EN-GB)";
        }


        SQLiteDatabase db4 = dbHelper.getReadableDatabase();
        SQLiteDatabase db5 = dbHelper.getWritableDatabase();
        String [] params1 = new String[] {"testinguser"};
        String query1 = "SELECT COUNT(*) FROM User WHERE user_id = ? ";
        Cursor cursor3 = db4.rawQuery(query1, params1);
        int testing_count = 0;
        while (cursor3.moveToNext())
        {
            testing_count = Integer.parseInt(cursor3.getString(0).toString());
            if (testing_count == 0)
            {
                ContentValues contentValues = new ContentValues();
                contentValues.put("user_id", "testinguser");
                contentValues.put("password", "1099511627776");
                contentValues.put("sorting_order", "date_finished ASC");
                contentValues.put("only_show_fourteen_days" , "false");
                contentValues.put("do_not_show_negative_days" , "false");
                contentValues.put("turn_on_notification" , "false");
                contentValues.put("notification_hour" , "0");
                contentValues.put("notification_minute" , "0");
                contentValues.put("colour" , "Blue");
                db5.insert("User", null, contentValues);
            }
        }
        if (cursor3 != null)
        {
            cursor3.close();
        }
        if (db4 != null)
        {
            db4.close();
        }
        if (db5 != null)
        {
            db5.close();
        }



        language_spinner = (Spinner) findViewById(R.id.language_spinner);
        list = new ArrayList<String>();

        list.add("English (EN-GB)");
        list.add("Simplified Chinese (CH-ZN)");
        list.add("Traditional Chinese (CH-TW)");
        list.add("Japanese (JA-JP)");
        list.add("French (FR-FA)");

        String [] mList = getResources().getStringArray(R.array.languages_array);

        adapter = new ArrayAdapter(this , android.R.layout.simple_spinner_dropdown_item, mList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        language_spinner.setAdapter(adapter);

        switch (LANGUAGE)
        {
            case "English (EN-GB)": language_spinner.setSelection(0);break;
            case "Simplified Chinese (CH-ZN)": language_spinner.setSelection(1);break;
            case "Traditional Chinese (CH-TW)": language_spinner.setSelection(2);break;
            case "Japanese (JA-JP)": language_spinner.setSelection(3);break;
            case "French (FR-FA)": language_spinner.setSelection(4);break;
            default: language_spinner.setSelection(0);break;
        }

        judge = 0;
        final String original_LANGUAGE = LANGUAGE;
        language_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0: LANGUAGE = "English (EN-GB)";judge = 1;break;
                    case 1: LANGUAGE = "Simplified Chinese (CH-ZN)";judge = 1;break;
                    case 2: LANGUAGE = "Traditional Chinese (CH-TW)";judge = 1;break;
                    case 3: LANGUAGE = "Japanese (JA-JP)";judge = 1;break;
                    case 4: LANGUAGE = "French (FR-FA)";judge = 1;break;
                    default: LANGUAGE = "English (EN-GB)";judge = 1;break;
                }
                Log.d("current_language" , LANGUAGE);
                //Log.d("current_judge" , String.valueOf(judge));
                if (!LANGUAGE.equalsIgnoreCase(original_LANGUAGE))
                {
                    Intent intent = new Intent (LoginActivity.this, LoadingActivity.class);
                    intent.putExtra("language" , LANGUAGE);
                    startActivity(intent);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*
        Log.d("LAN_o", original_lan);
        Log.d("LAN_n", LANGUAGE);
        */




        // create buttons
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        Button mEmailSignUpButton = (Button) findViewById(R.id.new_user_sign_up_button);


        // when sign-up button is clicked
        mEmailSignUpButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick (View view)
            {
                String email = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();
                boolean judge = isEmailValid(email) && isPasswordValid(password);
                if (judge == false)
                {
                    if (isEmailValid(email) == false && isPasswordValid(password) == true)
                    {
                        Context context = getApplicationContext();
                        String text = getString(R.string.email_invalid);
                        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    if (isPasswordValid(password) == false && isEmailValid(email) == true)
                    {
                        Context context = getApplicationContext();
                        String text = getString(R.string.password_invalid);
                        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    if (isEmailValid(email) == false && isPasswordValid(password) == false)
                    {
                        Context context = getApplicationContext();
                        String text = getString(R.string.email_and_password_invalid);
                        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                        toast.show();
                    }

                    attemptLogin();

                }
                else
                {
                    // Create a new database, add the new user into the database.
                    SQLiteDatabase db0 = dbHelper.getReadableDatabase();
                    String query = "SELECT COUNT(*) FROM User WHERE user_id = ? ";
                    String params [] = new String [] {email};
                    Cursor cursor = db0.rawQuery(query , params);
                    int result_len = 0;
                    while (cursor.moveToNext())
                    {
                        result_len = Integer.parseInt(cursor.getString(0).toString());
                        if (result_len != 0)
                        {
                            showMessage(getString(R.string.username_already_exist) , LoginActivity.this);
                        }
                    }
                    if (cursor != null)
                    {
                        cursor.close();
                    }
                    if (result_len == 0) {
                        SQLiteDatabase db4 = dbHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put("user_id", email);
                        values.put("password", password);
                        values.put("sorting_order" , "date_finished ASC");
                        values.put("only_show_fourteen_days" , "false");
                        values.put("do_not_show_negative_days" , "false");
                        values.put("turn_on_notification" , "false");
                        values.put("notification_hour" , -10);
                        values.put("notification_minute" , -10);
                        values.put("colour" , "Blue");
                        colour = "Blue";
                        db4.insert("User", null, values);
                        if (db4 != null)
                        {
                            db4.close();
                        }


                        // goto next activity
                        Intent gotoNavigationPage = new Intent(LoginActivity.this, NavigationActivity.class);
                        gotoNavigationPage.putExtra("currentUser", email);
                        gotoNavigationPage.putExtra("sorting_order" , "date_finished ASC");
                        gotoNavigationPage.putExtra("only_show_fourteen_days" , "false");
                        gotoNavigationPage.putExtra("do_not_show_negative_days" , "false");
                        gotoNavigationPage.putExtra("turn_on_notification" , false);
                        gotoNavigationPage.putExtra("notification_hour" , -10);
                        gotoNavigationPage.putExtra("notification_minute" , -10);
                        gotoNavigationPage.putExtra("colour" , colour);
                        gotoNavigationPage.putExtra("language" , LANGUAGE);
                        Log.d("sign_up_button" , "intent_from_sign_up");
                        startActivity(gotoNavigationPage);
                    }
                }
            }
        });



        // when sign-in button is clicked
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();
                boolean judge = isEmailValid(email) && isPasswordValid(password);
                if (judge == false)
                {
                    if (isEmailValid(email) == false && isPasswordValid(password) == true)
                    {
                        Context context = getApplicationContext();
                        String text = getString(R.string.email_invalid);
                        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    if (isPasswordValid(password) == false && isEmailValid(email) == true)
                    {
                        Context context = getApplicationContext();
                        String text = getString(R.string.password_invalid);
                        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    if (isPasswordValid(password) == false && isEmailValid(email) == false)
                    {
                        Context context = getApplicationContext();
                        String text = getString(R.string.email_and_password_invalid);
                        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                        toast.show();
                    }

                    attemptLogin();

                }

                // start checking sign in information
                boolean valid_sign_in = false;
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                String query = "SELECT COUNT(*) FROM User WHERE user_id = ?";
                String [] parameter = new String [] {email};
                Cursor cursor = db.rawQuery(query , parameter);
                while (cursor.moveToNext())
                {
                    int result = Integer.parseInt (cursor.getString(0).toString());
                    Log.d("Login Activity" , String.valueOf(result));
                    if (cursor == null || result <= 0)
                    {
                        valid_sign_in = false;
                        showMessage(getString(R.string.username_doesnt_exist) , LoginActivity.this);
                        break;
                    }
                    else
                    {
                        String query1 = "SELECT password, sorting_order, only_show_fourteen_days, do_not_show_negative_days, turn_on_notification, notification_hour, notification_minute, colour FROM User WHERE user_id = ?";
                        String [] parameter1 = new String [] {email};
                        Cursor cursor1 = db.rawQuery(query1 , parameter1);
                        while (cursor1.moveToNext())
                        {
                            Log.d("Login Activity1" , String.valueOf(cursor1.getCount()));
                            if (cursor1 != null && cursor1.getCount() > 0)
                            {
                                String valid_password = cursor1.getString(0).toString();
                                sorting_order = cursor1.getString(1).toString();
                                only_show_fourteen_days = cursor1.getString(2).toString();
                                do_not_show_negative_days = cursor1.getString(3).toString();
                                turn_on_notification = cursor1.getString(4).toString();
                                notification_hour = cursor1.getInt(5);
                                notification_minute = cursor1.getInt(6);

                                if (password.equals(valid_password)) {
                                    valid_sign_in = true;
                                    colour = cursor1.getString(7).toString();
                                } else {
                                    valid_sign_in = false;
                                    showMessage(getString(R.string.password_incorrect), LoginActivity.this);
                                    break;
                                }
                            }
                        }
                        if (cursor1 != null)
                        {
                            cursor1.close();
                        }
                    }
                    if (cursor != null)
                    {
                        cursor.close();
                    }
                }
                if (db != null)
                {
                    db.close();
                }


                if (valid_sign_in == true)
                {
                    Intent gotoNavigationPage = new Intent (LoginActivity.this, NavigationActivity.class);
                    gotoNavigationPage.putExtra("currentUser" , email);
                    gotoNavigationPage.putExtra("sorting_order" , sorting_order);
                    gotoNavigationPage.putExtra("only_show_fourteen_days" , only_show_fourteen_days);
                    gotoNavigationPage.putExtra("do_not_show_negative_days" , do_not_show_negative_days);
                    gotoNavigationPage.putExtra("turn_on_notification" , turn_on_notification);
                    gotoNavigationPage.putExtra("notification_hour" , notification_hour);
                    gotoNavigationPage.putExtra("notification_minute" , notification_minute);
                    gotoNavigationPage.putExtra("colour" , colour);
                    gotoNavigationPage.putExtra("language" , LANGUAGE);
                    Log.d("sign in button sending", email);
                    Log.d("sign_in_button" , "intent_from_sign_in");
                    startActivity(gotoNavigationPage);
                }
            }
        });


        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

    }


    // recently deleted to remove the contact uses permission

    /*
    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }
        getLoaderManager().initLoader(0, null, this);
    }

    */


    /*
    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }
    */

    public void showMessage (String message , Context context)
    {
        AlertDialog.Builder messageBox = new AlertDialog.Builder (context);
        messageBox.setTitle(getString(R.string.invalid_login));
        messageBox.setCancelable(false);
        messageBox.setMessage(message);
        messageBox.setPositiveButton(getString(R.string.okay) , null);
        messageBox.show();
    }





    /**
     * Callback received when a permissions request has been completed.
     */

    /*
    @Override

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }

    */



    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */

    // highlight login errors
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            //showProgress(true);
            //mAuthTask = new UserLoginTask(email, password);
            //mAuthTask.execute((Void) null);

            Intent gotoNavigationPage = new Intent (LoginActivity.this, NavigationActivity.class);
            gotoNavigationPage.putExtra("currentUser" , email);
            gotoNavigationPage.putExtra("language" , LANGUAGE);
            Log.d("cencel_button" , "intent_from_cencel");
            //startActivity(gotoNavigationPage);
        }
    }


    // checking email validation
    private boolean isEmailValid(String email) {
        boolean result = true;
        if (!email.contains("@"))
        {
            result = false;
        }
        if (!email.contains("."))
        {
            result = false;
        }
        if (email.equals("testinguser"))
        {
            result = true;
        }
        return result;
    }


    // checking password validation
    private boolean isPasswordValid(String password) {
        if (password.length() < 4)
        {
            return false;
        }
        return true;
    }















    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
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

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("language" , LANGUAGE);
        db.update("Lang" , values , "anon_user_id = ?" , new String [] {"anonymous_language_holder"});
        if (db != null)
        {
            db.close();
        }
    }

    @Override
    public void onStop ()
    {
        super.onStop();
        Log.d(TAG, "onStop");

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("language" , LANGUAGE);
        db.update("Lang" , values , "anon_user_id = ?" , new String [] {"anonymous_language_holder"});
        if (db != null)
        {
            db.close();
        }
    }

    @Override
    public void onDestroy ()
    {
        super.onDestroy();
        Log.d(TAG, "onDestroy");

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("language" , LANGUAGE);
        db.update("Lang" , values , "anon_user_id = ?" , new String [] {"anonymous_language_holder"});
        if (db != null)
        {
            db.close();
        }
    }

    @Override
    public void onRestart ()
    {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }


}

