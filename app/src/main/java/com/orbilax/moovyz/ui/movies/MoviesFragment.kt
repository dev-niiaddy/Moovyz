package com.orbilax.moovyz.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.billkainkoom.ogya.quicklist.ListableAdapter
import com.billkainkoom.ogya.quicklist.ListableHelper
import com.billkainkoom.ogya.quicklist.ListableType
import com.orbilax.moovyz.R
import com.orbilax.moovyz.databinding.MoviesTitledSectionBinding
import com.orbilax.moovyz.ui.viewall.MovieGroupingEnum
import com.orbilax.moovyz.util.DataResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.listable_view.*

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private val moviesViewModel by viewModels<MoviesViewModel>()

    private lateinit var moviesPageListable: MutableList<MovieSectionListable>

    private lateinit var listableAdapter: ListableAdapter<MovieSectionListable>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.listable_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesPageListable = mutableListOf()
        listableAdapter = ListableHelper.loadList(
            context = requireContext(),
            recyclerView = listableRecyclerView,
            listables = moviesPageListable,
            listableType = ListableType(R.layout.movies_titled_section),
            listableBindingListener = { listable, listableBinding, position ->
                MovieSectionComponent.render(
                    listableBinding as MoviesTitledSectionBinding,
                    listable
                )
            }
        )

        initNowPlaying()
        initComingSoon()
        initTopRated()
        initPopular()
    }

    private fun initNowPlaying() {

        moviesViewModel.nowPlayingPageResult.observe(
            this.viewLifecycleOwner, Observer { pageResult ->
                when (pageResult) {
                    is DataResult.Success -> {
                        val movieListable = MovieSectionListable(
                            pageResult.data,
                            onViewAllClicked = {
                                navigateToViewAll(MovieGroupingEnum.NOW_PLAYING)
                            },
                            headerTitle = getString(R.string.now_playing),
                            isLargeCard = true,
                            isZooming = true
                        )
                        moviesPageListable.add(movieListable)
                        listableAdapter.submitList(moviesPageListable.toMutableList())
                    }
                }
            })
    }


    private fun initComingSoon() {
        moviesViewModel.comingSoonPageResult.observe(
            this.viewLifecycleOwner,
            Observer { pageResult ->

                when (pageResult) {
                    is DataResult.InProgress -> {
                    }
                    is DataResult.Success -> {
                        val movieListable = MovieSectionListable(
                            pageResult.data,
                            onViewAllClicked = {
                                navigateToViewAll(MovieGroupingEnum.COMING_SOON)
                            },
                            headerTitle = getString(R.string.coming_soon)
                        )
                        moviesPageListable.add(movieListable)
                        listableAdapter.submitList(moviesPageListable.toMutableList())
                    }
                    is DataResult.Error -> {}
                }
            })
    }

    private fun initTopRated() {
        moviesViewModel.topRatedPageResults.observe(
                this.viewLifecycleOwner,
        Observer { pageResult ->

            when (pageResult) {
                is DataResult.InProgress -> {
                }
                is DataResult.Success -> {
                    val movieListable = MovieSectionListable(
                        pageResult.data,
                        onViewAllClicked = {
                            navigateToViewAll(MovieGroupingEnum.TOP_RATED)
                        },
                        headerTitle = getString(R.string.top_rated)
                    )
                    moviesPageListable.add(movieListable)
                    listableAdapter.submitList(moviesPageListable.toMutableList())
                }
                is DataResult.Error -> {}
            }
        })
    }

    private fun initPopular() {
        moviesViewModel.popularPageResult.observe(
                this.viewLifecycleOwner,
        Observer { pageResult ->

            when (pageResult) {
                is DataResult.InProgress -> {
                }
                is DataResult.Success -> {
                    val movieListable = MovieSectionListable(
                        pageResult.data,
                        onViewAllClicked = {
                            navigateToViewAll(MovieGroupingEnum.POPULAR)
                        },
                        headerTitle = getString(R.string.popular)
                    )
                    moviesPageListable.add(movieListable)
                    listableAdapter.submitList(moviesPageListable.toMutableList())
                }
                is DataResult.Error -> {}
            }
        })
    }

    private fun navigateToViewAll(movieGroupingEnum: MovieGroupingEnum) {
        val action = MoviesFragmentDirections
            .moviesToViewAllFragment(movieGroupingEnum)
        findNavController().navigate(action)
    }
}