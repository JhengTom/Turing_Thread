package com.example.turing_thread._09;

import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Fox
 * 可中斷
 */
@Slf4j
public class ReentrantLockDemo3 {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {

            log.debug("t1啟動...");

            try {
                lock.lockInterruptibly();
                try {
                    log.debug("t1獲得了鎖");
                } finally {
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.debug("t1等鎖的過程中被中斷");
            }

        }, "t1");


        lock.lock();
        try {
            log.debug("main線程獲得了鎖");
            t1.start();
            //先讓線程t1執行
            Thread.sleep(1000);

            t1.interrupt();
            log.debug("線程t1執行中斷");
        } finally {
            lock.unlock();
        }

    }

}
