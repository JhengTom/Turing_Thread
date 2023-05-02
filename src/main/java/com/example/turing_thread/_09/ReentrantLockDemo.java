package com.example.turing_thread._09;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Fox
 * 同步執行
 */
public class ReentrantLockDemo {

    private static  int sum = 0;
    private static Lock lock = new ReentrantLock();
    //private static TulingLock lock = new TulingLock();

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(()->{
                //加鎖
                lock.lock();
                try {
                    // 臨界區代碼
                    // TODO 業務邏輯：讀寫操作不能保證線程安全
                    for (int j = 0; j < 10000; j++) {
                        sum++;
                    }
                } finally {
                    // 解鎖
                    lock.unlock();
                }
            });
            thread.start();
        }

        Thread.sleep(2000);
        System.out.println(sum);
    }
}
