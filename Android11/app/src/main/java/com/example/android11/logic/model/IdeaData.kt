package com.example.android11.logic.model

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/30
 */

/*
{
    "id": 1,
    "name": "四时节气",
    "image": "https://md.udday.cn/danqing/temp/jq.png"
}
 */
data class IdeaData(val id: Int, val name: String, val image: String)

/*
{
    "title": "立夏",
    "image": "https://md.udday.cn/danqing/temp/zx.png",
    "intro": "暂无介绍",
    "colors":{ }
}
 */
data class IdeaDetailData(
    val title: String,
    val image: String,
    val intro: String,
    val colors: ColorsData,
    val shades: ShadeListData
)

/*
{
    "color_1": { }
    "color_2": { }
    "color_3": { }
    "color_4": { }
    "color_5": { }
    "color_6": { }
    "color_7": { }
}
 */
data class ColorsData(
    val color_1: ColorDetailData,
    val color_2: ColorDetailData,
    val color_3: ColorDetailData,
    val color_4: ColorDetailData,
    val color_5: ColorDetailData,
    val color_6: ColorDetailData,
    val color_7: ColorDetailData
)

/*
{
    "id": 107,
    "name": "溶溶月",
    "hex": "BEC2BC",
    "r": 190,
    "g": 194,
    "b": 188,
    "c": 2,
    "m": 3,
    "k": 24,
    "y": 0
}
 */
data class ColorDetailData(
    val id: Int,
    val name: String,
    val hex: String,
    val r: Int,
    val g: Int,
    val b: Int,
    val c: Int,
    val m: Int,
    val k: Int,
    val y: Int
)

/*
{
    "shade_list": [ ]
}
 */
data class ShadeListData(val shade_list: List<ShadeListDetailData>)

/*
{
    "shade": [ ]
 */
data class ShadeListDetailData(val shade: List<ShadeDetailData>)

/*
{
    "color": { }
}
 */
data class ShadeDetailData(val color: ColorDetailData)

