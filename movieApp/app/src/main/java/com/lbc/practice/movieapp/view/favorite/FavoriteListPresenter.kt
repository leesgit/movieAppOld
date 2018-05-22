package com.lbc.practice.movieapp.view.favorite;

import android.content.Context;

import com.lbc.practice.movieapp.adapter.PlayingListContract;
import com.lbc.practice.movieapp.callback.OnItemClickListener;
import com.lbc.practice.movieapp.data.PlayingMovieResult;
import com.lbc.practice.movieapp.data.resource.MovieDataSource;

import java.util.ArrayList;

class FavoriteListPresenter(internal var movieDataSource: MovieDataSource, internal var favoriteView: FavoriteListContract.FavoriteView) : FavoriteListContract.FavoritePre {
    var adapterView: PlayingListContract.View? = null
    var adapterModel: PlayingListContract.PlayingModel? = null
    internal var resultsBeans: MutableList<PlayingMovieResult.ResultsBean>


    init {
        resultsBeans = ArrayList()
    }

    override fun setPlayingAdaterModel(model: PlayingListContract.PlayingModel) {
        this.adapterModel = model
    }

    override fun setPlayingAdaterView(view: PlayingListContract.View) {
        this.adapterView = view
        adapterView!!.setOnclickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                favoriteView.moveToSecond(resultsBeans[position])
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
