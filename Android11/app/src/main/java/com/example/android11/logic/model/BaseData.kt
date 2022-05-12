package com.example.android11.logic.model

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/30
 */

/*
    "code": 114,
    "message": "OK",
    "data": { }
 */
data class BaseData<T>(val `data`: T, val code: Int, val message: String)
