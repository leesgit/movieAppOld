package com.lbc.practice.movieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String apiKey = "null";
        String language = "en-US";
        int page = 1;
        RetrofitManager.getInstance().getUrl().playingMovie(apiKey,language,page).enqueue(new Callback<PlayingMovieResult>() {
            @Override
            public void onResponse(Call<PlayingMovieResult> call, Response<PlayingMovieResult> response) {
                PlayingMovieResult playingMovieResult = response.body();
                List<PlayingMovieResult.ResultsBean> movieList = playingMovieResult.getResults();
                Log.e("test",movieList.get(0).getOriginal_title());
            }

            @Override
            public void onFailure(Call<PlayingMovieResult> call, Throwable t) {

            }
        });
    }
}
