package com.lbc.practice.movieapp.view.favorite;

import android.content.Context;

import com.lbc.practice.movieapp.adapter.PlayingListContract;
import com.lbc.practice.movieapp.data.PlayingMovieResult;


interface FavoriteListContract {

    interface FavoriteView {
        fun moveToSecond(resultsBean: PlayingMovieResult.ResultsBean)

    }

    interface FavoritePre {
        fun setPlayingAdaterModel(model: PlayingListContract.PlayingModel)
        fun setPlayingAdaterView(view: PlayingListContract.View)
        fun favoriteInfo(context: Context)
        fun takeView(view: FavoriteListContract.FavoriteView)
    }
}