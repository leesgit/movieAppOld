package com.lbc.practice.movieapp.view.second;

import com.lbc.practice.movieapp.di.ActivityScope
import dagger.Binds
import dagger.Module


@Module
abstract class SecondModule {
    @ActivityScope
    @Binds
    abstract fun secondPresent(secondPresenter: SecondPresenter): SecondContract.SecondPresent
}