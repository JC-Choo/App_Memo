package com.choo.application.memo.App.main.contract;

import com.choo.application.memo.App.main.fragment_main.FragmentMainItem;
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
        List<FragmentMainItem> setData(List<FragmentMainItem> fragmentMainItemList);
    }
}
