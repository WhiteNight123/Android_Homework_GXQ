package com.example.android10.ui.page.HeroList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.android10.logic.Repository

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/25
 */
class HeroListViewModel : ViewModel() {

    val heroListResult = Pager(PagingConfig(pageSize = 20)) {
        HeroListPagingSource(Repository)
    }.flow.cachedIn(viewModelScope)
}