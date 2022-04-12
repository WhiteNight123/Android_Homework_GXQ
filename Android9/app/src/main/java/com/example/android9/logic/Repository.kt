package com.example.android9.logic

import androidx.lifecycle.liveData
import com.example.android9.logic.model.DemoData
import com.example.android9.logic.network.DataNetwork
import kotlinx.coroutines.Dispatchers

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/10
 */
object Repository {
    fun searchPlaces(pageId:Int) = liveData(Dispatchers.IO) {
        val result = try {
            val dataResponse = DataNetwork.showWenda(pageId)
            val data = dataResponse.data
            val datas=data.datas
            Result.success(datas)
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }
}