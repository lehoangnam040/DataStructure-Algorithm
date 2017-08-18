/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

import stack_queue.MyQueue;

/**
 *
 * @author Nam
 * @param <T>
 */
public class BinaryTree<T extends Comparable<T>> {

    public BinaryTreeNode<T> root;

    public BinaryTree() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        root = null;
    }

    public void visit(BinaryTreeNode node) {
        System.out.print(node.data.toString() + " - ");
    }

    public void preOrderTraverse(BinaryTreeNode node) {
        if (node == null) {
            return;
        }
        visit(node);
        preOrderTraverse(node.left);
        preOrderTraverse(node.right);
    }

    public void postOrderTraverse(BinaryTreeNode node) {
        if (node == null) {
            return;
        }
        postOrderTraverse(node.left);
        postOrderTraverse(node.right);
        visit(node);
    }

    public void inOrderTraverse(BinaryTreeNode node) {
        if (node == null) {
            return;
        }
        inOrderTraverse(node.left);
        visit(node);
        inOrderTraverse(node.right);
    }

    public void breadthFirstTraverse() {
        if (isEmpty()) {
            return;
        }
        MyQueue<BinaryTreeNode<T>> queue = new MyQueue();
        queue.enqueue(root);
        BinaryTreeNode node;
        while (!queue.isEmpty()) {
            node = queue.dequeue();
            if (node.left != null) {
                queue.enqueue(node.left);
            }
            if (node.right != null) {
                queue.enqueue(node.right);
            }
            visit(node);
        }
    }
}
