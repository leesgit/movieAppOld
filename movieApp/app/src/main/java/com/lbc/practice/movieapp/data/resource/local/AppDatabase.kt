package com.lbc.practice.movieapp.data.resource.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.lbc.practice.movieapp.data.Memo;


@Database(entities = arrayOf(Memo::class), version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {


    abstract fun memoDao(): MemoDao

    companion object {

        private val LOG_TAG = AppDatabase::class.java.simpleName
        private val LOCK = Any()
        private val DATABASE_NAME = "todolist"
        private var sInstance: AppDatabase? = null

        fun getsInstance(context: Context): AppDatabase {
            if (sInstance == null) {
                synchronized(LOCK) {
                    sInstance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, AppDatabase.DATABASE_NAME).allowMainThreadQueries().build()
                }
            }
            return sInstance!!
        }
    }
}