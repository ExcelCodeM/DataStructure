package com.csdn.dataStructure.avl;

public class AVLTreeNode {

    public Integer data;
    public AVLTreeNode left;
    public AVLTreeNode right;

    public int height = 1;

    public AVLTreeNode(Integer data, AVLTreeNode left, AVLTreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }


    @Override
    public String toString() {
        return "AVLTreeNode{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                ", height=" + height +
                '}';
    }
}
