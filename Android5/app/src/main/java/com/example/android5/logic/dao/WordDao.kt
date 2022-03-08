package com.example.android5.logic.dao

import android.content.Context
import android.content.res.AssetManager
import androidx.core.content.edit
import com.example.android5.ReciteWordApplication
import com.example.android5.logic.model.WordResponse
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader


/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/3/8
 */
object WordDao {
    fun saveWord(word: WordResponse) {
        sharedPreferences().edit() {
            putString("word", Gson().toJson(word))
        }
    }

    fun getSavedWord(): WordResponse {
        val wordJson = sharedPreferences().getString("word", "")
        return Gson().fromJson(wordJson, WordResponse::class.java)
    }

    fun isWordSaved() = sharedPreferences().contains("word")
    private fun sharedPreferences() =
        ReciteWordApplication.context.getSharedPreferences("word_response", Context.MODE_PRIVATE)


    fun getWord(query: String): WordResponse {

        val stringBuilder = StringBuilder()
        //获得assets资源管理器
        val assetManager: AssetManager = ReciteWordApplication.context.getAssets()
        val buffReader =
            BufferedReader(InputStreamReader(assetManager.open("$query.json"), "utf-8"))
        var line: String?
        do {
            line = buffReader.readLine()
            if (line != null) {
                stringBuilder.append(line)
            } else {
                break
            }
        } while (true)
        buffReader.close()

        val wordJson = stringBuilder.toString()
        return Gson().fromJson(wordJson, WordResponse::class.java)
    }
}