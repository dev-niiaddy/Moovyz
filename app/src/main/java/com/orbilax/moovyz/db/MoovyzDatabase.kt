package com.orbilax.moovyz.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.orbilax.moovyz.db.dao.DBMovieItemDao
import com.orbilax.moovyz.db.entities.DBMovieItem

@Database(entities = [DBMovieItem::class], version = 1, exportSchema = false)
abstract class MoovyzDatabase : RoomDatabase() {
    abstract fun dbMovieItemDao(): DBMovieItemDao
}