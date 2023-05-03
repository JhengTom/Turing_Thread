package com.example.turing_thread._14.blokingqueue;

import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author Fox
 */
public class PriorityBlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        //創建優先級阻塞隊列  Comparator為null,自然排序 小到大
//        PriorityBlockingQueue<Integer> queue=new PriorityBlockingQueue<Integer>(3);


//         自定義Comparator
        PriorityBlockingQueue queue=new PriorityBlockingQueue<Integer>(
                5, new Comparator<Integer>() {
            @Override //大到小
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });


        Random random = new Random();
        System.out.println("put:");
        for (int i = 0; i < 5; i++) {
            int j = random.nextInt(100);
            System.out.print(j+"  ");
            queue.put(j);
        }

        System.out.println("\ntake:");
        for (int i = 0; i < 5; i++) {
            System.out.print(queue.take()+"  ");
        }


    }
}
