package com.example.android8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android8.ui.theme.Android8Theme
import com.example.android8.utils.TimeFormatUtils
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    private val viewModel: TimerViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Android8Theme {
                MyApp(viewModel)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.animatorController.stop()
    }
}

@Composable
fun MyApp(viewModel: TimerViewModel) {
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                }
            )
        },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CompleteText(viewModel = viewModel)
            TimeLeftText(viewModel = viewModel)
            ProgressCircle(viewModel = viewModel)
            EditText(viewModel = viewModel)
            Row {
                StartButton(viewModel = viewModel)
                StopButton(viewModel = viewModel)

            }

        }

    }
}

@Composable
private fun TimeLeftText(viewModel: TimerViewModel) {
    Text(
        text = TimeFormatUtils.formatTime(viewModel.timeLeft),
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
private fun EditText(viewModel: TimerViewModel) {
    Box(modifier = Modifier.size(300.dp, 120.dp), contentAlignment = Alignment.Center) {
        if (viewModel.status.showEditText()) {
            TextField(
                modifier = Modifier.size(200.dp, 60.dp),
                value = if (viewModel.totalTime == 0L) "" else viewModel.totalTime.toString(),
                onValueChange = viewModel::updateValue,
                label = { Text("Countdown Second") },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
    }
}

@Composable
private fun StartButton(viewModel: TimerViewModel) {
    Button(
        modifier = Modifier
            .width(150.dp)
            .padding(16.dp),
        enabled = viewModel.totalTime > 0,
        onClick = viewModel.status::clickStartButton
    ) {
        Text(text = viewModel.status.startButtonDisplayString())

    }
}

@Composable
private fun StopButton(viewModel: TimerViewModel) {
    Button(
        modifier = Modifier
            .width(150.dp)
            .padding(16.dp),
        enabled = viewModel.status.stopButtonEnabled(),
        onClick = viewModel.status::clickStopButton
    ) {
        Text(text = "Stop")
    }
}

@Composable
fun ProgressCircle(viewModel: TimerViewModel) {
    val size = 160.dp
    Box(contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(size)) {
            val sweepAngle = viewModel.status.progressSweepAngle()
            val r = size.toPx() / 2
            val stokeWidth = 12.dp.toPx()
            drawCircle(
                color = Color.LightGray,
                style = Stroke(
                    width = stokeWidth,
                    pathEffect = PathEffect.dashPathEffect(
                        intervals = floatArrayOf(
                            1.dp.toPx(),
                            3.dp.toPx()
                        )
                    )
                )
            )
            drawArc(
                brush = Brush.sweepGradient(
                    0f to Color.Magenta,
                    0.5f to Color.Blue,
                    0.75f to Color.Green,
                    0.75f to Color.Transparent,
                    1f to Color.Magenta
                ),
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(
                    width = stokeWidth
                ),
                alpha = 0.5f
            )
            val angle = (360 - sweepAngle) / 180 * Math.PI
            val pointTailLength = 8.dp.toPx()
            drawLine(
                color = Color.Red,
                start = Offset(
                    r + pointTailLength * sin(angle).toFloat(),
                    r + pointTailLength * cos(angle).toFloat()
                ),
                end = Offset(
                    (r - r * sin(angle) - sin(angle) * stokeWidth / 2).toFloat(),
                    (r - r * cos(angle) - cos(angle) * stokeWidth / 2).toFloat()
                ),
                strokeWidth = 2.dp.toPx()
            )
            drawCircle(
                color = Color.Red,
                radius = 5.dp.toPx()
            )
            drawCircle(
                color = Color.White,
                radius = 3.dp.toPx()
            )
        }
    }
}

@Composable
private fun CompleteText(viewModel: TimerViewModel) {
    Text(text = viewModel.status.completedString(), color = MaterialTheme.colors.primary)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Android8Theme {
    }
}