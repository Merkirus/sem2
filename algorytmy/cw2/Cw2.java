package cw2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Cw2 {

    public static void main(String[] args) {
/*        ArrayList<Integer> arr = new ArrayList<>();
        for (int i=0; i<10; i++) {
            arr.add(i);
        }

        Iterator<Integer> arrIt = arr.iterator();
        cw2.KthIterator<Integer> it = new cw2.KthIterator<>(arrIt, 2);

        while (it.hasNext()) {
            System.out.println(it.next());
        }*/

/*        cw2.FiboIterator it = new cw2.FiboIterator<>(2);

        while (it.hasNext()) {
            System.out.println(it.next());
        }*/

/*        ArrayList<Integer> arr1 = new ArrayList<>(List.of(-2,-1,0,1,2,3,4));
        ArrayList<Integer> arr2 = new ArrayList<>(List.of(10,11,12,13,14,15,16,17,18));

        Iterator<Integer> it1 = arr1.iterator();
        Iterator<Integer> it2 = arr2.iterator();

        cw2.ShuffleIterator<Integer> it = new cw2.ShuffleIterator<>(it1,it2);

        while (it.hasNext()) {
            System.out.println(it.next());
        }*/

        PrimeIterator it = new PrimeIterator(3, new PrimePredicate());

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

}

class NextIterator implements Iterator<Integer> {

    private int nextElem;
    private boolean hasNext;

    private int n;
    private int pos;

    public NextIterator(int n) {
        this.n = n;
        takeNext();
    }

    private void takeNext() {
        pos++;
    }

    @Override
    public boolean hasNext() {
        return pos < n;
    }

    @Override
    public Integer next() {
        Integer result = nextElem;
        takeNext();
        return result;
    }
}

class PIterator implements Iterator<Integer> {

    private int nextElem;
    private boolean hasNext;

    private int n;
    Iterator<Integer> it;

    public PIterator(int n) {
        this.n = n;
        it = new NextIterator(n);
        foo();
    }

    private void foo() {
        if (it.hasNext()) {
            nextElem = it.next();

        }
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public Integer next() {
        Integer result = nextElem;
        foo();
        return null;
    }
}

class PrimeIterator implements Iterator<Integer> {

    private final Predicate<Integer> predicate;

    private int nextElem;
    private boolean hasNext;

    private final int n;

    public PrimeIterator(int n, Predicate<Integer> predicate) {
        this.n = n;
        this.predicate = predicate;
        generatePrimeNums();
    }

    private void generatePrimeNums() {
        if (n<=1) return;
        int prime = nextElem + 1;
        while (!predicate.accept(prime)) {
            prime++;
        }
        if (prime > n) {
            hasNext = false;
            return;
        }
        nextElem = prime;
        hasNext = true;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public Integer next() {
        int result = nextElem;
        generatePrimeNums();
        return result;
    }
}

class FiboIterator implements Iterator<Integer> {

    private int pop = 1;
    private int ob = 1;

    private final int startIlosc;
    private int ilosc;

    private int nextElem;

    public FiboIterator(int n) {
        this.ilosc = n;
        this.startIlosc = n;
        fib();
    }

    private void fib() {
        nextElem = pop + ob;
        int temp = ob;
        ob += pop;
        pop = temp;
    }

    @Override
    public boolean hasNext() {
        ilosc--;
        return ilosc>=0;
    }

    @Override
    public Integer next() {
        int result = nextElem;
        if (ilosc==startIlosc-1 || ilosc==startIlosc-2) {
            return 1;
        } else {
            fib();
            return result;
        }
    }
}

class ShuffleIterator<T> implements Iterator<T> {

    private final Iterator<T> iterator1;
    private final Iterator<T> iterator2;

    private boolean isIt1;
    private boolean isIt2;

    private boolean emptyIt1 = false;
    private boolean emptyIt2 = false;

    private T nextElem;

    public ShuffleIterator(Iterator<T> iterator1, Iterator<T> iterator2) {
        super();
        this.iterator1 = iterator1;
        this.iterator2 = iterator2;

        isIt1 = true;
        isIt2 = false;

        shuffle();
    }

    private void shuffle() {
        if (isIt1) {
            if (iterator1.hasNext()) {
                addIt1();
                isIt1 = false;
                isIt2 = true;
                return;
            } else {
                isIt1 = false;
                isIt2 = true;
                emptyIt1 = true;
            }
        }
        if (isIt2) {
            if (iterator2.hasNext()) {
                addIt2();
                isIt2 = false;
                isIt1 = true;
            } else {
                isIt2 = false;
                isIt1 = true;
                emptyIt2 = true;
            }
        }
    }

    private void addIt1() {
        nextElem = iterator1.next();
    }

    private void addIt2() {
        nextElem = iterator2.next();
    }

    @Override
    public boolean hasNext() {
        return !emptyIt1 || !emptyIt2;
    }

    @Override
    public T next() {
        T result = nextElem;
        shuffle();
        return result;
    }
}

class KthIterator<T> implements Iterator<T> {

    private final Iterator<T> iterator;
    private final int k;

    private T nextElem;
    private boolean hasNext;

    public KthIterator(Iterator<T> iterator, int k) {
        super();
        this.iterator = iterator;
        this.k = k;
        checkForKth();
    }

    private void checkForKth() {
        for (int i=k; i>0; i--) {
            if (iterator.hasNext()) {
                nextElem = iterator.next();
            } else {
                nextElem = null;
                hasNext = false;
                return;
            }
        }
        hasNext = true;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public T next() {
        T result = nextElem;
        checkForKth();
        return result;
    }
}

interface Predicate<T> {
    boolean accept(T arg);
}

class PrimePredicate implements Predicate<Integer> {
    @Override
    public boolean accept(Integer arg) {
        if (arg < 2) return false;
        for (int iloraz=2; iloraz <= arg/2; iloraz++) {
            if (arg % iloraz == 0) {
                return false;
            }
        }
        return true;
    }
}

class ArrayIterator<T> implements Iterator<T> {
    private T[] array;
    private int pos = 0;

    public ArrayIterator(T[] array) {
        this.array = array;
    }
    public boolean hasNext() {
        return pos < array.length;
    }
    public T next() throws NoSuchElementException {
        if (hasNext()) {
            return array[pos++];
        } else {
            throw new NoSuchElementException();
        }
    }
    public void remove() {
        Object[] copy = new Object[array.length-1];
        System.arraycopy(array, 0, copy, 0, pos);
        System.arraycopy(array, pos+1, copy, pos, array.length-pos);
        array = (T[]) copy;
    }
    public void remove1() {
        // Solution for two iterators:
        // Nie można usunąć wart. w trakcie pracy drugiego iteratora, więc proponowałbym zmianę wart. na null
        // i dla arr[i] = null && i < array.length, po zakonczeniu dzialania iteratorow usuwamy nulla normalnym removem
        // ALBO tak jak wczesniej tworzylismy kopie, to robimy tym samym sposobem, ale nie implementujemy kopii do
        // czasu zakonczenia wszystkich iteracji, wtedy np flaga isIterated, żeby był znany stan array.
    }

}
