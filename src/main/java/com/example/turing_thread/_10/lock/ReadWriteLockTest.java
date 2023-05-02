package com.example.turing_thread._10.lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Fox
 */

public class ReadWriteLockTest {

    public static void main(String[] args) throws InterruptedException {
        final Queue queue = new Queue();


        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    queue.put(new Random().nextInt(10000));
                }
            }).start();
        }

        Thread.sleep(100);

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                        queue.get();
                }
            }).start();
        }


    }
}

@Slf4j
class Queue {
    //共享數據，只能有一個線程能寫該數據，但可以有多個線程同時讀該數據。
    private Object data = null;
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock readLock = readWriteLock.readLock();
    private Lock writeLock = readWriteLock.writeLock();

    public void get() {
        log.debug(Thread.currentThread().getName() + " be ready to read data!");
        //上讀鎖，其他線程只能讀不能寫
        readLock.lock();
        try {
            Thread.sleep(1000);
            log.debug(Thread.currentThread().getName() + " have read data :" + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //釋放讀鎖
            readLock.unlock();
        }
    }

    public void put(Object data) {
        log.debug(Thread.currentThread().getName() + " be ready to write data!");
        //上寫鎖，不允許其他線程讀也不允許寫
        writeLock.lock();
        try {
            Thread.sleep(5000);
            this.data = data;
            log.debug(Thread.currentThread().getName() + " have write data: " + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //釋放寫鎖
            writeLock.unlock();
        }
    }
}