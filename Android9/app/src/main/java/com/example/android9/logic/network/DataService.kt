package com.example.android9.logic.network

import com.example.android9.logic.model.DemoData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/10
 */
interface DataService {
    @GET("wenda/list/{pageId}/json")
    fun showWenda(@Path("pageId") pageId: Int): Call<DemoData>
}