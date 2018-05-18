package com.lbc.practice.movieapp.view.favorite;

import android.content.Context;

import com.lbc.practice.movieapp.adapter.PlayingListContract;
import com.lbc.practice.movieapp.callback.OnItemClickListener;
import com.lbc.practice.movieapp.model.PlayingMovieResult;
import com.lbc.practice.movieapp.model.resource.MovieDataSource;

import java.util.ArrayList;
import java.util.List;

public class FavoriteListPresenter implements FavoriteListContract.FavoritePre {

    MovieDataSource movieDataSource;
    FavoriteListContract.FavoriteView favoriteView;
    PlayingListContract.View adapterView;
    PlayingListContract.PlayingModel adapterModel;
    List<PlayingMovieResult.ResultsBean> resultsBeans;


    public FavoriteListPresenter(MovieDataSource movieDataSource, FavoriteListContract.FavoriteView favoriteView) {
        this.movieDataSource = movieDataSource;
        this.favoriteView = favoriteView;
        resultsBeans = new ArrayList<PlayingMovieResult.ResultsBean>();
    }

    @Override
    public void setPlayingAdaterModel(PlayingListContract.PlayingModel model) {
        this.adapterModel = model;
    }

    @Override
    public void setPlayingAdaterView(PlayingListContract.View view) {
        this.adapterView = view;
        adapterView.setOnclickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                favoriteView.moveToSecond(resultsBeans.get(position));
            }
        });
    }


    @Override
    public void favoriteInfo(Context context) {
        movieDataSource.favoriteMovieInfo(context, new MovieDataSource.LoadDataCallBack() {
            @Override
            public void onLoadData(List<PlayingMovieResult.ResultsBean> movieInfo) {
                resultsBeans.clear();
                resultsBeans.addAll(movieInfo);
                adapterModel.addMovieInfo(resultsBeans);
                adapterView.notifyAdapter();

            }

            @Override
            public void onFailData(String errorMsg) {

            }
        });

    }
}
