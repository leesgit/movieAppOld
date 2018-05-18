package com.lbc.practice.movieapp.adapter;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lbc.practice.movieapp.callback.OnItemClickListener;
import com.lbc.practice.movieapp.model.resource.local.AppDatabase;
import com.lbc.practice.movieapp.model.Memo;
import com.lbc.practice.movieapp.model.PlayingMovieResult;
import com.lbc.practice.movieapp.R;
import com.lbc.practice.movieapp.callback.Refresh;

import java.util.Date;

/**
 * Created by lbc on 2018-04-02.
 */

public class PlayingListViewHolder extends RecyclerView.ViewHolder {

    ImageView ivImage;
    TextView tvTitle, tvDate, tvScore;
    CheckBox checkBox;
    private AppDatabase appDatabase;
    Refresh refresh;


    public PlayingListViewHolder(View v, final OnItemClickListener callback) {
        super(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    int position = getAdapterPosition();
                    callback.onItemClick(position);
                }
            }
        });
        ivImage = (ImageView) v.findViewById(R.id.iv_item);
        tvTitle = (TextView) v.findViewById(R.id.tv_title);
        tvDate = (TextView) v.findViewById(R.id.tv_date);
        tvScore = (TextView) v.findViewById(R.id.tv_average_score);
        checkBox = (CheckBox) v.findViewById(R.id.checkBox);
        appDatabase = AppDatabase.getsInstance(v.getContext());
    }

    public PlayingListViewHolder(View v, final OnItemClickListener callback, Refresh refresh) {
        super(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    int position = getAdapterPosition();
                    callback.onItemClick(position);
                }
            }
        });
        ivImage = (ImageView) v.findViewById(R.id.iv_item);
        tvTitle = (TextView) v.findViewById(R.id.tv_title);
        tvDate = (TextView) v.findViewById(R.id.tv_date);
        tvScore = (TextView) v.findViewById(R.id.tv_average_score);
        checkBox = (CheckBox) v.findViewById(R.id.checkBox);
        appDatabase = AppDatabase.getsInstance(v.getContext());
        this.refresh = refresh;
    }


    public void setData(final PlayingMovieResult.ResultsBean item) {
        Glide.with(itemView.getContext()).load("http://image.tmdb.org/t/p/w185" + item.getPoster_path()).fitCenter().into(ivImage);


        if (item.getId() == appDatabase.memoDao().judgeMovie(item.getId())) {
            checkBox.setChecked(true);
            checkBox.setText("삭제");

        } else {
            checkBox.setChecked(false);
            checkBox.setText("저장");
        }

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (checkBox.isChecked()) {
                            checkBox.setText("삭제");
                            appDatabase.memoDao().insertMovie(new Memo(item.getPoster_path(), item.getVote_count(), item.getId(), item.getVote_average(), item.getTitle(),
                                    item.getPopularity(), item.getOverview(), item.getRelease_date(), new Date()));

                        } else {
                            checkBox.setText("저장");
                            appDatabase.memoDao().deleteMovie(appDatabase.memoDao().findMovie(item.getId()));
                            if (refresh != null) {
                                refresh.refresh();
                            }
                        }

                    }
                });

            }
        }, 100);


        tvTitle.setText(item.getTitle());
        tvDate.setText(item.getRelease_date());
        tvScore.setText(item.getVote_average() + "");

    }
}
