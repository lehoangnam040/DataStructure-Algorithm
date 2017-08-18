/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

import java.util.ArrayList;

/**
 *
 * @author Nam
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

    public BinarySearchTree() {
        super();
    }

    public BinaryTreeNode<T> search(T x) {
        BinaryTreeNode<T> node = root;
        while (node != null) {
            if (node.data.equals(x)) {
                return node;
            } else if (node.data.compareTo(x) > 0) { //turn left
                node = node.left;
            } else {    //turn right
                node = node.right;
            }
        }
        return node;
    }

    public void insert(T x) {
        if (this.isEmpty()) {
            root = new BinaryTreeNode(x);
        } else {
            BinaryTreeNode<T> node = root;
            BinaryTreeNode<T> prev = node;
            while (node != null) {
                if (node.data.compareTo(x) > 0) { //turn left
                    prev = node;
                    node = node.left;
                } else if (node.data.compareTo(x) < 0) { //turn right
                    prev = node;
                    node = node.right;
                } else {
                    return;
                }
            }
            if (prev.data.compareTo(x) > 0) { // insert left
                prev.left = new BinaryTreeNode(x);
            } else {    //insert right
                prev.right = new BinaryTreeNode(x);
            }
        }
    }

    public void deleteByMerge(T x) {
        if (this.isEmpty()) {
            System.out.println("tree is empty that no node to delete");
        } else {
            BinaryTreeNode<T> node = root;   //node to find value x
            BinaryTreeNode<T> prev = root;   //prev is alway father of node
            while (node != null && !node.data.equals(x)) { //find value x in tree
                prev = node;
                if (node.data.compareTo(x) > 0) { //turn left
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
            if (node == null) {
                System.out.println("can't find value " + x + " in tree");
            } else {    //delete by merge
                BinaryTreeNode<T> clone = node; //clone of node
                if (node.left == null) {
                    node = node.right;
                } else if (node.right == null) {
                    node = node.left;
                } else {
                    BinaryTreeNode<T> temp = node.left;
                    while (temp.right != null) { //find the most right
                        temp = temp.right;
                    }
                    temp.right = node.right;
                    node = node.left;
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
        }
    }

    public void deleteByCopy(T x) {
        if (this.isEmpty()) {
            System.out.println("tree is empty that no node to delete");
        } else {
            BinaryTreeNode<T> node = root;   //node to find value x
            BinaryTreeNode<T> prev = root;   //prev is alway father of node
            while (node != null && !node.data.equals(x)) { //find value x in tree
                prev = node;
                if (node.data.compareTo(x) > 0) { //turn left
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
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
        }
    }

    //Balance Tree
    public ArrayList<T> toSortedData(BinaryTreeNode<T> node) {
        if (node == null) {
            return null;
        } else {
            ArrayList<T> sortedData = new ArrayList();
            if (this.toSortedData(node.left) != null) {
                sortedData.addAll(this.toSortedData(node.left));
            }
            sortedData.add(node.data);
            if (this.toSortedData(node.right) != null) {
                sortedData.addAll(this.toSortedData(node.right));
            }
            return sortedData;
        }
    }

    public void balance(ArrayList<T> sortedData, int first, int last) {
        if (first <= last) {
            int middle = (first + last) / 2;
            this.insert(sortedData.get(middle));
            this.balance(sortedData, first, middle - 1);
            this.balance(sortedData, middle + 1, last);
        }
    }

    public void balance() { //to AVL tree
        ArrayList<T> data = this.toSortedData(root);
        this.clear();
        this.balance(data, 0, data.size() - 1);
    }

    public BinaryTreeNode<T> rightRotation(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> p = node.left;
        node.left = p.left;
        p.left = p.right;
        p.right = node.right;
        node.right = p;
        T temp = node.data;
        node.data = p.data;
        p.data = temp;
        return node;
    }

    public BinaryTreeNode<T> leftRotation(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> p = node.right;
        node.right = p.right;
        p.right = p.left;
        p.left = node.left;
        node.left = p;
        T temp = node.data;
        node.data = p.data;
        p.data = temp;
        return node;
    }

    public BinaryTreeNode<T> doubleLeftRightRotation(BinaryTreeNode<T> node) {
        node.left = this.leftRotation(node.left);
        return this.rightRotation(node);
    }

    public BinaryTreeNode<T> doubleRightLeftRotation(BinaryTreeNode<T> node) {
        node.right = this.rightRotation(node.right);
        return this.leftRotation(node);
    }
}
