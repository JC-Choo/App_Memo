package com.example.cnwlc.memo.Util.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cnwlc.memo.Common.Defines;

/**
 * Created by Bridge on 2018-05-24.
 */

public class DBHelper extends SQLiteOpenHelper {
    DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlStudent = "create table if not exists "+ Defines.DATABASE_USER+"(" +
                "_no integer primary key autoincrement," +
                "id text, " +
                "password text, " +
                "cellphone text);";
        db.execSQL(sqlStudent);

        String sqlTeacher = "create table if not exists "+Defines.DATABASE_MEMO+"(" +
                "_no integer primary key autoincrement," +
                "id text, " +
                "time text, " +
                "content text, " +
                "image_path text);";
        db.execSQL(sqlTeacher);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlStudent = "drop table if exists "+ Defines.DATABASE_USER+";";
        db.execSQL(sqlStudent);

        String sqlTeacher = "drop table if exists "+Defines.DATABASE_MEMO+";";
        db.execSQL(sqlTeacher);

        onCreate(db); // 다시 테이블 생성
    }
}