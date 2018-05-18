package com.lbc.practice.movieapp.view.favorite;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.lbc.practice.movieapp.MovieApplication;
import com.lbc.practice.movieapp.model.resource.MovieRepository;
import com.lbc.practice.movieapp.model.resource.local.AppDatabase;
import com.lbc.practice.movieapp.model.PlayingMovieResult;
import com.lbc.practice.movieapp.R;
import com.lbc.practice.movieapp.adapter.PlayingListAdapter;
import com.lbc.practice.movieapp.callback.Refresh;

import com.lbc.practice.movieapp.view.main.MainActivity;
import com.lbc.practice.movieapp.view.second.SecondActivity;

import javax.inject.Inject;

public class FavoriteListActivity extends AppCompatActivity implements FavoriteListContract.FavoriteView {
    private PlayingListAdapter playingListAdapter;
    private LinearLayoutManager linearLayoutManager;
    private AppDatabase appDatabase;
    private FavoriteListPresenter favoriteListPresenter;
//    List<PlayingMovieResult.ResultsBean> movieList;
//    LiveData<List<Memo>> memos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);
        appDatabase = AppDatabase.getsInstance(getApplicationContext());
//        movieList = new ArrayList<PlayingMovieResult.ResultsBean>();

        Log.e("repo2",MovieApplication.getMovieApplication().getRepository().toString());
        favoriteListPresenter = new FavoriteListPresenter(MovieApplication.getMovieApplication().getRepository(),this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.favorite_recyclerview);
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        playingListAdapter = new PlayingListAdapter(new Refresh() {
            @Override
            public void refresh() {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        add();
                    }
                },100);
            }
        });
//        playingListAdapter.setOnCustomItemClickListener(new OnItemClickListener() {
////            @Override
////            public void onItemClick(int position, PlayingMovieResult.ResultsBean item) {
////                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
////                intent.putExtra("movieInfo",item);
////                startActivity(intent);
////                overridePendingTransition(R.anim.right_in,R.anim.left_out);
////            }
////        });

        recyclerView.setAdapter(playingListAdapter);
        favoriteListPresenter.setPlayingAdaterModel(playingListAdapter);
        favoriteListPresenter.setPlayingAdaterView(playingListAdapter);
        add();
    }

    private void add() {
//        movieList.clear();
        favoriteListPresenter.favoriteInfo(getApplicationContext());

//        memos = appDatabase.memoDao().loadAllMovie();
//        memos.observe(this, new Observer<List<Memo>>() {
//            @Override
//            public void onChanged(@Nullable List<Memo> memos) {
//                for(int i=0; i<memos.size(); i++) {
//                    movieList.add(new PlayingMovieResult.ResultsBean(memos.get(i).getPoster_path(),memos.get(i).getVote_count(),memos.get(i).getId(),memos.get(i).getVote_average(),
//                            memos.get(i).getTitle(),memos.get(i).getPopularity(),memos.get(i).getOverview(),memos.get(i).getRelease_date(),memos.get(i).getStoredTime()));
//                    playingListAdapter.clearItem();
//                    playingListAdapter.addItem(movieList);
//                    playingListAdapter.notifyDataSetChanged();
//                }
//            }
//        });
    }


    @Override
    public void moveToSecond(PlayingMovieResult.ResultsBean resultsBean) {
        Intent intent = new Intent(FavoriteListActivity.this,SecondActivity.class);
        intent.putExtra("movieInfo",resultsBean);
        startActivity(intent);
        overridePendingTransition(R.anim.right_in,R.anim.left_out);
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(FavoriteListActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

