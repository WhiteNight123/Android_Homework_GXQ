package com.example.android6;

import java.util.LinkedList;

/**
 * ...
 * synchronized+wait+notify
 *
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/3/17
 */
public class BlockLinkedList1<E> {
    private final LinkedList<E> list = new LinkedList<>();
    private static final int MAX = 10;
    private int num = 0;

    public void put(E e) throws Exception {
        synchronized (list) {
            if (num == MAX) {
                list.wait();
            }
            list.offer(e);
            num++;
            System.out.println(Thread.currentThread().getName() + "放入了元素" + e);
            list.notify();
        }
    }

    public E take() throws Exception {
        synchronized (list) {
            if (num == 0) {
                list.wait();
            }
            E e = list.poll();
            num--;
            System.out.println(Thread.currentThread().getName() + "取出了元素" + e);
            list.notify();
            return e;
        }
    }
}
