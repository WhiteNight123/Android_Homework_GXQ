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
    private val searchLiveData1 = MutableLiveData<String>()
    private val searchLiveData2 = MutableLiveData<String>()
    val wordList = ArrayList<WordResponse>()
    val wordLiveData = Transformations.switchMap(searchLiveData1) { query ->
        Repository.searchWord(query)
    }

    fun searchWord1(query: String) {
        searchLiveData1.value = query
    }

    val wordLiveData2 = Transformations.switchMap(searchLiveData2) { query ->
        Repository.getWord(query)
    }

    fun searchWord2(query: String) {
        searchLiveData2.value = query
    }

    fun saveWord(word: WordResponse) = Repository.saveWord(word)
    fun getSavedWord() = Repository.getSavedWord()
    fun isWordSaved() = Repository.isWordSaved()
}