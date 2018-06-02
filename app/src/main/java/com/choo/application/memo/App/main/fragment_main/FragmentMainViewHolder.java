package com.choo.application.memo.App.main.fragment_main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.choo.application.memo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bridge on 2018-05-28.
 */

public class FragmentMainViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.memo_item_textView_content)
    TextView textViewContent;
    @BindView(R.id.memo_item_textView_time)
    TextView textViewTime;
    @BindView(R.id.memo_item_textView_etc)
    TextView textViewEtc;

    public FragmentMainViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(FragmentMainItem fragmentMainItem) {
        textViewContent.setText(fragmentMainItem.getContent());
        textViewTime.setText(fragmentMainItem.getTime());
        textViewEtc.setText(fragmentMainItem.getEtc());
    }
}
