package com.lbc.practice.movieapp.callback;

import com.lbc.practice.movieapp.PlayingMovieResult;

/**
 * Created by lbc on 2018-04-02.
 */

public interface OnItemClickListener {
    void onItemClick(int position, PlayingMovieResult.ResultsBean item);

}
