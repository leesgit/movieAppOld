package com.lbc.practice.movieapp.model.resource.remote;


import com.lbc.practice.movieapp.view.ActivityModule;
import com.lbc.practice.movieapp.view.main.MainActivity;

import dagger.Component;

@RetroScope
@Component(modules = RemoteModule.class)
public interface RemoteComponent {
    void remoteInject(MovieRemoteDataSource movieRemoteDataSource);

}
