package com.example.android7.demo3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import com.example.android7.R
import kotlin.concurrent.thread


/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/3/22
 */
class LoadMoreWrapper(adapter: RecyclerView.Adapter<*>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    private val adapter1: RecyclerView.Adapter<RecyclerView.ViewHolder> = adapter as RecyclerView.Adapter<RecyclerView.ViewHolder>

    // 普通布局
    private val TYPE_ITEM = 1

    // 脚布局
    private val TYPE_FOOTER = 2

    // 当前加载状态，默认为加载完成
    private var loadState = 2

    // 正在加载
    val LOADING = 1

    // 加载完成
    val LOADING_COMPLETE = 2

    // 加载到底
    val LOADING_END = 3
    override fun getItemViewType(position: Int): Int {
        // 最后一个item设置为FooterView
        return if (position + 1 == itemCount) {
            TYPE_FOOTER
        } else {
            TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // 通过判断显示类型，来创建不同的View
        return if (viewType == TYPE_FOOTER) {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.demo3_layout_refresh_footer, parent, false)
            FootViewHolder(view)
        } else {
            adapter1.onCreateViewHolder(parent, viewType)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FootViewHolder) {
            val footViewHolder = holder
            when (loadState) {
                LOADING -> {
                    footViewHolder.pbLoading.visibility = View.VISIBLE
                    footViewHolder.tvLoading.visibility = View.VISIBLE
                    footViewHolder.llEnd.visibility = View.GONE
                }
                LOADING_COMPLETE -> {
                    footViewHolder.pbLoading.visibility = View.INVISIBLE
                    footViewHolder.tvLoading.visibility = View.INVISIBLE
                    footViewHolder.llEnd.visibility = View.GONE
                }
                LOADING_END -> {
                    footViewHolder.pbLoading.visibility = View.GONE
                    footViewHolder.tvLoading.visibility = View.GONE
                    footViewHolder.llEnd.visibility = View.VISIBLE
//                    Thread.sleep(500)
//                        footViewHolder.llEnd.visibility = View.INVISIBLE
//                        adapter1.itemCount-1
//                        adapter1.notifyItemRemoved(adapter1.itemCount)

                }
                else -> {}
            }
        } else {
            adapter1.onBindViewHolder(holder, position)
        }
    }

    override fun getItemCount(): Int {
        return adapter1.itemCount + 1
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val manager = recyclerView.layoutManager
        if (manager is GridLayoutManager) {
            val gridManager = manager
            gridManager.spanSizeLookup = object : SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    // 如果当前是footer的位置，那么该item占据2个单元格，正常情况下占据1个单元格
                    return if (getItemViewType(position) == TYPE_FOOTER) gridManager.spanCount else 1
                }
            }
        }
    }

    private inner class FootViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var pbLoading: ProgressBar
        var tvLoading: TextView
        var llEnd: LinearLayout

        init {
            pbLoading = itemView.findViewById(R.id.pb_loading)
            tvLoading = itemView.findViewById(R.id.tv_loading)
            llEnd = itemView.findViewById(R.id.ll_end)
        }
    }

    /**
     * 设置上拉加载状态
     *
     * @param loadState 0.正在加载 1.加载完成 2.加载到底
     */
    fun setLoadState(loadState: Int) {
        this.loadState = loadState
        notifyDataSetChanged()

    }

}