package com.example.administrator.note;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Matrix;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/4/20.
 */

public class NotesDB extends SQLiteOpenHelper{

    public static final String TABLE_NAME = "notes";
    public static final String CONTENT = "content";
    public static final String PATH = "path";
    public static final String VIDEO = "video";
    public static final String  ID = "_id";
    public static final String TIME = "time";

    public NotesDB(Context context) {
        super(context, "notes", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME + "("
                    + ID + " integer primary key autoincrement,"
                    + CONTENT +" text not null,"
                    + PATH + " text not null,"
                    + VIDEO + " text not null,"
                    + TIME + " text not null)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
