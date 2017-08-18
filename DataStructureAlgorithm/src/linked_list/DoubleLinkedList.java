/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linked_list;

/**
 *
 * @author Nam
 */
public class DoubleLinkedList<T> {

    DLLNode<T> head;
    DLLNode<T> tail;

    public DoubleLinkedList() {
        head = null;
        tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public void insertHead(T x) {
        if (isEmpty()) {
            head = tail = new DLLNode(x);
        } else {
            head = new DLLNode(x, null, head);
            head.next.prev = head;
        }
    }

    public void insertTail(T x) {
        if (isEmpty()) {
            head = tail = new DLLNode(x);
        } else {
            tail = new DLLNode(x, tail, null);
            tail.prev.next = tail;
        }
    }

    public void insertPosition(int pos, T x) {
        DLLNode node = head;
        int i;
        for (i = 1; i < pos && node.next != null; i++) {
            node = node.next;
        }
        if (i == 1) {
            insertHead(x);
        } else if (i < pos) {
            insertTail(x);
        } else {
            DLLNode p = new DLLNode(x, node.prev, node);
            p.prev.next = p;
            p.next.prev = p;
        }
    }

    public void deleteHead() {
        if (isEmpty()) {
            System.out.println("can't delete empty list");
        } else if (head == tail) {
            clear();
        } else {
            head = head.next;
            head.prev = null;
        }
    }

    public void deleteTail() {
        if (isEmpty()) {
            System.out.println("can't delete empty list");
        } else if (head == tail) {
            clear();
        } else {
            tail = tail.prev;
            tail.next = null;
        }
    }

    public void deletePosition(int pos) {
        DLLNode node = head;
        int i;
        for (i = 1; i < pos && node.next != null; i++) {
            node = node.next;
        }
        if (i == 1) {
            deleteHead();
        } else if (i < pos) {
            deleteTail();
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    public void traverse() {
        DLLNode node = head;
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    public DLLNode search(T x) {
        DLLNode node = head;
        while (node.next != null) {
            if (node.data.equals(x)) {
                return node;
            }
        }
        return null;
    }
}
