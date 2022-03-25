package com.example.android7.demo4

import android.text.Html
import android.text.Html.FROM_HTML_MODE_COMPACT
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android7.databinding.ItemRecyclerviewDemo4Binding

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/3/23
 */
class RecyclerViewAdapter4(val itemUpdate: (Int, DatasBean?, RecyclerViewAdapter4) -> Unit) :
    PagingDataAdapter<DatasBean, RecyclerView.ViewHolder>(object :
        DiffUtil.ItemCallback<DatasBean>() {
        override fun areItemsTheSame(oldItem: DatasBean, newItem: DatasBean): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DatasBean, newItem: DatasBean): Boolean {
            return oldItem == newItem
        }
    }) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val datasBean = getItem(position)
        if (datasBean != null) {
            (holder as DataViewHolder).binding.textViewAuthor.text = datasBean.author
            holder.binding.textViewContent.text =
                Html.fromHtml(datasBean.desc, FROM_HTML_MODE_COMPACT)
            holder.binding.textViewTime.text = datasBean.niceDate
            holder.binding.textViewTitle.text = datasBean.title
            holder.binding.UpdateButton.setOnClickListener {
                itemUpdate(position, datasBean, this)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemRecyclerviewDemo4Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = DataViewHolder(binding)
        holder.binding.UpdateButton.setOnClickListener {
            itemUpdate(holder.bindingAdapterPosition, getItem(holder.absoluteAdapterPosition), this)
        }
        return DataViewHolder(binding)
    }

    inner class DataViewHolder(val binding: ItemRecyclerviewDemo4Binding) :
        RecyclerView.ViewHolder(binding.root)

}