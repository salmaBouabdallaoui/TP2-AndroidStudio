package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static synchronized DatabaseHelper getInstance(Context context){
        super(context,"contacts.db",null,1)
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists contacts (id integer primary key autoincrement, nom text, phone text);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        sqLiteDatabase.exesSQL("DROP TABLE IF EXISTS CONTACTS;");
        onCreate(sqLiteDatabase);
    }
}
