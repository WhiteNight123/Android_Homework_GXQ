package com.example.android5.logic

import androidx.lifecycle.liveData
import com.example.android5.logic.network.WordNetwork
import kotlinx.coroutines.Dispatchers

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/3/7
 */
object Repository {
    fun searchWord(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val wordResponse = WordNetwork.searchWord(query)
            if (wordResponse.word != null) {
                Result.success(wordResponse)
            } else {
                Result.failure(RuntimeException("Couldn't find the word"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }

}