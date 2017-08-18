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
public class QuickSort<T extends Comparable<T>> extends Sorting<T> {

    public QuickSort(T[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        time = Calendar.getInstance().getTimeInMillis();
        quickSort(arr, 0, arr.length - 1);
        System.out.println("executing time: " + (Calendar.getInstance().getTimeInMillis() - time) + " ms");
    }

    public void quickSort(T[] a, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(a, left, right);
            if (left < pivotIndex - 1) {
                quickSort(a, left, pivotIndex - 1);
            }
            if (pivotIndex < right) {
                quickSort(a, pivotIndex, right);
            }
        }
    }

    public int partition(T[] a, int first, int last) {
        T pivot = a[(first + last) / 2];
        int up = first;
        int down = last;
        while (up < down) {
            while (a[up].compareTo(pivot) < 0) {
                up++;
            }
            while (a[down].compareTo(pivot) > 0) {
                down--;
            }
            if (up <= down) {
                T temp = a[up];
                a[up] = a[down];
                a[down] = temp;
                up++;
                down--;
            }
        }
        return up;
    }

    public int partitionLast(T[] arr, int low, int high) {
        T pivot = arr[high];
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j].compareTo(pivot) <= 0) {
                i++;

                // swap arr[i] and arr[j]
                T temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        T temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }


    /*
        Chọn pivot, đầu dãy cuối dãy hoặc giữa dãy, thậm chí là ko nằm trong dãy
        chia dãy thành 2 phần, bên trái luôn nhỏ hơn pivot và bên phải luôn lớn hơn pivot
        đệ quy 2 phần đó.
     */
}
