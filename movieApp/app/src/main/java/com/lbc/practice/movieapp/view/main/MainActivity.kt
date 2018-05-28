package com.lbc.practice.movieapp.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.lbc.practice.movieapp.R;
import com.lbc.practice.movieapp.adapter.PlayingListAdapter;
import com.lbc.practice.movieapp.data.PlayingMovieResult;
import com.lbc.practice.movieapp.data.resource.MovieRepository;
import com.lbc.practice.movieapp.view.favorite.FavoriteListActivity;
import com.lbc.practice.movieapp.view.second.SecondActivity;
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity(), MainContract.MainView {
    private var playingListAdapter: PlayingListAdapter? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var page = 1
    private var more = true
    private var loading = false
    private val apiKey = ""
    private val language = "ko"

    var toggle: ActionBarDrawerToggle?=null

    @Inject
    lateinit var mainPresenter :MainPresenter

    @Inject
    lateinit var disposeRetro : CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter.takeView(this)
        setSupportActionBar(toolbar)

        toggle = ActionBarDrawerToggle(this, main_drawer, toolbar, R.string.drawer_open, R.string.drawer_close)
        toggle?.syncState()

        main_drawer_view.setNavigationItemSelectedListener { item ->
            val id = item.itemId
            if (id == R.id.menu_drawer_home) {
            } else if (id == R.id.menu_drawer_favorite) {
                moveToFavorite()
            }
            false
        }

        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        main_recyclerview.layoutManager = linearLayoutManager

        playingListAdapter = PlayingListAdapter()



        main_recyclerview.adapter = playingListAdapter

        mainPresenter?.setPlayingAdaterView(playingListAdapter!!)
        mainPresenter?.setPlayingAdaterModel(playingListAdapter!!)

        main_swiperefreshlayout!!.setOnRefreshListener { reload() }

        main_recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!loading && more && linearLayoutManager!!.findLastVisibleItemPosition() == playingListAdapter!!.itemCount - 1) {
                    load()
                }
            }
        })
        main_swiperefreshlayout!!.isRefreshing = true
        reload()
    }

    fun reload() {
        playingListAdapter!!.clearItem()
        playingListAdapter!!.notifyDataSetChanged()
        page = 1
        more = true
        load()
    }

    @Synchronized
    fun load() {
        if (!loading) {
            loading = true
            mainPresenter?.movieInfo(apiKey, language, page)
            Log.e("page", page.toString() + "")

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (toggle!!.onOptionsItemSelected(item)) {
            false
        } else super.onOptionsItemSelected(item)
    }


    override fun moveToFavorite() {
        val intent = Intent(this@MainActivity, FavoriteListActivity::class.java)
        startActivity(intent)
    }

    override fun moveToSecond(resultsBean: PlayingMovieResult.ResultsBean) {
        val intent = Intent(applicationContext, SecondActivity::class.java)
        intent.putExtra("movieInfo", resultsBean)
        startActivity(intent)
        overridePendingTransition(R.anim.right_in, R.anim.left_out)
    }

    override fun errorMassage(error: String) {
        Toast.makeText(applicationContext, error, Toast.LENGTH_SHORT).show()
    }

    override fun increasePageCount() {
        page++
        loading = false
        main_swiperefreshlayout!!.isRefreshing = false
    }

    override fun onDestroy() {
        super.onDestroy()
        disposeRetro.clear()
    }
}
