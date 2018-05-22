package com.lbc.practice.movieapp.data.resource;

import com.lbc.practice.movieapp.data.resource.local.MovieLocalDataSource;
import com.lbc.practice.movieapp.data.resource.remote.MovieRemoteDataSource;

import dagger.Binds;
import dagger.Module;

@Module
abstract class MovieRepoistoryModule {


    @Binds
    @LocalScope
    internal abstract fun provideMoiveLocal(movieLocalDataSource: MovieLocalDataSource): MovieDataSource

    @Binds
    @RemoteScope
    internal abstract fun provideMoiveRemote(movieLocalDataSource: MovieRemoteDataSource): MovieRemoteDataSource

}
