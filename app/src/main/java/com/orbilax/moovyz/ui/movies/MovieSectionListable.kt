package com.orbilax.moovyz.ui.movies

import com.billkainkoom.ogya.quicklist.Listable
import com.billkainkoom.ogya.quicklist.ListableType
import com.orbilax.moovyz.R
import com.orbilax.moovyz.util.MoviePage

class MovieSectionListable(
    val data: MoviePage,
    val onViewAllClicked: () -> Unit,
    val headerTitle: String = "",
    val isLargeCard: Boolean = false,
    val isZooming: Boolean = false
) : Listable() {
    override fun getListableType(): ListableType? {
        return ListableType(R.layout.movies_titled_section)
    }
}