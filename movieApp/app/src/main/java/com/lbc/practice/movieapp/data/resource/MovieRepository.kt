package com.lbc.practice.movieapp.data.resource;

import android.content.Context;

import com.lbc.practice.movieapp.data.resource.local.MovieLocalDataSource;
import com.lbc.practice.movieapp.data.resource.remote.MovieRemoteDataSource;


class MovieRepository : MovieDataSource {


    internal var movieRemoteDataSource: MovieDataSource = MovieRemoteDataSource()
    internal var movieLocalDataSource: MovieDataSource = MovieLocalDataSource()

    //    public static void setMovieRepository(MovieRepository movieRepository) {
    //        MovieRepository.movieRepository = movieRepository;
    //    }

    override fun seeMovieInfo(apiKey: String, language: String, page: Int, loadDataCallBack: MovieDataSource.LoadDataCallBack) {
        movieRemoteDataSource.seeMovieInfo(apiKey, language, page, loadDataCallBack)
    }

    override fun favoriteMovieInfo(context: Context, loadDataCallBack: MovieDataSource.LoadDataCallBack) {
        movieLocalDataSource.favoriteMovieInfo(context, loadDataCallBack)
    }

    companion object {

        //    @Inject
        //    public MovieRepository(@RemoteScope MovieDataSource movieRemoteDataSource, @LocalScope MovieDataSource movieLocalDataSource){
        //        this.movieRemoteDataSource = movieRemoteDataSource;
        //        this.movieLocalDataSource = movieLocalDataSource;
        //    }

        private var movieRepository: MovieRepository? = null


        val instance: MovieRepository
            get() {
                if (movieRepository == null) {
                    movieRepository = MovieRepository()
                }
                return movieRepository!!
            }
    }


}
