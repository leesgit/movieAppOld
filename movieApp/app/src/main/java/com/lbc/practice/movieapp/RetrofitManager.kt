package com.lbc.practice.movieapp

import com.google.gson.Gson
import com.lbc.practice.movieapp.data.PlayingMovieResult
import io.reactivex.Observable
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.net.CookieManager
import java.net.CookiePolicy

public class RetrofitManager {
    val BASE_URL = "https://api.themoviedb.org"

    var url :RetrofitUrl?=null

    constructor() {
        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)
        val client = OkHttpClient.Builder()
                .cookieJar(JavaNetCookieJar(cookieManager))
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl(RetrofitUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
        url = retrofit.create(RetrofitUrl::class.java)
    }


    interface RetrofitUrl {

        @GET("/3/movie/now_playing")
        fun playingMovie(
                @Query("api_key") apiKey: String,
                @Query("language") language: String,
                @Query("page") page: Int): Call<PlayingMovieResult>

        @GET("/3/movie/now_playing")
        fun rxplayingMovie(
                @Query("api_key") apiKey: String,
                @Query("language") language: String,
                @Query("page") page: Int): Observable<PlayingMovieResult>

        companion object {
            val BASE_URL = "https://api.themoviedb.org"
        }

    }
}