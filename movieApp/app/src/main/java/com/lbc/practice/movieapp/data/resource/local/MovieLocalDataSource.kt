package com.lbc.practice.movieapp.data.resource.local;

import android.content.Context;

import com.lbc.practice.movieapp.data.Memo;
import com.lbc.practice.movieapp.data.PlayingMovieResult;
import com.lbc.practice.movieapp.data.resource.MovieDataSource;

import java.util.ArrayList;
import java.util.List;

class MovieLocalDataSource : MovieDataSource {

    internal var movieList: MutableList<PlayingMovieResult.ResultsBean> = ArrayList()

    override fun seeMovieInfo(apiKey: String, language: String, page: Int, loadDataCallBack: MovieDataSource.LoadDataCallBack) {

    }

    override fun favoriteMovieInfo(context: Context, loadDataCallBack: MovieDataSource.LoadDataCallBack) {
        val appDatabase = AppDatabase.getsInstance(context)


        //        LiveData<List<Memo>> memos;
        val memos: kotlin.collections.List<Memo>
        memos = appDatabase.memoDao().loadAllMovie()


        //        memos.observe(this, new Observer<List<Memo>>() {
        //            @Override
        //            public void onChanged(@Nullable List<Memo> memos) {
        movieList.clear()
        for (i in memos.indices) {
            movieList.add(PlayingMovieResult.ResultsBean(memos[i].poster_path, memos[i].vote_count!!, memos[i].id!!, memos[i].vote_average!!,
                    memos[i].title, memos[i].popularity!!, memos[i].overview, memos[i].release_date, memos[i].storedTime))

        }
        //            }
        //        });
        loadDataCallBack.onLoadData(movieList)
    }
}
