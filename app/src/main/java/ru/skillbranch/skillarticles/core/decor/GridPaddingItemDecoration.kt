package ru.skillbranch.skillarticles.core.decor

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class GridPaddingItemDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = (spacing * 2).toInt()
        outRect.bottom = (spacing * 2).toInt()
        outRect.left = spacing
        outRect.right = spacing
    }

}