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
public class InsertionSort<T extends Comparable<T>> extends Sorting<T> {

    public InsertionSort(T[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        time = Calendar.getInstance().getTimeInMillis();
        for (int i = 1; i < n; i++) {
            T key = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1].compareTo(key) > 0) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = key;
        }
        System.out.println("executing time: " + (Calendar.getInstance().getTimeInMillis() - time) + " ms");
    }
}
