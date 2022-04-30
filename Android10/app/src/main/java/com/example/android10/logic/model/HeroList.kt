package com.example.android10.logic.model

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/26
 */

/*
    "code":200,
    "data":Array[109]
 */
data class HeroList(val data: List<HeroListDetail>, val code: Int)

/*
{
    "ename":105,
    "cname":"廉颇",
    "title":"正义爆轰",
    "iconUrl":"https://game.gtimg.cn/images/yxzj/img201606/heroimg/105/105.jpg"
      }
 */
data class HeroListDetail(
    val ename: String,
    val cname: String,
    val title: String,
    val iconUrl: String
)