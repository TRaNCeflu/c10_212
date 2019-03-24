package com.example.newsapitest.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.newsapitest.models.ChannelDB;
import com.example.newsapitest.models.NewsDB;
import com.example.newsapitest.models.NewsForHistory;

import java.util.ArrayList;
import java.util.List;

/**
 * 更新数据库
 */

public class Daompl {
    public static void saveChannelinDB(List<ChannelDB> channelDBList,MyDataBaseHelper dbHelper){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("delete from channel where 1=1");
        ContentValues values = new ContentValues();
        for(int i = 0;i<channelDBList.size();i++){
            ChannelDB channelDB = channelDBList.get(i);
            values.put("id",channelDB.getId());
            values.put("name",channelDB.getName());
            db.insert("channel",null,values);
        }
    }

    public static void saveNewsinDB(List<NewsDB> newsDBList,MyDataBaseHelper dbHelper){
        SQLiteDatabase db  = dbHelper.getWritableDatabase();
        db.execSQL("delete from news where 1=1");
        ContentValues values = new ContentValues();
        for(int i = 0;i<newsDBList.size();i++){
            NewsDB newsDB = newsDBList.get(i);
            values.put("id",newsDB.getId());
            values.put("title",newsDB.getTitle());
            values.put("havePic",newsDB.getHavePic());
            values.put("channelId",newsDB.getChannelId());
            values.put("channelName",newsDB.getChannelName());
            values.put("source",newsDB.getSource());
            db.insert("news",null,values);
        }
    }
    public static List<ChannelDB> getChannelListFromDB(MyDataBaseHelper dbHelper){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor  = db.rawQuery("select * from channel",null);
        List<ChannelDB> channelDBList = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                ChannelDB channel = new ChannelDB();
                channel.setName(cursor.getString(cursor.getColumnIndex("name")));
                channel.setId(cursor.getString(cursor.getColumnIndex("id")));
                channelDBList.add(channel);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return channelDBList;
    }
    public static void saveIdinHistory(String id,String title,MyDataBaseHelper dbHelper){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id",id);
        values.put("title",title);
        db.insert("history",null,values);
    }

    /**
     * 从history数据库中抽取数据方入List中
     * @param dbHelper
     * @return
     */
    public static List<NewsForHistory> getNewsListFromHisttory(MyDataBaseHelper dbHelper){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from history",null);
        List<NewsForHistory> newsForHistoryList = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                NewsForHistory news = new NewsForHistory();
                news.setId(cursor.getString(cursor.getColumnIndex("id")));
                news.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                newsForHistoryList.add(news);
            }while(cursor.moveToNext());
        }
        List<NewsForHistory> newsForHistoryList1 = new ArrayList<>();
        for(int i = newsForHistoryList.size()-1;i>=0;i--){
            newsForHistoryList1.add(newsForHistoryList.get(i));
        }
        cursor.close();
        return newsForHistoryList;
    }
}
