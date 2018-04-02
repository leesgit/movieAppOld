package com.lbc.practice.movieapp.view;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.lbc.practice.movieapp.PlayingMovieResult;
import com.lbc.practice.movieapp.R;
import com.lbc.practice.movieapp.RetrofitManager;
import com.lbc.practice.movieapp.adapter.PlayingListAdapter;
import com.lbc.practice.movieapp.callback.OnItemClickListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  {
    private SwipeRefreshLayout swipeRefreshLayout;
    private PlayingListAdapter playingListAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int page = 1;
    private boolean more = true;
    private boolean loading = false;
    private String apiKey = "null";
    private String language = "ko";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_recyclerview);
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        playingListAdapter = new PlayingListAdapter();
        playingListAdapter.setOnCustomItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, PlayingMovieResult.ResultsBean item) {
                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                intent.putExtra("movieInfo",item);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in,R.anim.left_out);
            }
        });
        recyclerView.setAdapter(playingListAdapter);

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.main_swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reload();
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!loading && more && linearLayoutManager.findLastVisibleItemPosition() == playingListAdapter.getItemCount()-1){
                    load();
                }
            }
        });
        swipeRefreshLayout.setRefreshing(true);
        reload();
    }

    public void reload(){
        playingListAdapter.clearItem();
        playingListAdapter.notifyDataSetChanged();
        page = 1;
        more = true;
        load();
    }

    public synchronized void load(){
        if(!loading) {
            loading = true;
            RetrofitManager.getInstance().getUrl().playingMovie(apiKey,language,page).enqueue(new Callback<PlayingMovieResult>() {
                @Override
                public void onResponse(Call<PlayingMovieResult> call, Response<PlayingMovieResult> response) {
                    PlayingMovieResult playingMovieResult = response.body();
                    List<PlayingMovieResult.ResultsBean> movieList = playingMovieResult.getResults();

                    playingListAdapter.addItem(movieList);
                    playingListAdapter.notifyDataSetChanged();
                    page++;

                    loading = false;
                    swipeRefreshLayout.setRefreshing(false);
                }

                @Override
                public void onFailure(Call<PlayingMovieResult> call, Throwable t) {

                }
            });
        }
    }
}
