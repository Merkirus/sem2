package cw4;

import java.util.Comparator;

public class zad3<T extends Comparable<? super T>> {
    private final Comparator<T> comparator;

    public zad3() {
        comparator = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
        };
    }

    public void sort(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int right= arr.length-1; right > i; right--) {
                int left = right - 1;
                if (comparator.compare(arr[left], arr[right]) < 0) {
                    T temp = arr[left];
                    arr[left] = arr[right];
                    arr[right] = temp;
                }
            }

            for (T elem : arr) {
                System.out.print(elem);
                System.out.print(",");
            }
            System.out.println();
        }
    }
}
