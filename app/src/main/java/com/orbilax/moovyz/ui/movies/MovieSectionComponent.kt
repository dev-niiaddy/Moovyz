package com.orbilax.moovyz.ui.movies

import com.billkainkoom.ogya.quicklist.BaseComponent
import com.billkainkoom.ogya.quicklist.ListableType
import com.orbilax.moovyz.R
import com.orbilax.moovyz.custom.MovieItemAdapter
import com.orbilax.moovyz.custom.VisibleCardZoomLayout
import com.orbilax.moovyz.databinding.MoviesTitledSectionBinding

object  MovieSectionComponent: BaseComponent<MoviesTitledSectionBinding, MovieSectionListable>() {
    override fun listableType(): ListableType {
        return ListableType(R.layout.movies_titled_section)
    }

    override fun render(binding: MoviesTitledSectionBinding, listable: MovieSectionListable) {
        binding.headerTitle = listable.headerTitle
        binding.moviesRecyclerView.apply {
            if(listable.isZooming) {
                layoutManager = VisibleCardZoomLayout(this.context)
            }
            adapter = MovieItemAdapter(listable.data.results, isLargeCard = listable.isLargeCard)
        }
        binding.viewAllBtn.setOnClickListener {
            listable.onViewAllClicked()
        }
    }
}