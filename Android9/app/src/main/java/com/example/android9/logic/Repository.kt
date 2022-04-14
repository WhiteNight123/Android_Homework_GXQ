package com.example.android9.logic

import android.util.Log
import com.example.android9.logic.model.DemoData
import com.example.android9.logic.network.DataService
import com.example.android9.logic.network.ServiceCreator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import java.util.regex.Pattern

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/10
 */
object Repository {
    suspend fun searchPlaces(pageId: Int): MutableStateFlow<List<DemoData>> {
        val data = MutableStateFlow<List<DemoData>>(listOf())
        val dataList: MutableList<DemoData> = mutableListOf()
        val list1 = ServiceCreator.create(DataService::class.java).showWenda(pageId)
        withContext(Dispatchers.IO) {
            list1.data.datas.forEach {
                it.desc = Pattern.compile(
                    "<[^>]+>\\s*|\t|\n" +
                            "|\n", Pattern.CASE_INSENSITIVE
                ).matcher(it.desc).replaceAll("") //
                it.desc = "   " + it.desc
                Log.e("Html", "load: ${it.desc}")
            }
            dataList.add(list1)
            data.value = dataList
        }
        return data
    }
}