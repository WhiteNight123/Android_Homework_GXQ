package com.example.android6

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/3/17
 */
fun main() {
    val defaultThreadPool =
        ThreadPoolExecutor1(10)
    for (i in 0..50) {
        defaultThreadPool.execute {
            try {
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            println("111thread id is: " + Thread.currentThread().id + "--$i")
        }
    }

    val testThreadPoolExecutos = ThreadPoolExecutor2(10)
    for (i in 0..50) {
        testThreadPoolExecutos.execute {
            try {
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            println("222thread id is: " + Thread.currentThread().id + "--$i")
        }
    }
}
