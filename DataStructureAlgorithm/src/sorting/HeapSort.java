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
public class HeapSort<T extends Comparable<T>> extends Sorting<T> {

    public HeapSort(T[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        time = Calendar.getInstance().getTimeInMillis();
        //build heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        // One by one extract an element from heap
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end
            T temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
        System.out.println("executing time: " + (Calendar.getInstance().getTimeInMillis() - time) + " ms");
    }

    //vun đống 1 cây từ nốt ở vị trí i của mảng a từ vị trí 0 đến n
    public void heapify(T[] a, int n, int i) {
        int largest = i;    //vị trí i sẽ để chứa giá trị lớn nhất của heap
        int left = 2 * i + 1;
        int right = 2 * i + 2;    //2 vị trí trái phải của cây con gốc vị trí i

        // If left child is larger than root
        if (left < n && arr[left].compareTo(arr[largest]) > 0) {
            largest = left;
        }

        // If right child is larger than largest so far
        if (right < n && arr[right].compareTo(arr[largest]) > 0) {
            largest = right;
        }

        // If largest is not root
        if (largest != i) {
            T temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }
}
