package cw4;

import java.util.Comparator;
import java.util.List;

public class zad7<T extends Comparable<? super T>> {
    private final Comparator<T> comparator;
    public zad7() {
        comparator = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
        };
    }

    public List<T> sort(List<T> list) {
        heapsort(list, list.size());
        return list;
    }

    private void heapsort(List<T> list, int n) {
        heapAdjustment(list,n);
        for (T elem : list) {
            System.out.print(elem);
            System.out.print(",");
        }
        System.out.println("Stworzenie stogu");
        for (int i=n-1;i>0;i--) {
            swap(list,i,0);
            sink(list,0,i);
            for (T elem : list) {
                System.out.print(elem);
                System.out.print(",");
            }
            System.out.println();
        }
    }

    private void swap(List<T> list, int left, int right) {
        T temp = list.get(left);
        list.set(left, list.get(right));
        list.set(right, temp);
    }

    public void sink(List<T> list, int index, int n) {
        int indexOfBigger = 2*index+1;
        if (indexOfBigger<n) {
            if (indexOfBigger+1<n &&
            comparator.compare(list.get(indexOfBigger),list.get(indexOfBigger+1))<0) indexOfBigger++;
            if (comparator.compare(list.get(index), list.get(indexOfBigger))<0) {
                swap(list,index,indexOfBigger);
                sink(list,indexOfBigger,n);
            }
        }
    }

    public void heapAdjustment(List<T> list, int n) {
        for (int i=(n-1)/2; i>=0; i--) {
            sink(list, i, n);
        }
    }
}
