package com.choo.application.memo.App.folder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.choo.application.memo.R;

import java.util.List;

/**
 * Created by JCChu on 2018-06-12.
 */

public class FolderAdapter extends RecyclerView.Adapter<FolderViewHolder> {

    private List<FolderItem> folderItemList;

    public FolderAdapter(List<FolderItem> folderItemList) {
        this.folderItemList = folderItemList;
    }

    @NonNull
    @Override
    public FolderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_folder, parent, false);
        return new FolderViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull FolderViewHolder holder, int position) {
        FolderItem folderItem = (FolderItem) folderItemList.get(position);
        holder.bind(folderItem);
    }

    @Override
    public int getItemCount() {
        return folderItemList.size();
    }
}
