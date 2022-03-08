package com.example.android5.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.android5.logic.Repository
import com.example.android5.logic.model.WordResponse

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/3/7
 */
class WordViewModel : ViewModel() {
    private val searchLiveData = MutableLiveData<String>()
    val wordList = ArrayList<WordResponse>()
    val wordLiveData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchWord(query)
    }

    fun searchWord(query: String) {
        searchLiveData.value = query
    }
}