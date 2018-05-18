package com.lbc.practice.movieapp.view.main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.lbc.practice.movieapp.MovieApplication;
import com.lbc.practice.movieapp.model.PlayingMovieResult;
import com.lbc.practice.movieapp.R;
import com.lbc.practice.movieapp.adapter.PlayingListAdapter;
import com.lbc.practice.movieapp.model.resource.MovieRepository;
import com.lbc.practice.movieapp.view.favorite.FavoriteListActivity;
import com.lbc.practice.movieapp.view.second.SecondActivity;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainContract.MainView  {
    private SwipeRefreshLayout swipeRefreshLayout;
    private PlayingListAdapter playingListAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int page = 1;
    private boolean more = true;
    private boolean loading = false;
    private String apiKey = "";
    private String language = "ko";

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    MainPresenter mainPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("repo1", MovieApplication.getMovieApplication().getRepository().toString());
        mainPresenter = new MainPresenter(MovieApplication.getMovieApplication().getRepository(),this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout =(DrawerLayout)findViewById(R.id.main_drawer);
        toggle=new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.drawer_open, R.string.drawer_close);
        toggle.syncState();

        NavigationView navigationView=(NavigationView)findViewById(R.id.main_drawer_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.menu_drawer_home){
                }else if(id==R.id.menu_drawer_favorite) {
//                    Intent intent = new Intent(MainActivity.this, FavoriteListActivity.class);
//                    startActivity(intent);
                    moveToFavorite();
                }
                return false;
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_recyclerview);
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        playingListAdapter = new PlayingListAdapter();



//        playingListAdapter.setOnCustomItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(int position, PlayingMovieResult.ResultsBean item) {
//                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
//                intent.putExtra("movieInfo",item);
//                startActivity(intent);
//                overridePendingTransition(R.anim.right_in,R.anim.left_out);
//            }
//        });

        recyclerView.setAdapter(playingListAdapter);

        mainPresenter.setPlayingAdaterView(playingListAdapter);
        mainPresenter.setPlayingAdaterModel(playingListAdapter);

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
            mainPresenter.movieInfo(apiKey,language,page);
            Log.e("page",page+"");


//            RetrofitManager.getInstance().getUrl().playingMovie(apiKey,language,page).enqueue(new Callback<PlayingMovieResult>() {
//                @Override
//                public void onResponse(Call<PlayingMovieResult> call, Response<PlayingMovieResult> response) {
//                    PlayingMovieResult playingMovieResult = response.body();
//                    List<PlayingMovieResult.ResultsBean> movieList = playingMovieResult.getResults();
//
//                    playingListAdapter.addItem(movieList);
//                    playingListAdapter.notifyDataSetChanged();
//                    page++;
//                    Log.e("page",page+"");
//                    loading = false;
//                    swipeRefreshLayout.setRefreshing(false);
//                }
//
//                @Override
//                public void onFailure(Call<PlayingMovieResult> call, Throwable t) {
//
//                }
//            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        reload();
//    }

    @Override
    public void moveToFavorite() {
        Intent intent = new Intent(MainActivity.this, FavoriteListActivity.class);
        startActivity(intent);
    }

    @Override
    public void moveToSecond(PlayingMovieResult.ResultsBean resultsBean) {
        Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
        intent.putExtra("movieInfo",resultsBean);
        startActivity(intent);
        overridePendingTransition(R.anim.right_in,R.anim.left_out);
    }

    @Override
    public void errorMassage(String error) {
        Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void increasePageCount() {
        page++;
        loading =false;
        swipeRefreshLayout.setRefreshing(false);
    }
}
