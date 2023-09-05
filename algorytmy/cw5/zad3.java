package cw5;

import cw3.*;
import cw3.EmptyStackException;
import lab6.HeadAndTailQueue;
import lab7.BubbleSort;

import java.util.*;

public class zad3 {
    public zad3() throws EmptyQueueException, FullQueueException, EmptyStackException, FullStackException {
        Random random = new Random();
        MergeSort<Integer> mergeSort = new MergeSort<>();
        QSort qSort = new QSort();
        int size = 200;

        int[] arrRandom = new int[size];
        for (int i = 0; i < size; i++)
            arrRandom[i] = random.nextInt(100);
        int[] arrRandom2 = new int[size];
        for (int i = 0; i < size; i++)
            arrRandom2[i] = arrRandom[i];

        LinkedList<Integer> list = new LinkedList<>();
        for (int i: arrRandom) {
                list.add(i);
        }
        mergeSort.solve(list);
        qSort.sort(arrRandom2);

        System.out.println("RANDOM");
        System.out.println("MERGESORT");
        System.out.println("Przyrówania: " + mergeSort.przyrownania + " Przypisania: " + mergeSort.przypisania);
        System.out.println("QuickSort");
        System.out.println("Przyrówania: " + qSort.przyrownania + " Przypisania: " + qSort.przypisania);

        mergeSort = new MergeSort<>();
        qSort = new QSort();

        int[] arrInc = new int[size];
        for (int i = 0; i < size; i++)
            arrInc[i] = i;
        int[] arrInc2 = new int[size];
        for (int i = 0; i < size; i++)
            arrInc2[i] = arrInc[i];

        LinkedList<Integer> list2 = new LinkedList<>();
        for (int i: arrInc) {
            list2.add(i);
        }
        mergeSort.solve(list2);
        qSort.sort(arrInc2);

        System.out.println("Increasing");
        System.out.println("MERGESORT");
        System.out.println("Przyrówania: " + mergeSort.przyrownania + " Przypisania: " + mergeSort.przypisania);
        System.out.println("QuickSort");
        System.out.println("Przyrówania: " + qSort.przyrownania + " Przypisania: " + qSort.przypisania);

        mergeSort = new MergeSort<>();
        qSort = new QSort();

        int[] arrDec = new int[size];
        for (int i = 0; i < size; i++)
            arrDec[i] = size-1-i;
        int[] arrDec2 = new int[size];
        for (int i = 0; i < size; i++)
            arrDec2[i] = arrDec[i];

        LinkedList<Integer> list3 = new LinkedList<>();
        for (int i: arrDec) {
            list3.add(i);
        }
        mergeSort.solve(list3);
        qSort.sort(arrDec2);

        System.out.println("Decreasing");
        System.out.println("MERGESORT");
        System.out.println("Przyrówania: " + mergeSort.przyrownania + " Przypisania: " + mergeSort.przypisania);
        System.out.println("QuickSort");
        System.out.println("Przyrówania: " + qSort.przyrownania + " Przypisania: " + qSort.przypisania);

    }
}

class MergeSort<T extends Comparable<? super T>> {
    int przyrownania;
    int przypisania;
    private HeadAndTailQueue<LinkedList<T>> queue;
    private final Comparator<T> comparator;
    public MergeSort()
    {
        comparator = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
        };
        queue = new HeadAndTailQueue<>();
    }

    public void solve(LinkedList<T> linkedList) throws FullQueueException, EmptyQueueException, EmptyStackException, FullStackException {
        przyrownania++;
        for (;!linkedList.isEmpty();przyrownania++) {
            LinkedList<T> newLinkedList = new LinkedList<>();
            przypisania++;
            newLinkedList.add(linkedList.remove(0));
            przypisania++;
            queue.enqueue(newLinkedList);
        }
        przyrownania++;
        for (;queue.size() >= 2;przyrownania++) {
            LinkedList<T> prev  = queue.dequeue();
            LinkedList<T> curr = queue.dequeue();
            przypisania++;
            queue.enqueue(merge(prev,curr));
        }
    }

    private LinkedList<T> merge(LinkedList<T> first, LinkedList<T> second) {
        LinkedList<T> result = new LinkedList<>();
        Iterator<T> l = first.iterator();
        Iterator<T> r = second.iterator();
        T elemL = null;
        T elemR = null;
        boolean contL, contR;
        przyrownania++;
        if (contL=l.hasNext()) elemL = l.next();
        przyrownania++;
        if (contR=r.hasNext()) elemR = r.next();
        przyrownania++;
        for (;contL && contR;przyrownania++) {
            przyrownania++;
            if (comparator.compare(elemL,elemR) <= 0) {
                result.add(elemL);
                przyrownania++;
                if (contL=l.hasNext()) elemL=l.next();
                else result.add(elemR);
            } else {
                result.add(elemR);
                przyrownania++;
                if (contR=r.hasNext()) elemR = r.next();
                else result.add(elemL);
            }
        }
        przyrownania++;
        for(;l.hasNext();przyrownania++) result.add(l.next());
        przyrownania++;
        for(;r.hasNext();przyrownania++) result.add(r.next());
        return result;
    }
}

class QSort {
    int przyrownania;
    int przypisania;

    static Random random = new Random();

    public void sort(int[] arr) {
        quicksort(arr, 0, arr.length-1);
    }

    private int partition(int[] arr, int left, int right) {
        int i = left;
        int j = right+1;
        int randomIndex = random.nextInt(i,j);
        swap(arr, i, randomIndex);
        int pivot = arr[i];
        while (true) {
            przyrownania++;
            for (;arr[++i] < pivot; przyrownania++) {
                if (i == right) break;
            }
            przyrownania++;
            for (;pivot < arr[--j]; przyrownania++) {
                if (j == left) break;
            }
            przyrownania++;
            if (i >= j) {
                break;
            }
            swap(arr, i , j);
        }
        swap(arr, left, j);
        return j;
    }

    private int super_partition(int[] arr, int left, int right) {
        int i = left;
        int j = right+1;

        int randomIndex0 = random.nextInt(i,j);
        int randomIndex1 = random.nextInt(i,j);
        int randomIndex2 = random.nextInt(i,j);
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(arr[randomIndex0], randomIndex0);
        map.put(arr[randomIndex1], randomIndex1);
        map.put(arr[randomIndex2], randomIndex2);
        int[] randomPivots = {arr[randomIndex0], arr[randomIndex1], arr[randomIndex2]};
        sort(randomPivots);
        swap(arr, i, map.get(randomPivots[1]));
        int pivot = arr[i];
        while (true) {
            przyrownania++;
            for (;arr[++i] < pivot; przyrownania++) {
                if (i == right) break;
            }
            przyrownania++;
            for (;pivot < arr[--j]; przyrownania++) {
                if (j == left) break;
            }
            przyrownania++;
            if (i >= j) {
                break;
            }
            swap(arr, i , j);
        }
        swap(arr, left, j);
        return j;
    }

    private void swap(int[] arr, int left, int right) {
        przyrownania++;
        if (left == right) {
            return;
        }
        przypisania++;
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    private void quicksort(int[] arr, int begin, int end) {
        przyrownania++;
        if (end <= begin) {
            return;
        }
        int pivot;
        przyrownania++;
        if ((end+1) - begin > 100) {
            pivot = super_partition(arr,begin,end);
        }
        else
            pivot = partition(arr, begin, end);
        quicksort(arr, begin, pivot-1);
        quicksort(arr, pivot+1, end);
    }
}

