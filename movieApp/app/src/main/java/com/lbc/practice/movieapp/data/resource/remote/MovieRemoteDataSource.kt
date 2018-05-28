package com.lbc.practice.movieapp.data.resource.remote;

import android.content.Context;
import android.util.Log;

import com.lbc.practice.movieapp.RetrofitManager;
import com.lbc.practice.movieapp.data.PlayingMovieResult;
import com.lbc.practice.movieapp.data.resource.MovieDataSource;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers;


open class MovieRemoteDataSource : MovieDataSource {

     val Error = "통신 실패 했습니다."

    @Inject
    constructor()


    @Inject
    lateinit var retrofitManager: RetrofitManager

    @Inject
    lateinit var compositeDisposable: CompositeDisposable


    override fun seeMovieInfo(apiKey: String, language: String, page: Int, loadDataCallBack: MovieDataSource.LoadDataCallBack) {
        Log.e("retro", retrofitManager.toString())

        val disposable = retrofitManager.url!!.rxplayingMovie(apiKey, language, page).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    val movieList = data.results
                    loadDataCallBack.onLoadData(movieList!!)
                }) { err ->
                    loadDataCallBack.onFailData(Error)

                }
                compositeDisposable.add(disposable);

    }

    override fun favoriteMovieInfo(context: Context, loadDataCallBack: MovieDataSource.LoadDataCallBack) {

    }
}
