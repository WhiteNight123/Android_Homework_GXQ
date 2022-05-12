package com.example.android11.logic.net

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @date 2022/4/30
 */
object ServiceCreator {
    private const val BASE_URL = "http://redrock.udday.cn:8888/"
    private val retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
}