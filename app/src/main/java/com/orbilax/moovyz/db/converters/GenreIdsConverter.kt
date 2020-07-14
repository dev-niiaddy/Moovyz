package com.orbilax.moovyz.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.orbilax.moovyz.util.fromJson

class GenreIdsConverter {

    @TypeConverter
    fun genreIdsToString(genreIds: List<Int>): String {
        val gson = Gson()
        return gson.toJson(genreIds)
    }

    @TypeConverter
    fun stringToGenreIds(genreIdsJson: String): List<Int> {
        return Gson().fromJson(genreIdsJson)
    }
}