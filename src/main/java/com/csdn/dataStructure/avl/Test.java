package com.csdn.dataStructure.avl;

public class Test {

    public static void main(String[] args) {

        AVLTree tree = new AVLTree();

        tree.insert(45);
        tree.insert(33);
        tree.insert(87);
        tree.insert(123);
        tree.insert(12);
        tree.insert(3);
        tree.insert(7);
        tree.insert(65);
        tree.insert(8);
        tree.insert(77);

        System.out.println(tree.getRoot());
    }

}
