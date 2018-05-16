package com.example.cnwlc.memo.Util.sqlite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Bridge on 2018-05-11.
 */

public class PersonListAdapter extends BaseAdapter {
    private List people;
    private Context context;

    public PersonListAdapter(List people, Context context) {
        this.people = people;
        this.context = context;
    }

    @Override
    public int getCount() {
        return people.size();
    }

    @Override
    public Object getItem(int position) {
        return people.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DBHolder dbHolder = null;
        if (convertView == null) {
            // convertView 가 없으면 초기화
            convertView = new LinearLayout(context);
            ((LinearLayout) convertView).setOrientation(LinearLayout.HORIZONTAL);

            TextView textViewId = new TextView(context);
            textViewId.setPadding(10, 0, 20, 0);
            textViewId.setTextColor(Color.rgb(0, 0, 0));

            TextView textViewName = new TextView(context);
            textViewName.setPadding(20, 0, 20, 0);
            textViewName.setTextColor(Color.rgb(0, 0, 0));

            TextView textViewAge = new TextView(context);
            textViewAge.setPadding(20, 0, 20, 0);
            textViewAge.setTextColor(Color.rgb(0, 0, 0));

            TextView textViewPhone = new TextView(context);
            textViewPhone.setPadding(20, 0, 20, 0);
            textViewPhone.setTextColor(Color.rgb(0, 0, 0));

            ((LinearLayout) convertView).addView(textViewId);
            ((LinearLayout) convertView).addView(textViewName);
            ((LinearLayout) convertView).addView(textViewAge);
            ((LinearLayout) convertView).addView(textViewPhone);

            dbHolder = new DBHolder();
            dbHolder.textViewId = textViewId;
            dbHolder.textViewName = textViewName;
            dbHolder.textViewAge = textViewAge;
            dbHolder.textViewPhone = textViewPhone;

            convertView.setTag(dbHolder);
        } else {
            // convertView가 있으면 홀더를 꺼냅니다.
            dbHolder = (DBHolder) convertView.getTag();
        }

        // 한명의 데이터를 받아와서 입력합니다.
        Person person = (Person) getItem(position);
        dbHolder.textViewId.setText(person.get_id() + "");
        dbHolder.textViewName.setText(person.getName());
        dbHolder.textViewAge.setText(person.getAge() + "");
        dbHolder.textViewPhone.setText(person.getPhone());

        return convertView;
    }
}