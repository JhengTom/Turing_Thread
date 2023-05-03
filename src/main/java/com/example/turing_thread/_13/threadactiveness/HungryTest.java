package com.example.turing_thread._13.threadactiveness;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lombok.extern.slf4j.Slf4j;


/**
 * @author  Fox
 * 定長線程池飢餓示例
 */
@Slf4j
public class HungryTest {

    static final List<String> FOODS = Arrays.asList("豬腳飯", "宮保雞丁", "魚香肉絲", "麻婆豆腐");

    static final Random RANDOM = new Random();

    static ExecutorService pool = Executors.newFixedThreadPool(2);

    //隨機做菜
    public static String cooking() {
        return FOODS.get(RANDOM.nextInt(FOODS.size()));
    }


    public static void main(String[] args) throws InterruptedException {
        // 服務員需要點菜、以及自己去做菜
        HungryTest.test();
    }

    public static void test() {
        pool.execute(() -> {
            //服務員開始點菜
            log.info("開始給顧客點菜1");
            Future<String> food = pool.submit(() -> {
                log.info("開始做菜");
                return cooking();
            });

            //該服務員點完菜上菜
            try {
                log.info("上菜:{}", food.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        pool.execute(() -> {
            //服務員開始點菜
            log.info("開始給顧客點菜2");
            Future<String> food = pool.submit(() -> {
                log.info("開始做菜");
                return cooking();
            });

            //該服務員點完菜上菜
            try {
                log.info("上菜:{}", food.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

    }
}
