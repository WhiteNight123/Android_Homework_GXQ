package com.example.android10.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat.Type.ime
import androidx.datastore.preferences.preferencesDataStore

/**
 * Context工具类
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/23
 */

//创建DataStore
val Context.dataStore by preferencesDataStore("Cookie")

/**
 * 判断网络状态
 * @return 如果wifi或数据可用返回ture
 */
fun Context.checkNetConnect(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    val networkCapabilities =
        connectivityManager?.getNetworkCapabilities(connectivityManager.activeNetwork)
    return when {
        networkCapabilities == null -> {
            false
        }
        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
            Log.d("Network", "checkNetConnect: 使用移动网络")
            true
        }
        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
            Log.d("NetWork", "checkNetConnect: 使用wifi网络")
            true
        }
        else -> {
            false
        }
    }
}

/**
 * 判断是否是深色模式
 * 深色模式值:0x21
 * 浅色模式值:0x11
 * @return 深色模式为true
 */
fun Context.isDarkMode(): Boolean {
    return resources.configuration.uiMode == 0x21
}

/**
 * 隐藏ime
 */
fun Activity?.hideIme() {
    if (this == null || window == null) return
    val controller = WindowCompat.getInsetsController(window, window.decorView)
    controller?.hide(ime())
}

/**
 * 显示ime
 */
fun Activity?.showIme() {
    if (this == null || window == null) return
    val controller = WindowCompat.getInsetsController(window, window.decorView)
    controller?.show(ime())
}
