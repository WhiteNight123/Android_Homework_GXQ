package com.example.android8

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.view.animation.LinearInterpolator
import com.example.android8.state.CompletedStatus
import com.example.android8.state.NotStartedStatus
import com.example.android8.state.PausedStatus
import com.example.android8.state.StartedStatus

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/4/5
 */
const val SPEED = 100

class AnimatorController(private val viewModel: TimerViewModel) {
    private var valueAnimator: ValueAnimator? = null
    fun start() {
        if (viewModel.totalTime == 0L) return
        if (valueAnimator == null) {
            valueAnimator = ValueAnimator.ofInt(viewModel.totalTime.toInt() * SPEED, 0)
            valueAnimator?.interpolator = LinearInterpolator()
            valueAnimator?.addUpdateListener {
                viewModel.animValue = (it.animatedValue as Int) / SPEED.toFloat()
                viewModel.timeLeft = (it.animatedValue as Int).toLong() / SPEED
            }
            valueAnimator?.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    complete()
                }
            })
        } else {
            valueAnimator?.setIntValues(viewModel.totalTime.toInt() * SPEED, 0)
        }
        valueAnimator?.duration = viewModel.totalTime * 1000L
        valueAnimator?.start()
        viewModel.status = StartedStatus(viewModel)
    }

    fun pause() {
        valueAnimator?.pause()
        viewModel.status = PausedStatus(viewModel)
    }

    fun resume() {
        valueAnimator?.resume()
        viewModel.status = StartedStatus(viewModel)
    }

    fun stop() {
        valueAnimator?.cancel()
        viewModel.timeLeft = 0
        viewModel.status = NotStartedStatus(viewModel)
    }

    fun complete() {
        viewModel.totalTime = 0
        viewModel.status = CompletedStatus(viewModel)
    }
}