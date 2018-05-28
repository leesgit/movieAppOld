package com.lbc.practice.movieapp.view.main;

import com.lbc.practice.movieapp.di.ActivityScope;

import dagger.Binds;
import dagger.Module;


@Module
abstract class MainModule {
    @ActivityScope
    @Binds
    internal abstract fun mainPre(mainPresenter: MainPresenter): MainContract.MainPre
}
