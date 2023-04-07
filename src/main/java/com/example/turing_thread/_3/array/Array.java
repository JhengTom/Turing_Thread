package com.example.turing_thread._3.array;

public class Array {


    //数组:采用一段连续的存储单元来存储数据。
    //特点:查询O(1) 删除插入O（N） 总结：查询快 删除插入慢
    public static void main(String[] args) {
        Integer integers[]=new Integer[10];
        integers[0] = 0;
        integers[1] = 1;
        integers[9] = 2;//lies
        integers[9] = 400;//foes
        System.out.println(integers[9] );
    }











   /* public static void main2(String[] args) {
        Entry[] entries = new Entry[10];
        entries[4]=new Entry("刘一","刘一",671464,null);
        entries[4]=new Entry("张三","张三",671464,null);
        *//*integers[0] = 0;
        integers[1] = 1;
        integers[2] = 2;
        integers[9] = 9;
        integers[8] = 9;*//*
        System.out.println(entries[4] );
    }*/
}
