package com.example.android11.ui.page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.android11.logic.model.ColorDetailData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

/**
 *
 * @author :WhiteNight123(Guo Xiaoqiang)
 * @email 1448375249@qq.com
 * @date 2022/5/12
 */
class SpectrumViewModel : ViewModel() {
    private val searchResults = MutableSharedFlow<Int>(1)

    @OptIn(ExperimentalCoroutinesApi::class)
    val spectrumList: Flow<PagingData<ColorDetailData>> = searchResults.flatMapLatest {
        Pager(PagingConfig(pageSize = 10)) {
            SpectrumPagingSource(it)
        }.flow
    }.cachedIn(viewModelScope)

    fun getSpectrumList(id: Int) {
        viewModelScope.launch {
            searchResults.emit(id)
        }
    }
}