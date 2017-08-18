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
public class SLLNode<T> {

    public T data;
    public SLLNode next;

    public SLLNode() {
        this.next = null;
    }

    public SLLNode(T data) {
        this.data = data;
        this.next = null;
    }

    public SLLNode(T data, SLLNode next) {
        this.data = data;
        this.next = next;
    }

}
