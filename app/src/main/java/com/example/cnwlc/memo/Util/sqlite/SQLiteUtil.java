package com.example.cnwlc.memo.Util.sqlite;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.cnwlc.memo.R;

import java.util.List;

/**
 * Created by Bridge on 2018-05-17.
 */

public class SQLiteUtil {

    /** 멀티쓰레드에서 안전한(Thread-Safe) SingleTon Class, Instance 만드는 방법
     * Initialization on demand holder idiom 기법(holder 에 의한 초기화)
     * class 안에 class(holder(LazyHolder -> SingleTon 으로 바꿔서 사용했음))을 두어 JVM 의 Class loader 매커니즘과 Class가 로드되는 시점을 이용한 방법
     * JVM 의 Class loader 의 매커니즘과 class의 load 시점을 이용하여 내부 class를 생성시킴으로 Thread 간의 동기화 문제를 해결하는 방법
     * 가장 많이 사용하고 일반적인 SingleTon Class 사용 방법
     *
     * 참고 :
     * https://blog.seotory.com/post/2016/03/java-singleton-pattern
     * http://jeong-pro.tistory.com/86
     *
     */
    private SQLiteUtil() {}
    private static class SingleTon {
        public static final SQLiteUtil Instance = new SQLiteUtil();
    }
    public static SQLiteUtil getInstance() {
        return SingleTon.Instance;
    }

    private Activity context;
    private String dataBaseName;
    // SQLiteUtil 에서 사용할 초기값 할당할 메소드
    public void setInitValue(Activity context, String dataBaseName) {
        this.context = context;
        this.dataBaseName = dataBaseName;
    }

    // Dialog 형태에 대한 layout 정의
    public void setTestDialogView() {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText etName = new EditText(context);
        etName.setHint(R.string.SQLiteUtil_enter_name);

        final EditText etAge = new EditText(context);
        etAge.setHint(R.string.SQLiteUtil_enter_age);

        final EditText etPhone = new EditText(context);
        etPhone.setHint(R.string.SQLiteUtil_enter_cellphone);

        layout.addView(etName);
        layout.addView(etAge);
        layout.addView(etPhone);

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("정보를 입력하세요")
                .setView(layout)
                .setPositiveButton("등록", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = etName.getText().toString();
                String age = etAge.getText().toString();
                String phone = etPhone.getText().toString();

                dataName(name, age, phone);
            }
        }).setNeutralButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).create().show();
    }


    // DBHelper 호출
    private DBHelper dbHelper;
    // DBHelper 정의
    // DataBase Name 을 정할 method(context 는 Toast를 띄우기 위해 사용, dataBaseName 은 db의 이름을 정하기 위해 사용)
    public void dataBaseName() {
        dbHelper = new DBHelper(context, dataBaseName, null, 1);
        dbHelper.testDB();
    }

    public void dataName(String name, String age, String phone) {
        if (dbHelper == null) {
            dbHelper = new DBHelper(context, dataBaseName, null, 1);
        }

        Person person = new Person();
        person.setName(name);
        person.setAge(age);
        person.setPhone(phone);

        dbHelper.addPerson(person);
    }

    public void getData(ListView lvPeople) {
        // ListView를 보여준다.
        lvPeople.setVisibility(View.VISIBLE);

        // DB Helper가 Null이면 초기화 시켜준다.
        if (dbHelper == null) {
            dbHelper = new DBHelper(context, dataBaseName, null, 1);
        }

        // 1. Person 데이터를 모두 가져온다.
        List people = dbHelper.getAllPersonData();

        // 2. ListView에 Person 데이터를 모두 보여준다.
        lvPeople.setAdapter(new PersonAdapter(people, context));
    }
}
