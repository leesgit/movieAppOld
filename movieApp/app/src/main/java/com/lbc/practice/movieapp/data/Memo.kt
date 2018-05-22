package com.lbc.practice.movieapp.data;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;


@Entity(tableName = "movie")
class Memo {

    var poster_path: String?=null
    var vote_count: Int?=null
    @PrimaryKey(autoGenerate = true) //id값을 입력받지 않는 생성자가 있어도 자동으로 할당 mysql이랑 똑같음
    var id: Int?=null
    var vote_average: Double?=null
    var title: String? = null
    var popularity: Double?=null
    var overview: String? = null
    var release_date: String? = null
    @ColumnInfo(name = "stored_time")
    var storedTime: Date? = null

    constructor(poster_path: String, vote_count: Int, id: Int, vote_average: Double, title: String, popularity: Double, overview: String, release_date: String, storedTime: Date) {
        this.poster_path = poster_path
        this.vote_count = vote_count
        this.id = id
        this.vote_average = vote_average
        this.title = title
        this.popularity = popularity
        this.overview = overview
        this.release_date = release_date
        this.storedTime = storedTime
    }

    @Ignore  //PrimaryKey인 아이디가 없으므로
    constructor(vote_count: Int, vote_average: Double, title: String, popularity: Double, overview: String, release_date: String) {
        this.vote_count = vote_count
        this.vote_average = vote_average
        this.title = title
        this.popularity = popularity
        this.overview = overview
        this.release_date = release_date
    }
}
