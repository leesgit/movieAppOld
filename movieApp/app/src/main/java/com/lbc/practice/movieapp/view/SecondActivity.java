package com.lbc.practice.movieapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.lbc.practice.movieapp.PlayingMovieResult;
import com.lbc.practice.movieapp.R;
import com.lbc.practice.movieapp.adapter.ImagePagerAdapter;

import java.util.Locale;

/**
 * Created by lbc on 2018-04-02.
 */

public class SecondActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private ViewPager viewPager;
    private ImagePagerAdapter imagePagerAdapter;
    private TextView tvPagerCounter, tvTitle, tvGrade, tvStory;
    private int page;

    PlayingMovieResult.ResultsBean resultsBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tvPagerCounter = (TextView)findViewById(R.id.second_tv_viewpager_counter);
        tvTitle = (TextView)findViewById(R.id.second_tv_title);
        tvGrade = (TextView)findViewById(R.id.second_tv_grade);
        tvStory = (TextView)findViewById(R.id.second_tv_story);

        Intent intent =getIntent();
        resultsBean = (PlayingMovieResult.ResultsBean) intent.getSerializableExtra("movieInfo");

        Log.e("test2",resultsBean.getOverview());
        String img[] = new String[2];
        img[0] = "http://image.tmdb.org/t/p/w185"+resultsBean.getPoster_path();
        img[1] = "http://image.tmdb.org/t/p/w185"+resultsBean.getBackdrop_path();

        viewPager = (ViewPager)findViewById(R.id.second_viewpager);
        imagePagerAdapter = new ImagePagerAdapter();
        viewPager.setAdapter(imagePagerAdapter);
        viewPager.addOnPageChangeListener(this);
        imagePagerAdapter.setImageUrls(img);
        displayViewPage(1);
        imagePagerAdapter.notifyDataSetChanged();

        tvTitle.setText(resultsBean.getTitle());
        if (resultsBean.isAdult()) {
            tvGrade.setText("성인 등급");
        } else {
            tvGrade.setText("일반 등급");
        }

        tvStory.setText(resultsBean.getOverview());

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(positionOffset > 0.5){
            if(page != position + 2)
                displayViewPage(position + 2);
        }else if(page != position + 1){
            displayViewPage(position + 1);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if(state == ViewPager.SCROLL_STATE_IDLE){
            displayViewPage(viewPager.getCurrentItem()+1);
        }
    }
    private void displayViewPage(int page){
        this.page = page;
        tvPagerCounter.setText(page+"/"+2);
    }
}
