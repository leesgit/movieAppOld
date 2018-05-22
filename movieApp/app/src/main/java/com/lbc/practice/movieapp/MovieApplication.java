//package com.lbc.practice.movieapp;
//
//import android.graphics.Movie;
//
//import com.lbc.practice.movieapp.data.resource.MovieRepository;
//
//import javax.inject.Inject;
//
//import dagger.android.AndroidInjector;
//import dagger.android.DaggerApplication;
//
//public class MovieApplication extends DaggerApplication {
//
//    @Inject
//    MovieRepository movieRepository;
//
//    @Override
//    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
//        return null;
//    }
//
//    public MovieRepository getMovieRepository (){
//        return movieRepository;
//    }
//}
