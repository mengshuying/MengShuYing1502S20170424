package com.example.administrator.mengshuying1502s20170424;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * date:${DATA}
 * author:孟淑英
 * function:
 */

public class MySqilte extends SQLiteOpenHelper{
    public MySqilte(Context context){
        super(context, "test.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String creaTTable = "create table user (_id integer PRIMARY KEY AUTOINCREMENT NOT NULL,imageurl varchar,title varchar,fromname  varchar,showtime varchar)";
        db.execSQL(creaTTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
