package com.choo.application.memo.App.main.mvp;

import android.app.Activity;

import com.choo.application.memo.App.main.MainItem;
import com.choo.application.memo.Common.Defines;
import com.choo.application.memo.Common.Dlog;
import com.choo.application.memo.Util.sqlite.SQLiteUtil;

import java.util.List;

/**
 * Created by Bridge on 2018-06-01.
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
        SQLiteUtil.getInstance().setInintView(context, Defines.TABLE_MEMO);
        SQLiteUtil.getInstance().selectAll();
        List<String> memo = SQLiteUtil.getInstance().selectMemoAll();
            for(int a = 0; a < memo.size(); a++) {
                String[] dividedMemo = memo.get(a).split("\\|");
                MainItem mainItem;
                if(dividedMemo.length > 2)
                    mainItem = new MainItem(dividedMemo[1], dividedMemo[0], dividedMemo[2]);
                else
                    mainItem = new MainItem(dividedMemo[1], dividedMemo[0], null);

                mainItemList.add(mainItem);
            }

        return mainItemList;
    }
}
