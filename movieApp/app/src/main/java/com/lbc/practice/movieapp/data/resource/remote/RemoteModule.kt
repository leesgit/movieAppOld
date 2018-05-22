package com.lbc.practice.movieapp.data.resource.remote;


import com.lbc.practice.movieapp.RetrofitManager;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
class RemoteModule {

    @Provides
    fun retrofitManager(): RetrofitManager {
        return RetrofitManager()
    }

    @Provides
    fun disposeRetro(): CompositeDisposable {
        return CompositeDisposable()
    }
}
