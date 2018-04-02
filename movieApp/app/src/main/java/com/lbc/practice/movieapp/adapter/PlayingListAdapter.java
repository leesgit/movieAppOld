package com.lbc.practice.movieapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lbc.practice.movieapp.PlayingMovieResult;
import com.lbc.practice.movieapp.R;
import com.lbc.practice.movieapp.callback.ListItemViewCallBack;
import com.lbc.practice.movieapp.callback.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lbc on 2018-04-02.
 */

public class PlayingListAdapter extends RecyclerView.Adapter<PlayingListViewHolder> implements ListItemViewCallBack {

    List<PlayingMovieResult.ResultsBean> list = new ArrayList<>();

    public void clearItem(){
        list.clear();
    }

    public void addItem(PlayingMovieResult.ResultsBean item){
        list.add(item);
    }

    public void addItem(List<PlayingMovieResult.ResultsBean> items){
        list.addAll(items);
    }

    @Override
    public PlayingListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_view_listitem,parent,false);
        PlayingListViewHolder holder = new PlayingListViewHolder(v, this);
        return holder;
    }

    @Override
    public void onBindViewHolder(PlayingListViewHolder holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public PlayingMovieResult.ResultsBean getItem(int position) {
        return list.get(position);
    }


    private OnItemClickListener onItemClickListener;

    public void setOnCustomItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public OnItemClickListener getOnCustomItemClickListener() {
        return onItemClickListener;
    }

}
