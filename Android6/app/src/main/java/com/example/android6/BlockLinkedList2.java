package com.example.android6;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ...
 * Lock
 *
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/3/16
 */
public class BlockLinkedList2<E> {
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    private final int MAX = 10;
    private int num = 0;
    private final LinkedList<E> list = new LinkedList<>();

    public void put(E e) {
        lock.lock();
        try {
            while (num == MAX) {
                try {
                    notFull.await();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
            list.addLast(e);
            System.out.println(Thread.currentThread().getName() + "放入了元素" + e);
            num++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public E take() {
        lock.lock();
        try {
            while (num == 0) {
                try {
                    notEmpty.await();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
            E e = list.removeFirst();
            num--;
            System.out.println(Thread.currentThread().getName() + "取出了元素" + e);
            notFull.signal();
            return e;
        } finally {
            lock.unlock();
        }
    }
}