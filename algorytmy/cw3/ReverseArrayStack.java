package cw3;

import java.util.Arrays;

public class ReverseArrayStack<T> implements IStack<T> {

    private static final int DEFAULT_CAPACITY = 16;
    private T[] array;
    private int topIndex;

    @SuppressWarnings("unchecked")
    public ReverseArrayStack(int initialSize){
        this.array = (T[])(new Object[initialSize]);
        this.topIndex = 0;
    }

    public ReverseArrayStack(){
        this(DEFAULT_CAPACITY);
    }

    //TODO bez kopiowania, przechowywac jedna zmienna i zmieniac jej kolejnosc
    // Dla 3 stosow 3 przerzucenia i done
    public void reverseStack() throws FullStackException, EmptyStackException {
        ReverseArrayStack<T> helpStack = new ReverseArrayStack<>(array.length);
        int tempIndex = this.topIndex;
        while (!this.isEmpty()) {
            helpStack.push(this.pop());
        }
    }

    @Override
    public boolean isEmpty() {
        return topIndex==0;
    }

    @Override
    public boolean isFull() {
        return topIndex==array.length;
    }

    @Override
    public T pop() throws EmptyStackException {
        if(isEmpty())
            throw new EmptyStackException();
        return array[--topIndex];
    }

    @Override
    public void push(T elem) throws FullStackException {
        if(isFull())
            throw new FullStackException();
        array[topIndex++]=elem;

    }

    @Override
    public int size() {
        return topIndex;
    }

    public T top() throws EmptyStackException {
        if(isEmpty())
            throw new EmptyStackException();
        return array[topIndex-1];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReverseArrayStack<?> that = (ReverseArrayStack<?>) o;
        return topIndex == that.topIndex && Arrays.equals(array, that.array);
    }
}
