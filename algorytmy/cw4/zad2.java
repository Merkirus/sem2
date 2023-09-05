package cw4;

import java.util.Comparator;

public class zad2<T extends Comparable<? super T>> {
    private final Comparator<T> comparator;

    public zad2() {
        comparator = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
        };
    }

    public void sort(T[] arr) {
        for (int right = arr.length-1; right >= 0; right--) {
            int smallest = right;
            for (int left = right-1; left >= 0; left--) {
                if (comparator.compare(arr[left],arr[smallest]) < 0)
                    smallest = left;
            }
            swap(arr, smallest, right);
            for (T elem : arr) {
                System.out.print(elem);
                System.out.print(",");
            }
            System.out.println();
        }
    }

    private void swap(T[] arr, int left, int right) {
        if (left!=right) {
            T temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }
    }
}
