package com.example.cnwlc.memo.App.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.cnwlc.memo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bridge on 2018-05-28.
 */

public class MainViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.memo_item_textView_content)
    TextView textViewContent;
    @BindView(R.id.memo_item_textView_time)
    TextView textViewTime;
    @BindView(R.id.memo_item_textView_etc)
    TextView textViewEtc;

    public MainViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(MainItem mainItem) {
        textViewContent.setText(mainItem.getContent());
        textViewTime.setText(mainItem.getTime());
        textViewEtc.setText(mainItem.getEtc());
    }
}
