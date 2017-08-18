/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

import stack_queue.MyStack;

/**
 *
 * @author Nam
 * @param <T>
 */
public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    public AVLTree() {
        super();
    }

    public int height(BinaryTreeNode<T> node) {
        if (node == null) {
            return 0;
        } else if (node.left == null && node.right == null) {
            return 1;
        } else if (node.right == null) {    //mean node.left have data
            return height(node.left) + 1;
        } else if (node.left == null) {
            return height(node.right) + 1;
        } else {
            return Math.max(height(node.left), height(node.right)) + 1;
        }
    }

    public int balanceFactor(BinaryTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return height(node.right) - height(node.left);
    }

    public void balanceBinaryTreeNode(BinaryTreeNode<T> node, int balanceFactor) {
        if (balanceFactor == -2) {    //left devitation, X - right rotation
            if (balanceFactor(node.left) <= 0) {   //single right rotation
                node = rightRotation(node);
            } else {
                node = doubleLeftRightRotation(node);
            }
        } else if (balanceFactor == 2) {         //right devitation, X - left rotation 
            if (balanceFactor(node.right) >= 0) { //single left rotation
                node = leftRotation(node);
            } else {
                node = doubleRightLeftRotation(node);
            }
        }
    }

    @Override
    public void insert(T x) {
        if (this.isEmpty()) {
            root = new BinaryTreeNode(x);
        } else {
            MyStack<BinaryTreeNode<T>> stack = new MyStack();
            BinaryTreeNode<T> node = root;
            BinaryTreeNode<T> prev = node;
            while (node != null) {
                if (node.data.compareTo(x) > 0) { //turn left
                    stack.push(node);
                    prev = node;
                    node = node.left;
                } else if (node.data.compareTo(x) < 0) { //turn right
                    stack.push(node);
                    prev = node;
                    node = node.right;
                } else {
                    break;
                }
            }
            if (prev.data.compareTo(x) > 0) { // insert left
                prev.left = new BinaryTreeNode(x);
            } else {    //insert right
                prev.right = new BinaryTreeNode(x);
            }
            node = stack.pop();
            int balanceFactor = balanceFactor(node);
            while (!stack.isEmpty() && balanceFactor != 2 && balanceFactor != -2) {
                //find the first unbalance node from found node to root
                node = stack.pop();
                balanceFactor = balanceFactor(node);
            }
            balanceBinaryTreeNode(node, balanceFactor);
        }
    }

    @Override
    public void deleteByCopy(T x) {
        if (this.isEmpty()) {
            System.out.println("tree is empty that no node to delete");
        } else {
            MyStack<BinaryTreeNode<T>> stack = new MyStack();
            BinaryTreeNode<T> node = root;   //node to find value x
            BinaryTreeNode<T> prev = root;   //prev is alway father of node
            while (node != null && !node.data.equals(x)) { //find value x in tree
                stack.push(node);
                prev = node;
                if (node.data.compareTo(x) > 0) { //turn left
                    node = node.left;
                } else if (node.data.compareTo(x) < 0) {
                    node = node.right;
                }
            }
            //Deletion
            if (node == null) {
                System.out.println("can't find value " + x + " in tree");
            } else {
                BinaryTreeNode clone = node; //clone of node
                if (node.left == null) {
                    node = node.right;
                } else if (node.right == null) {
                    node = node.left;
                } else {    //delete by copy
                    BinaryTreeNode<T> temp = node.left;  //temp to find the right most node in the left sub-tree
                    BinaryTreeNode<T> preTemp = node;    //preTemp is always father of temp
                    while (temp.right != null) {
                        stack.push(preTemp);
                        preTemp = temp;
                        temp = temp.right;
                    }
                    node.data = temp.data;   //copy data
                    if (preTemp == node) {   //right subtree of temp is null, so that preTemp is not change
                        preTemp.left = temp.left;
                    } else {
                        preTemp.right = temp.left;
                    }
                }
                if (clone == root) {
                    /*in this case, root is value x, prev and clone is point to
                    *same point is root, we don't know whether clone is the left
                    *or the right to prev, but we assign root to node to delete root value
                     */
                    root = node;
                } else if (prev.left == clone) {
                    prev.left = node;
                } else {
                    prev.right = node;
                }
            }
            //Re-balancing
            node = stack.pop();
            int balanceFactor = balanceFactor(node);
            while (!stack.isEmpty()) {
                //find the first unbalance node from found node to root
                System.out.println("balance " + node.data);
                balanceBinaryTreeNode(node, balanceFactor);
                node = stack.pop();
                balanceFactor = balanceFactor(node);
            }
            System.out.println("balance " + node.data);
            balanceBinaryTreeNode(node, balanceFactor);
        }
    }

}
