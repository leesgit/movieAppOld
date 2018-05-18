package com.lbc.practice.movieapp;


import com.lbc.practice.movieapp.model.resource.MovieRepository;
import com.lbc.practice.movieapp.model.resource.local.MovieLocalDataSource;
import com.lbc.practice.movieapp.model.resource.remote.MovieRemoteDataSource;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    @Provides
    @ApplictionScope
    public MovieLocalDataSource getMovieLocalDataSource() {
        return new MovieLocalDataSource();
    }

    @Provides
    @ApplictionScope
    public MovieRemoteDataSource getMovieRemoteDataSource() {
        return new MovieRemoteDataSource();
    }

    @Provides
    @ApplictionScope
    public MovieRepository getRopository(MovieLocalDataSource movieLocalDataSource, MovieRemoteDataSource movieRemoteDataSource) {
        return new MovieRepository(movieRemoteDataSource,movieLocalDataSource);
    }
}
