package com.lbc.practice.movieapp.view.second;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lbc.practice.movieapp.R;
import com.lbc.practice.movieapp.adapter.ImagePagerAdapter;
import com.lbc.practice.movieapp.data.PlayingMovieResult;
import kotlinx.android.synthetic.main.activity_second.*

/**
 * Created by lbc on 2018-04-02.
 */

class SecondActivity : AppCompatActivity(), ViewPager.OnPageChangeListener, SecondContract.SecondView {

    private var imagePagerAdapter: ImagePagerAdapter? = null
    private var tvPagerCounter: TextView? = null

    private var page: Int = 0
     var secondPresenter: SecondPresenter?=null

     var resultsBean: PlayingMovieResult.ResultsBean?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        secondPresenter = SecondPresenter(this)
        val intent = intent
        resultsBean = intent.getSerializableExtra("movieInfo") as PlayingMovieResult.ResultsBean


        val img = arrayOfNulls<String>(2)
        img[0] = "http://image.tmdb.org/t/p/w185" + resultsBean?.poster_path
        img[1] = "http://image.tmdb.org/t/p/w185" + resultsBean?.backdrop_path

        imagePagerAdapter = ImagePagerAdapter()
        second_viewpager!!.adapter = imagePagerAdapter
        second_viewpager!!.addOnPageChangeListener(this)
//        imagePagerAdapter!!.setImageUrls(img)
        imagePagerAdapter!!.imageUrls =img
        secondPresenter?.displayViewPage(1)
        imagePagerAdapter!!.notifyDataSetChanged()

        second_tv_title!!.text = resultsBean?.title
        if (resultsBean!!.isAdult) {
            second_tv_grade!!.text = "성인 등급"
        } else {
            second_tv_grade!!.text = "일반 등급"
        }

        second_tv_story!!.text = resultsBean?.overview

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        if (positionOffset > 0.5) {
            if (page != position + 2)
                secondPresenter?.displayViewPage(position + 2)
        } else if (page != position + 1) {
            secondPresenter?.displayViewPage(position + 1)
        }
    }

    override fun onPageSelected(position: Int) {

    }

    override fun onPageScrollStateChanged(state: Int) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            secondPresenter?.displayViewPage(second_viewpager!!.currentItem + 1)
        }
    }

    override fun setPage(page: Int) {
        this.page = page
    }

    override fun setText(page: Int) {
        second_tv_viewpager_counter!!.text = page.toString() + "/" + 2
    }
}
