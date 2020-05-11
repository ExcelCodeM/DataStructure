package com.csdn.dataStructure.linkedList;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Stack;

/**
 * @author ：Breeze
 * @date ：Created in 2020/5/5 15:29
 * @description：
 */
public class SingleLinkedListTest {

    public static void main(String[] args) {
        Node node1 = new Node(1, "宋江", "及时雨");
        Node node2 = new Node(2, "卢俊义", "玉麒麟");
        Node node3 = new Node(3, "吴用", "智多星");
        Node node4 = new Node(4, "鲁智深", "花和尚");

        SingleLinkedList linkedList = new SingleLinkedList();

        //尾部添加节点
        linkedList.add(node1);
        linkedList.add(node2);
        linkedList.add(node3);
        linkedList.add(node4);

        //有序添加节点
//        linkedList.addByOrder(node4);
//        linkedList.addByOrder(node3);
//        linkedList.addByOrder(node2);
//        linkedList.addByOrder(node1);

        //链表更新
//        Node node5 = new Node(4, "林冲", "豹子头");
//        linkedList.update(node5);

        //反转链表，但是已经破坏了链表的结构
//        linkedList.reverseList(linkedList.getHead());

        //逆序打印链表，不破坏链表结构
        linkedList.reversePrint(linkedList.getHead());

        //linkedList.showLinkedList();
    }
}

/**
 * 定义SingleLinkedList单链表，管理Node节点
 */
class SingleLinkedList {
    //创建头节点，不能动
    private Node head = new Node(0, null, null);

    public Node getHead() {
        return head;
    }

    /**
     * 链表尾部添加节点
     *
     * @param node
     */
    public void add(Node node) {
        Node temp = head;   //因为头节点不能动，需要一个辅助变量，类似指针
        while (true) {
            if (temp.next == null) {  //说明为链表最后
                break;
            } else {
                temp = temp.next;   //指针后移
            }
        }
        temp.next = node;   //添加节点
    }

    /**
     * 打印链表
     */
    public void showLinkedList() {
        if (head.next == null) {
            throw new RuntimeException("空链表");
        }

        Node temp = head.next;   //因为头节点不能动，需要一个辅助变量，类似指针
        while (true) {
            if (temp == null) {   //说明为链表最后
                break;
            } else {
                System.out.println(temp);
                temp = temp.next;   //指针后移
            }
        }
    }

    /**
     * 按顺序添加节点
     */
    public void addByOrder(Node node) {
        Node temp = head;   //因为头节点不能动，需要一个辅助变量，类似指针
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > node.no) {
                break;
            }
            if (temp.next.no == node.no) {
                throw new RuntimeException("不能重复插入相同的节点");
            }
            temp = temp.next;
        }
        node.next = temp.next;
        temp.next = node;
    }

    /**
     * 修改节点，但是节点no不能改变
     */
    public void update(Node node) {
        Node temp = head.next;   //因为头节点不能动，需要一个辅助变量，类似指针
        Boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = node.name;
            temp.nickName = node.nickName;
        } else {
            throw new RuntimeException("没有节点");
        }
    }

    /**
     * 实现链表的反转，传入参数为头节点
     *
     * @param head
     */
    public void reverseList(Node head) {
        //空链表、只有一个节点链表，直接返回
        if (head.next == null || head.next.next == null) {
            System.out.println("空链表");
            return;
        }

        //创建临时头节点
        Node reverseNode = new Node(0, null, null);
        Node temp = head.next;   //因为头节点不能动，需要一个辅助变量，类似指针
        Node next = null;   //因为是单链表，记录指针的下一个节点，保证链表不断

        //便利原链表，每便利一个节点，将其取出，放到新链表的头部
        while (temp != null) {
            next = temp.next;     //先暂时保存当前节点的下一个节点
            //把新链表的第一个节点挂到temp指针节点下面，并把temp节点挂到新链表头节点后，相当于在新节点头部插入
            temp.next = reverseNode.next;
            reverseNode.next = temp;
            temp = next;
        }
        head.next = reverseNode.next;
    }

    /**
     * 逆序打印链表，但是不能破坏链表结构（就是不能使用反转链表的方式），本方法使用栈的数据结构
     * @param head
     */
    public void reversePrint(Node head) {
        if (head.next == null) {
            System.out.println("空链表");
            return;
        }
        Stack<Node> stack = new Stack<Node>();
        Node temp = head.next;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
}


/**
 * 定义节点
 */
class Node {
    public int no;
    public String name;     //真实名称
    public String nickName;     //昵称
    public Node next;

    public Node(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
//                ", next=" + next +
                '}';
    }
}



