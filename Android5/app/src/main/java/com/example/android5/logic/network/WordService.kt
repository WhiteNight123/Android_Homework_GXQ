package com.example.android5.logic.network

import com.example.android5.logic.model.WordResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/3/7
 */
interface WordService {

    @GET("data/words/{word}.json")
    fun searchWord(@Path("word") word: String): Call<WordResponse>
}
