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
public class MyStack<T> {

    SingleLinkedList<T> t;

    public MyStack() {
        t = new SingleLinkedList();
    }

    public boolean isEmpty() {
        return (t.isEmpty());
    }

    public void clear() {
        t.clear();
    }

    public void push(T x) {
        t.addLast(x);
    }

    public T pop() {
        if (isEmpty()) {
            return (null);
        }
        return (t.removeLast());
    }

    public T top() {
        if (isEmpty()) {
            return (null);
        }
        return (t.tail.data);
    }
}
