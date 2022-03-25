package com.example.android7.demo4

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/3/23
 */
class DataRepository {
    private var network = ServiceCreator.create(DataService::class.java)
    suspend fun getData(pageId: Int): DemoData {
        return network.getData(pageId)
    }
}