package lab6;

import cw3.*;

public class HeadAndTailQueue<E> implements IQueue<E> {

    private LinkedListWithHeadAndTail<E> queue;

    public HeadAndTailQueue() {
        this.queue = new LinkedListWithHeadAndTail<>();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public E dequeue() throws EmptyStackException {
        return queue.remove(0);
    }

    @Override
    public void enqueue(E elem) throws FullQueueException, FullStackException, EmptyStackException {
        queue.add(elem);
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public E first() throws EmptyStackException {
        return queue.get(0);
    }
}
