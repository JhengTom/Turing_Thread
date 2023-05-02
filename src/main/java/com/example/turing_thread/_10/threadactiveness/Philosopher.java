package com.example.turing_thread._10.threadactiveness;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Fox
 * 哲学家
 */
@Slf4j
public class Philosopher extends Thread {

    private Chopstick left;
    private Chopstick right;

    private static Lock lock = new ReentrantLock();

    public Philosopher(String name, Chopstick left, Chopstick right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    public void eat() {
        log.debug("eating...");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void think() {
        log.debug("thinking...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {

            // 获得左手筷子
            lock.lock();//配合解法 ReentrentLock+Condition
            synchronized (left) {
                log.debug("获得左手筷子" + left.getNumber());
                // 获得右手筷子
                synchronized (right) {
                    log.debug("获得右手筷子" + right.getNumber());
                    // 吃饭
                    eat();
                }
                // 放下右手筷子
            }
            lock.unlock();//配合解法 ReentrentLock+Condition
            // 放下左手筷子
            log.debug("吃完了，把筷子放回了原处，开始thinking");
            think();
        }
    }

    // 思考： 如何用ReentrentLock+Condition实现

}
