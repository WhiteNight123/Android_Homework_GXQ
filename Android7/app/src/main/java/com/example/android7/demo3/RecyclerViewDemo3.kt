package com.example.android7.demo3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android7.R
import com.example.android7.demo1.LinearItemDecoration
import java.util.*
import kotlin.collections.ArrayList

class RecyclerViewDemo3 : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter3
    private var list1 = ArrayList<String>()
    private var list2 = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        setContentView(R.layout.activity_recycler_view_demo3)
        val layoutManager = LinearLayoutManager(this)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        adapter = RecyclerViewAdapter3(list1)
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)

        val divider = LinearItemDecoration()
        recyclerView.addItemDecoration(divider)
        val loadMoreWrapper=LoadMoreWrapper(adapter)
        recyclerView.adapter=loadMoreWrapper
        recyclerView.addOnScrollListener(object : EndlessRecyclerOnScrollListener() {
            override fun onLoadMore() {
                loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING)
                if (list1.size < 52) {
                    // 模拟获取网络数据，延时1s
                    Timer().schedule(object : TimerTask() {
                        override fun run() {
                            runOnUiThread {
                                loadMoreData()
                                //loadMoreWrapper.notifyDataSetChanged()
//                                adapter.notifyItemInserted(10)
                                loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE)
                            }
                        }
                    }, 1000)
                } else {
                    // 显示加载到底的提示
                    loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_END)
                }
            }

        })
//        mSimpleRefreshLayout.setOnSimpleRefreshListener(object :
//            SimpleRefreshLayout.OnSimpleRefreshListener {
//            override fun onRefresh() {
//                refreshData()
//                val diffResult = DiffUtil.calculateDiff(DiffCallBack(list1, list2), true)
//                diffResult.dispatchUpdatesTo(adapter)
//                list1 = list2
//                adapter.setData(list1)
//                //下拉刷新完成
//                mSimpleRefreshLayout.onRefreshComplete()
//            }
//
//            override fun onLoadMore() {
//                loadMoreData()
//                adapter.notifyItemInserted(10)
//                //上拉加载完成
//                mSimpleRefreshLayout.onLoadMoreComplete()
//            }
//        })

//        recyclerView.setOnScrollListener(object : RecyclerView.OnScrollListener() {
//            var lastVisibleItem = 0
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.itemCount) {
//                    Handler().postDelayed(Runnable {
//                        val newDatas: MutableList<String> = ArrayList()
//                        for (i in 0..10) {
//                            val index = i + 1
//                            list1.add("这是新加的第$i 条数据")
//                        }
//                        list1.addAll(newDatas)
//                        adapter.notifyItemInserted(10)
//                    }, 1000)
//                }
//            }
//
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                lastVisibleItem = layoutManager.findLastVisibleItemPosition()
//            }
//        })
    }

    private fun initData() {
        for (i in 0..30) {
            val str = "这是第$i 条数据"
            list1.add(str)
        }
    }

    private fun refreshData() {
        list2.clear()
        for (i in 0..30) {
            val ii = (0..30).random()
            val str = "这是新的第${ii} 条数据"
            list2.add(str)
        }
    }

    private fun loadMoreData() {
        list2.clear()
        for (i in 0..10) {
            list1.add("这是新加的第$i 条数据")
        }
    }
}