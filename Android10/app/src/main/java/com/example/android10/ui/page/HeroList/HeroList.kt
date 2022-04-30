package com.example.android10.ui.page.HeroList

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.compose.rememberImagePainter
import com.example.android10.R
import com.example.android10.logic.model.HeroListDetail
import com.example.android10.ui.theme.Android10Theme

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/25
 */
@Composable
fun HeroList(heroListViewModel: HeroListViewModel) {
    val heroList = heroListViewModel.heroListResult.collectAsLazyPagingItems()
    LazyColumn {
        itemsIndexed(heroList) { _, data ->
            com.example.android10.ui.page.HeroList.HeroListDetail(heroListDetail = data!!)
            Log.d("TAG", "HeroList: ${data.toString()}")
        }
    }

}

@Composable
fun HeroListDetail(heroListDetail: HeroListDetail) {
    Row(
        modifier = Modifier.fillMaxHeight().padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(48.dp, 48.dp),
            painter = rememberImagePainter(heroListDetail.iconUrl, builder = {
                placeholder(R.drawable.wang_zhe)//占位图
                crossfade(true)
            }),
            contentDescription = null
        )
        Column(modifier = Modifier
            .weight(1f)
            .padding(start = 8.dp)) {
            Text(text = heroListDetail.title)
            Text(text = heroListDetail.cname)
        }
    }
}

@Preview
@Composable
fun a() {
    Android10Theme() {

    }
}