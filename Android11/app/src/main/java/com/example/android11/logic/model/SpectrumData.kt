package com.example.android11.logic.model

import androidx.compose.ui.graphics.Color

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @date 2022/4/30
 */

/*
{
    "count": 7,
    "list": [ ]
}
 */
data class SpectrumData(val count: Int, val list: List<SpectrumListPageData>)

/*
{
    "id": 1,
    "theme": "立春"
}
 */
data class SpectrumListPageData(val id: Int, val theme: String)

/*
{
    "has_more": true,
    "color_list": [ ]
}
 */
data class SpectrumListData(val has_more: Boolean, val color_list: List<ColorDetailData>)

/*
{
    "intro": "暂无介绍",
    "colors": { }
    "shades": { }
}
 */
data class SpectrumDetailData(
    val intro: String,
    val colors: ColorsData,
    val shades: ShadeListData,
    var shadeList: MutableList<MutableList<Color>>
)

data class Query(var id: Int = -1)
