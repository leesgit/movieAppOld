package com.lbc.practice.movieapp.di;


import android.app.Application;

import com.lbc.practice.movieapp.MovieApplication;
import com.lbc.practice.movieapp.data.resource.MovieRepoistoryModule
import com.lbc.practice.movieapp.data.resource.remote.RemoteModule

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;


@Singleton
@Component(modules = arrayOf(RemoteModule::class, MovieRepoistoryModule::class, ApplicationModule::class,
        ActivityBindingModule::class, AndroidSupportInjectionModule::class))
interface AppComponent : AndroidInjector<MovieApplication> {


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponent.Builder
        fun build(): AppComponent
    }
}