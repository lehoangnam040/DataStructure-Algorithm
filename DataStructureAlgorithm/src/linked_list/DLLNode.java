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
public class DLLNode<T> {

    public T data;
    public DLLNode next;
    public DLLNode prev;

    public DLLNode() {
        this.next = null;
        this.prev = null;
    }

    public DLLNode(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public DLLNode(T data, DLLNode next, DLLNode prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

}
