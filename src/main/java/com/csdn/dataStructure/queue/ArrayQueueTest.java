package com.csdn.dataStructure.queue;

/**
 * @author ：Breeze
 * @date ：Created in 2020/5/5 0:49
 * @description：
 */

/**
 * 队列（数组实现）， 测试类
 */
public class ArrayQueueTest {

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(10);
        for (int i = 0; i < 10; i++) {
            queue.addQueue(i);
        }
        queue.showQueue();
        for (int i = 0; i < 10; i++) {
            System.out.println(queue.getQueue());
            queue.showQueue();
        }
    }
}
