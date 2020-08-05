package com.orbilax.moovyz.ui.viewall

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.orbilax.moovyz.R
import com.orbilax.moovyz.custom.StaggeredPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_view_all.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ViewAllFragment : Fragment() {

    private val viewAllFragmentArgs: ViewAllFragmentArgs by navArgs()
    private val viewAllViewModel by viewModels<ViewAllViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_view_all, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val pagingAdapter = StaggeredPagingAdapter()
        viewAllRecyclerView.apply {
            layoutManager =
                StaggeredGridLayoutManager(
                    2,
                    StaggeredGridLayoutManager.VERTICAL
                )
            adapter = pagingAdapter
        }

        loadGrouping(pagingAdapter)

        Log.i("Passed Argument", viewAllFragmentArgs.movieGrouping.name)
    }

    private fun loadGrouping(pagingAdapter: StaggeredPagingAdapter) {
        when (viewAllFragmentArgs.movieGrouping) {
            MovieGroupingEnum.NOW_PLAYING -> {
                lifecycleScope.launch {
                    viewAllViewModel.getNowPlayingPaging()
                        .collectLatest { value ->
                            pagingAdapter.submitData(value)
                        }
                }
            }
            MovieGroupingEnum.COMING_SOON -> {
                lifecycleScope.launch {
                    viewAllViewModel.getComingSoonPaging()
                        .collectLatest { value ->
                            pagingAdapter.submitData(value)
                        }
                }
            }
            MovieGroupingEnum.TOP_RATED -> {
                lifecycleScope.launch {
                    viewAllViewModel.getTopRatedPaging()
                        .collectLatest { value ->
                            pagingAdapter.submitData(value)
                        }
                }
            }
            MovieGroupingEnum.POPULAR -> {
                lifecycleScope.launch {
                    viewAllViewModel.getPopularPaging()
                        .collectLatest { value ->
                            pagingAdapter.submitData(value)
                        }
                }
            }
        }
    }
}