package com.example.android5.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/3/7
 */
object ServiceCreator {
    //需要梯子
    private const val BASE_URL = "https://cdn.jsdelivr.net/gh/lyc8503/baicizhan-word-meaning-API/"
    //使用本地网络
    //private const val BASE_URL = "http://10.17.77.215"
    private val retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)


}
