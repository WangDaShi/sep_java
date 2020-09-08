package com.example.talent.sep;

/**
 * 环形链表
 */
public class RingBuffer {

    private final int len;//可保存的最大长度
    private int head;// 指向当前头部位置
    private final char[] arr;

    public RingBuffer(int len){
        this.len = len;
        this.arr = new char[len];
        this.head = 0;
    }

    public void add(char c){
        int tail;
        if(head == 0){
            tail = head -1 + len;
        }else{
            tail = head -1;
        }
        arr[tail] = c;
        head = tail;
    }

    public Character get(int i){
        if(i >= len){
            throw new IllegalArgumentException("超出最大长度");
        }else{
            int index = i + head;
            return arr[index >= len ? index-len : index];
        }
    }

    public int length(){
        return len;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        for(int i =0;i < len;i++){
            builder.append(get(i));
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        RingBuffer ring = new RingBuffer(5);
        ring.add('a');
        ring.add('a');
        ring.add('a');
        ring.add('a');
        ring.add('a');
        System.out.println(ring.toString());
        ring.add('b');
        System.out.println(ring.toString());
        ring.add('c');
        System.out.println(ring.toString());
        ring.add('d');
        ring.add('e');
        System.out.println(ring.toString());
        ring.add('1');
        System.out.println(ring.toString());
    }
}
