package com.choo.application.memo.App.main.fragment_main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.choo.application.memo.App.main.RecyclerItemClickListener;
import com.choo.application.memo.App.main.contract.MainContract;
import com.choo.application.memo.App.main.presenter.MainPresenter;
import com.choo.application.memo.App.main_memo.MemoActivity;
import com.choo.application.memo.Common.Defines;
import com.choo.application.memo.Common.Dlog;
import com.choo.application.memo.R;
import com.choo.application.memo.Util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by JCChu on 2018-06-02.
 */

public class FragmentMain extends Fragment implements MainContract.View {

    @BindView(R.id.fragmentMain_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.fragmentMain_text_view_number_of_memos)
    TextView textViewNumberOfMemos;
    @BindView(R.id.fragmentMain_text_view_search)
    TextView textViewSearch;

    private Activity context;
    private InputMethodManager inputMethodManager;

    private MainPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);

        context = getActivity();

        return rootView;
    }

    @Override
    public void showPermissionDialog() {
//        PermissionUtil.getInstance().setInitValue(context, recyclerView);
//        PermissionUtil.getInstance().showPermission();
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) { }

    @SuppressLint("SetTextI18n")
    private void initView() {
        presenter = new MainPresenter(this, context);
        presenter.start();

        inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

        List<FragmentMainItem> fragmentMainItemList = new ArrayList<>();
        FragmentMainRecyclerAdapter fragmentMainRecyclerAdapter = new FragmentMainRecyclerAdapter(presenter.setData(fragmentMainItemList));
        recyclerView.setAdapter(fragmentMainRecyclerAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context.getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(context, MemoActivity.class);
                intent.putExtra(Defines.MAIN_TO_MEMO, Defines.READ);
                intent.putExtra(Defines.THE_NUMBER_OF_MEMO, position);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                Dlog.d("Long_Click_Event");
            }
        }));

        textViewNumberOfMemos.setText(fragmentMainRecyclerAdapter.getItemCount()+getString(R.string.count_of_memo));
        if(fragmentMainRecyclerAdapter.getItemCount() == 0)
            textViewSearch.setVisibility(View.GONE);
    }

    @OnClick({R.id.fragmentMain_relative_layout_back, R.id.fragmentMain_image_view_add_memo, R.id.fragmentMain_text_view_edit, R.id.fragmentMain_text_view_search})
    public void onClickedSecond(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.fragmentMain_relative_layout_back :
                ToastUtil.shortToast(context, "wait...");
                break;
            case R.id.fragmentMain_image_view_add_memo :
                intent = new Intent(context, MemoActivity.class);
                intent.putExtra(Defines.MAIN_TO_MEMO, Defines.WRITE);
                break;
            case R.id.fragmentMain_text_view_edit :
                ToastUtil.shortToast(context, "wait...");
                break;
            case R.id.fragmentMain_text_view_search :
                inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                break;
        }

        if(intent != null)
            startActivity(intent);
    }
}
