package com.orbilax.moovyz.custom

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.orbilax.moovyz.model.MovieItem
import com.orbilax.moovyz.service.TMDBService
import com.orbilax.moovyz.util.loadImage
import kotlinx.android.synthetic.main.card_fixed_movie_item.view.*
import kotlin.random.Random

class StaggeredMovieItemVH(private val view: View) : RecyclerView.ViewHolder(view),
    GenericAdapter.Binder<MovieItem> {

    override fun bindItem(item: MovieItem) {
        view.layoutParams.height = Random
            .nextInt(350, 501)

        bindMovieItem(item)
    }

    private fun bindMovieItem(movieItem: MovieItem) {
        movieItem.posterPath?.let {
            view.moviePosterImageView
                .loadImage(TMDBService.tmdbImageUrl(it))
        }

        view.movieTitle.text = movieItem.title
    }

}