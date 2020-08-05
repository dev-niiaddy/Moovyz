package com.orbilax.moovyz.ui.viewall

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.orbilax.moovyz.data.MovieItemRepository
import com.orbilax.moovyz.model.MovieItem
import kotlinx.coroutines.flow.Flow

class ViewAllViewModel @ViewModelInject constructor(
    private val repository: MovieItemRepository
): ViewModel() {

    fun getNowPlayingPaging(): Flow<PagingData<MovieItem>> {
        return repository
        .getNowPlayingPaging()
        .cachedIn(viewModelScope)
    }

    fun getComingSoonPaging(): Flow<PagingData<MovieItem>> {
        return repository
            .getComingSoonPaging()
            .cachedIn(viewModelScope)
    }

    fun getTopRatedPaging():Flow<PagingData<MovieItem>> {
        return repository
            .getTopRatedPaging()
            .cachedIn(viewModelScope)
    }

    fun getPopularPaging():Flow<PagingData<MovieItem>> {
        return repository
            .getPopularPaging()
            .cachedIn(viewModelScope)
    }

//
//    val inCinemasResult: Flow<PagingData<MovieItem>> = repository
//        .getInCinemas()
//        .cachedIn(viewModelScope)
//
//    val exploreMovieResults: Flow<PagingData<MovieItem>> = repository
//        .getExploreMovies()
//        .cachedIn(viewModelScope)

}