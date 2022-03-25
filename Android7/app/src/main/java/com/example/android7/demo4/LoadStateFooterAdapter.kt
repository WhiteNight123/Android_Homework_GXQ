package com.example.android7.demo4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android7.databinding.Paging3ItemStateBinding

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/3/25
 */
class LoadStateFooterAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoadStateFooterAdapter.LoadStateViewHolder>() {
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        (holder as LoadStateViewHolder).bindState(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding =
            Paging3ItemStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateViewHolder(retry, binding)

    }

    inner class LoadStateViewHolder(
        var retry: () -> Unit,
        private val binding: Paging3ItemStateBinding
    ) :
        RecyclerView.ViewHolder(
            binding.root
        ) {

        fun bindState(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.btnRetry.visibility = View.VISIBLE
                binding.btnRetry.setOnClickListener {
                    retry()
                }
            } else if (loadState is LoadState.Loading) {
                binding.paging3FooterLoading.visibility = View.VISIBLE
            }
        }
    }
}