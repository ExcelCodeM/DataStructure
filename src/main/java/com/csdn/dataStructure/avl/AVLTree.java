package com.csdn.dataStructure.avl;

public class AVLTree {

    private AVLTreeNode root = null;    //默认avl树根节点
    private static final int MAX_HEIGHT_DIFFERENCE = 1;     //左子树与右子树最大高度差

    public void insert(Integer key){
        if(key == null){
            throw new NullPointerException();
        }

        root = insert(root, key);

    }

    private AVLTreeNode insert(AVLTreeNode node, Integer key){
        if(node == null){
            return new AVLTreeNode(key,null,null);
        }
        int cop = key.compareTo(node.data);
        if(cop == 0){
            return node;
        }
        if(cop < 0){
            node.left = insert(node.left, key);
        }
        if(cop > 0){
            node.right = insert(node.right,key);
        }
        if (Math.abs(height(node.left) - height(node.right)) > MAX_HEIGHT_DIFFERENCE) {
            node = balance(node);
        }
        refreshHeight(node);

        return node;

    }

    private void refreshHeight(AVLTreeNode node){
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }


    private AVLTreeNode balance(AVLTreeNode node) {
        AVLTreeNode node1;
        AVLTreeNode node2;
        // ll 右旋
        if (height(node.left) > height(node.right) &&
                height(node.left.left) >= height(node.left.right)) {
            node1 = node.left;
            node.left = node1.right;
            node1.right = node;

            refreshHeight(node);
            return node1;
        }
        // lr
        if (height(node.left) > height(node.right) &&
                height(node.left.right) > height(node.left.left)) {
            node1 = node.left;
            node2 = node.left.right;
            node.left = node2.right;
            node1.right = node2.left;
            node2.left = node1;
            node2.right = node;

            refreshHeight(node);
            refreshHeight(node1);
            return node2;
        }
        // rr
        if (height(node.right) > height(node.left) &&
                height(node.right.right) >= height(node.right.left)) {
            node1 = node.right;
            node.right = node1.left;
            node1.left = node;

            refreshHeight(node);
            return node1;
        }
        // rl
        if (height(node.right) > height(node.left) &&
                height(node.right.left) > height(node.right.right)) {
            node1 = node.right;
            node2 = node.right.left;
            node.right = node2.left;
            node1.left = node2.right;
            node2.left = node;
            node2.right = node1;

            refreshHeight(node);
            refreshHeight(node1);
            return node2;
        }
        return node;
    }

    /**
     * 获取节点高度
     * @param node
     * @return
     */
    private int height(AVLTreeNode node){
        if(node == null){
            return 0;
        }else{
            return node.height;
        }
    }

    public AVLTreeNode getRoot(){
        return root;
    }

}
