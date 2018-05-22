package com.lbc.practice.movieapp.adapter;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lbc.practice.movieapp.R;
import com.lbc.practice.movieapp.callback.OnItemClickListener;
import com.lbc.practice.movieapp.callback.Refresh;
import com.lbc.practice.movieapp.data.Memo;
import com.lbc.practice.movieapp.data.PlayingMovieResult;
import com.lbc.practice.movieapp.data.resource.local.AppDatabase;


import java.util.Date;

/**
 * Created by lbc on 2018-04-02.
 */


class PlayingListViewHolder : RecyclerView.ViewHolder {

    var ivImage: ImageView
    var tvTitle: TextView
    var tvDate: TextView
    var tvScore: TextView
    var checkBox: CheckBox
    private var appDatabase: AppDatabase
    var refresh: Refresh? = null


    constructor(v: View, callback: OnItemClickListener?) : super(v) {
        v.setOnClickListener {
            if (callback != null) {
                val position = adapterPosition
                callback.onItemClick(position)
            }
        }
        ivImage = v.findViewById<View>(R.id.iv_item) as ImageView
        tvTitle = v.findViewById<View>(R.id.tv_title) as TextView
        tvDate = v.findViewById<View>(R.id.tv_date) as TextView
        tvScore = v.findViewById<View>(R.id.tv_average_score) as TextView
        checkBox = v.findViewById<View>(R.id.checkBox) as CheckBox
        appDatabase = AppDatabase.getsInstance(v.context)
    }

    constructor(v: View, callback: OnItemClickListener?, refresh: Refresh) : super(v) {
        v.setOnClickListener {
            if (callback != null) {
                val position = adapterPosition
                callback.onItemClick(position)
            }
        }
        ivImage = v.findViewById<View>(R.id.iv_item) as ImageView
        tvTitle = v.findViewById<View>(R.id.tv_title) as TextView
        tvDate = v.findViewById<View>(R.id.tv_date) as TextView
        tvScore = v.findViewById<View>(R.id.tv_average_score) as TextView
        checkBox = v.findViewById<View>(R.id.checkBox) as CheckBox
        appDatabase = AppDatabase.getsInstance(v.context)
        this.refresh = refresh
    }


    fun setData(item: PlayingMovieResult.ResultsBean) {
        Glide.with(itemView.context).load<Any>("http://image.tmdb.org/t/p/w185" + item.poster_path).fitCenter().into(ivImage)


        if (item.id == appDatabase!!.memoDao().judgeMovie(item.id)) {
            checkBox.isChecked = true
            checkBox.text = "삭제"

        } else {
            checkBox.isChecked = false
            checkBox.text = "저장"
        }

        Handler(Looper.getMainLooper()).postDelayed({
            checkBox.setOnClickListener {
                if (checkBox.isChecked) {
                    checkBox.text = "삭제"
                    appDatabase!!.memoDao().insertMovie(Memo(item.poster_path!!, item.vote_count, item.id, item.vote_average, item.title!!,
                            item.popularity, item.overview!!, item.release_date!!, Date()))

                } else {
                    checkBox.text = "저장"
                    appDatabase!!.memoDao().deleteMovie(appDatabase!!.memoDao().findMovie(item.id))
                    if (refresh != null) {
                        refresh!!.refresh()
                    }
                }
            }
        }, 100)

        tvTitle.text = item.title
        tvDate.text = item.release_date
        tvScore.text = item.vote_average.toString() + ""

    }
}


