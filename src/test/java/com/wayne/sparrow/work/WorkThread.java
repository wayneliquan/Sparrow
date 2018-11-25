package com.wayne.sparrow.work;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WorkThread extends Thread {
    private final List<Worker> workerList = Collections.synchronizedList(new ArrayList<Worker>());
    private int workNum = 0;

    public void addWorker(Worker worker) {
        synchronized (workerList) {
            workerList.add(worker);
            workNum ++ ;
        }
    }

    public void removeWorker(int num) {
        synchronized (workerList) {
            if (num >= this.workNum) {
                throw new IllegalArgumentException("超过了已有的线程数量");
            }
            for (int i = 0; i < num; i++) {
                Worker worker = workerList.get(i);
                if (worker != null) {
                    worker.shutdown();
                    workerList.remove(i);
                }
            }
            this.workNum -= num;
        }
    }
}