package com.lyflexi.threadpoolpractice.exception;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: lyflexi
 * @project: java-thread-practice
 * @Date: 2024/11/10 21:23
 */
@Slf4j
public class ThreadPoolTaskExceptionSample {
    /**
     * 控制台默认没有打印异常信息
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        pool.submit(() -> {

            log.debug("task");
            int i = 1 / 0;
          
        });
    }
}
