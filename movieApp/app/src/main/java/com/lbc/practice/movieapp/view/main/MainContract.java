package com.lbc.practice.movieapp.view.main;

import com.lbc.practice.movieapp.adapter.PlayingListContract;
import com.lbc.practice.movieapp.model.PlayingMovieResult;

public interface MainContract {

    interface MainView {
        void moveToFavorite();
        void moveToSecond(PlayingMovieResult.ResultsBean resultsBean);
        void errorMassage(String error);
        void increasePageCount();
    }

    interface MainPre {
        void setPlayingAdaterModel(PlayingListContract.PlayingModel model);
        void setPlayingAdaterView(PlayingListContract.View view);
        void movieInfo(String apiKey,String language, int page);
    }
}
