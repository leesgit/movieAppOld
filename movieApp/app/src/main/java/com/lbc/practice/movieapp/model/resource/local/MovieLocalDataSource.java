package com.lbc.practice.movieapp.model.resource.local;

import android.content.Context;
import android.util.Log;

import com.lbc.practice.movieapp.model.Memo;
import com.lbc.practice.movieapp.model.PlayingMovieResult;
import com.lbc.practice.movieapp.model.resource.LocalScope;
import com.lbc.practice.movieapp.model.resource.MovieDataSource;
import com.lbc.practice.movieapp.model.resource.RemoteScope;
import com.lbc.practice.movieapp.model.resource.remote.MovieRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MovieLocalDataSource implements MovieDataSource {

    List<PlayingMovieResult.ResultsBean> movieList = new ArrayList<PlayingMovieResult.ResultsBean>();

    @Override
    public void seeMovieInfo(String apiKey, String language, int page, LoadDataCallBack loadDataCallBack) {

    }

    @Override
    public void favoriteMovieInfo(Context context, LoadDataCallBack loadDataCallBack) {
        AppDatabase appDatabase = AppDatabase.getsInstance(context);



//        LiveData<List<Memo>> memos;
        List<Memo> memos;
        memos = appDatabase.memoDao().loadAllMovie();


//        memos.observe(this, new Observer<List<Memo>>() {
//            @Override
//            public void onChanged(@Nullable List<Memo> memos) {
        movieList.clear();
        for(int i=0; i<memos.size(); i++) {
            movieList.add(new PlayingMovieResult.ResultsBean(memos.get(i).getPoster_path(),memos.get(i).getVote_count(),memos.get(i).getId(),memos.get(i).getVote_average(),
                    memos.get(i).getTitle(),memos.get(i).getPopularity(),memos.get(i).getOverview(),memos.get(i).getRelease_date(),memos.get(i).getStoredTime()));

        }
//            }
//        });
        loadDataCallBack.onLoadData(movieList);
    }
}
