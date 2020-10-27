package com.liuyang19900520.layman.starter.common.logger;

import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 日志管理器
 *
 * @author Max Liu
 * @date 2017-03-30 16:29
 */
public class LogManager {

    /**
     * 异步操作记录日志的线程池
     */
    private final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10, new ThreadFactory() {
        private final AtomicInteger atoInteger = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setName("Logger-Thread " + atoInteger.getAndIncrement());
            return t;
        }
    });


    public static LogManager logManager = new LogManager();

    public static LogManager getInstance() {
        return logManager;
    }

    /**
     * 执行日志任务
     *
     * @param task 执行日志任务
     */
    public void executeLog(TimerTask task) {
        executor.schedule(task, 10, TimeUnit.MILLISECONDS);
    }
}
