package com.example.android9.ui.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.android9.logic.Repository
import com.example.android9.logic.model.DatasBean

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/10
 */
class DataViewModel : ViewModel() {
    private val searchLiveData = MutableLiveData<Int>()
    var data = mutableStateListOf<DatasBean>()

    val demoLiveData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPlaces(query)
    }

    fun showWenda(pageId:Int) {
        searchLiveData.value = pageId
    }
}