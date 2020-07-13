package com.orbilax.moovyz.custom

import android.graphics.drawable.AnimatedVectorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.orbilax.moovyz.R
import kotlinx.android.synthetic.main.card_fixed_loading_movie_item.view.*
import kotlin.random.Random

class LoadingRVAdapter(
    private val listSize: Int = 8,
    private val isStaggered: Boolean = false
) : RecyclerView.Adapter<LoadingRVAdapter.LoadingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoadingViewHolder {
        val view = when {
            isStaggered -> {
                val staggeredView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.card_staggered_loading_movie_item, parent, false)
                staggeredView.layoutParams.height = Random.nextInt(350, 501)
                staggeredView
            }
            else -> LayoutInflater.from(parent.context)
                .inflate(R.layout.card_fixed_loading_movie_item, parent, false)
        }
        return LoadingViewHolder(view)
    }

    override fun onBindViewHolder(holder: LoadingViewHolder, position: Int) {
        holder.animateReel()
    }

    override fun getItemCount(): Int = listSize

    class LoadingViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun animateReel() {
            val avd = AppCompatResources.getDrawable(
                view.context,
                R.drawable.avd_film_reel
            ) as AnimatedVectorDrawable

            view.vectorImageView.setImageDrawable(avd)
            avd.start()
        }
    }
}