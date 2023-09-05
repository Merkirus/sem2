package cw5;

import lab7.BubbleSort;

import java.util.HashMap;
import java.util.Random;

public class zad2 {

    static Random random = new Random();

    public static void sort(int[] arr) {
        quicksort(arr, 0, arr.length-1);
    }

    private static int partition(int[] arr, int left, int right) {
        int i = left;
        int j = right+1;
        int randomIndex = random.nextInt(i,j);
        swap(arr, i, randomIndex);
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

    private static int super_partition(int[] arr, int left, int right) {
        int i = left;
        int j = right+1;

        int randomIndex0 = random.nextInt(i,j);
        int randomIndex1 = random.nextInt(i,j);
        int randomIndex2 = random.nextInt(i,j);
        /*TODO XD*/
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(arr[randomIndex0], randomIndex0);
        map.put(arr[randomIndex1], randomIndex1);
        map.put(arr[randomIndex2], randomIndex2);
        int[] randomPivots = {arr[randomIndex0], arr[randomIndex1], arr[randomIndex2]};
        BubbleSort.sort(randomPivots);
        /*KONIEC XD*/
        swap(arr, i, map.get(randomPivots[1]));
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
        int pivot;
        if ((end+1) - begin > 100)
            pivot = super_partition(arr,begin,end);
        else
            pivot = partition(arr, begin, end);
        quicksort(arr, begin, pivot-1);
        quicksort(arr, pivot+1, end);
    }
}
