package com.example.android7.demo4

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android7.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class Paging3Demo : AppCompatActivity() {
    private lateinit var recyclerViewAdapter4: RecyclerViewAdapter4
    private lateinit var recyclerView: RecyclerView
    private val demo4ViewModel by lazy { ViewModelProvider(this).get(Demo4ViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging3_demo)
        val manager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.recycler_view_demo4)
        recyclerViewAdapter4 = RecyclerViewAdapter4 { position, it, adapter ->
            it?.author = "Redrock"
            adapter.notifyItemChanged(position)
        }
        recyclerView.layoutManager = manager
        recyclerView.adapter = recyclerViewAdapter4
        recyclerView.adapter =
            recyclerViewAdapter4.withLoadStateFooter(footer = LoadStateFooterAdapter {
                recyclerViewAdapter4.retry()
            })
        lifecycleScope.launch {
            recyclerViewAdapter4.addLoadStateListener {
                when (it.refresh) {
                    is LoadState.NotLoading -> {
                        Log.d("TAG", "onCreate: is NotLoading")
                    }
                    is LoadState.Loading -> {
                        Log.d("TAG", "onCreate: is Loading")
                    }
                    is LoadState.Error -> {
                        Log.d("TAG", "onCreate: is Error")
                    }
                }
            }
        }
        lifecycleScope.launch {
            demo4ViewModel.getData().collectLatest {
                recyclerViewAdapter4.submitData(it)
            }
        }


    }
}