package com.lbc.practice.movieapp.view.favorite;

import android.content.Context;

import com.lbc.practice.movieapp.adapter.PlayingListContract;
import com.lbc.practice.movieapp.callback.OnItemClickListener;
import com.lbc.practice.movieapp.data.PlayingMovieResult;
import com.lbc.practice.movieapp.data.resource.MovieDataSource;
import com.lbc.practice.movieapp.data.resource.MovieRepository

import java.util.ArrayList;
import javax.inject.Inject

class FavoriteListPresenter : FavoriteListContract.FavoritePre {


    var adapterView: PlayingListContract.View? = null
    var adapterModel: PlayingListContract.PlayingModel? = null
    var resultsBeans: MutableList<PlayingMovieResult.ResultsBean>
    var favoriteView: FavoriteListContract.FavoriteView? =null

    var movieDataSource: MovieDataSource

    @Inject
    constructor(movieRepository : MovieRepository) {
        movieDataSource =movieRepository
    }

    init {
        resultsBeans = ArrayList()
    }

    override fun takeView(view: FavoriteListContract.FavoriteView) {
        this.favoriteView = view
    }
    override fun setPlayingAdaterModel(model: PlayingListContract.PlayingModel) {
        this.adapterModel = model
    }

    override fun setPlayingAdaterView(view: PlayingListContract.View) {
        this.adapterView = view
        adapterView!!.setOnclickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                favoriteView!!.moveToSecond(resultsBeans[position])
            }
        })
    }


    override fun favoriteInfo(context: Context) {
        movieDataSource.favoriteMovieInfo(context, object : MovieDataSource.LoadDataCallBack {
            override fun onLoadData(movieInfo: kotlin.collections.List<PlayingMovieResult.ResultsBean>) {
                resultsBeans.clear()
                resultsBeans.addAll(movieInfo)
                adapterModel!!.addMovieInfo(resultsBeans)
                adapterView!!.notifyAdapter()

            }

            override fun onFailData(errorMsg: String) {

            }
        })

    }
}
