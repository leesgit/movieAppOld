package com.lbc.practice.movieapp.data.resource;

import android.content.Context;

import com.lbc.practice.movieapp.data.PlayingMovieResult;


interface MovieDataSource {
    interface LoadDataCallBack {
        fun onLoadData(movieInfo: kotlin.collections.List<PlayingMovieResult.ResultsBean>)
        fun onFailData(errorMsg: String)
    }

    fun seeMovieInfo(apiKey: String, language: String, page: Int, loadDataCallBack: LoadDataCallBack)

    fun favoriteMovieInfo(context: Context, loadDataCallBack: LoadDataCallBack)

}
