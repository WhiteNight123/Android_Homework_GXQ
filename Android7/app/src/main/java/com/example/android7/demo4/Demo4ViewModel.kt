package com.example.android7.demo4

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/3/23
 */
class Demo4ViewModel : ViewModel() {
    fun getData() = Pager(PagingConfig(pageSize = 1)) {
        DataSource()
    }.flow
}