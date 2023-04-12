//package com.example.turing_thread._06.jucdemo.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author  Fox
// */
//@RestController
//public class StockController {
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @RequestMapping("/reduce_stock")
//    public String reduceStock() {
//
//        //reentrantLock
//        //查库存    库存超卖
//        int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
//        if (stock > 0) {
//            // 扣减库存
//            stock--;
//            stringRedisTemplate.opsForValue().set("stock", stock + "");
//            System.out.println("扣减成功，库存stock:" + stock);
//        } else {
//            System.out.println("扣减失败，库存不足");
//        }
//
//        return "end";
//    }
//
//}
