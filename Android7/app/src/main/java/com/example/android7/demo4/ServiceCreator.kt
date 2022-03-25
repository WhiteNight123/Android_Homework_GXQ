package com.example.android7.demo4

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/3/23
 */
object ServiceCreator {
    private const val BASE_URL = "https://www.wanandroid.com/"
    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
        GsonConverterFactory.create()
    ).build()

    fun <T> create(serciveClass: Class<T>): T = retrofit.create(serciveClass)
}