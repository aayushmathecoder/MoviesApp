package com.sample.moviesapp.data.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sample.moviesapp.model.Movie
import com.sample.moviesapp.model.MovieResultInfo

@Database(entities = [Movie::class],version=1,exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    abstract val moviesDao: MoviesDao
}

private lateinit var INSTANCE:MoviesDatabase

fun getDatabase(context: Context): MoviesDatabase
{
    synchronized(MoviesDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                MoviesDatabase::class.java,
                "movies_database").build()
        }
    }
    return INSTANCE
}

