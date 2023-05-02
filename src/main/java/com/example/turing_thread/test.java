package com.example.turing_thread;

import java.nio.ByteBuffer;
import java.sql.Struct;
import java.util.HashMap;

public class test {
    public static void main(String[] args) {
//        String test1=new String("abc");
//        String test2="abc";
//        System.out.println(test1==test2);
//        System.out.println(test1);
//        HashMap<String, String> testMap = new HashMap<String, String>();
//        testMap.put("t1","t1");
//        System.out.println(testMap.put("t1","22"));


        //位元轉int
//        byte [] bytes = { 0x0000000,0x0000000,0x00,0x00 };
//        System.out.println(ByteBuffer.wrap(bytes).getInt()+" ");

        System.out.println(Integer.parseUnsignedInt("10000000000000000000000000000000", 2));
        System.out.println(Integer.parseUnsignedInt("00000000000000000000000000000001", 2));
        System.out.println(Integer.parseUnsignedInt("00000000000000000000000000000000", 2));
        System.out.println(Integer.parseUnsignedInt("00000000000000001111111111111111", 2));

    }
}
