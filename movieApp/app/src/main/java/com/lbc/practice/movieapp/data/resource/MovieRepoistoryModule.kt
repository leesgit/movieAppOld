package com.lbc.practice.movieapp.data.resource;

import com.lbc.practice.movieapp.data.resource.local.MovieLocalDataSource;
import com.lbc.practice.movieapp.data.resource.remote.MovieRemoteDataSource;

import dagger.Binds;
import dagger.Module;
import javax.inject.Singleton

@Module
abstract class MovieRepoistoryModule {


    @Singleton
    @Binds
    @LocalScope
    internal abstract fun provideLocalDataSource(movieLocalDataSource: MovieLocalDataSource): MovieDataSource

    @Singleton
    @Binds
    @RemoteScope
    abstract fun provideRemoteDataSource(movieRemoteDataSource: MovieRemoteDataSource): MovieDataSource

}
