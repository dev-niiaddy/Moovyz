package com.orbilax.moovyz.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.orbilax.moovyz.database.dao.DBMovieItemDao
import com.orbilax.moovyz.model.MovieItem
import com.orbilax.moovyz.service.TMDBService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieItemRepository @Inject constructor(
    private val tmdbService: TMDBService,
    private val movieItemDao: DBMovieItemDao
) {

     fun getComingSoon(): Flow<PagingData<MovieItem>> {
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

    fun getInCinemas(): Flow<PagingData<MovieItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MovieItemPagingSource(
                    pagingMovieSource = { pageNo ->
                        tmdbService.getInCinemas(pageNo)
                    }
                )
            }
        ).flow
    }

    fun getExploreMovies(): Flow<PagingData<MovieItem>> {
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

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }
}