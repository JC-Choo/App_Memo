package com.example.cnwlc.memo.Util.sqlite;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.cnwlc.memo.R;

import java.util.List;

public class SQLiteUtil {
    private static Activity context;
    private static String dataBaseName;

    private static SQLiteUtil instance;
    public static SQLiteUtil getInstance() {
        if(instance == null)
            instance = new SQLiteUtil(context, dataBaseName);

        return instance;
    }

    private SQLiteUtil(Activity context, String dataBaseName) {
        this.context = context;
        this.dataBaseName = dataBaseName;

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
    }


    private DBHelper dbHelper;
    public void dataBaseName() {
        dbHelper = new DBHelper(context, dataBaseName, null, 1);
//        dbHelper.testDB();
    }

    public void dataName(String name, String age, String phone) {
        if (dbHelper == null) {
            dbHelper = new DBHelper(context, "TEST", null, 1);
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
            dbHelper = new DBHelper(context, "TEST", null, 1);
        }

        // 1. Person 데이터를 모두 가져온다.
        List people = dbHelper.getAllPersonData();

        // 2. ListView에 Person 데이터를 모두 보여준다.
        lvPeople.setAdapter(new PersonListAdapter(people, context));
    }
}
