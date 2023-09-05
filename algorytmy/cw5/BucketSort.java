package cw5;

import cw3.IQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BucketSort {
    /* N ilość kubełków */
    public static void sort(int[] arr, int n) {
        if (n < 1) return;

        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < n; i++)
            buckets.add(new ArrayList<>());

        int max = 0;

        if (arr.length != 0)
            max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i])
                max = arr[i];
        }

        max++;

        for (int j : arr) {
            int partition = (int) Math.floor((double) j / max * n);
            buckets.get(partition).add(j);
        }

        ArrayList<Algo> algos = new ArrayList<>();
        Algo insertSort = new InsertSort();
        Algo bubbleSort = new BubbleSort();
        Algo selectSort = new SelectSort();
        Algo quickSort = new QuickSort();
        algos.add(insertSort);
        algos.add(bubbleSort);
        algos.add(selectSort);
        algos.add(quickSort);

        Random rand = new Random();

        for (ArrayList<Integer> bucket : buckets) {
            int algorytm = rand.nextInt(algos.size());
            algos.get(algorytm).sort(bucket);
        }

        int index = 0;
        for (ArrayList<Integer> bucket : buckets) {
            for (Integer i : bucket) {
                arr[index] = i;
                index++;
            }
        }
    }
}

abstract class Algo {
    public abstract void sort(List<Integer> bucket);
}

class InsertSort extends Algo {
    @Override
    public void sort(List<Integer> bucket) {
        for (int i = 1; i < bucket.size(); i++) {
            int value = bucket.get(i);
            int temp, j;
            for (j = i; j > 0 && value < (temp=bucket.get(j-1)); j--)
                bucket.set(j, temp);
            bucket.set(j, value);
        }
    }
}

class BubbleSort extends Algo {
    @Override
    public void sort(List<Integer> bucket) {
        for (int i = 0; i < bucket.size(); i++) {
            for (int right = 1; right < bucket.size() - i; right++) {
                int left = right - 1;
                if (bucket.get(left) > bucket.get(right)) {
                    int temp = bucket.get(left);
                    bucket.set(left, bucket.get(right));
                    bucket.set(right, temp);
                }
            }
        }
    }
}

class SelectSort extends Algo {
    @Override
    public void sort(List<Integer> bucket) {
        int size = bucket.size();
        for (int left = 0; left < size; left++) {
            int smallest = left;
            for (int right = left + 1; right < size; right++) {
                if (bucket.get(right) < bucket.get(smallest))
                    smallest = right;
            }
            if (smallest != left) {
                int temp = bucket.get(smallest);
                bucket.set(smallest, bucket.get(left));
                bucket.set(left, temp);
            }
        }
    }
}

class QuickSort extends Algo {
    @Override
    public void sort(List<Integer> bucket) {
        quicksort(bucket, 0, bucket.size()-1);
    }

    private int partition(List<Integer> bucket, int left, int right) {
        int i = left;
        int j = right + 1;
        int pivot = bucket.get(i);
        while (true) {
            while (bucket.get(++i) < pivot)
                if (i == right) break;
            while (pivot < bucket.get(--j))
                if (j == left) break;
            if (i >= j) break;
            swap(bucket, i, j);
        }
        swap(bucket, left, j);
        return j;
    }

    private void quicksort(List<Integer> bucket, int begin, int end) {
        if (end <= begin) return;
        int pivot = partition(bucket, begin, end);
        quicksort(bucket, begin, pivot-1);
        quicksort(bucket, pivot+1, end);
    }

    private void swap(List<Integer> bucket, int left, int right) {
        if (left == right) return;
        int temp = bucket.get(left);
        bucket.set(left, bucket.get(right));
        bucket.set(right, temp);
    }
}
