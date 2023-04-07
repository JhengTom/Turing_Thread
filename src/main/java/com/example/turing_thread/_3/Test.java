package com.example.turing_thread._3;

public class Test {

    public static void main(String[] args) {
        Map map = new HashMap();

        for (int i = 0; i < 10000; i++) {
            map.put("Monkey" + i, "我是monkey老师" + i);

        }
        System.out.println(map);

    }
}
