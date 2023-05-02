package com.example.turing_thread._09;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Fox
 * 鎖超時
 */
@Slf4j
public class ReentrantLockDemo4 {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {

            log.debug("t1啟動...");
            // 注意： 即使是設置的公平鎖，此方法也會立即返回獲取鎖成功或失敗，公平策略不生效
//            if (!lock.tryLock()) {
//                log.debug("t1獲 取鎖失敗，立即返回false");
//                return;
//            }

            //超時
            try {
                if (!lock.tryLock(1, TimeUnit.SECONDS)) {
                    log.debug("等待 1s 后獲取鎖失敗，返回");
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }

            try {
                log.debug("t1獲得了鎖");
            } finally {
                lock.unlock();
            }

        }, "t1");


        lock.lock();
        try {
            log.debug("main線程獲得了鎖");
            t1.start();
            //先讓線程t1執行
            Thread.sleep(2000);
        } finally {
            lock.unlock();
        }

    }

}
