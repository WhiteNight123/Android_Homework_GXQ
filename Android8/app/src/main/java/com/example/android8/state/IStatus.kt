package com.example.android8.state

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/5
 */
interface IStatus {
    fun startButtonDisplayString(): String
    fun clickStartButton()
    fun stopButtonEnabled(): Boolean
    fun clickStopButton()
    fun showEditText(): Boolean
    fun progressSweepAngle(): Float
    fun completedString(): String
}