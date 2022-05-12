package com.example.android11.logic

import com.example.android11.logic.net.LingGanApi
import com.example.android11.logic.net.ServiceCreator

/**
 *
 * @author :WhiteNight123(Guo Xiaoqiang)
 * @email 1448375249@qq.com
 * @date 2022/5/12
 */
object Repository {
    private val retrofit = ServiceCreator.create(LingGanApi::class.java)
    suspend fun getSpectrumList(theme_id: Int, page: Int) = retrofit.getSpectrumList(theme_id, page)
}