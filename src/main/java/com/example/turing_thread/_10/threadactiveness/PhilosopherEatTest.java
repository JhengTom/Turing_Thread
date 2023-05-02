package com.example.turing_thread._10.threadactiveness;

/**
 * @author Fox
 * 哲學家就餐問題
 */
public class PhilosopherEatTest {

    public static void main(String[] args) {

        //初始化五根筷子
        Chopstick c1 = new Chopstick(1);
        Chopstick c2 = new Chopstick(2);
        Chopstick c3 = new Chopstick(3);
        Chopstick c4 = new Chopstick(4);
        Chopstick c5 = new Chopstick(5);
        // 思考： 如何打破循環
        new Philosopher("蘇格拉底", c1, c2).start();
        new Philosopher("柏拉圖", c2, c3).start();
        new Philosopher("亞里士多德", c3, c4).start();
        new Philosopher("赫拉克利特", c4, c5).start();
        new Philosopher("阿基米德", c5,c1).start();//配合解法 ReentrentLock+Condition
//        new Philosopher("阿基米德", c1,c5).start();//配合解法 改變順序

    }
}
