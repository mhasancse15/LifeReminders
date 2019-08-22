package com.timetable.todolist.Schedule.lifereminders;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by David Wu on 1/12/2017.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private int databaseVersion = 6;
    private String databaseName = "information2.db";

    public MyDatabaseHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    public static final String CREATE_USER_TABLE = "CREATE TABLE User ("
            + "user_id VARCHAR(100) PRIMARY KEY, "
            + "password VARCHAR(600) NOT NULL, "
            + "sorting_order VARCHAR(200), "
            + "only_show_fourteen_days VARCHAR(200), "
            + "do_not_show_negative_days VARCHAR(200), "
            + "turn_on_notification VARCHAR(200), "
            + "notification_hour INTEGER, "
            + "notification_minute INTEGER, "
            + "colour VARCHAR(200) ) ";

    // User table: id, name

    public static final String CREATE_TODO_TABLE = "CREATE TABLE Item ("
            + "item_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "date_created TEXT NOT NULL, "
            + "date_started TEXT, "
            + "date_finished TEXT NOT NULL, "
            + "creator_id VARCHAR(100) REFERENCES User (user_id) ON UPDATE CASCADE ON DELETE CASCADE, "
            + "name VARCHAR (100) NOT NULL, "
            + "classification VARCHAR(100) NOT NULL, "
            + "content VARCHAR (10000) )";

    // Item table: id, name, classification, context, creator_id, date_created, date_started, date_finished

    public static final String CREATE_LANG_TABLE = "CREATE TABLE Lang ("
            + "anon_user_id VARCHAR(100) PRIMARY KEY, "
            + "language VARCHAR(600) )";

    // Anonymous user table: id, language

    @Override
    public void onCreate (SQLiteDatabase db)
    {
        //db.execSQL("DROP TABLE IF EXISTS User");
        //db.execSQL("DROP TABLE IF EXISTS Item");

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_TODO_TABLE);
        db.execSQL(CREATE_LANG_TABLE);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS User");
        db.execSQL("DROP TABLE IF EXISTS Item");
        db.execSQL("DROP TABLE IF EXISTS Lang");
        onCreate(db);
    }

}
