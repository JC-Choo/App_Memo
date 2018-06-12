package com.choo.application.memo.App.folder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.choo.application.memo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JCChu on 2018-06-12.
 */

public class FolderViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.folder_item_text_view_title)
    TextView textViewTitle;
    @BindView(R.id.folder_item_text_view_count_of_memos)
    TextView textViewCountOfMemos;
    @BindView(R.id.folder_item_image_view)
    ImageView imageView;

    public FolderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(FolderItem folderItem) {
        textViewTitle.setText(folderItem.getStringTitle());
        textViewCountOfMemos.setText(folderItem.getStringCountOfMemos());
    }
}
