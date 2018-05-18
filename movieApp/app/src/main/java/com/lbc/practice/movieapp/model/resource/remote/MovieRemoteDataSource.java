package com.lbc.practice.movieapp.model.resource.remote;

import android.content.Context;
import android.os.Looper;
import android.util.Log;

import com.lbc.practice.movieapp.RetrofitManager;
import com.lbc.practice.movieapp.model.PlayingMovieResult;
import com.lbc.practice.movieapp.model.resource.MovieDataSource;
import com.lbc.practice.movieapp.model.resource.RemoteScope;
import com.lbc.practice.movieapp.view.main.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRemoteDataSource implements MovieDataSource {

    final String Error = "통신 실패 했습니다.";

    @Inject
    RetrofitManager retrofitManager;
    RemoteComponent remoteComponent = DaggerRemoteComponent.builder().remoteModule(new RemoteModule()).build();
    List<PlayingMovieResult.ResultsBean> movieList;

//    @Inject
//    CompositeDisposable compositeDisposable;


    @Override
    public void seeMovieInfo(String apiKey, String language, int page, final LoadDataCallBack loadDataCallBack) {

        remoteComponent.remoteInject(this);
        Log.e("retro", retrofitManager.toString());

        Disposable disposable =
        retrofitManager.getUrl().rxplayingMovie(apiKey,language,page).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data->{
                    PlayingMovieResult playingMovieResult = data;
                    List<PlayingMovieResult.ResultsBean> movieList = playingMovieResult.getResults();
                    loadDataCallBack.onLoadData(movieList);
                },err->{
                    loadDataCallBack.onFailData(Error);

                });
//        compositeDisposable.add(disposable);
//        disposable.dispose();

//        compositeDisposable.add(disposable);
//        compositeDisposable.dispose();

//        retrofitManager.getUrl().rxplayingMovie(apiKey,language,page)


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


//        RetrofitManager.getInstance().getUrl().playingMovie(apiKey,language,page).enqueue(new Callback<PlayingMovieResult>() {
//            @Override
//            public void onResponse(Call<PlayingMovieResult> call, Response<PlayingMovieResult> response) {
//                PlayingMovieResult playingMovieResult = response.body();
//                List<PlayingMovieResult.ResultsBean> movieList = playingMovieResult.getResults();
////                Log.e("retro", movieList.get(0).getTitle());
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

    @Override
    public void favoriteMovieInfo(Context context, LoadDataCallBack loadDataCallBack) {

    }
}
