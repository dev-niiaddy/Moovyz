package com.orbilax.moovyz.custom

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs
import kotlin.math.min

class VisibleCardZoomLayout(
    context: Context,
    private val mShrinkDistance: Float = .6f,
    private val mShrinkAmount: Float = .2f
) : LinearLayoutManager(context, HORIZONTAL, false) {

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        scaleChildren()
    }

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        return if (orientation == HORIZONTAL) {
            super.scrollHorizontallyBy(dx, recycler, state).also { scaleChildren() }
        } else {
            0
        }
    }

    private fun scaleChildren() {
        val midpoint = width / 2f
        val d1 = mShrinkDistance * midpoint
        for (i in 0 until childCount) {
            val child = getChildAt(i) as View
            val d = min(d1, abs(midpoint - (getDecoratedRight(child) + getDecoratedLeft(child)) / 2f))
            val scale = (1.08f - (mShrinkAmount * (d / d1)))
            child.scaleX = scale
            child.scaleY = scale
        }
    }
}