package com.orbilax.moovyz.ui.movies

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.orbilax.moovyz.model.MovieItem
import com.orbilax.moovyz.repository.MovieItemRepository
import kotlinx.coroutines.flow.Flow

class MoviesViewModel @ViewModelInject constructor(
    private val repository: MovieItemRepository
): ViewModel() {

    val comingSoonResult: Flow<PagingData<MovieItem>> = repository
        .getComingSoon()
        .cachedIn(viewModelScope)

    val inCinemasResult: Flow<PagingData<MovieItem>> = repository
        .getInCinemas()
        .cachedIn(viewModelScope)

    val exploreMovieResults: Flow<PagingData<MovieItem>> = repository
        .getExploreMovies()
        .cachedIn(viewModelScope)
}