package com.example.cnwlc.memo.Util.sqlite.signup;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;

import com.example.cnwlc.memo.Common.Dlog;

import java.util.List;

/**
 * Created by Bridge on 2018-05-21.
 */

public class SQLiteUtilUser {
    private SQLiteUtilUser() {}
    private static class SingleTon {
        public static final SQLiteUtilUser Instance = new SQLiteUtilUser();
    }
    public static SQLiteUtilUser getInstance() {
        return SingleTon.Instance;
    }

    private Activity context;
    private String dataBaseName;
    // SQLiteUtil 에서 사용할 초기값 할당할 메소드
    public void setInitValueUser(Activity context, String dataBaseName) {
        this.context = context;
        this.dataBaseName = dataBaseName;
    }

    // DBHelper 호출
    private DBHelperUser dbHelperUser;
    // DBHelper 정의
    // DataBase Name 을 정할 method(context 는 Toast를 띄우기 위해 사용, dataBaseName 은 db의 이름을 정하기 위해 사용)
    public void dataBaseNameUser() {
        dbHelperUser = new DBHelperUser(context, dataBaseName, null, 1);
        Dlog.i("dataBaseName : "+dataBaseName);
    }

    // Data를 입력하기 위한 method
    public void setDataUser(String id, String password, String mobilePhone) {
        if (dbHelperUser == null) {
            dbHelperUser = new DBHelperUser(context, dataBaseName, null, 1);
        }

        User user = new User();
        user.setId(id);
        user.setPassword(password);
        user.setPhone(mobilePhone);

        dbHelperUser.insertDataUser(user);
    }

    // Data를 출력하기 위한 method
    public void showDataUser() {
        // DB Helper가 Null이면 초기화 시켜준다.
        if (dbHelperUser == null) {
            dbHelperUser = new DBHelperUser(context, dataBaseName, null, 1);
        }

        // 1. Person 데이터를 모두 가져온다.
        List user = dbHelperUser.selectAllDataUser();

        // 2. ListView에 Person 데이터를 모두 보여준다.
        for(int i=0; i<user.size(); i++) {
            Dlog.d("user.get("+i+") : "+user.get(i));
        }
    }
}
