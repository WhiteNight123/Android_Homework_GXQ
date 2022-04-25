package com.example.android10.ui.page.lce

import androidx.compose.runtime.Composable
import com.example.android10.ui.*

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/23
 */
@Composable
fun <T> LcePage(myState: MyState<T>, onErrorClick: () -> Unit, content: @Composable (T) -> Unit) =
    when (myState) {
        MyLoading -> {
            LoadingContent()
        }
        is MyNoContent -> {
            NoContent(tip = myState.reason)
        }
        is MyError -> {
            ErrorContent(onErrorClick = onErrorClick)
        }
        is MySuccess<T> -> {
            content(myState.data)
        }
    }