package com.lbc.practice.movieapp.data.resource.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.lbc.practice.movieapp.data.Memo;

import java.util.List;


@Dao
interface MemoDao {
    @Query("SELECT * FROM movie ORDER BY stored_time")
    fun loadAllMovie(): kotlin.collections.List<Memo>
    //    LiveData<List<Memo>> loadAllMovie();

    @Query("SELECT count(*) FROM movie")
    fun countlMovie(): Int

    @Query("SELECT * FROM movie WHERE id=:id")
    fun findMovie(id: Int): Memo

    @Query("SELECT id FROM movie WHERE id=:id")
    fun judgeMovie(id: Int): Int

    @Insert
    fun insertMovie(memo: Memo)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMovie(memo: Memo)

    @Delete
    fun deleteMovie(memo: Memo)

}
