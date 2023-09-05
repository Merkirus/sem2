package cw3;

public interface IQueue<T> {
    boolean isEmpty();
    boolean isFull();
    T dequeue() throws EmptyStackException, EmptyQueueException;
    void enqueue(T elem) throws FullQueueException, FullStackException, EmptyStackException;
    int size();
    T first() throws EmptyStackException, EmptyQueueException;
}
