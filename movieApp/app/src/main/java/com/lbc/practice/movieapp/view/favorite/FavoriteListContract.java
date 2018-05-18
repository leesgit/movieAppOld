package com.lbc.practice.movieapp.view.favorite;

import android.content.Context;

import com.lbc.practice.movieapp.adapter.PlayingListContract;
import com.lbc.practice.movieapp.model.PlayingMovieResult;

public interface FavoriteListContract {

    interface FavoriteView {
        void moveToSecond(PlayingMovieResult.ResultsBean resultsBean);

    }

    interface FavoritePre {
        void setPlayingAdaterModel(PlayingListContract.PlayingModel model);
        void setPlayingAdaterView(PlayingListContract.View view);
        void favoriteInfo(Context context);
    }
}
