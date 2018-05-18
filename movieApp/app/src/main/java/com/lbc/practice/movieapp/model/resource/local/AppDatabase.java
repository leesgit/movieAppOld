package com.lbc.practice.movieapp.model.resource.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.lbc.practice.movieapp.model.Memo;

@Database(entities = {Memo.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "todolist";
    private static AppDatabase sInstance;

    public static AppDatabase getsInstance(Context context) {
        if (sInstance ==null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, AppDatabase.DATABASE_NAME).allowMainThreadQueries().build();
            }
        }
        return sInstance;
    }



    public abstract MemoDao memoDao();
}
