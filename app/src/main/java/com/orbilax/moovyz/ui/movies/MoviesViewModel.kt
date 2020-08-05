package com.orbilax.moovyz.ui.movies

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.orbilax.moovyz.data.MovieItemRepository
import com.orbilax.moovyz.util.DataResult
import com.orbilax.moovyz.util.MoviePage
import kotlinx.coroutines.Dispatchers

class MoviesViewModel @ViewModelInject constructor(
    private val repository: MovieItemRepository
): ViewModel() {

    val comingSoonPageResult: LiveData<DataResult<MoviePage>> = liveData(Dispatchers.IO) {
        emit(DataResult.InProgress)
        try {
            val data = repository.getComingSoonPage()
            emit(DataResult.Success(data))
        } catch (e: Exception) {
            emit(DataResult.Error(e))
            e.printStackTrace()
        }
    }

    val nowPlayingPageResult: LiveData<DataResult<MoviePage>> = liveData(Dispatchers.IO) {
        emit(DataResult.InProgress)
        try {
            val data = repository.getNowPlaying()
            emit(DataResult.Success(data))
        } catch (e: Exception) {
            emit(DataResult.Error(e))
            e.printStackTrace()
        }
    }

    val topRatedPageResults: LiveData<DataResult<MoviePage>> = liveData(Dispatchers.IO) {
        emit(DataResult.InProgress)
        try {
            val data = repository.getTopRatedPage()
            emit(DataResult.Success(data))
        } catch (e: Exception) {
            emit(DataResult.Error(e))
            e.printStackTrace()
        }
    }

    val popularPageResult: LiveData<DataResult<MoviePage>> = liveData(Dispatchers.IO) {
        emit(DataResult.InProgress)
        try {
            val data = repository.getPopularPage()
            emit(DataResult.Success(data))
        } catch (e: Exception) {
            emit(DataResult.Error(e))
            e.printStackTrace()
        }
    }
}