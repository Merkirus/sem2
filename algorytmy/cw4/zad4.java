package cw4;

import java.util.Comparator;

public class zad4<T extends Comparable<? super T>> {
    private final Comparator<T> comparator;

    public zad4() {
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
        while (begin<end) {
            for (int i=begin; i < arr.length-1; i++) {
                if (comparator.compare(arr[i], arr[i+1]) > 0) {
                    T temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                }
            }
            end--;

            for (int j=end; j > 0; j--) {
                if (comparator.compare(arr[j], arr[j-1]) < 0) {
                    T temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
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

    public void sortB(T[] arr) {
        int begin = 0;
        int end = arr.length-1;
        while (begin<end) {
            for (int i=begin; i < arr.length; i++) {
                if (comparator.compare(arr[i], arr[begin]) > 0) {
                    T temp = arr[i];
                    arr[i] = arr[begin];
                    arr[begin] = temp;
                }
            }
            begin++;

            for (int j=end; j >= 0; j--) {
                if (comparator.compare(arr[end], arr[j]) < 0) {
                    T temp = arr[j];
                    arr[j] = arr[end];
                    arr[end] = temp;
                }
            }
            end--;

            for (T elem : arr) {
                System.out.print(elem);
                System.out.print(",");
            }
            System.out.println();
        }
    }
}
