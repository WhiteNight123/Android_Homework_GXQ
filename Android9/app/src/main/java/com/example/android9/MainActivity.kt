package com.example.android9

import android.content.Context
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.example.android9.logic.model.DatasBean
import com.example.android9.ui.data.DataViewModel
import com.example.android9.ui.theme.Android9Theme
import com.example.android9.ui.theme.Purple700
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MainActivity : ComponentActivity() {
    private val demoViewModel by viewModels<DataViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Android9Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavController1(demoViewModel = demoViewModel)
                }
            }
        }
    }


}


@Composable
fun Wenda(demoViewModel: DataViewModel, navController: NavController) {
    val data = demoViewModel.data1.collectAsLazyPagingItems()
    when (data.loadState.refresh) {
        is LoadState.NotLoading -> LazyColumn {
            itemsIndexed(data) { _, datasBean ->
                WendaDetail(datasBean!!, navController)
            }
            when (data.loadState.append) {
                is LoadState.NotLoading -> itemsIndexed(data) { _, datasBean ->
                    WendaDetail(datasBean!!, navController)
                }
                is LoadState.Error -> item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(onClick = { data.retry() }) {
                            Text(text = "重试")
                        }
                    }
                }
                LoadState.Loading -> item {
                    LoadingPage()
                }
            }
        }
        is LoadState.Error -> ErrorPage { data.refresh() }
        LoadState.Loading -> LoadingPage()
    }

}

@Composable
fun NavController1(demoViewModel: DataViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "first") {
        composable("first") { Wenda(demoViewModel = demoViewModel, navController) }
        composable(
            "second/{url}",
            arguments = listOf(navArgument("url") { type = NavType.StringType }),

            ) { backStackEntry -> WebView1(navController, backStackEntry) }
    }
}

@Composable
fun WendaDetail(datasBean: DatasBean, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
            .clickable {
                navController.navigate(
                    "second/${
                        URLEncoder.encode(
                            datasBean.link,
                            StandardCharsets.UTF_8.toString()
                        )
                    }"
                )
            }
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = datasBean.author,
                style = MaterialTheme.typography.body2
            )
            Text(
                modifier = Modifier.padding(end = 8.dp),
                text = datasBean.niceDate,
                style = MaterialTheme.typography.body2
            )
        }
        Text(text = datasBean.title, style = MaterialTheme.typography.subtitle2)
//        AndroidView(factory = { context ->
//            TextView(context).apply {
//                setText(
//                    HtmlCompat.fromHtml(
//                        datasBean.desc,
//                        HtmlCompat.FROM_HTML_MODE_COMPACT
//                    )
//                )
//                maxLines=3
//            }
//        })
        Text(text = datasBean.desc, maxLines = 4, style = MaterialTheme.typography.body2)
    }

}

@Composable
fun ErrorPage(onclick: () -> Unit = {}) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(300.dp, 180.dp),
            painter = painterResource(id = R.drawable.ic_neterror),
            contentDescription = "网络问题",
            contentScale = ContentScale.Crop
        )
        Button(modifier = Modifier.padding(8.dp), onClick = onclick) {
            Text(text = "网络不佳,点击重试")
        }
    }
}

@Composable
fun LoadingPage(context: Context = LocalContext.current) {
    val animate by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(500, easing = LinearEasing), RepeatMode.Restart)
    )
    val radius = context.dp2px(80f)
    Canvas(modifier = Modifier.fillMaxSize()) {
        translate(size.width / 2 - radius, size.height / 2 - radius) {
            drawArc(
                Purple700,
                0f,
                animate, false, size = Size(radius * 2f, radius * 2f),
                style = Stroke(context.dp2px(4f)),
                alpha = 0.6f
            )
        }
    }
}

fun Context.dp2px(dp: Float): Float {
    val density = resources.displayMetrics.density
    return dp * density + 0.5f
}

@Composable
fun WebView1(navController: NavController, navBackStackEntry: NavBackStackEntry) {
    val url = navBackStackEntry.arguments?.getString("url")
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            val webView = WebView(context)
            webView.settings.javaScriptEnabled = true
            webView.settings.javaScriptCanOpenWindowsAutomatically = true
            webView.settings.domStorageEnabled = true
            webView.settings.loadsImagesAutomatically = true
            webView.settings.mediaPlaybackRequiresUserGesture = false
            webView.webViewClient = WebViewClient()
            webView.loadUrl(url!!)
            webView
        })
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Android9Theme {

    }
}