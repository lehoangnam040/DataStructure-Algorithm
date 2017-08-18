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
public class SelectionSort<T extends Comparable<T>> extends Sorting<T> {

    public SelectionSort(T[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        time = Calendar.getInstance().getTimeInMillis();
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j].compareTo(arr[min]) < 0) {
                    min = j;
                }
            }
            //swap
            if (i != min) {
                T temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
        System.out.println("executing time: " + (Calendar.getInstance().getTimeInMillis() - time) + " ms");
    }
}
