package com.example.android6

import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var progressBar1: ProgressBar
    private lateinit var progressBar2: ProgressBar
    private lateinit var progressBar3: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.StartAll_TV)
        progressBar1 = findViewById(R.id.ProgressBar1)
        progressBar2 = findViewById(R.id.ProgressBar2)
        progressBar3 = findViewById(R.id.ProgressBar3)
        textView.setOnClickListener {
            val fixedThreadPool = Executors.newFixedThreadPool(3)
            val runnable1 = Runnable {
                try {
                    for (i in 0..100 step 2) {
                        Thread.sleep(200)
                        progressBar1.progress = i
                        Log.d("当前线程：", Thread.currentThread().name)
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            val runnable2 = Runnable {
                try {
                    for (i in 0..100 step 2) {
                        Thread.sleep(200)
                        progressBar2.progress = i
                        Log.d("当前线程：", Thread.currentThread().name)
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            val runnable3 = Runnable {
                try {
                    for (i in 0..100 step 2) {
                        Thread.sleep(200)
                        progressBar3.progress = i
                        Log.d("当前线程：", Thread.currentThread().name)
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            fixedThreadPool.execute(runnable1)
            fixedThreadPool.execute(runnable2)
            fixedThreadPool.execute(runnable3)
        }
    }
}