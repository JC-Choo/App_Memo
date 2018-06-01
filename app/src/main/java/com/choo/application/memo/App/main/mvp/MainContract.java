package com.choo.application.memo.App.main.mvp;

import com.choo.application.memo.App.main.MainItem;
import com.choo.application.memo.Common.BasePresenter;
import com.choo.application.memo.Common.BaseView;

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
