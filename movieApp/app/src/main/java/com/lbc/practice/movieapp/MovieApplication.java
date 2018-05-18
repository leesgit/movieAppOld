package com.lbc.practice.movieapp;

import android.app.Application;
import android.util.Log;

import com.lbc.practice.movieapp.model.resource.MovieRepository;

import javax.inject.Inject;

public class MovieApplication extends Application {

    private static MovieApplication movieApplication;

    @Inject
    MovieRepository movieRepository;

    public static MovieApplication getMovieApplication () {
        return movieApplication;
    }

    public MovieRepository getRepository() {
        return this.movieRepository;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        movieApplication=this;
        ApplicationComponent applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule()).build();
        applicationComponent.inject(this);
//        Log.e("appliciotn", movieRepository.toString()+" ");
    }
}