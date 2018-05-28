package com.lbc.practice.movieapp.di;

import com.lbc.practice.movieapp.view.favorite.FavoriteListActivity
import com.lbc.practice.movieapp.view.favorite.FavoriteListModule
import com.lbc.practice.movieapp.view.favorite.FavoriteModule
import com.lbc.practice.movieapp.view.main.MainActivity;
import com.lbc.practice.movieapp.view.main.MainModule;
import com.lbc.practice.movieapp.view.second.SecondActivity
import com.lbc.practice.movieapp.view.second.SecondModule

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(MainModule::class))
    abstract fun mainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(FavoriteModule::class))
    abstract fun favoriteListActivity(): FavoriteListActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(SecondModule::class))
    abstract fun secondActivity(): SecondActivity
}