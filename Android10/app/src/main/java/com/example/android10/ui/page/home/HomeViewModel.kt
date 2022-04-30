package com.example.android10.ui.page.home

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android10.App
import com.example.android10.logic.Repository
import com.example.android10.logic.model.HeroPower
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

    private val _heroState = MutableLiveData<MyState<HeroPower>>()
    val heroState: LiveData<MyState<HeroPower>>
        get() = _heroState

    val textKey = stringPreferencesKey("222")

    fun getText(dataStore: DataStore<Preferences>) {
        viewModelScope.launch(Dispatchers.IO) {
            val textKey = stringPreferencesKey("222")
            dataStore.edit { settings ->
                val text = settings[textKey]
            }
        }
    }

    fun saveText(dataStore: DataStore<Preferences>, content: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val textKey = stringPreferencesKey("222")
            dataStore.edit { settings ->
                settings[textKey] = content
            }
        }
    }


    fun getData(str: String) {
        searchHeroJob.checkCoroutines()
        searchHeroJob = viewModelScope.launch(Dispatchers.IO) {
            Repository.searchHero(_heroState, App.context, str, "qq")
        }
    }
}