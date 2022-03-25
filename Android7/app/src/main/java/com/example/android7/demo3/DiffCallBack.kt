package com.example.android7.demo3

import androidx.recyclerview.widget.DiffUtil

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/3/20
 */
class DiffCallBack(val oldData: ArrayList<String>, val newData: ArrayList<String>) :
    DiffUtil.Callback() {


    override fun getOldListSize(): Int {
        return oldData.size
    }

    override fun getNewListSize(): Int {
        return newData.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition] == newData[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        if (oldData[oldItemPosition] != newData[newItemPosition]) {
            return false
        }
        return true
    }
}