package com.lyflexi.threadpoolpractice.handsonpool_1988_v1;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * @Author: liuyanoutsee@outlook.com
 * @Date: 2025/4/4 15:12
 * @Project: java-thread-practice
 * @Version: 1.0.0
 * @Description:
 */
@Slf4j
public class SimpleThreadPool1 {

    /**
     * 任务队列
     */
    private final BlockingQueue<Runnable> workQueue;
    /**
     * 工作线程
     */
    private final List<Worker> workers = new ArrayList<>();

    /**
     * 构造器
     *
     * @param poolSize  线程数
     * @param workQueue 任务队列
     */
    public SimpleThreadPool1(int poolSize, BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;
        // 创建线程，并加入线程池
        for (int i = 0; i < poolSize; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            worker.start();
        }
    }

    /**
     * 提交任务
     *
     * @param command 待执行任务
     */
    public void execute(Runnable command) {
        try {
            // 任务队列满了则阻塞提交
            workQueue.put(command);
        } catch (InterruptedException e) {
            log.error("任务提交失败", e);
        }
    }

    /**
     * 工作线程，负责执行任务
     */
    private class Worker extends Thread {
        public void run() {
            // 循环获取任务，如果任务为空则阻塞等待
            while (true) {
                try {
                    Runnable task = workQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    log.error("任务获取失败", e);
                }
            }
        }
    }
}