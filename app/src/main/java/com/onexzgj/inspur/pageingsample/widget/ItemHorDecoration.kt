package com.onexzgj.inspur.pageingsample.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.onexzgj.inspur.pageingsample.R


/**
 * des：
 * author：onexzgj
 * time：2020-03-09
 */

class ItemHorDecoration(context: Context) : ItemDecoration() {
    private val tagWidth: Int
    private val leftPaint: Paint
    private val rightPaint: Paint

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state!!)
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child: View = parent.getChildAt(i)
            val pos = parent.getChildAdapterPosition(child)
            val isLeft = pos % 2 == 0
            if (isLeft) {
                val left: Int = child.getLeft()
                val right = left + tagWidth
                val top: Int = child.getTop()
                val bottom: Int = child.getBottom()
                c.drawRect(
                    left.toFloat(), top.toFloat(), right.toFloat(),
                    bottom.toFloat(), leftPaint
                )
            } else {
                val right: Int = child.getRight()
                val left = right - tagWidth
                val top: Int = child.getTop()
                val bottom: Int = child.getBottom()
                c.drawRect(
                    left.toFloat(), top.toFloat(), right.toFloat(),
                    bottom.toFloat(), rightPaint
                )
            }
        }
    }

    init {
        leftPaint = Paint()
        leftPaint.setColor(ContextCompat.getColor(context, R.color.colorAccent))
        rightPaint = Paint()
        rightPaint.setColor(ContextCompat.getColor(context, R.color.colorPrimary))
        tagWidth = context.getResources().getDimensionPixelSize(R.dimen.dp_4)
    }
}