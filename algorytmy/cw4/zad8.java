package cw4;

import java.util.Comparator;

public class zad8<T extends Comparable<? super T>> {
    private final Comparator<T> comparator;
    public zad8() {
        comparator = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
        };
    }
    public void sort(Integer[] arr, int k) {
        k++;
        int n = arr.length;
        int[] pos = new int[k];
        int[] result = new int[n];
        int i,j;
        for (i=0;i<k;i++)
            pos[i]=0;
        for (j=0;j<n;j++)
            pos[arr[j]]++;
        pos[0]--;
        for (i=1;i<k;i++)
            pos[i]+=pos[i-1];
        for (int elem : pos) {
            System.out.print(elem);
            System.out.print(",");
        }
        System.out.println();
        for(j=n-1;j>=0;j--) {
            result[pos[arr[j]]] = arr[j];
            pos[arr[j]]--;
            for (int elem : result) {
                System.out.print(elem);
                System.out.print(",");
            }
            System.out.println();
            for (int elem : pos) {
                System.out.print(elem);
                System.out.print(",");
            }
            System.out.println();
        }
        for(j=0;j<n;j++)
            arr[j]=result[j];
    }
}
