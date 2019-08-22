package com.timetable.todolist.Schedule.lifereminders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadingActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView progreeeBarTextview;
    private String LANGUAGE = "";

    private int databaseVersion = 6;
    private String databaseName = "information2.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        if (getIntent().getStringExtra("language") != null)
        {
            LANGUAGE = getIntent().getStringExtra("language").toString();
            //initLocaleLanguage();
        }

        if (!LANGUAGE.equalsIgnoreCase("English (EN-GB)") && !LANGUAGE.equalsIgnoreCase("Simplified Chinese (CH-ZN)") && !LANGUAGE.equalsIgnoreCase("Traditional Chinese (CH-TW)") && !LANGUAGE.equalsIgnoreCase("Japanese (JA-JP)") && !LANGUAGE.equalsIgnoreCase("French (FR-FA)"))
        {
            LANGUAGE = "English (EN-GB)";
        }

        progressBar = (ProgressBar) findViewById(R.id.loading_page_progress_bar);
        progreeeBarTextview = (TextView) findViewById(R.id.progress_bar_textview);

        /*
        new Thread()
        {
            @Override
            public void run ()
            {
                int i = 0;
                while (i < 100)
                {
                    i = i + 1;
                    try
                    {
                        Thread.sleep(8);
                    }
                    catch (InterruptedException ie)
                    {
                        ie.printStackTrace();
                    }
                    final int j = i;
                    progressBar.setProgress(i);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progreeeBarTextview.setText(j + "%");
                        }
                    });
                }
            }


        }.start();
        */


        Intent intent = new Intent (LoadingActivity.this, LoginActivity.class);
        intent.putExtra("language", LANGUAGE);
        startActivity(intent);
        finish();

    }
}
