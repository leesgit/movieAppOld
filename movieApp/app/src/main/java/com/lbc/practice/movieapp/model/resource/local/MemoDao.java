package com.lbc.practice.movieapp.model.resource.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.lbc.practice.movieapp.model.Memo;

import java.util.List;

@Dao
public interface MemoDao {
    @Query("SELECT * FROM movie ORDER BY stored_time")
    List<Memo> loadAllMovie();
//    LiveData<List<Memo>> loadAllMovie();

    @Query("SELECT count(*) FROM movie")
    int countlMovie();

    @Query("SELECT * FROM movie WHERE id=:id")
    Memo findMovie(int id);

    @Query("SELECT id FROM movie WHERE id=:id")
    int judgeMovie(int id);

    @Insert
    void insertMovie(Memo memo);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMovie(Memo memo);

    @Delete
    void deleteMovie(Memo memo);

}
