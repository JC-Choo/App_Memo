package com.choo.application.memo.App.folder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.choo.application.memo.Common.Dlog;
import com.choo.application.memo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JCChu on 2018-06-16.
 */

public class FolderAdapter extends RecyclerView.Adapter<FolderViewHolder> {

    private Context context;
    private List<FolderItem> folderItemList;
    private int checkBoxVisibility;

    public FolderAdapter(Context context, List<FolderItem> folderItemList, int checkBoxVisibility) {
        this.context = context;
        this.folderItemList = folderItemList;
        this.checkBoxVisibility = checkBoxVisibility;
    }

    @Override
    public int getItemCount() {
        return folderItemList.size();
    }

    @NonNull
    @Override
    public FolderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_folder, parent, false);
        return new FolderViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull FolderViewHolder holder, int position) {
        FolderItem folderItem = folderItemList.get(position);
        initializeViews(folderItem, holder, position);
    }

    private void initializeViews(final FolderItem folderItem, final FolderViewHolder holder, int position) {
        if(checkBoxVisibility == 0) holder.checkBoxEdit.setVisibility(View.GONE);
        else holder.checkBoxEdit.setVisibility(View.VISIBLE);

        holder.textViewTitle.setText(folderItem.getStringTitle());
        holder.textViewCountOfMemos.setText(folderItem.getStringCountOfMemos());

        holder.checkBoxEdit.setChecked(folderItem.isSelected());
        holder.checkBoxEdit.setTag(position);
        holder.checkBoxEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox checkBox = (CheckBox) view;
                int clickedPosition = (Integer) checkBox.getTag();
                folderItemList.get(clickedPosition).setSelected(checkBox.isChecked());
                notifyDataSetChanged();
            }
        });
    }

    public List<FolderItem> getSelectedItem() {
        List<FolderItem> folderItems = new ArrayList<>();

        for (int i = 0; i < folderItemList.size(); i++) {
            FolderItem itemModel = folderItemList.get(i);
            if (itemModel.isSelected()) {
                folderItems.add(itemModel);
            }
        }
        return folderItems;
    }
}
