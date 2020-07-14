package com.orbilax.moovyz.remove.custom

import android.view.View
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.recyclerview.widget.RecyclerView
import com.orbilax.moovyz.model.MovieItem
import com.orbilax.moovyz.api.TMDBService
import com.orbilax.moovyz.util.loadNetworkImage
import kotlinx.android.synthetic.main.card_fixed_movie_item.view.*

class MovieItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view),
    GenericAdapter.Binder<MovieItem> {

    override fun bindItem(item: MovieItem) {
        bindMovieItem(item)
        animateCard()
    }

    private fun bindMovieItem(movieItem: MovieItem) {
        movieItem.posterPath?.let {
            view.moviePosterImageView
                .loadNetworkImage(TMDBService.tmdbImageUrl(it))
        }

        view.movieTitle.text = movieItem.title
        animateCard()
    }

    private fun animateCard() {
        view.translationX = 300f
        val springAnim = SpringAnimation(view, SpringAnimation.TRANSLATION_X, 24f).apply {
            spring.dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY
            spring.stiffness = SpringForce.STIFFNESS_LOW
        }
        springAnim.animateToFinalPosition(0f)
    }
}