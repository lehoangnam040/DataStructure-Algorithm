/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linked_list;

/**
 *
 * @author Nam
 * @param <T>
 */
public class SingleLinkedList<T> {

    public SLLNode<T> head;
    public SLLNode<T> tail;

    public SingleLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public void addFirst(T x) {
        if (isEmpty()) {
            head = tail = new SLLNode(x);
        } else {
            head = new SLLNode(x, head);
        }
    }

    public void addLast(T x) {
        if (isEmpty()) {
            head = tail = new SLLNode(x);
        } else {
            SLLNode node = new SLLNode(x, null);
            tail.next = node;
            tail = node;
        }
    }

    public void insertPosition(int pos, T x) {
        SLLNode node = head;
        int i;
        for (i = 1; i < pos - 1 && node.next != null; i++) {
            node = node.next;
        }
        if (i == 1) {
            addFirst(x);
        } else if (i < pos) {
            addLast(x);
        } else {
            SLLNode p = new SLLNode(x, node.next);
            node.next = p;
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else if (head == tail) {
            T first = head.data;
            clear();
            return first;
        } else {
            T first = head.data;
            head = head.next;
            return first;
        }
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else if (head == tail) {
            T last = tail.data;
            clear();
            return last;
        } else {
            SLLNode node = head;
            while (node.next != tail) {
                node = node.next;
            }
            node.next = null;
            T last = tail.data;
            tail = node;
            return last;
        }
    }

    public void deletePosition(int pos) {
        SLLNode node = head;
        int i;
        for (i = 1; i < pos - 1 && node.next != null; i++) {
            node = node.next;
        }
        if (i == 1) {
            removeFirst();
        } else if (i < pos) {
            removeLast();
        } else {
            node.next = node.next.next;
        }
    }

    public void traverse() {
        SLLNode node = head;
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    public SLLNode search(T x) {
        SLLNode node = head;
        while (node.next != null) {
            if (node.data.equals(x)) {
                return node;
            }
        }
        return null;
    }
}
