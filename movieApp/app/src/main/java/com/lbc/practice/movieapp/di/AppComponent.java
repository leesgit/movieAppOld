//package com.lbc.practice.movieapp.di;
//
//
//import android.app.Application;
//
//import com.lbc.practice.movieapp.MovieApplication;
//import com.lbc.practice.movieapp.view.favorite.FavoriteModule;
//import com.lbc.practice.movieapp.view.main.MainModule;
//import com.lbc.practice.movieapp.view.second.SecondModule;
//
//import javax.inject.Singleton;
//
//import dagger.BindsInstance;
//import dagger.Component;
//import dagger.android.AndroidInjector;
//
//@Singleton
//@Component(modules = {})
//public interface AppComponent extends AndroidInjector<MovieApplication> {
//
//    @Component.Builder
//    interface Builder {
//
//        @BindsInstance
//        AppComponent.Builder application(Application application);
//
//        AppComponent build();
//
//    }
//}
