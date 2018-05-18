package com.lbc.practice.movieapp.view;

import com.lbc.practice.movieapp.view.favorite.FavoriteListActivity;
import com.lbc.practice.movieapp.view.main.MainActivity;

import dagger.Component;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@ActivityScope
@Component(modules = ActivityModule.class)
public interface AppComponent {

}
