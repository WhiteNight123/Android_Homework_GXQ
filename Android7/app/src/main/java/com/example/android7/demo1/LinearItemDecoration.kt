package com.example.android7.demo1

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/3/20
 */
class LinearItemDecoration : ItemDecoration() {
    private var mPaint: Paint? = null

    init {
        mPaint = Paint()
        mPaint!!.setColor(Color.GREEN)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val childCount = parent.childCount
        val manager = parent.layoutManager
        for (i in 0 until childCount) {
            val child: View = parent.getChildAt(i)
            val left = manager!!.getLeftDecorationWidth(child)
            val cx = left / 2
            val cy = child.top + child.height / 2
            c.drawCircle(cx.toFloat(), cy.toFloat(), 20F, mPaint!!)
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.left=100
    }


}
