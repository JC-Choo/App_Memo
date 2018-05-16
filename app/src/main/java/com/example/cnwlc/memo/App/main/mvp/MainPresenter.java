package com.example.cnwlc.memo.App.main.mvp;

import android.app.Activity;

import com.example.cnwlc.memo.App.main.MainItem;
import com.example.cnwlc.memo.Util.DateUtil;

import java.util.List;

/**
 * Created by Bridge on 2018-05-16.
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
        String[] stringDividedTime = DateUtil.getCurrentTimeYMDAHM().split(" ");
        StringBuilder stringBuilderTime = new StringBuilder();

        for (String aStringDividedTime : stringDividedTime) {
            if (aStringDividedTime.equals(DateUtil.getCurrentTimeYMD())) {
                stringBuilderTime.append(stringDividedTime[1]).append(" ").append(stringDividedTime[2]);
                break;
            } else
                stringBuilderTime = new StringBuilder(aStringDividedTime);
        }

        for (int i=0; i<15; i++) {
            MainItem mainItem = new MainItem("MainItem Test_"+i, stringBuilderTime.toString(), "상");
            mainItemList.add(mainItem);
        }

        return mainItemList;
    }
}
