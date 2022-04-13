package com.example.android9.logic

import com.example.android9.logic.network.DataService
import com.example.android9.logic.network.ServiceCreator

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/10
 */
object Repository {
    suspend fun searchPlaces(pageId: Int) =
        ServiceCreator.create(DataService::class.java).showWenda(pageId)
}