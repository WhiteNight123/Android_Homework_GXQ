package com.example.android8.state

import com.example.android8.TimerViewModel

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/5
 */
class CompletedStatus(private val viewModel: TimerViewModel) : IStatus {
    override fun startButtonDisplayString() = "Start"
    override fun clickStartButton() = viewModel.animatorController.start()
    override fun stopButtonEnabled() = false
    override fun clickStopButton() {}
    override fun showEditText() = true
    override fun progressSweepAngle() = 0f
    override fun completedString(): String = "Completed!"
}