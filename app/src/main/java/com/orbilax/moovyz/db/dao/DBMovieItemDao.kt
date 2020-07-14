package com.orbilax.moovyz.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.orbilax.moovyz.db.entities.DBMovieItem

@Dao
interface DBMovieItemDao {

    @Query("SELECT * FROM movie_item")
    fun getAllDBMovieItems(): List<DBMovieItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieItem: DBMovieItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg movieItem: DBMovieItem)

    @Query("DELETE FROM movie_item")
    suspend fun deleteAll()
}