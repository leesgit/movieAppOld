package com.lbc.practice.movieapp.view.favorite;


import com.lbc.practice.movieapp.di.ActivityScope;
import com.lbc.practice.movieapp.view.main.MainContract;
import com.lbc.practice.movieapp.view.main.MainPresenter;

import dagger.Binds;
import dagger.Module;

@Module
abstract class FavoriteListModule {

    @ActivityScope
    @Binds
     abstract fun favoritePre(favoriteListPresenter: FavoriteListPresenter): FavoriteListContract.FavoritePre
}
