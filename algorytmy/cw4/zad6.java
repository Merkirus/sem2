package cw4;

import cw3.ArrayQueue;
import cw3.EmptyQueueException;
import cw3.FullQueueException;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class zad6<T extends Comparable<? super T>> {
    private ArrayQueue<LinkedList<T>> queue;
    private final Comparator<T> comparator;
    public zad6()
    {
        comparator = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
        };
        queue = new ArrayQueue<>();
    }

    public void solve(LinkedList<T> linkedList) throws FullQueueException, EmptyQueueException {
        while (!linkedList.isEmpty()) {
            LinkedList<T> newLinkedList = new LinkedList<>();
            newLinkedList.add(linkedList.remove(0));
            queue.enqueue(newLinkedList);
        }
        while (queue.size() >= 2) {
            LinkedList<T> prev  = queue.dequeue();
            LinkedList<T> curr = queue.dequeue();
            queue.enqueue(merge(prev,curr));
        }
        for (T elem : queue.dequeue()) {
            System.out.print(elem);
            System.out.print(",");
        }
        System.out.println();
    }

    private LinkedList<T> merge(LinkedList<T> first, LinkedList<T> second) {
        LinkedList<T> result = new LinkedList<>();
        Iterator<T> l = first.iterator();
        Iterator<T> r = second.iterator();
        T elemL = null;
        T elemR = null;
        boolean contL, contR;
        if (contL=l.hasNext()) elemL = l.next();
        if (contR=r.hasNext()) elemR = r.next();
        while (contL && contR) {
            if (comparator.compare(elemL,elemR) <= 0) {
                result.add(elemL);
                if (contL=l.hasNext()) elemL=l.next();
                else result.add(elemR);
            } else {
                result.add(elemR);
                if (contR=r.hasNext()) elemR = r.next();
                else result.add(elemL);
            }
        }
        while(l.hasNext()) result.add(l.next());
        while(r.hasNext()) result.add(r.next());
        return result;
    }
}
