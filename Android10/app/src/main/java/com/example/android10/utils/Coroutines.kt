package com.example.android10.utils

import android.util.Log
import kotlinx.coroutines.Job

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/23
 */
fun Job?.checkCoroutines() {
    if (this?.isActive == true) return
    this?.cancel()
    Log.d("TAG", "checkCoroutines: 已在查询，先取消之前的协程")
}