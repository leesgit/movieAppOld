package com.lbc.practice.movieapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.lbc.practice.movieapp.R;
import com.lbc.practice.movieapp.callback.OnItemClickListener;
import com.lbc.practice.movieapp.callback.Refresh;
import com.lbc.practice.movieapp.data.PlayingMovieResult;

import java.util.ArrayList;

/**
 * Created by lbc on 2018-04-02.
 */

class PlayingListAdapter : RecyclerView.Adapter<PlayingListViewHolder>, PlayingListContract.PlayingModel, PlayingListContract.View {


    var list: MutableList<PlayingMovieResult.ResultsBean> = ArrayList()
    var refresh: Refresh? = null

    private var onItemClickListener: OnItemClickListener? = null

    constructor(refresh: Refresh) {
        this.refresh = refresh
    }

    constructor() {
    }


    override fun clearItem() {
        list.clear()
    }

    fun addItem(item: PlayingMovieResult.ResultsBean) {
        list.add(item)
    }

    fun addItem(items: kotlin.collections.List<PlayingMovieResult.ResultsBean>) {
        list.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayingListViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.content_view_listitem, parent, false)
        return if (refresh != null) {

            PlayingListViewHolder(v, onItemClickListener, refresh!!)
        } else {
            PlayingListViewHolder(v, onItemClickListener)
        }

    }

    override fun onBindViewHolder(holder: PlayingListViewHolder, position: Int) {
        holder.setData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun addMovieInfo(enrollIntroduces: MutableList<PlayingMovieResult.ResultsBean>) {
        this.list = enrollIntroduces
    }



    override fun notifyAdapter() {
        notifyDataSetChanged()
    }

    override fun setOnclickListener(onclickListener: OnItemClickListener) {
        this.onItemClickListener = onclickListener
    }


}
