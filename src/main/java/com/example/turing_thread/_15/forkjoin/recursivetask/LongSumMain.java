package com.example.turing_thread._15.forkjoin.recursivetask;


import com.example.turing_thread._15.forkjoin.util.Utils;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;


/**
 * @author Fox
 *
 * 利用ForkJoinPool計算1億個整數的和
 */
public class LongSumMain {
	// 獲取邏輯處理器數量 12
	static final int NCPU = Runtime.getRuntime().availableProcessors();

	static long calcSum;


	public static void main(String[] args) throws Exception {
		//準備數組
		int[] array = Utils.buildRandomIntArray(100000000);

		Instant now = Instant.now();
		// 單線程計算數組總和
		calcSum = seqSum(array);
		System.out.println("seq sum=" + calcSum);
		System.out.println("執行時間："+ Duration.between(now,Instant.now()).toMillis());

		//遞歸任務
		LongSum ls = new LongSum(array, 0, array.length);
		// 構建ForkJoinPool
  		ForkJoinPool fjp  = new ForkJoinPool(NCPU);

		now = Instant.now();
		//ForkJoin計算數組總和
		ForkJoinTask<Long> result = fjp.submit(ls);
		System.out.println("forkjoin sum=" + result.get());
		System.out.println("執行時間："+ Duration.between(now,Instant.now()).toMillis());

		fjp.shutdown();

		now = Instant.now();
		//并行流計算數組總和
		Long sum = (Long) IntStream.of(array).asLongStream().parallel().sum();
		System.out.println("IntStream sum="+sum);
		System.out.println("執行時間："+ Duration.between(now,Instant.now()).toMillis());

	}


	static long seqSum(int[] array) {
		long sum = 0;
		for (int i = 0; i < array.length; ++i) {
			sum += array[i];
		}
		return sum;
	}
}