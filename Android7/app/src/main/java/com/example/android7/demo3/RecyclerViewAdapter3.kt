package com.example.android7.demo3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.android7.R
import com.example.android7.demo1.RecyclerViewAdapter

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/3/20
 */
class RecyclerViewAdapter3(var list: ArrayList<String>) :
    RecyclerView.Adapter<RecyclerViewAdapter3.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)

    }
    fun setData(list1: ArrayList<String>){
        list=list1
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recyclerview_demo1, parent, false)
        val holder = ViewHolder(view)
        holder.textView.setOnClickListener {
            val position = holder.adapterPosition
            Toast.makeText(parent.context, "You clicked body item $position", Toast.LENGTH_SHORT)
                .show()
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = list[position]
    }

    override fun getItemCount(): Int = list.size
}