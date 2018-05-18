package com.lbc.practice.movieapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lbc.practice.movieapp.model.PlayingMovieResult;
import com.lbc.practice.movieapp.R;
import com.lbc.practice.movieapp.callback.OnItemClickListener;
import com.lbc.practice.movieapp.callback.Refresh;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lbc on 2018-04-02.
 */

public class PlayingListAdapter extends RecyclerView.Adapter<PlayingListViewHolder> implements PlayingListContract.PlayingModel,PlayingListContract.View {

    List<PlayingMovieResult.ResultsBean> list = new ArrayList<>();
    Refresh refresh;

    public PlayingListAdapter() {
    }

    public PlayingListAdapter(Refresh refresh) {
        this.refresh = refresh;
        Log.e("refresh-1", "11");
    }


    @Override
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
        if (refresh!=null) {
            PlayingListViewHolder holder = new PlayingListViewHolder(v, onItemClickListener,refresh);

            return holder;
        } else {
            PlayingListViewHolder holder = new PlayingListViewHolder(v, onItemClickListener);
            return holder;
        }

    }

    @Override
    public void onBindViewHolder(PlayingListViewHolder holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private OnItemClickListener onItemClickListener;


    @Override
    public void addMovieInfo(List<PlayingMovieResult.ResultsBean> enrollIntroduces) {
        this.list =enrollIntroduces;
    }


    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public void setOnclickListener(OnItemClickListener onclickListener) {
        this.onItemClickListener = onclickListener;
    }


}
