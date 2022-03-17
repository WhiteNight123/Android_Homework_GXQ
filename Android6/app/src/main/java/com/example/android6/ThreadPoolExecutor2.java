package com.example.android6;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ...
 *
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/3/17
 */
public class ThreadPoolExecutor2 {
    private final ArrayList<Worker> workers = new ArrayList<>();
    private final LinkedList<Runnable> list = new LinkedList<>();
    private final int threadNum;
    private int workNum;
    private volatile boolean isShutDown;
    private final ReentrantLock mainLock = new ReentrantLock();

    public ThreadPoolExecutor2(int threadNum) {
        this.threadNum = threadNum;
        this.workNum = 0;
    }

    void execute(Runnable runnable) {
        if (isShutDown) {
            throw new RuntimeException("线程池已经关闭");
        }
        try {
            mainLock.lock();
            if (workNum < threadNum) {
                Worker thread = new Worker(runnable);
                thread.start();
                workers.add(thread);
                workNum++;
            } else {
                if (!list.offer(runnable)) {
                    System.out.println("队列已满");
                }
            }

        } finally {
            mainLock.unlock();
        }
    }

    void shutDown() {
        for (Worker worker : workers) {
            worker.interrupt();
        }
        isShutDown = true;
    }

    class Worker extends Thread {
        Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                if (task != null) {
                    task.run();
                    task = null;
                } else {
                    //当前线程处理完自己的了，就会处理队列中的任务
                    Runnable poll = list.poll();
                    if (poll != null) {
                        poll.run();
                    }
                }
            }
        }
    }
}