package com.example.android11.ui.page

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.android11.logic.model.ColorDetailData
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

/**
 *
 * @author :WhiteNight123(Guo Xiaoqiang)
 * @email 1448375249@qq.com
 * @date 2022/5/12
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun Spectrum() {
    val viewModel: SpectrumViewModel = hiltViewModel()
    val spectrumList = viewModel.spectrumList.collectAsLazyPagingItems()

    Column(Modifier.background(Color(200, 219, 185))) {
        val pagerState = rememberPagerState(0)
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
        )
        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }.collect { page ->
                Log.e("TESE", "Spectrum: $page+1")
                viewModel.getSpectrumList(page + 1)
            }
        }
        HorizontalPager(
            count = 7,
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            LazyColumn {
                items(spectrumList) { data ->
                    SpectrumListDetail(data!!)
                    //                        toDetail = { navController.navigate("${LingGanDestination.SPECTRUM_DETAIL_ROUTE}/$it") })
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpectrumListDetail(colorDetailData: ColorDetailData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                Log.e("TAG", "SpectrumListDetail: id=${colorDetailData.id}")
            }
    ) {
        Column(
            modifier = Modifier.background(
                Color(
                    red = colorDetailData.r,
                    green = colorDetailData.g,
                    blue = colorDetailData.b
                )
            )
        ) {
            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth(1f)) {
                Text(
                    text = colorDetailData.name,
                    fontSize = 32.sp,
                    modifier = Modifier.padding(top = 4.dp, end = 24.dp)
                )
            }

            Text(
                text = "Hex #${colorDetailData.hex}",
                color = Color(255, 255, 255, 85),
                modifier = Modifier.padding(start = 16.dp, top = 32.dp)
            )
            Text(
                color = Color(255, 255, 255, 80),
                text = "RGB ${colorDetailData.r},${colorDetailData.g},${colorDetailData.b}",
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                color = Color(255, 255, 255, 80),
                text = "CMYK ${colorDetailData.c},${colorDetailData.m},${colorDetailData.y},${colorDetailData.k}",
                modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
            )
        }
    }
}