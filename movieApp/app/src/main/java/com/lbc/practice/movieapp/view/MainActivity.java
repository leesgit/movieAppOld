package com.lbc.practice.movieapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lbc.practice.movieapp.PlayingMovieResult;
import com.lbc.practice.movieapp.R;
import com.lbc.practice.movieapp.RetrofitManager;

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
        String language = "ko";
        int page = 1;
        RetrofitManager.getInstance().getUrl().playingMovie(apiKey,language,page).enqueue(new Callback<PlayingMovieResult>() {
            @Override
            public void onResponse(Call<PlayingMovieResult> call, Response<PlayingMovieResult> response) {
                PlayingMovieResult playingMovieResult = response.body();
                List<PlayingMovieResult.ResultsBean> movieList = playingMovieResult.getResults();
            }

            @Override
            public void onFailure(Call<PlayingMovieResult> call, Throwable t) {

            }
        });
    }
}
