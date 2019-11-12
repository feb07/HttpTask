package com.feb.httptask;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lilichun
 * createDate: 2019-11-12
 */
public class ThreadPoolManager {

    private static ThreadPoolManager instance = new ThreadPoolManager();

    private ThreadPoolExecutor threadPoolExecutor;

    private LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

    public static synchronized ThreadPoolManager getInstance() {
        return instance;
    }

    private ThreadPoolManager() {
        threadPoolExecutor = new ThreadPoolExecutor(10, 20,
                15, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5), rejectedExecutionHandler);
        threadPoolExecutor.execute(runnable);
    }

    private RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                queue.put(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                Runnable runnable = null;
                try {
                    runnable = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (runnable != null) {
                    threadPoolExecutor.execute(runnable);
                }
            }
        }
    };

    public void execute(Runnable runnable) {
        if (runnable == null) return;
        try {
            queue.put(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
