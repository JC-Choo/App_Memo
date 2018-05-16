package com.example.cnwlc.memo.App.main.mvp;

import com.example.cnwlc.memo.App.main.MainItem;
import com.example.cnwlc.memo.Common.BasePresenter;
import com.example.cnwlc.memo.Common.BaseView;

import java.util.List;

/**
 * Created by Bridge on 2018-05-16.
 */

public interface MainContract {
    interface View extends BaseView<Presenter> {
        void showPermissionDialog();
    }
    interface Presenter extends BasePresenter {
        List<MainItem> setData(List<MainItem> mainItemList);
    }
}
