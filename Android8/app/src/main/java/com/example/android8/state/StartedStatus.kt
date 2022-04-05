package com.example.android8.state

import com.example.android8.TimerViewModel

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/5
 */
class StartedStatus(private val viewModel: TimerViewModel) : IStatus {
    override fun startButtonDisplayString(): String = "Pause"
    override fun clickStartButton() = viewModel.animatorController.pause()
    override fun stopButtonEnabled(): Boolean = true
    override fun clickStopButton() = viewModel.animatorController.stop()
    override fun showEditText(): Boolean = false
    override fun progressSweepAngle(): Float = viewModel.animValue / viewModel.totalTime * 360
    override fun completedString() = ""

}