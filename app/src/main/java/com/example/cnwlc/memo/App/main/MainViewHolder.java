package com.example.cnwlc.memo.App.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.cnwlc.memo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bridge on 2018-05-16.
 */

public class MainViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.memo_item_textView_title)
    TextView textViewTitle;
    @BindView(R.id.memo_item_textView_import)
    TextView textViewImport;
    @BindView(R.id.memo_item_textView_daily)
    TextView textViewDaily;

    public MainViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(MainItem mainItem) {
        textViewTitle.setText(mainItem.getTitle());
        textViewImport.setText(mainItem.getImportance());
        textViewDaily.setText(mainItem.getDaily());
    }
}
