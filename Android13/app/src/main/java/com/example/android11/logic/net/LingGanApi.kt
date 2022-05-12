package com.example.android11.logic.net

import com.example.android11.logic.model.*
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @date 2022/4/30
 */

interface LingGanApi {
    @GET("idea/idea")
    suspend fun getIdeaPage(): BaseData<IdeaData>

    @GET("idea/idea_detail")
    suspend fun getIdeaDetail(@Query("idea_detail_id") id: Int): BaseData<IdeaDetailData>

    @GET("color/page")
    suspend fun getSpectrumPage(): BaseData<SpectrumData>

    @GET("color/color_list")
    suspend fun getSpectrumList(
        @Query("theme_id") id: Int,
        @Query("page") page: Int
    ): BaseData<SpectrumListData>

    @GET("color/color_detail")
    suspend fun getSpectrumDetail(@Query("color_detail_id") id: Int): BaseData<SpectrumDetailData>

}