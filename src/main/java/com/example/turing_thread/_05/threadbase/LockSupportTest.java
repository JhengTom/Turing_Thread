package com.example.turing_thread._05.threadbase;

import java.util.concurrent.locks.LockSupport;

import static java.lang.Thread.sleep;

public class LockSupportTest {

    public void main(String[] args) {
        Thread parkThread = new Thread(new ParkThread());
        parkThread.start();

        System.out.println("唤醒parkThread");
        //为指定线程parkThread提供“许可”
        LockSupport.unpark(parkThread);
    }

    static class ParkThread implements Runnable{

        @Override
        public void run() {
            System.out.println("ParkThread开始执行");
            // 等待“许可”
            LockSupport.park();
            System.out.println("ParkThread执行完成");
        }
    }
}