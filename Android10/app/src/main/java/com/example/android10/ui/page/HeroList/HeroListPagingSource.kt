package com.example.android10.ui.page.HeroList

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.android10.logic.Repository
import com.example.android10.logic.model.HeroListDetail

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/26
 */
class HeroListPagingSource(private val repository: Repository) :
    PagingSource<Int, HeroListDetail>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HeroListDetail> {
        return try {
            val nextPage = params.key ?: 1
            val response=repository.getHeroList()

            LoadResult.Page(
                data = response.data,
                prevKey = if (nextPage==1)null else nextPage-1,
                nextKey=if (response.data.size<109)nextPage+1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, HeroListDetail>): Int? {
        return null
    }
}