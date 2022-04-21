package com.example.android10.ui.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android10.logic.Repository
import com.example.android10.logic.model.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/20
 */
class DataViewModel : ViewModel() {
    var data = MutableLiveData<Data>()
    fun getData(str: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val data1=Repository.searchHero(str,"qq")
            data.postValue(data1)
        }
    }
}