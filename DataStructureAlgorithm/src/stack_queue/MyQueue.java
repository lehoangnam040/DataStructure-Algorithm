/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stack_queue;

import linked_list.SingleLinkedList;

/**
 *
 * @author Nam
 * @param <T>
 */
public class MyQueue<T> {

    SingleLinkedList<T> t;

    public MyQueue() {
        t = new SingleLinkedList();
    }

    public boolean isEmpty() {
        return (t.isEmpty());
    }

    public void clear() {
        t.clear();
    }

    public void enqueue(T x) {
        t.addLast(x);
    }

    public T dequeue() {
        if (isEmpty()) {
            return (null);
        }
        return (t.removeFirst());
    }

    public T front() {
        if (isEmpty()) {
            return (null);
        }
        return (t.head.data);
    }
}
