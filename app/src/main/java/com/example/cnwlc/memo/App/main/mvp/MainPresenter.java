package com.example.cnwlc.memo.App.main.mvp;

import android.app.Activity;

import com.example.cnwlc.memo.App.main.MainItem;
import com.example.cnwlc.memo.Common.Defines;
import com.example.cnwlc.memo.Util.sqlite.SQLiteUtil;

import java.util.List;

/**
 * Created by Bridge on 2018-05-28.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private Activity context;

    public MainPresenter(MainContract.View view, Activity context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void start() {
        view.showPermissionDialog();
    }

    @Override
    public List<MainItem> setData(List<MainItem> mainItemList) {
//        String[] stringDividedTime = DateUtil.getCurrentTimeYMDAHM().split(" ");
//        StringBuilder stringBuilderTime = new StringBuilder();
//
//        for (String aStringDividedTime : stringDividedTime) {
//            if (aStringDividedTime.equals(DateUtil.getCurrentTimeYMD())) {
//                stringBuilderTime.append(stringDividedTime[1]).append(" ").append(stringDividedTime[2]);
//                break;
//            } else
//                stringBuilderTime = new StringBuilder(aStringDividedTime);
//        }


        SQLiteUtil.getInstance().setInintView(context, Defines.TABLE_MEMO);
        SQLiteUtil.getInstance().selectAll();
        List<String> memo = SQLiteUtil.getInstance().selectMemoAll();
        String[] dividedMemo = new String[0];
        for(int a = 0; a < memo.size(); a++) {
            dividedMemo = memo.get(a).split("\\|");

            MainItem mainItem = new MainItem(dividedMemo[1], dividedMemo[0], dividedMemo[2]);
            mainItemList.add(mainItem);
        }

        return mainItemList;
    }
}
