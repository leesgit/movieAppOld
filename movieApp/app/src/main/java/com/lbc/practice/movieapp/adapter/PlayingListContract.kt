package com.lbc.practice.movieapp.adapter;

import com.lbc.practice.movieapp.callback.OnItemClickListener;
import com.lbc.practice.movieapp.data.PlayingMovieResult;



interface PlayingListContract {

    interface View {
        fun notifyAdapter()
        fun setOnclickListener(onclickListener: OnItemClickListener)
    }

    interface PlayingModel {
        fun addMovieInfo(enrollIntroduces: kotlin.collections.MutableList<PlayingMovieResult.ResultsBean>)
        fun clearItem()
    }
}