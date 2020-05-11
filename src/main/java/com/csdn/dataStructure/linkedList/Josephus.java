package com.csdn.dataStructure.linkedList;

/**
 * @author ：Breeze
 * @date ：Created in 2020/5/5 23:24
 * @description：
 */

/**
 * 约瑟夫问题
 */
public class Josephus {
}

class CircleSingleLinked{
    //初始节点
    private Boy first = new Boy(0);

    public void add(Boy boy){
        if(boy.getNo()<1){
            System.out.println("当前boy编号不合法");
            return;
        }

    }
}

/**
 * 节点类Boy
 */
class Boy{
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                ", next=" + next +
                '}';
    }
}
