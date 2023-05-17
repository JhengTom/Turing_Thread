package com.example.turing_thread._15.forkjoin.recursivetask;

import java.util.concurrent.RecursiveTask;

public class LongSum extends RecursiveTask<Long> {
    // 任務拆分最小閾值
    static final int SEQUENTIAL_THRESHOLD = 10000000;

    int low;
    int high;
    int[] array;

    LongSum(int[] arr, int lo, int hi) {
        array = arr;
        low = lo;
        high = hi;
    }

    @Override
    protected Long compute() {

        //當任務拆分到小於等於閥值時開始求和
        if (high - low <= SEQUENTIAL_THRESHOLD) {

            long sum = 0;
            for (int i = low; i < high; ++i) {
                sum += array[i];
            }
            return sum;
        } else {  // 任務過大繼續拆分
            int mid = low + (high - low) / 2;
            LongSum left = new LongSum(array, low, mid);
            LongSum right = new LongSum(array, mid, high);
            // 提交任務
            left.fork();
            right.fork();
            //獲取任務的執行結果,將阻塞當前線程直到對應的子任務完成運行並返回結果
            long rightAns = right.compute();
            long leftAns = left.join();
            return leftAns + rightAns;
        }
    }
}

       