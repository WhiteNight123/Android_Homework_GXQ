package com.example.android6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * ...
 *
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/3/17
 */
public class ThreadPoolExecutor1 {
    private static final int MAX_WORKER_NUMBERS = 30;
    private static final int DEFAULT_WORKER_NUMBERS = 5;
    private final LinkedList<Runnable> list = new LinkedList();
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
    private final int workerNum;

    public ThreadPoolExecutor1(int num) {
        if (num > MAX_WORKER_NUMBERS) {
            this.workerNum = DEFAULT_WORKER_NUMBERS;
        } else {
            this.workerNum = num;
        }
        initializeWorkers(workerNum);
    }

    /**
     * 初始化每个工作者线程
     */
    private void initializeWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker);
            thread.start();
        }
    }

    public void execute(Runnable runnable) {
        if (runnable == null) {
            throw new NullPointerException();
        }
        if (runnable != null) {
            synchronized (list) {
                list.addLast(runnable);
                list.notify();
            }
        }
    }

    public void shutdown() {
        for (Worker worker : workers) {
            worker.shutdown();
        }
    }

    public int getJobSize() {
        return workers.size();
    }

    class Worker implements Runnable {
        // 表示是否运行该worker
        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                Runnable runnable;
                synchronized (list) {
                    if (list.isEmpty()) {
                        try {
                            list.wait();
                        } catch (InterruptedException e) {
                            //外部对该线程的中断操作
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    // 取出一个runnable
                    runnable = list.removeFirst();
                }
                //执行runnable
                if (runnable != null) {
                    runnable.run();
                }
            }
        }

        public void shutdown() {
            running = false;
        }
    }
}
