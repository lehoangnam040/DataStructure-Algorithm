/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorting;

/**
 *
 * @author Nam
 * @param <T>
 */
public abstract class Sorting<T extends Comparable<T>> {

    public int n;
    public T[] arr;
    public long time;

    public Sorting(T[] arr) {
        this.arr = arr;
        n = arr.length;
    }

    public final void display() {
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }

    public abstract void sort();
}
