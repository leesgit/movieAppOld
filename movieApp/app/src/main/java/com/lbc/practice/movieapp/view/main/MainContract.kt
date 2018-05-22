package com.lbc.practice.movieapp.view.main;

import com.lbc.practice.movieapp.adapter.PlayingListContract;
import com.lbc.practice.movieapp.data.PlayingMovieResult;

interface MainContract {

    interface MainView {
        fun moveToFavorite()
        fun moveToSecond(resultsBean: PlayingMovieResult.ResultsBean)
        fun errorMassage(error: String)
        fun increasePageCount()
    }

    interface MainPre {
        fun setPlayingAdaterModel(model: PlayingListContract.PlayingModel)
        fun setPlayingAdaterView(view: PlayingListContract.View)
        fun movieInfo(apiKey: String, language: String, page: Int)
    }
}
