package com.example.android10.logic.net

import com.example.android10.logic.model.Data
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/20
 */
interface DataApi {
    @GET("hero/select.php")
    suspend fun searchHero(@Query("hero") hero: String, @Query("type") type: String): Data
}