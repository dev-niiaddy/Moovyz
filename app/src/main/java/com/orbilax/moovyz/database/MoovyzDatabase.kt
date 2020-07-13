package com.orbilax.moovyz.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.orbilax.moovyz.database.dao.DBMovieItemDao
import com.orbilax.moovyz.database.entities.DBMovieItem

@Database(entities = arrayOf(DBMovieItem::class), version = 1, exportSchema = false)
abstract class MoovyzDatabase : RoomDatabase() {

    abstract fun dbMovieItemDao(): DBMovieItemDao

    companion object {
        @Volatile
        private var INSTANCE: MoovyzDatabase? = null


        fun getDatabase(context: Context): MoovyzDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoovyzDatabase::class.java,
                    "moovyz_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}