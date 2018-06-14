package com.choo.application.memo.Util.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.choo.application.memo.Common.Defines;

/**
 * Created by Bridge on 2018-06-14.
 */

public class DBHelper extends SQLiteOpenHelper {
    DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlFolderName = "create table if not exists "+ Defines.FOLDER+"(" +
                "id integer primary key autoincrement," +
                "folder_name text);";
        db.execSQL(sqlFolderName);

        String sqlMemo = "create table if not exists "+Defines.MEMO+"(" +
                "id integer primary key autoincrement," +
                "folder_name_id integer, " +
                "time text, " +
                "content text, " +
                "image_path text);";
        db.execSQL(sqlMemo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlFolderName = "drop table if exists "+ Defines.FOLDER+";";
        db.execSQL(sqlFolderName);

        String sqlMemo = "drop table if exists "+Defines.MEMO+";";
        db.execSQL(sqlMemo);

        onCreate(db); // 다시 테이블 생성
    }
}