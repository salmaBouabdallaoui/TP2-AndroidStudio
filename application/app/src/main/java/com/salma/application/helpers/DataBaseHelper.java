package com.salma.application.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static DataBaseHelper instance;
    public static synchronized DataBaseHelper getInstance(Context context){
        if(instance == null){
            instance = new DataBaseHelper(context);

        }
        return instance;
    }

    public DataBaseHelper(@Nullable Context context) {

        super(context, "contacts.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists contacts(id integer primary key autoincrement, nom text, phone text);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists contacts");
        onCreate(sqLiteDatabase);
    }
}
