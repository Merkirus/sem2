package lab7;

import java.util.Random;

public class QuickSort {
    public static void sort(int[] arr) {
        quicksort(arr, 0, arr.length-1);
    }

    private static int partition(int[] arr, int left, int right) {
        int i = left;
        int j = right+1;
        int pivot = arr[i];
        while (true) {
            while (arr[++i] < pivot)
                if (i == right) break;
            while (pivot < arr[--j])
                if (j == left) break;
            if (i >= j) break;
            swap(arr, i , j);
        }
        swap(arr, left, j);
        return j;
    }

    private static void swap(int[] arr, int left, int right) {
        if (left == right) return;
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    private static void quicksort(int[] arr, int begin, int end) {
        if (end <= begin) return;
        int pivot = partition(arr, begin, end);
        quicksort(arr, begin, pivot-1);
        quicksort(arr, pivot+1, end);
    }
}
