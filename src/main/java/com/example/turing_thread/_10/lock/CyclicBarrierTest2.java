package com.example.turing_thread._10.lock;

import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 柵欄與閉鎖的關鍵區別在於，所有的線程必須同時到達柵欄位置，才能繼續執行。
 */
public class CyclicBarrierTest2 {

    //保存每個學生的平均成績
    private ConcurrentHashMap<String, Integer> map=new ConcurrentHashMap<String,Integer>();

    private ExecutorService threadPool= Executors.newFixedThreadPool(3);

    private CyclicBarrier cb=new CyclicBarrier(3,()->{
        int result=0;
        Set<String> set = map.keySet();
        for(String s:set){
            result+=map.get(s);
        }
        System.out.println("三人平均成績為:"+(result/3)+"分");
    });


    public void count(){
        for(int i=0;i<3;i++){
            threadPool.execute(new Runnable(){

                @Override
                public void run() {
                    //獲取學生平均成績
                    int score=(int)(Math.random()*40+60);
                    map.put(Thread.currentThread().getName(), score);
                    System.out.println(Thread.currentThread().getName()
                            +"同學的平均成績為："+score);
                    try {
                        //執行完運行await(),等待所有學生平均成績都計算完畢
                        cb.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }

            });
        }
    }


    public static void main(String[] args) {
        CyclicBarrierTest2 cb=new CyclicBarrierTest2();
        cb.count();
    }
}

