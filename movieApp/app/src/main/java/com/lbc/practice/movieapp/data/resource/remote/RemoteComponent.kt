package com.lbc.practice.movieapp.data.resource.remote;


import dagger.Component;


@Component(modules = arrayOf(RemoteModule::class))
interface RemoteComponent {
    fun remoteInject(movieRemoteDataSource: MovieRemoteDataSource)

}