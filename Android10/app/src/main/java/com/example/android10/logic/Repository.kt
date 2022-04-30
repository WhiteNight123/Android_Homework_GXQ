package com.example.android10.logic

import android.accounts.NetworkErrorException
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.android10.logic.model.HeroList
import com.example.android10.logic.model.HeroPower
import com.example.android10.logic.net.DataApi
import com.example.android10.logic.net.ServiceCreator
import com.example.android10.ui.*
import com.example.android10.utils.checkNetConnect
import com.example.android10.utils.showToast


/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/20
 */
object Repository {
    val retrofit=ServiceCreator.create(DataApi::class.java)
    //suspend fun searchHero(name: String, type: String) = ServiceCreator.create(DataApi::class.java).searchHero(name, type)
    suspend fun searchHero(
        state: MutableLiveData<MyState<HeroPower>>,
        context: Context,
        name: String,
        type: String
    ) {
        if (!context.checkNetConnect()) {
            state.postValue(MyError(NetworkErrorException("网络未连接")))
            return
        }
        state.postValue(MyLoading)
        val dataResponse = retrofit.searchHero(name, type)
        if (dataResponse.code == 200) {
            state.postValue(MySuccess(dataResponse))
        } else {
            state.postValue(MyNoContent("没有数据"))
            showToast(context, "参数错误")
        }
    }

    suspend fun getHeroList() = retrofit.getHeroList()

}