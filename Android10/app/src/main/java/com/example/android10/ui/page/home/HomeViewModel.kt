package com.example.android10.ui.page.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android10.App
import com.example.android10.logic.Repository
import com.example.android10.logic.model.Data
import com.example.android10.ui.MyLoading
import com.example.android10.ui.MyState
import com.example.android10.utils.checkCoroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/20
 */
class HomeViewModel : ViewModel() {
    private var searchHeroJob: Job? = null

    private var _heroData = MutableLiveData<MyState<Data>>(MyLoading)
    val heroData: LiveData<MyState<Data>> = _heroData

    private val _heroState = MutableLiveData<MyState<Data>>()
    val heroState: LiveData<MyState<Data>>
        get() = _heroState

    private fun onHeroChanged(dataBean: MyState<Data>) {
        if (dataBean == _heroData.value) {
            return
        }
        _heroData.postValue(dataBean)
    }

    fun getData(str: String) {
        searchHeroJob.checkCoroutines()
        searchHeroJob = viewModelScope.launch(Dispatchers.IO) {
            Repository.searchHero(_heroState, App.context, str, "qq")
        }

    }
}