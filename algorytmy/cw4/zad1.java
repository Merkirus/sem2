package cw4;

import java.util.Comparator;

public class zad1<T extends Comparable<? super T>> {

    private final Comparator<T> comparator;

    public zad1() {
        this.comparator = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
        };
    }

    public void sort(T[] arr) {
        for (int i=arr.length-2; i >= 0; i--) {
            T value = arr[i];
            T temp;
            int j;
            for (j = i; j < arr.length-1 && comparator.compare(value,temp=arr[j+1]) < 0; j++)
                arr[j] = temp;
            arr[j] = value;
            for (T elem : arr) {
                System.out.print(elem);
                System.out.print(",");
            }
            System.out.println();
        }
    }
}
