package com.lbc.practice.movieapp.model.resource;

import android.content.Context;

import com.lbc.practice.movieapp.model.PlayingMovieResult;

import java.util.List;

public interface MovieDataSource {
    interface LoadDataCallBack {
        void onLoadData(List<PlayingMovieResult.ResultsBean> movieInfo);
        void onFailData(String errorMsg);
    }

    void seeMovieInfo(String apiKey,String language, int page, LoadDataCallBack loadDataCallBack);

    void favoriteMovieInfo(Context context, LoadDataCallBack loadDataCallBack);

}
