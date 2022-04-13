package com.example.android9.logic.model

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/10
 */
/**
 * data : {...}
 * errorCode : 0
 * errorMsg :
 */
data class DemoData(val errorCode: Int, val errorMsg: String, val data: DataBean)

/**
 * curPage : 1
 * datas : [...]
 * offset : 0
 * over : false
 * pageCount : 6
 * size : 21
 * total : 119
 */
data class DataBean(
    val curPage: Int, val offset: Int, val over: Boolean,
    val pageCount: Int, val size: Int, val total: Int, val datas: List<DatasBean>
)

/**
 * apkLink :
 * audit : 1
 * author : xiaoyang
 * canEdit : false
 * chapterId : 440
 * chapterName : 官方
 * collect : false
 * courseId : 13
 * desc : <p>我们在写代码的时候，有时候很容易使用一个高版本的 API，如果不注意，可能会导致在一些低版本的设备崩溃。</p>
 * <p>因此，我们可以选择引入 lint 在编译时进行检查。</p>
 * <p>今天的问题是？</p>
 * <ol>
 * <li>应用：如何在打包时，强制开启 New API检查？</li>
 * <li>原理：lint 怎么能知道某个 方法是哪个版本加入的？是有一个汇总的地方维护着这样的方法列表吗？</li>
 * <li>原理：即使有这样的一个列表，lint是怎么扫描每一个方法调用的？</li>
 * </ol>
 * descMd :
 * envelopePic :
 * fresh : false
 * id : 15949
 * link : https://www.wanandroid.com/wenda/show/15949
 * niceDate : 2020-11-02 00:15
 * niceShareDate : 2020-11-02 00:14
 * origin :
 * prefix :
 * projectLink :
 * publishTime : 1604247343000
 * realSuperChapterId : 439
 * selfVisible : 0
 * shareDate : 1604247264000
 * shareUser :
 * superChapterId : 440
 * superChapterName : 问答
 * tags : [{"name":"本站发布","url":"/article/list/0?cid=440"},{"name":"问答","url":"/wenda"}]
 * title : 每日一问 | Call requires API level 23 (current min is 14) 扫描出来的原理是？
 * type : 1
 * userId : 2
 * visible : 1
 * zan : 4
 */
data class DatasBean(
    val id: Int,
    val niceDate: String,
    var author: String,
    val title: String,
    var desc: String,
    val link: String
)
