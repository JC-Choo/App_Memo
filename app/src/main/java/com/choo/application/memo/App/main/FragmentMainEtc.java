package com.choo.application.memo.App.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.choo.application.memo.App.etc.TimerCountDownActivity;
import com.choo.application.memo.App.etc.VersionActivity;
import com.choo.application.memo.App.etc.alarm.AlarmActivity;
import com.choo.application.memo.App.etc.game.one.OneToFifty;
import com.choo.application.memo.App.etc.game.snake.Snake;
import com.choo.application.memo.R;
import com.choo.application.memo.Util.DialogUtil;
import com.choo.application.memo.Util.SharedPreferenceUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by JCChu on 2018-06-12.
 */

public class FragmentMainEtc extends Fragment {
    private DialogUtil dialogUtil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_etc, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @OnClick({R.id.fragmentMainEtc_button_alarm, R.id.fragmentMainEtc_button_timer, R.id.fragmentMainEtc_button_game_1,
            R.id.fragmentMainEtc_button_game_2, R.id.fragmentMainEtc_button_version})
    public void onClickEvent(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.fragmentMainEtc_button_alarm :
                intent = new Intent(getActivity(), AlarmActivity.class);
                break;
            case R.id.fragmentMainEtc_button_timer :
                intent = new Intent(getActivity(), TimerCountDownActivity.class);
                break;
            case R.id.fragmentMainEtc_button_game_1 :
                intent = new Intent(getActivity(), OneToFifty.class);
                break;
            case R.id.fragmentMainEtc_button_game_2 :
                intent = new Intent(getActivity(), Snake.class);
                break;
            case R.id.fragmentMainEtc_button_version :
                intent = new Intent(getActivity(), VersionActivity.class);
                break;
//            case R.id.fragmentMainEtc_button_log_out :
//                dialogUtil = DialogUtil.getDialog(getActivity(), getString(R.string.confirm), getString(R.string.dialog_content_log_out), yes, dissMiss);
//                dialogUtil.show();
//                break;
        }

        if(intent != null)
            startActivity(intent);
    }

//    View.OnClickListener yes = new View.OnClickListener() {
//        public void onClick(View v) {
//            Intent intent = new Intent(getActivity(), SignInActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//
//            SharedPreferenceUtil.getInstance().clearLoginCheckBox();
//
//            dialogUtil.dismiss();
//        }
//    };
//    View.OnClickListener dissMiss = new View.OnClickListener() {
//        public void onClick(View v) {
//            dialogUtil.dismiss();
//        }
//    };
}
