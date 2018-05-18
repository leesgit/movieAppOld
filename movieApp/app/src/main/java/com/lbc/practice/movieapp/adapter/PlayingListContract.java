package com.lbc.practice.movieapp.adapter;

import com.lbc.practice.movieapp.callback.OnItemClickListener;
import com.lbc.practice.movieapp.model.PlayingMovieResult;

import java.util.List;

public interface PlayingListContract {

    interface View {
        void notifyAdapter();
        void setOnclickListener(OnItemClickListener onclickListener);
    }

    interface PlayingModel {
        void addMovieInfo (List<PlayingMovieResult.ResultsBean> enrollIntroduces);
        void clearItem();
    }
}
