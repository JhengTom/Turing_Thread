package com.example.turing_thread._3.node;
//链表结构
public class Node {

    public Node next;
    private Object data;

    public Node(Object data) {
        this.data = data;
    }

    //链表：链表是一种物理存储单元上非连续、非顺序的存储结构
    //特点：插入、删除时间复杂度O（1） 查找遍历时间复杂度0（N） 总结：插入快 查找慢
    public static void main(String[] args) {
        Node head = new Node("monkey");
        head.next = new Node("张三");
        head.next.next = new Node("刘一");
        System.out.println(head.data);
        System.out.println(head.next.data);
        System.out.println(head.next.next.data);

    }
}
