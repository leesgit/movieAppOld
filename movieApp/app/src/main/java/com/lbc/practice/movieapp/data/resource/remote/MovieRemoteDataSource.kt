package com.lbc.practice.movieapp.data.resource.remote;

import android.content.Context;
import android.util.Log;

import com.lbc.practice.movieapp.RetrofitManager;
import com.lbc.practice.movieapp.data.PlayingMovieResult;
import com.lbc.practice.movieapp.data.resource.MovieDataSource;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


open class MovieRemoteDataSource : MovieDataSource {

     val Error = "통신 실패 했습니다."

    @Inject
    lateinit var retrofitManager: RetrofitManager

    //    @Inject
    //    CompositeDisposable compositeDisposable;


    override fun seeMovieInfo(apiKey: String, language: String, page: Int, loadDataCallBack: MovieDataSource.LoadDataCallBack) {
        var remoteComponent = DaggerRemoteComponent.builder().remoteModule(RemoteModule()).build()
        remoteComponent.remoteInject(this)
        Log.e("retro", retrofitManager.toString())

        val disposable = retrofitManager.url!!.rxplayingMovie(apiKey, language, page).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    val movieList = data.results
                    loadDataCallBack.onLoadData(movieList!!)
                }) { err ->
                    loadDataCallBack.onFailData(Error)

                }
        //        compositeDisposable.add(disposable);
        //        compositeDisposable.dispose();


        //        retrofitManager.getUrl().playingMovie(apiKey,language,page).enqueue(new Callback<PlayingMovieResult>() {
        //            @Override
        //            public void onResponse(Call<PlayingMovieResult> call, Response<PlayingMovieResult> response) {
        //                PlayingMovieResult playingMovieResult = response.body();
        //                List<PlayingMovieResult.ResultsBean> movieList = playingMovieResult.getResults();
        //
        //                loadDataCallBack.onLoadData(movieList);
        //            }
        //
        //            @Override
        //            public void onFailure(Call<PlayingMovieResult> call, Throwable t) {
        //                loadDataCallBack.onFailData(Error);
        //            }
        //        });


    }

    override fun favoriteMovieInfo(context: Context, loadDataCallBack: MovieDataSource.LoadDataCallBack) {

    }
}
