package com.example.newsapitest.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * 创建数据库
 */

public class MyDataBaseHelper extends SQLiteOpenHelper {
    public static  final String CREATE_CHANNEL= "create table channel(id text," +
            "name text)";
    public static final String CREATE_NEWS = "create table news(id text," +
            "title text," +
            "havePic int,"+
            "channelId text,"+
            "channelName text," +
            "source text)";
    public static final String CREATE_HISTORY="create table history(id text,title text)" ;
    Context m_context;
    public MyDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        m_context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CHANNEL);
        db.execSQL(CREATE_NEWS);
        db.execSQL(CREATE_HISTORY);
        //Toast.makeText(m_context,"database create succeed",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists channel");
        db.execSQL("drop table if exists news");
        db.execSQL("drop table if exists history");
        onCreate(db);
    }
}
