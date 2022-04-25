package com.example.android10.utils

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import com.example.android10.ui.page.home.HomeViewModel
import com.example.android10.ui.page.widge.ShowDialog
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/23
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequiresLocationPermissions(homeViewModel: HomeViewModel) {
    val context = LocalContext.current
    val alertDialog = rememberSaveable {
        mutableStateOf(false)
    }
    val locationPermissionState = rememberMultiplePermissionsState(
        permissions = arrayListOf(
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )
    when {
        locationPermissionState.allPermissionsGranted -> {
            LaunchedEffect(Unit) {
                getLocation(context, homeViewModel)
            }
        }
        locationPermissionState.shouldShowRationale || !locationPermissionState.allPermissionsGranted -> {
            LaunchedEffect(Unit) {
                locationPermissionState.launchMultiplePermissionRequest()
            }
        }
        else -> {
            LaunchedEffect(Unit) {
                alertDialog.value = true
            }
            ShowDialog(
                alertDialog = alertDialog,
                title = "开启位置服",
                content = "这将意味着，我们会给您提供精准的位置服务，并且您将接受关于您订阅的位置信息",
                cancelString = "取消",
                confirmString = "打开设置"
            ) {
                startSettingAppDetail(context)
            }

        }

    }
}

fun startSettingAppDetail(context: Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    intent.data = Uri.parse("package:" + context.packageName)
    context.startActivity(intent)
}