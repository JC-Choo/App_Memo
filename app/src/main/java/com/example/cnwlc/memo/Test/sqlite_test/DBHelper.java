package com.example.cnwlc.memo.Test.sqlite_test;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cnwlc.memo.Common.Defines;
import com.example.cnwlc.memo.Common.Dlog;
import com.example.cnwlc.memo.MemoApplication;
import com.example.cnwlc.memo.R;
import com.example.cnwlc.memo.Util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bridge on 2018-05-21.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = Defines.CODE_1;

    private Activity context;
    private String dataBaseName;
    private int dataBaseVersion;

    public DBHelper(Activity context, String dataBaseName, SQLiteDatabase.CursorFactory factory, int dataBaseVersion) {
        super(context, dataBaseName, factory, dataBaseVersion);

        this.context = context;
        this.dataBaseName = dataBaseName;
        this.dataBaseVersion = dataBaseVersion;
    }

    // Database 가 존재하지 않을 때, 딱 한번 실행된다. DB를 만드는 역할을 한다.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // String 보다 StringBuffer가 Query 만들기 편하다.
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" CREATE TABLE "+dataBaseName+" ( ");
        stringBuffer.append(" _ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
        stringBuffer.append(" NAME TEXT, ");
        stringBuffer.append(" AGE TEXT, ");
        stringBuffer.append(" PHONE TEXT ) ");

        // SQLite Database로 쿼리 실행
        sqLiteDatabase.execSQL(stringBuffer.toString());
        ToastUtil.shortToast(context, dataBaseName+" "+MemoApplication.getInstance().getString(R.string.DBHelper_make_table));
    }

    // Application 의 버전이 올라가서 Table 구조가 변경되었을 때 실행된다.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Dlog.d("db : "+db+", oldVersion : "+oldVersion+", newVersion : "+newVersion);
        Dlog.d(MemoApplication.getInstance().getString(R.string.DBHelper_change_version));
    }

    // Data 를 Insert 하기 위한 method
    public void insertData(Person person) {
        // 1. 쓸 수 있는 DB 객체를 가져온다.
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        // 2. Person Data를 Insert한다.
        // _id는 자동으로 증가하기 때문에 넣지 않습니다.
//        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append(" INSERT INTO "+dataBaseName+" ( ");
//        stringBuffer.append(" NAME, AGE, PHONE ) ");
//        stringBuffer.append(" VALUES ( ?, ?, ? ) ");
        // 원래 위처럼 StringBuffer였으나 String 으로 수정 가능(어차피 한 번만 생성하기 때문에 메모리 차지 같을 듯)
        String stringBuffer = " INSERT INTO "+dataBaseName+" ( NAME, AGE, PHONE ) VALUES ( ?, ?, ? ) ";

        sqLiteDatabase.execSQL(stringBuffer, new Object[]{
                person.getName(), person.getAge(), person.getPhone()
        });

        ToastUtil.shortToast(context, MemoApplication.getInstance().getString(R.string.DBHelper_insert_data));
        sqLiteDatabase.close();
    }

    // Data 를 select 하기 위한 method
    public List selectAllData() {
        String stringSelect = " SELECT _ID, NAME, AGE, PHONE FROM "+dataBaseName+" ";

        // 읽기 전용 DB 객체를 만든다.
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(stringSelect, null);
        List<Person> people = new ArrayList<>();
        Person person = null;

        // moveToNext 다음에 데이터가 있으면 true 없으면 false
        while (cursor.moveToNext()) {
            person = new Person();
            person.set_id(cursor.getInt(0));
            person.setName(cursor.getString(1));
            person.setAge(String.valueOf(cursor.getInt(2)));
            person.setPhone(cursor.getString(3));

            people.add(person);
        }

        sqLiteDatabase.close();
        return people;
    }

//    public void updateData(Person person) {
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//
//        String stringUpdate = " UPDATE "+dataBaseName+" SET AGE ="+person.getAge()+", PHONE ="+person.getPhone()+" WHERE NAME = "+person.getName();
//
//        // 입력한 항목과 일치하는 행의 가격 정보 수정
//        sqLiteDatabase.execSQL(stringUpdate);
//        sqLiteDatabase.close();
//    }
//
//    public void deleteData(String name) {
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//
//        String stringDelete = "DELETE FROM "+dataBaseName+" WHERE NAME=" + name;
//
//        // 입력한 항목과 일치하는 행 삭제
//        sqLiteDatabase.execSQL(stringDelete);
//        sqLiteDatabase.close();
//    }
}