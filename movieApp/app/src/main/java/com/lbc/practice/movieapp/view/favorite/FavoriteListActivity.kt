package com.lbc.practice.movieapp.view.favorite;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lbc.practice.movieapp.R;
import com.lbc.practice.movieapp.adapter.PlayingListAdapter;
import com.lbc.practice.movieapp.callback.Refresh;
import com.lbc.practice.movieapp.data.PlayingMovieResult;
import com.lbc.practice.movieapp.data.resource.MovieRepository;
import com.lbc.practice.movieapp.data.resource.local.AppDatabase;
import com.lbc.practice.movieapp.view.main.MainActivity;
import com.lbc.practice.movieapp.view.second.SecondActivity;

class FavoriteListActivity : AppCompatActivity(), FavoriteListContract.FavoriteView {
    private var playingListAdapter: PlayingListAdapter? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var appDatabase: AppDatabase? = null
    private var favoriteListPresenter: FavoriteListPresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_list)
        appDatabase = AppDatabase.getsInstance(applicationContext)

        favoriteListPresenter = FavoriteListPresenter(MovieRepository.instance, this)

        val recyclerView = findViewById<RecyclerView>(R.id.favorite_recyclerview) as RecyclerView
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager

        playingListAdapter = PlayingListAdapter(object : Refresh {
            override fun refresh() {
                Handler(Looper.getMainLooper()).postDelayed({ add() }, 100)
            }
        })

        recyclerView.adapter = playingListAdapter
        favoriteListPresenter!!.setPlayingAdaterModel(playingListAdapter!!)
        favoriteListPresenter!!.setPlayingAdaterView(playingListAdapter!!)
        add()
    }

    private fun add() {
        favoriteListPresenter!!.favoriteInfo(applicationContext)

    }


    override fun moveToSecond(resultsBean: PlayingMovieResult.ResultsBean) {
        val intent = Intent(this@FavoriteListActivity, SecondActivity::class.java)
        intent.putExtra("movieInfo", resultsBean)
        startActivity(intent)
        overridePendingTransition(R.anim.right_in, R.anim.left_out)
    }


    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@FavoriteListActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}

