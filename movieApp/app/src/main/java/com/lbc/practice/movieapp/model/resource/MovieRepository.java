package com.lbc.practice.movieapp.model.resource;

import android.content.Context;


import javax.inject.Inject;

public class MovieRepository implements MovieDataSource {



     MovieDataSource movieRemoteDataSource;
     MovieDataSource movieLocalDataSource;

    @Inject
    public MovieRepository(@RemoteScope MovieDataSource movieRemoteDataSource, @LocalScope MovieDataSource movieLocalDataSource){
        this.movieRemoteDataSource = movieRemoteDataSource;
        this.movieLocalDataSource = movieLocalDataSource;
    }


//    @Inject
//    MovieDataSource movieRemoteDataSource;
//
//    @Inject
//    MovieDataSource movieLocalDataSource;
//
//    public MovieRepository() {
//
//        MovieRepositoryComponent movieRepositoryComponent = DaggerMovieRepositoryComponent.builder().movieRepositoryModule(new MovieRepositoryModule()).build();
//        movieRepositoryComponent.repositoryInject(this);
//    }

//    private static MovieRepository movieRepository;


//    public static MovieRepository getInstance(){
//        if (movieRepository ==null) {
//            movieRepository =new MovieRepository();
//        }
//        return movieRepository;
//    }

//    public static void setMovieRepository(MovieRepository movieRepository) {
//        MovieRepository.movieRepository = movieRepository;
//    }

    @Override
    public void seeMovieInfo(String apiKey, String language, int page, LoadDataCallBack loadDataCallBack) {
        movieRemoteDataSource.seeMovieInfo(apiKey,language,page,loadDataCallBack);
    }

    @Override
    public void favoriteMovieInfo(Context context, LoadDataCallBack loadDataCallBack) {
        movieLocalDataSource.favoriteMovieInfo(context,loadDataCallBack);
    }


}
