package com.lbc.practice.movieapp.view.main;

import android.util.Log;

import com.lbc.practice.movieapp.adapter.PlayingListContract;
import com.lbc.practice.movieapp.callback.OnItemClickListener
import com.lbc.practice.movieapp.data.PlayingMovieResult;
import com.lbc.practice.movieapp.data.resource.MovieDataSource;

import java.util.ArrayList;

class MainPresenter(internal var movieDataSource: MovieDataSource, internal var mainView: MainContract.MainView) : MainContract.MainPre {
    var adapterView: PlayingListContract.View?=null
    var adapterModel: PlayingListContract.PlayingModel?=null
    var resultsBeans: MutableList<PlayingMovieResult.ResultsBean>?=null


    init {
        resultsBeans = ArrayList()
    }

    override fun movieInfo(apiKey: String, language: String, page: Int) {
        movieDataSource.seeMovieInfo(apiKey, language, page, object : MovieDataSource.LoadDataCallBack {
            override fun onLoadData(movieInfo: kotlin.collections.List<PlayingMovieResult.ResultsBean>) {
                Log.e("retropre", movieInfo[0].title)

                resultsBeans?.addAll(movieInfo)
                adapterModel?.addMovieInfo(resultsBeans!!)
                adapterView?.notifyAdapter()
                mainView.increasePageCount()
            }

            override fun onFailData(errorMsg: String) {
                mainView.errorMassage(errorMsg)
            }
        })
    }


    override fun setPlayingAdaterModel(model: PlayingListContract.PlayingModel) {
        this.adapterModel = model
    }

    override fun setPlayingAdaterView(view: PlayingListContract.View) {
        this.adapterView = view
        adapterView?.setOnclickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                mainView.moveToSecond(resultsBeans!!.get(position))
            }
        })

//        adapterView!!.setOnclickListener(position-> {
//            mainView.moveToSecond(resultsBeans!![position])
//        })
    }


}
