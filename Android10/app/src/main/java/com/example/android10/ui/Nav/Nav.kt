package com.example.android10.ui.page

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.android10.ui.Nav.HomeTabs
import com.example.android10.ui.page.HeroList.HeroList
import com.example.android10.ui.page.HeroList.HeroListViewModel
import com.example.android10.ui.page.home.Home
import com.example.android10.ui.page.home.HomeViewModel
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/25
 */

@Composable
fun Nav() {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val heroListViewModel: HeroListViewModel = hiltViewModel()

    val (selectedTab, setSelectedTab) = remember { mutableStateOf(HomeTabs.SEARCH_PAGE) }
    val tabs = HomeTabs.values()
    val nacController = rememberNavController()
    Scaffold(bottomBar = {
        BottomNavigation(Modifier.navigationBarsHeight(56.dp)) {
            tabs.forEach { tab ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = tab.icon),
                            contentDescription = null
                        )
                    },
                    label = { Text(tab.title) },
                    selected = tab == selectedTab,
                    onClick = { setSelectedTab(tab) },
                    alwaysShowLabel = false,
                    modifier = Modifier.navigationBarsPadding()
                )
            }
        }
    }) { innerPadding ->

        val modifier = Modifier.padding(innerPadding)
        when (selectedTab) {
            HomeTabs.SEARCH_PAGE -> Home(homeViewModel = homeViewModel)
            HomeTabs.MORE_HERO -> HeroList(heroListViewModel)
        }

    }
}