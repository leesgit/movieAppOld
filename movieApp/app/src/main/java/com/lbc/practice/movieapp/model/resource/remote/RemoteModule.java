package com.lbc.practice.movieapp.model.resource.remote;


import com.lbc.practice.movieapp.RetrofitManager;
import com.lbc.practice.movieapp.view.ActivityScope;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class RemoteModule {

    @RetroScope
    @Provides
    public RetrofitManager retrofitManager() {
        return new RetrofitManager();
    }

    @ActivityScope
    @Provides
    public CompositeDisposable disposeRetro() {
        return new CompositeDisposable();
    }
}
