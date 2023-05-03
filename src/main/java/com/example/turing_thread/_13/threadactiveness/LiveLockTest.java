package com.example.turing_thread._13.threadactiveness;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Fox
 * 活鎖
 */
@Slf4j
public class LiveLockTest {

    /**
     * 定義一個勺子，ower 表示這個勺子的擁有者
     */
    static class Spoon {
        Diner owner;

        public Spoon(Diner diner) {
            this.owner = diner;
        }

        public String getOwnerName() {
            return owner.getName();
        }

        public void setOwner(Diner diner) {
            this.owner = diner;
        }

        //表示正在用餐
        public void use() {
            log.info( "{} 用這個勺子吃飯.",owner.getName());
        }
    }

    /**
     * 定義一個晚餐類
     */
    static class Diner {

        private boolean isHungry;
        //用餐者的名字
        private String name;

        public Diner(boolean isHungry, String name) {
            this.isHungry = isHungry;
            this.name = name;
        }

        //和某人吃飯
        public void eatWith(Diner diner, Spoon sharedSpoon) {
            try {
                synchronized (sharedSpoon) {
                    while (isHungry) {
                        //當前用餐者和勺子擁有者不是同一個人，則進行等待
                        while (!sharedSpoon.getOwnerName().equals(name)) {
                            sharedSpoon.wait();
                        }
                        if (diner.isHungry()) {
                            log.info( "{}：親愛的我餓了，然後{}把勺子給了{}",
                                    diner.getName(),name,diner.getName());
                            sharedSpoon.setOwner(diner);
                            //用餐 -->用餐完後再喚醒 避免活鎖
                            sharedSpoon.use();
                            //喚醒等待的線程
                            sharedSpoon.notifyAll();
                        } else {
                            //用餐
                            sharedSpoon.use();
                            sharedSpoon.setOwner(diner);
                            isHungry = false;
                        }
                        Thread.sleep(500);
                    }
                }
            } catch (InterruptedException e) {
                log.info("{} is interrupted.",name);
            }
        }

        public boolean isHungry() {
            return isHungry;
        }

        public String getName() {
            return name;
        }

    }

    public static void main(String[] args) {
        final Diner husband = new Diner(true, "丈夫");
        final Diner wife = new Diner(true, "妻子");
        //最開始牛郎持有勺子
        final Spoon sharedSpoon = new Spoon(husband);

        //織女和牛郎吃飯
        Thread h = new Thread(()->wife.eatWith(husband, sharedSpoon));
        h.start();

        //牛郎和織女吃飯
        Thread w = new Thread(()->husband.eatWith(wife, sharedSpoon));
        w.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       h.interrupt();
       w.interrupt();

    }

}
