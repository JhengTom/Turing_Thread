package com.example.turing_thread._15.forkjoin.arraysum;


import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import com.example.turing_thread._15.forkjoin.util.Utils;

/**
 * @author Fox
 *
 * 多線程計算1億個整數的和
 */
public class SumMultiThreads {
    //拆分的粒度
    public final static int NUM = 10000000;

    public static long sum(int[] arr, ExecutorService executor) throws Exception {
        long result = 0;
        int numThreads = arr.length / NUM > 0 ? arr.length / NUM : 1;
        int num = arr.length / numThreads;
        //任務分解
        SumTask[] tasks = new SumTask[numThreads];
        Future<Long>[] sums = new Future[numThreads];
        for (int i = 0; i < numThreads; i++) {
            tasks[i] = new SumTask(arr, (i * NUM),
                    ((i + 1) * NUM));
            sums[i] = executor.submit(tasks[i]);
        }
        //結果合併
        for (int i = 0; i < numThreads; i++) {
            result += sums[i].get();
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        // 準備數組
        int[] arr = Utils.buildRandomIntArray(100000000);
        //獲取線程數
        int numThreads = arr.length / NUM > 0 ? arr.length / NUM : 1;

        System.out.printf("The array length is: %d\n", arr.length);
        // 構建線程池
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        //預熱 : 提前先創建線程池 可加快
        ((ThreadPoolExecutor)executor).prestartAllCoreThreads();

        Instant now = Instant.now();
        // 數組求和
        long result = sum(arr, executor);
        System.out.println("執行時間："+Duration.between(now,Instant.now()).toMillis());

        System.out.printf("The result is: %d\n", result);

        executor.shutdown();

    }
}
