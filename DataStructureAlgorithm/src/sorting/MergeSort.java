/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorting;

import java.util.Calendar;

/**
 *
 * @author Nam
 * @param <T>
 */
public class MergeSort<T extends Comparable<T>> extends Sorting<T> {

    public MergeSort(T[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        time = Calendar.getInstance().getTimeInMillis();
        mergeSort(arr, 0, arr.length - 1);
        System.out.println("executing time: " + (Calendar.getInstance().getTimeInMillis() - time) + " ms");
    }

    public void mergeSort(T[] a, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(a, left, mid);
            mergeSort(a, mid + 1, right);
            merge(a, left, mid, right);
        }
    }

    public void merge(T[] a, int left, int mid, int right) {
        int x = left;
        int z = mid + 1;
        T[] temp = (T[]) new Comparable[right - left + 1];
        for (int i = 0; i < temp.length; i++) {
            if (x > mid) {
                temp[i] = a[z];
                z++;
            } else if (z > right) {
                temp[i] = a[x];
                x++;
            } else if (a[x].compareTo(a[z]) > 0) {
                temp[i] = a[z];
                z++;
            } else {
                temp[i] = a[x];
                x++;
            }
        }
        for (int i = left; i <= right; i++) {
            a[i] = temp[i - left];
        }
    }
}
