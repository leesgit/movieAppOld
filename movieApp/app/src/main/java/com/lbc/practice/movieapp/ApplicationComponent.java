package com.lbc.practice.movieapp;


import dagger.Component;

@ApplictionScope
@Component(modules =ApplicationModule.class)
public interface ApplicationComponent {
    void inject(MovieApplication movieApplication);
}
