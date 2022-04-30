package com.example.android10.ui.page.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.android10.logic.model.HeroPower
import com.example.android10.logic.model.HeroPowerDetail
import com.example.android10.ui.MyState
import com.example.android10.ui.page.lce.LcePage

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/23
 */


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Home(homeViewModel: HomeViewModel) {
    val searchHero = remember {
        mutableStateOf("")
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            val keyboard = LocalSoftwareKeyboardController.current
            OutlinedTextField(
                value = searchHero.value,
                onValueChange = { searchHero.value = it },
                label = {
                    Text(
                        text = "请输入要查询的英雄"
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    homeViewModel.getData(searchHero.value)
                    keyboard?.hide()
                })
            )
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = {
                    homeViewModel.getData(searchHero.value)
                    keyboard?.hide()
                }) {
                Text(text = "搜索")
            }
        }
        val heroDataModels by homeViewModel.heroState.observeAsState(null)
        heroDataModels?.let { HeroDetails(it) }
    }

}

@Composable
fun HeroDetails(state: MyState<HeroPower>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LcePage(myState = state, onErrorClick = { /*TODO*/ }) { data ->
            HeroDetails2(data = data.data)
        }
    }
}

@Composable
fun HeroDetails2(data: HeroPowerDetail) {
    Text(
        text = "战力查询结果", style = TextStyle(
            color = Color.Blue,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
        )
    )
    Image(painter = rememberImagePainter(data.photo), contentDescription = null)
    Text(text = "英雄 :${data.name}")
    Text(text = "平台 :${data.platform}")
    Text(text = "县标 :${data.area}(${data.areaPower})")
    Text(text = "市标 :${data.city}(${data.cityPower})")
    Text(text = "省标 :${data.province}(${data.provincePower})")
    Text(text = "更新时间 :${data.updatetime}")

}
