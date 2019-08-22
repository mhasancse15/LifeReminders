package com.timetable.todolist.Schedule.lifereminders;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class StartUpActivity extends AppCompatActivity {

    private String TAG = "TAG_start_up_page";
    private String LANGUAGE = "";

    private MyDatabaseHelper dbHelper;

    private int databaseVersion = 6;
    private String databaseName = "information2.db";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);


        dbHelper = new MyDatabaseHelper (this, databaseName, null, databaseVersion);
        //DatabaseVersion.version = DatabaseVersion.version + 1;
        dbHelper.getWritableDatabase();


        SQLiteDatabase db2 = dbHelper.getReadableDatabase();

        String [] params = new String[] {};
        String query = "SELECT COUNT(*), language FROM Lang";
        Cursor cursor2 = db2.rawQuery(query , params);
        int anon_count = 0;
        while (cursor2.moveToNext())
        {
            anon_count = Integer.parseInt(cursor2.getString(0).toString());
            if (anon_count != 0)
            {
                LANGUAGE = cursor2.getString(1).toString();
            }
        }
        if (!LANGUAGE.equalsIgnoreCase("English (EN-GB)") && !LANGUAGE.equalsIgnoreCase("Simplified Chinese (CH-ZN)") && !LANGUAGE.equalsIgnoreCase("Traditional Chinese (CH-TW)") && !LANGUAGE.equalsIgnoreCase("Japanese (JA-JP)") && !LANGUAGE.equalsIgnoreCase("French (FR-FA)"))
        {
            LANGUAGE = "English (EN-GB)";
        }
        if (cursor2 != null)
        {
            cursor2.close();
        }
        if (db2 != null)
        {
            db2.close();
        }
        if (dbHelper != null)
        {
            dbHelper.close();
        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartUpActivity.this , LoginActivity.class);
                intent.putExtra("language" , LANGUAGE);
                StartUpActivity.this.startActivity(intent);
                StartUpActivity.this.finish();
            }
        }, 5000);
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
