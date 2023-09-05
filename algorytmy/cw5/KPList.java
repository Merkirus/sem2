package cw5;

import cw3.*;
import lab5.L2KCzS;

import java.util.Comparator;
import java.util.Iterator;

public class KPList<T extends Comparable<? super T>> implements IQueue<T> {

    private final L2KCzS<T> list;
    private final Comparator<T> comparator;

    public KPList(Comparator<T> comparator) {
        this.list = new L2KCzS<>();
        this.comparator = comparator;
    }

    public KPList() {
        this.list = new L2KCzS<>();
        this.comparator = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
        };
    }


    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public T dequeue() throws EmptyStackException, EmptyQueueException {
        if (list.isEmpty())
            throw new EmptyQueueException();
        return list.remove(getIndexOfLargestElement());
    }

    @Override
    public void enqueue(T elem) throws FullQueueException, FullStackException, EmptyStackException {
        list.add(elem);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public T first() throws EmptyStackException, EmptyQueueException {
        if (list.isEmpty())
            throw new EmptyQueueException();
        return list.get(getIndexOfLargestElement());
    }

    /*Changed comparator thus smallest element is returned*/
    private int getIndexOfLargestElement() {
        if (list.isEmpty())
            return -1;
        Iterator<T> iter = list.iterator();
        int counter = 0;
        int maxPos = 0;
        T value = iter.next();
        T elem = null;
        while (iter.hasNext()) {
            counter++;
            if (comparator.compare(elem = iter.next(), value) < 0) {
                maxPos = counter;
                value = elem;
            }
        }
        return maxPos;
    }
}
