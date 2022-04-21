package com.example.android10.logic.model

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/20
 */
/*
    "code":200,
    "data":Object{...},
    "msg":"若无杀父之仇 请勿攻击本站"
 */
data class Data(val data: DataDetail)

/*{
        "uid":"167",
        "name":"孙悟空",
        "alias":"齐天大圣-孙悟空",
        "platform":"安卓-扣扣区",
        "photo":"https:\/\/game.gtimg.cn\/images\/yxzj\/img201606\/heroimg\/167\/167.jpg",
        "area":"安平县",
        "areaPower":"3171",
        "city":"嘉峪关市",
        "cityPower":"4434",
        "province":"天津市",
        "provincePower":"5544",
        "stamp":"1650417052",
        "updatetime":"2022\/04\/20 09:10:52",
        "clientIP":"125.86.164.57"
    }
 */
data class DataDetail(
    val name: String,
    val platform: String,
    val photo: String,
    val area: String,
    val areaPower: Int,
    val city: String,
    val cityPower: Int,
    val province: String,
    val provincePower: Int,
    val updatetime: String,
)