package com.choo.application.memo.App.main.fragment_main;

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

public class FragmentMainRecyclerAdapter extends RecyclerView.Adapter<FragmentMainViewHolder> {

    private List<FragmentMainItem> fragmentMainItemList;

    public FragmentMainRecyclerAdapter(List<FragmentMainItem> fragmentMainItemList) {
        this.fragmentMainItemList = fragmentMainItemList;
    }

    @NonNull
    @Override
    public FragmentMainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_memo, parent, false);
        return new FragmentMainViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull FragmentMainViewHolder holder, int position) {
        FragmentMainItem fragmentMainItem = (FragmentMainItem) fragmentMainItemList.get(position);
        holder.bind(fragmentMainItem);
    }

    @Override
    public int getItemCount() {
        return fragmentMainItemList.size();
    }
}
