package com.example.turing_thread._2.jmm;

/**
 * @author  Fox
 */
public class Test {

    private volatile static int sum = 0;
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    sum++;
                }
            });
            thread.start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(sum);

    }

}
