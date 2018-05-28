package com.lbc.practice.movieapp.data.resource;

import android.content.Context;

import com.lbc.practice.movieapp.data.resource.local.MovieLocalDataSource;
import com.lbc.practice.movieapp.data.resource.remote.MovieRemoteDataSource;
import javax.inject.Inject


class MovieRepository : MovieDataSource {


    var movieRemoteDataSource: MovieDataSource ?= null
    var movieLocalDataSource: MovieDataSource ?= null

    @Inject
    constructor(@RemoteScope movieRemoteDataSource: MovieDataSource?, @LocalScope movieLocalDataSource: MovieDataSource?) {
        this.movieRemoteDataSource = movieRemoteDataSource
        this.movieLocalDataSource = movieLocalDataSource
    }


    override fun seeMovieInfo(apiKey: String, language: String, page: Int, loadDataCallBack: MovieDataSource.LoadDataCallBack) {
        movieRemoteDataSource!!.seeMovieInfo(apiKey, language, page, loadDataCallBack)
    }

    override fun favoriteMovieInfo(context: Context, loadDataCallBack: MovieDataSource.LoadDataCallBack) {
        movieLocalDataSource!!.favoriteMovieInfo(context, loadDataCallBack)
    }

}
