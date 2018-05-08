package com.example.cnwlc.memo.App;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.cnwlc.memo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bridge on 2018-05-06.
 */


public class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.memo_item_textView_title)
    TextView tvTitle;
    @BindView(R.id.memo_item_textView_import)
    TextView tvImport;
    @BindView(R.id.memo_item_textView_daily)
    TextView tvDaily;

    public ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
