package com.lbc.practice.movieapp;

import android.graphics.Movie;

import com.lbc.practice.movieapp.data.resource.MovieRepository;
import com.lbc.practice.movieapp.di.DaggerAppComponent

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;


class MovieApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

}
