package com.example.android8.state

import com.example.android8.TimerViewModel

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/5
 */
class NotStartedStatus(private val viewModel: TimerViewModel) : IStatus {
    override fun startButtonDisplayString(): String = "Start"
    override fun clickStartButton() = viewModel.animatorController.start()
    override fun stopButtonEnabled(): Boolean = false
    override fun clickStopButton() {}
    override fun showEditText(): Boolean = true
    override fun progressSweepAngle(): Float = if (viewModel.totalTime > 0) 360f else 0f
    override fun completedString() = ""
}