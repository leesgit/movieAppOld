package com.lbc.practice.movieapp.model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "movie")
public class Memo {

    String poster_path;
    private int vote_count;
    @PrimaryKey(autoGenerate = true) //id값을 입력받지 않는 생성자가 있어도 자동으로 할당 mysql이랑 똑같음
    private int id;
    private double vote_average;
    private String title;
    private double popularity;
    private String overview;
    private String release_date;
    @ColumnInfo(name = "stored_time")
    private Date storedTime;


    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public Date getStoredTime() {
        return storedTime;
    }

    public void setStoredTime(Date storedTime) {
        this.storedTime = storedTime;
    }

    public Memo(String poster_path, int vote_count, int id, double vote_average, String title, double popularity, String overview, String release_date, Date storedTime) {
        this.poster_path = poster_path;
        this.vote_count = vote_count;
        this.id = id;
        this.vote_average = vote_average;
        this.title = title;
        this.popularity = popularity;
        this.overview = overview;
        this.release_date = release_date;
        this.storedTime = storedTime;
    }

    @Ignore  //PrimaryKey인 아이디가 없으므로
    public Memo(int vote_count, double vote_average, String title, double popularity, String overview, String release_date) {
        this.vote_count = vote_count;
        this.vote_average = vote_average;
        this.title = title;
        this.popularity = popularity;
        this.overview = overview;
        this.release_date = release_date;
    }


    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
