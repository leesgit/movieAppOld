package com.lbc.practice.movieapp.adapter;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lbc.practice.movieapp.PlayingMovieResult;
import com.lbc.practice.movieapp.R;
import com.lbc.practice.movieapp.callback.ListItemViewCallBack;

/**
 * Created by lbc on 2018-04-02.
 */

public class PlayingListViewHolder extends RecyclerView.ViewHolder {

    ImageView ivImage;
    TextView tvTitle , tvDate,tvScore;

    public PlayingListViewHolder(View v, final ListItemViewCallBack callback){
        super(v);
        if( callback.getOnCustomItemClickListener() != null){
            int position = getAdapterPosition();
            callback.getOnCustomItemClickListener().onItemClick(position, callback.getItem(position));
        }
        ivImage = (ImageView)v.findViewById(R.id.iv_item);
        tvTitle = (TextView)v.findViewById(R.id.tv_title);
        tvDate =  (TextView)v.findViewById(R.id.tv_date);
        tvScore = (TextView)v.findViewById(R.id.tv_average_score);
    }
    public void setData(PlayingMovieResult.ResultsBean item){
        Glide.with(itemView.getContext()).load(item.getPoster_path()).fitCenter().into(ivImage);
        tvTitle.setText(item.getTitle());
        tvDate.setText(item.getRelease_date());
        tvScore.setText(item.getVote_average()+"");

    }
}