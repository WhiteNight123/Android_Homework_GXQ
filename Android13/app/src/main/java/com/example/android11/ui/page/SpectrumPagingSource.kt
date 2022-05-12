package com.example.android11.ui.page

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.android11.logic.Repository
import com.example.android11.logic.model.ColorDetailData

/**
 *
 * @author :WhiteNight123(Guo Xiaoqiang)
 * @email 1448375249@qq.com
 * @date 2022/5/12
 */
class SpectrumPagingSource(private val theme_id: Int) : PagingSource<Int, ColorDetailData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ColorDetailData> {
        return try {
            val page = params.key ?: 0 // set page 1 as default
            Log.e("TAG", "page: $page")
            Log.e("TAG", "theme_id: $theme_id")
            val apiResponse = Repository.getSpectrumList(theme_id, page)
            val spectrumList = apiResponse.data.color_list
            val prevKey = if (page > 0) page - 1 else null
            val nextKey = if (apiResponse.data.has_more) page + 2 else null
            LoadResult.Page(spectrumList, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ColorDetailData> {
//        return try {
//            val nextPage = params.key ?: 1
//            val response = Repository.getSpectrumList(theme_id, nextPage)
//            val spectrumList = response.data.color_list
//            Log.e("TAG", "load: ${spectrumList.toString()}")
//            val prevKey = if (nextPage > 0) nextPage - 1 else null
//            val nextKey = if (response.data.has_more) nextPage + 1 else null
//            LoadResult.Page(spectrumList, prevKey, nextKey)
//        } catch (e: Exception) {
//            LoadResult.Error(e)
//        }
//    }

    override fun getRefreshKey(state: PagingState<Int, ColorDetailData>): Int? {
        return null
    }
}