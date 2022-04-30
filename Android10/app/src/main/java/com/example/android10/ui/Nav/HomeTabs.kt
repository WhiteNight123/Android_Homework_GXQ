package com.example.android10.ui.Nav

import androidx.annotation.DrawableRes
import com.example.android10.R

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/25
 */
enum class HomeTabs(
    val title: String,
    @DrawableRes val icon: Int
) {
    SEARCH_PAGE("搜索", R.drawable.ic_search),
    MORE_HERO("更多", R.drawable.ic_star)
}
