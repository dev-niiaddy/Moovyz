package com.orbilax.moovyz.ui.movies

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.orbilax.moovyz.R
import com.orbilax.moovyz.adapters.MovieItemAdapter
import com.orbilax.moovyz.adapters.StaggeredMovieItemAdapter
import com.orbilax.moovyz.custom.VisibleCardZoomLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private val moviesViewModel by viewModels<MoviesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ObjectAnimator.ofFloat(arrowUpImageView, View.TRANSLATION_Y, -5f).apply {
            repeatCount = ValueAnimator.INFINITE
            duration = 3000L
            interpolator = CycleInterpolator(5f)
        }.start()

        initComingSoon()
        initInCinemas()
        initExplore()
    }

    private fun initComingSoon() {
        comingSoonRecyclerView.layoutManager = VisibleCardZoomLayout(requireContext())

        val adapter = MovieItemAdapter()
        comingSoonRecyclerView.adapter = adapter

        lifecycleScope.launch {
            moviesViewModel.comingSoonResult.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }


    private fun initInCinemas() {
        inCinemasRecyclerView.layoutManager = VisibleCardZoomLayout(requireContext())

        val adapter = MovieItemAdapter()
        inCinemasRecyclerView.adapter = adapter

        lifecycleScope.launch {
            moviesViewModel.inCinemasResult.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun initExplore() {
        exploreRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        val adapter = StaggeredMovieItemAdapter()
        exploreRecyclerView.adapter = adapter

        lifecycleScope.launch {
            moviesViewModel.exploreMovieResults.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}