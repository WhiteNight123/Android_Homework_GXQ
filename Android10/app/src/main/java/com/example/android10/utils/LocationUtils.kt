package com.example.android10.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.*
import android.os.Build
import android.os.CancellationSignal
import android.os.Looper
import android.util.Log
import com.example.android10.ui.page.home.HomeViewModel
import java.util.*
import java.util.function.Consumer

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/23
 */
@SuppressLint("MissingPermission")
fun getLocation(context: Context, homeViewModel: HomeViewModel) {
    val locationManager: LocationManager?
    val locationProvider: String?
    //1.获取位置管理器
    locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    //2.获取位置提供器，GPS或是NetWork
    val providers: List<String> = locationManager.getProviders(true)
    Log.e("getLocation", "getLocation: $providers")
    when {
        providers.contains(LocationManager.NETWORK_PROVIDER) -> {
            locationProvider = LocationManager.NETWORK_PROVIDER
            Log.d("getLocation", "getLocation: 网络定位")
        }
        providers.contains(LocationManager.GPS_PROVIDER) -> {
            locationProvider = LocationManager.GPS_PROVIDER
            Log.d("getLocation", "getLocation: GPS定位")
        }
        else -> {
            Log.e("getLocation", "getLocation: 没有位置提供器")
            return
        }
    }
    //3.获取当前位置，R以上的版本需要使用getCurrentLocation

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        locationManager.getCurrentLocation(
            locationProvider,
            CancellationSignal(),
            context.mainExecutor,
            Consumer { location -> getAddress(context, location, homeViewModel) })
    } else {
        locationManager.requestSingleUpdate(
            locationProvider,
            LocationListener { location ->
                getAddress(context, location, homeViewModel)
            }, Looper.getMainLooper()
        )

    }
}

//获取地址信息:城市、街道等信息
private fun getAddress(
    context: Context,
    location: Location?,
    homeViewModel: HomeViewModel,
): List<Address?>? {
    Log.e(
        "getAddress", "获取当前位置-经纬度：" + location?.longitude
            .toString() + "   " + location?.latitude
    )
    var result: List<Address?>? = null
    if (location == null) return result
    val gc = Geocoder(context, Locale.getDefault())
    result = gc.getFromLocation(location.latitude, location.longitude, 1)
    //homeViewModel.updateCityInfo(location, result)
    Log.e("getAdress", "getAddress: $result")
    return result
}