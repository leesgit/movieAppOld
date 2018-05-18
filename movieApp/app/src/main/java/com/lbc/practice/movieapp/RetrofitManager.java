package com.lbc.practice.movieapp;

import com.google.gson.Gson;
import com.lbc.practice.movieapp.model.PlayingMovieResult;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.List;
import io.reactivex.Observable;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jwk on 2017. 9. 9..
 */

public class RetrofitManager {
//     static RetrofitManager instance;
     RetrofitUrl url;
     public RetrofitManager(){
        final CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(new JavaNetCookieJar(cookieManager))
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        url = retrofit.create(RetrofitUrl.class);
    }

//    public static RetrofitManager getInstance(){
//        if( instance == null ){
//            instance = new RetrofitManager();
//        }
//        return instance;
//    }
//    public static void clearInstance(){
//        instance = null;
//    }

    public RetrofitUrl getUrl(){
        return url;
    }


    public interface RetrofitUrl{
        String BASE_URL = "https://api.themoviedb.org";

        @GET("/3/movie/now_playing")
        Call<PlayingMovieResult> playingMovie(
                @Query("api_key") String apiKey,
                @Query("language") String language,
                @Query("page") int page);

        @GET("/3/movie/now_playing")
        Observable<PlayingMovieResult> rxplayingMovie(
                @Query("api_key") String apiKey,
                @Query("language") String language,
                @Query("page") int page);

    }
}
