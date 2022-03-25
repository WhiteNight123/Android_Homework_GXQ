package com.example.android7.demo1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android7.R

class RecyclerViewDemo1 : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter
    private var list = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        setContentView(R.layout.activity_recycler_view_demo1)
        val layoutManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        adapter = RecyclerViewAdapter(list)
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        val helper = ItemTouchHelper(MyItemTouchCallback(adapter))
        recyclerView.addItemDecoration(decoration)
        recyclerView.adapter = adapter
        helper.attachToRecyclerView(recyclerView)
        val divider= LinearItemDecoration()
        recyclerView.addItemDecoration(divider)

    }

    private fun initData() {
        for (i in 0..30) {
            val str = " 这是第$i 条数据"
            list.add(str)
        }
    }
}