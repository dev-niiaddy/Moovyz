package com.orbilax.moovyz.custom

import android.view.View
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.recyclerview.widget.RecyclerView
import com.orbilax.moovyz.api.TMDBService
import com.orbilax.moovyz.model.MovieItem
import com.orbilax.moovyz.extentsions.loadNetworkImage
import com.orbilax.moovyz.extentsions.toPercentage
import kotlinx.android.synthetic.main.card_movie_item.view.*
import java.util.*

class MovieItemViewHolder(
    private val view: View,
    private val shouldAnimate: Boolean = true) : RecyclerView.ViewHolder(view) {

    fun bindMovieItem(movieItem: MovieItem?) {
        if (movieItem != null) {
            movieItem.posterPath?.let {
                view.posterImageView
                    .loadNetworkImage(TMDBService.tmdbImageUrl(it))
            }

            view.movieTitleText.text = movieItem.title
            view.ratingText.text = movieItem.voteAverage.toPercentage().toString() + "%"
            view.languageText.text = movieItem.originalLanguage.toUpperCase(Locale.getDefault())

            if (shouldAnimate) {
                animateCard()
            }
        }
    }

    private fun animateCard() {
        view.translationX = 200f
        val springAnim = SpringAnimation(view, SpringAnimation.TRANSLATION_X, 0f).apply {
            spring.dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY
            spring.stiffness = SpringForce.STIFFNESS_LOW
        }
        springAnim.animateToFinalPosition(0f)
    }
}