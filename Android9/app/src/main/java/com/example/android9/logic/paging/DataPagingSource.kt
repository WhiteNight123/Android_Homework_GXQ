package com.example.android9.logic.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.android9.logic.Repository
import com.example.android9.logic.model.DatasBean
import java.util.regex.Pattern

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/12
 */
class DataPagingSource(private val repository: Repository) : PagingSource<Int, DatasBean>() {
    override fun getRefreshKey(state: PagingState<Int, DatasBean>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DatasBean> {
        return try {
            val nextPage = params.key ?: 1
            val data = repository.searchPlaces(nextPage).data
            data.datas.forEach {
                it.desc = Pattern.compile(
                    "<[^>]+>\\s*|\t|\n" +
                            "|\n", Pattern.CASE_INSENSITIVE
                ).matcher(it.desc).replaceAll("") //
                Log.e("Html", "load: ${it.desc}")
            }
            LoadResult.Page(
                data = data.datas,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (nextPage < data.pageCount) nextPage + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}