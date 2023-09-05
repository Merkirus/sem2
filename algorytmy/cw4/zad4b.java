package cw4;

import java.util.Comparator;

public class zad4b<T extends Comparable<? super T>> {
    private final Comparator<T> comparator;

    public zad4b() {
        comparator = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
        };
    }

    public void sort(T[] arr) {
        int begin = 0;
        int end = arr.length-1;
        boolean swap = true;
        while (swap) {
            swap = false;
            for (int i=begin; i < end; i++) {
                if (comparator.compare(arr[i], arr[i+1]) > 0) {
                    T temp = arr[i];
                    arr[i]  = arr[i+1];
                    arr[i+1] = temp;
                    swap = true;
                }
            }
            end--;
            if(!swap) break;
            swap = false;
            for (int j=end; j > begin; j--) {
                if (comparator.compare(arr[j], arr[j-1]) < 0) {
                    T temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                    swap = true;
                }
            }
            begin++;
            for (T elem : arr) {
                System.out.print(elem);
                System.out.print(",");
            }
            System.out.println();
        }
    }
}
