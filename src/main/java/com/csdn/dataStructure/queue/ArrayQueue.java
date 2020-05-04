package com.csdn.dataStructure.queue;

/**
 * @author ：Breeze
 * @date ：Created in 2020/5/5 0:27
 * @description：
 */

import java.util.Arrays;

/**
 * 使用数组实现队列, 具体操作类
 */
public class ArrayQueue {

    private int maxArray;   //队列最大长度
    private int head;       //队列 头指针
    private int font;       //队列 尾指针
    private int[] arr;      //队列实体

    //队列构造
    public ArrayQueue(int maxArray) {
        this.maxArray = maxArray;
        arr = new int[maxArray];
        head = -1;
        font = -1;
    }

    //判断队列是否已满
    public Boolean isFull() {
        return maxArray - 1 == font;
    }

    //判断队列是否为空
    public Boolean isEmpty() {
        return head == font;
    }

    public void addQueue(int data){
        if(isFull()){
            throw new RuntimeException("队列已满！");
        }
        font++;
        arr[font] = data;
    }

    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空！");
        }
        head++;
        return arr[head];
    }

    public void showQueue(){
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "ArrayQueue{" +
                "arr=" + Arrays.toString(arr) +
                '}' + head + "\t" + font;
    }
}
