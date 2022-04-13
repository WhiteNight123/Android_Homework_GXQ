package com.example.android9.ui.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.android9.logic.Repository
import com.example.android9.logic.model.DatasBean
import com.example.android9.logic.paging.DataPagingSource

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/10
 */
class DataViewModel : ViewModel() {
    val data1 = Pager(PagingConfig(pageSize = 20)) {
        DataPagingSource(Repository)
    }.flow.cachedIn(viewModelScope)
//    private val searchLiveData = MutableLiveData<Int>()
//    var data = mutableStateListOf<DatasBean>()
//
//    val demoLiveData = Transformations.switchMap(searchLiveData) { query ->
//        Repository.searchPlaces(query)
//    }
//
//    fun showWenda(pageId: Int) {
//        searchLiveData.value = pageId
//    }
}