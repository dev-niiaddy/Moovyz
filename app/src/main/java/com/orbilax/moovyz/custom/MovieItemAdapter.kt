package com.orbilax.moovyz.custom

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.orbilax.moovyz.R
import com.orbilax.moovyz.model.MovieItem
import com.orbilax.moovyz.extentsions.inflateLayout
import kotlinx.android.synthetic.main.card_movie_item.view.*

class MovieItemAdapter(
    private val movieItems: List<MovieItem>,
    private val isLargeCard: Boolean =  false): RecyclerView.Adapter<MovieItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val view = parent.inflateLayout(R.layout.card_movie_item)

        if(!isLargeCard) {
            view.materialCardView.layoutParams.apply {
                height =  500
                width = 400
            }
        }

        return MovieItemViewHolder(view)
    }

    override fun getItemCount(): Int = movieItems.size

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        val movieItem = movieItems[position]
        holder.bindMovieItem(movieItem)
    }
}