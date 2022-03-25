package com.example.android7.demo2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android7.R
import com.example.android7.demo1.MyItemTouchCallback
import com.example.android7.demo1.RecyclerViewAdapter

class RecyclerViewDemo2 : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter2
    private var list = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        setContentView(R.layout.activity_recycler_view_demo2)
        val layoutManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.recyclerView2)
        recyclerView.layoutManager = layoutManager
        adapter = RecyclerViewAdapter2(list)
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)
        recyclerView.adapter = adapter

    }

    private fun initData() {
        for (i in 0..30) {
            val str = " 这是第$i 条数据"
            list.add(str)
        }
    }
}