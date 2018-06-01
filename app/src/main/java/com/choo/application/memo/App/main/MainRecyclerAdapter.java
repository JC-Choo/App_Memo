package com.choo.application.memo.App.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.choo.application.memo.R;

import java.util.List;

/**
 * Created by Bridge on 2018-05-16.
 */

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainViewHolder> {

    private List<MainItem> mainItemList;

    public MainRecyclerAdapter(List<MainItem> mainItemList) {
        this.mainItemList = mainItemList;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_memo, parent, false);
        return new MainViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        MainItem mainItem = (MainItem) mainItemList.get(position);
        holder.bind(mainItem);
    }

    @Override
    public int getItemCount() {
        return mainItemList.size();
    }
}
