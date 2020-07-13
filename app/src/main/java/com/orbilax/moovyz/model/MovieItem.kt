package com.orbilax.moovyz.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.orbilax.moovyz.database.entities.DBMovieItem

@Entity
data class MovieItem(
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("overview")
    val overview: String = "",

    @SerializedName("original_language")
    val originalLanguage: String = "",

    @SerializedName("original_title")
    val originalTitle: String = "",

    @SerializedName("video")
    val video: Boolean = false,

    @SerializedName("title")
    val title: String = "",

    @SerializedName("genre_ids")
    val genreIds: List<Int> = emptyList(),

    @SerializedName("poster_path")
    val posterPath: String? = null,

    @SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @SerializedName("release_date")
    val releaseDate: String = "",

    @SerializedName("popularity")
    val popularity: Double = 0.0,

    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,

    @SerializedName("adult")
    val adult: Boolean = false,

    @SerializedName("vote_count")
    val voteCount: Int = 0
) {
    fun toDBMovieItem(): DBMovieItem {
        return DBMovieItem(
            id = this.id,
            overview = this.overview,
            originalLanguage = this.originalLanguage,
            originalTitle = this.originalTitle,
            video = this.video,
            title = this.title,
            genreIds = this.genreIds,
            posterPath = this.posterPath,
            backdropPath = this.backdropPath,
            releaseDate = this.releaseDate,
            popularity = this.popularity,
            voteAverage = this.voteAverage,
            adult = this.adult,
            voteCount = this.voteCount
        )
    }
}