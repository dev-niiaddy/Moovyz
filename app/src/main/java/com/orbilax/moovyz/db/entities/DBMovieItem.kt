package com.orbilax.moovyz.db.entities

import androidx.room.*
import com.orbilax.moovyz.db.converters.GenreIdsConverter
import com.orbilax.moovyz.model.MovieItem

@Entity(tableName = "movie_item")
@TypeConverters(GenreIdsConverter::class)
data class DBMovieItem(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "overview")
    val overview: String = "",

    @ColumnInfo(name = "original_language")
    val originalLanguage: String = "",

    @ColumnInfo(name = "original_title")
    val originalTitle: String = "",

    @ColumnInfo(name = "video")
    val video: Boolean = false,

    @ColumnInfo(name = "title")
    val title: String = "",

    @ColumnInfo(name = "genre_ids")
    val genreIds: List<Int> = emptyList(),

    @ColumnInfo(name = "poster_path")
    val posterPath: String? = null,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String? = null,

    @ColumnInfo(name = "release_date")
    val releaseDate: String = "",

    @ColumnInfo(name = "popularity")
    val popularity: Double = 0.0,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double = 0.0,

    @ColumnInfo(name = "adult")
    val adult: Boolean = false,

    @ColumnInfo(name = "vote_count")
    val voteCount: Int = 0
) {
    fun toMovieItem(): MovieItem {
        return MovieItem(
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