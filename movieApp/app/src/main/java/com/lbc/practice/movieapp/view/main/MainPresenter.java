package com.lbc.practice.movieapp.view.main;

import android.util.Log;

import com.lbc.practice.movieapp.adapter.PlayingListContract;
import com.lbc.practice.movieapp.callback.OnItemClickListener;
import com.lbc.practice.movieapp.model.PlayingMovieResult;
import com.lbc.practice.movieapp.model.resource.MovieDataSource;
import com.lbc.practice.movieapp.model.resource.remote.MovieRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainPresenter implements MainContract.MainPre {

    MovieDataSource movieDataSource;
    MainContract.MainView mainView;
    PlayingListContract.View adapterView;
    PlayingListContract.PlayingModel adapterModel;
    List<PlayingMovieResult.ResultsBean> resultsBeans;


    public MainPresenter(MovieDataSource movieDataSource,  MainContract.MainView mainView) {
        this.movieDataSource = movieDataSource;
        this.mainView = mainView;
        resultsBeans = new ArrayList<PlayingMovieResult.ResultsBean>();

    }

    @Override
    public void movieInfo(String apiKey,String language, int page) {
        movieDataSource.seeMovieInfo(apiKey, language, page, new MovieDataSource.LoadDataCallBack() {
            @Override
            public void onLoadData(List<PlayingMovieResult.ResultsBean> movieInfo) {
                Log.e("retropre",movieInfo.get(0).getTitle());

                    resultsBeans.addAll(movieInfo);
                    adapterModel.addMovieInfo(resultsBeans);
                    adapterView.notifyAdapter();
                    mainView.increasePageCount();
            }

            @Override
            public void onFailData(String errorMsg) {
                    mainView.errorMassage(errorMsg);
            }
        });
    }



    @Override
    public void setPlayingAdaterModel(PlayingListContract.PlayingModel model) {
        this.adapterModel = model;
    }

    @Override
    public void setPlayingAdaterView(PlayingListContract.View view) {
        this.adapterView =view;
        adapterView.setOnclickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mainView.moveToSecond(resultsBeans.get(position));
            }
        });
    }


}
