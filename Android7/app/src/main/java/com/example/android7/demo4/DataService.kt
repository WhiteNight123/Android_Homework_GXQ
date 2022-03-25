package com.example.android7.demo4

import retrofit2.http.GET
import retrofit2.http.Path

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/3/23
 */
interface DataService {
    @GET("wenda/list/{pageId}/json")
    suspend fun getData(@Path("pageId") pageId:Int):DemoData
}