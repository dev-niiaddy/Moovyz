package com.orbilax.moovyz.data

import androidx.paging.PagingSource
import com.orbilax.moovyz.model.MovieItem
import com.orbilax.moovyz.util.MoviePage
import retrofit2.HttpException
import java.io.IOException

class MovieItemPagingSource (
    private val pagingMovieSource: suspend (pageNo: Int) -> MoviePage
): PagingSource<Int, MovieItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        val position = params.key ?: TMDB_START_PAGE_INDEX

        return try {
            val moviePage = pagingMovieSource.invoke(position)
            val movieItems = moviePage.results
            LoadResult.Page(
                data = movieItems,
                prevKey = if(position == TMDB_START_PAGE_INDEX) null else position - 1,
                nextKey = if(movieItems.isEmpty()) null else position + 1
            )
        } catch (ioException: IOException) {
            LoadResult.Error(ioException)
        } catch (httpException: HttpException) {
            LoadResult.Error(httpException)
        }
    }



    companion object {
        const val TMDB_START_PAGE_INDEX = 1
    }
}