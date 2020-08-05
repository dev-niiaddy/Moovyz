package com.orbilax.moovyz.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.orbilax.moovyz.db.dao.DBMovieItemDao
import com.orbilax.moovyz.model.MovieItem
import com.orbilax.moovyz.api.TMDBService
import com.orbilax.moovyz.util.MoviePage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieItemRepository @Inject constructor(
    private val tmdbService: TMDBService,
    private val movieItemDao: DBMovieItemDao
) {
    suspend fun getComingSoonPage(): MoviePage = tmdbService.getComingSoon(1)

    suspend fun getNowPlaying(): MoviePage = tmdbService.getNowPlaying(1)

    suspend fun getTopRatedPage(): MoviePage = tmdbService.getTopRated(1)

    suspend fun getPopularPage(): MoviePage = tmdbService.getPopular(1)

     fun getNowPlayingPaging(): Flow<PagingData<MovieItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MovieItemPagingSource(
                    pagingMovieSource = { pageNo ->
                        tmdbService.getNowPlaying(pageNo)
                    }
                )
            }
        ).flow
    }

   fun getComingSoonPaging(): Flow<PagingData<MovieItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MovieItemPagingSource(
                    pagingMovieSource = { pageNo ->
                        tmdbService.getComingSoon(pageNo)
                    }
                )
            }
        ).flow
    }

    fun getExploreMoviesPaging(): Flow<PagingData<MovieItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MovieItemPagingSource(
                    pagingMovieSource = { pageNo ->
                        tmdbService.getExploreMovie(pageNo)
                    }
                )
            }
        ).flow
    }

    fun getTopRatedPaging(): Flow<PagingData<MovieItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MovieItemPagingSource(
                    pagingMovieSource = { pageNo ->
                        tmdbService.getTopRated(pageNo)
                    }
                )
            }
        ).flow
    }

    fun getPopularPaging(): Flow<PagingData<MovieItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MovieItemPagingSource(
                    pagingMovieSource = { pageNo ->
                        tmdbService.getPopular(pageNo)
                    }
                )
            }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }
}