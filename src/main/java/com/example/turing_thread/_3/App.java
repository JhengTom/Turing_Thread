package com.example.turing_thread._3;


public class App {
    public static void main(String[] args) {
        //Map<String, String> map = new HashMap<String, String>();
        App map=new App();
        map.put("刘一", "刘一");
        map.put("陈二", "陈二");
        map.put("张三", "张三");
        map.put("李四", "李四");
        map.put("王五", "王五");
        map.put("Monkey", "我是monkey老师");



    }










    public void put(String key, String value) {
        System.out.printf("key:%s::::::::::::::::::hash值:%s::::::::::::::::::存储位置:%s\r\n", key, key.hashCode(), Math.abs(key.hashCode() % 15));

    }


}
