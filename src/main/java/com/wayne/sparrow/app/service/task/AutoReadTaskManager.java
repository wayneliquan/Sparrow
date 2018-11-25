package com.wayne.sparrow.app.service.task;

import groovy.lang.Singleton;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
@Singleton
public class AutoReadTaskManager {
    private ThreadPoolExecutor executor;
    private BlockingQueue<Runnable> workQueue;
    private int corePoolSize;
    private int maximumPoolSize;
    private Long keepAliveTime;
    public void init() {
        corePoolSize = 5;
        maximumPoolSize = 10;
        keepAliveTime = 10000L;
        workQueue = new LinkedBlockingQueue<>();
        executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);
    }

    public void addTask(Runnable task) {
        executor.execute(task);
    }
}
