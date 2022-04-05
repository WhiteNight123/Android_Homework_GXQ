package com.example.android8

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.android8.state.CompletedStatus
import com.example.android8.state.IStatus
import com.example.android8.state.NotStartedStatus

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/5
 */
const val MAX_INPUT_LENGTH = 5

class TimerViewModel : ViewModel() {
    var totalTime: Long by mutableStateOf(0)
    var timeLeft: Long by mutableStateOf(0)
    var animValue: Float by mutableStateOf(0.0f)
    var animatorController = AnimatorController(this)
    var status: IStatus by mutableStateOf(NotStartedStatus(this))
    fun updateValue(text: String) {
        if (text.length > MAX_INPUT_LENGTH) return
        var value = text.replace("\\D".toRegex(), "")
        if (value.startsWith("0")) value = value.substring(1)
        if (value.isBlank()) value = "0"
        totalTime = value.toLong()
        timeLeft = value.toLong()
        if (status is CompletedStatus) status = NotStartedStatus(this)
    }
}