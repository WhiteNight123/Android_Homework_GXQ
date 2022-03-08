package com.example.android5.logic.model

import com.google.gson.annotations.SerializedName

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/3/7
 */
data class WordResponse(
    val word: String,
    val accent: String,
    @SerializedName("mean_cn") val meanCn: String,
    @SerializedName("mean_en") val meanEn: String,
    val sentence: String,
    @SerializedName("sentence_trans") val sentenceTrans: String
)
