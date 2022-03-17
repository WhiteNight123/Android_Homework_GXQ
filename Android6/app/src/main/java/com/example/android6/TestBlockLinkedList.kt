package com.example.android6

import java.util.concurrent.CyclicBarrier
import kotlin.concurrent.thread

/**
 *
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/3/16
 */
fun main() {
    val blockLinkedList1 = BlockLinkedList1<String>()
    val blockLinkedList2 = BlockLinkedList2<String>()
    //两个线程，都执行完成了打印
    val barrier = CyclicBarrier(2) {
        println(
            "-------结束了-------"
        )
    }

    thread {
        try {
            for (i in 0..49) {
                blockLinkedList1.put("——$i")
                blockLinkedList2.put("——$i")
            }
            //生产完了
            barrier.await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    thread {
        try {
            for (j in 0..49) {
                blockLinkedList1.take()
                blockLinkedList2.take()
            }
            //消费完了
            barrier.await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

