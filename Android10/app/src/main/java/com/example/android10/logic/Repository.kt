package com.example.android10.logic

import com.example.android10.logic.net.DataApi
import com.example.android10.logic.net.ServiceCreator


/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/20
 */
object Repository {
    suspend fun searchHero(name: String, type: String) =
        ServiceCreator.create(DataApi::class.java).searchHero(name, type)
}