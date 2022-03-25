package com.example.android7.demo2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.android7.R


/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/3/20
 */
class RecyclerViewAdapter2(val list: ArrayList<String>) :
    RecyclerView.Adapter<RecyclerViewAdapter2.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView2)
        val delete: TextView = view.findViewById(R.id.delete2)
        val top: TextView = view.findViewById(R.id.top)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recyclerview_demo2, parent, false)
        val holder = ViewHolder(view)
        holder.textView.setOnClickListener {
            val position = holder.adapterPosition
            Toast.makeText(parent.context, "You clicked body item $position", Toast.LENGTH_SHORT)
                .show()
        }
        holder.delete.setOnClickListener {
            val position = holder.adapterPosition
            list.removeAt(position)
            notifyItemRemoved(position)
            Toast.makeText(parent.context, "You deleted item $position", Toast.LENGTH_SHORT)
                .show()
        }
        holder.top.setOnClickListener {
            val position = holder.adapterPosition
            list.add(0, list[position])
            notifyItemInserted(0)
            list.removeAt(position + 1)
            notifyItemRemoved(position + 1)
            Toast.makeText(parent.context, "You top body item $position", Toast.LENGTH_SHORT)
                .show()
            view.scrollTo(0, 0)
        }

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = list[position]
    }

    override fun getItemCount(): Int = list.size
}