package com.lbc.practice.movieapp.data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lbc on 2018-04-02.
 */

class PlayingMovieResult {

    var page: Int = 0
    var total_results: Int = 0
    var dates: DatesBean? = null
    var total_pages: Int = 0
    var results: kotlin.collections.List<ResultsBean>? = null

    class DatesBean {
        var maximum: String? = null
        var minimum: String? = null
    }

    class ResultsBean(var poster_path: String?, var vote_count: Int, var id: Int, var vote_average: Double, var title: String?,
                      var popularity: Double, var overview: String?, var release_date: String?, var storedTime: Date?) : Serializable {
        var isVideo: Boolean = false
        var original_language: String? = null
        var original_title: String? = null
        var backdrop_path: String? = null
        var isAdult: Boolean = false
        var genre_ids: kotlin.collections.List<Int>? = null
    }
}
