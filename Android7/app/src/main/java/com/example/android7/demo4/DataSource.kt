package com.example.android7.demo4

import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.lang.Exception

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/3/23
 */
class DataSource():PagingSource<Int,DatasBean>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DatasBean> {
        return try {
            val currentPage=params.key?:1
            val demoData=DataRepository().getData(currentPage)
            val nextPage=if (currentPage< demoData.data.pageCount ?:0){
                currentPage+1
            }else{
                null
            }

            LoadResult.Page(data = demoData.data.datas,prevKey = null, nextKey = nextPage)
        }catch (e:Exception){
            LoadResult.Error(throwable = e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DatasBean>): Int {
        return 0
    }
}