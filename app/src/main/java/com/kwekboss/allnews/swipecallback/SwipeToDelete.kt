package com.kwekboss.allnews.swipecallback

import android.content.Context
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.kwekboss.allnews.R
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator

// Implementing swiping in recyclerView to delete item

abstract class SwipeToDelete(context:Context):ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {

    val deleteBackgroundColor = ContextCompat.getColor(context,R.color.red)

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
       RecyclerViewSwipeDecorator.Builder(c,recyclerView,viewHolder,dX,dY,actionState,isCurrentlyActive)
           .addBackgroundColor(deleteBackgroundColor)
           .addActionIcon(R.drawable.ic_baseline_delete_24)
           .create()
           .decorate()
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}