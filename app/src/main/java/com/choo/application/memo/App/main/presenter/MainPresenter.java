package com.choo.application.memo.App.main.presenter;

import android.app.Activity;

import com.choo.application.memo.App.main.fragment_main.FragmentMainItem;
import com.choo.application.memo.App.main.contract.MainContract;
import com.choo.application.memo.Common.Defines;
import com.choo.application.memo.Util.sqlite.SQLiteUtil;

import java.util.List;

/**
 * Created by Bridge on 2018-06-12.
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
    public List<FragmentMainItem> setData(List<FragmentMainItem> fragmentMainItemList) {
        SQLiteUtil.getInstance().setInintView(context, Defines.TABLE_MEMO);
        SQLiteUtil.getInstance().selectAll();

        List<String> memo = SQLiteUtil.getInstance().selectMemoAll();
        for (int a = 0; a < memo.size(); a++) {
            String[] dividedMemo = memo.get(a).split("\\|");
            FragmentMainItem fragmentMainItem;
            if (dividedMemo.length > 2)
                fragmentMainItem = new FragmentMainItem(dividedMemo[1], dividedMemo[0], dividedMemo[2]);
            else
                fragmentMainItem = new FragmentMainItem(dividedMemo[1], dividedMemo[0], null);

            fragmentMainItemList.add(fragmentMainItem);
        }

        return fragmentMainItemList;
    }
}
