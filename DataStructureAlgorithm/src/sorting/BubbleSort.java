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
public class BubbleSort<T extends Comparable<T>> extends Sorting<T> {

    public BubbleSort(T[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        boolean swap;
        int j = 0;
        time = Calendar.getInstance().getTimeInMillis();
        do {
            swap = false;
            for (int i = 0; i < n - 1 - j; i++) {
                if (arr[i].compareTo(arr[i + 1]) > 0) {
                    T temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swap = true;
                }
            }
            j++;
        } while (swap);
        System.out.println("executing time: " + (Calendar.getInstance().getTimeInMillis() - time) + " ms");
    }
}
