package com.example.android7.demo1

import android.graphics.Canvas
import android.graphics.Color
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import java.util.*


/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/3/18
 */
class MyItemTouchCallback(adapter: RecyclerViewAdapter) : ItemTouchHelper.Callback() {
    private val adapter: RecyclerViewAdapter

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlag: Int //拖拽方向
        val swipeFlag: Int //滑动方向
        val layoutManager = recyclerView.layoutManager
        if (layoutManager is GridLayoutManager) { //网格布局时可以左右上下拖拽
            dragFlag = (ItemTouchHelper.DOWN or ItemTouchHelper.UP
                    or ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT)
            swipeFlag = 0
        } else {
            dragFlag = ItemTouchHelper.DOWN or ItemTouchHelper.UP
            swipeFlag = ItemTouchHelper.START //滑动结束
        }
        return makeMovementFlags(dragFlag, swipeFlag)
    }

    //拖拽时回调
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val fromPosition = viewHolder.adapterPosition
        val toPosition = target.adapterPosition
        //根据位置重新排序更新数据
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(adapter.getData(), i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(adapter.getData(), i, i - 1)
            }
        }
        recyclerView.adapter?.notifyItemMoved(fromPosition, toPosition)
        return true
    }

    //滑动时回调
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        if (direction == ItemTouchHelper.START) { //侧滑删除
            adapter.list.removeAt(position)
            adapter.notifyItemRemoved(position)
        }
    }


    //状态变化时回调
    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            viewHolder?.itemView?.setBackgroundColor(Color.YELLOW)
        }
    }

    //清除
    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        viewHolder.itemView.setBackgroundColor(0)
        viewHolder.itemView.scrollX = 0

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
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            if (Math.abs(dX) <= getSlideLimitation(viewHolder)) {
                viewHolder.itemView.scrollTo((-dX).toInt(), 0)
            }
        }
    }

    fun getSlideLimitation(viewHolder: RecyclerView.ViewHolder): Int {
        val viewGroup = viewHolder.itemView as ViewGroup
        return viewGroup.getChildAt(1).layoutParams.width
    }

    init {
        this.adapter = adapter
    }
}

