package cw3;

public class QueueStack<T> implements IQueue<T> {

    private static final int DEFAULT_CAPACITY = 16;
    private ArrayStack<T> arrayStack1;
    private ArrayStack<T> arrayStack2;

    public QueueStack(int initialSize) {
        this.arrayStack1 = new ArrayStack<>(initialSize);
        this.arrayStack2 = new ArrayStack<>(initialSize);
    }

    public QueueStack() {
        this(DEFAULT_CAPACITY);
    }


    @Override
    public boolean isEmpty() {
        return arrayStack2.isEmpty();
    }

    @Override
    public boolean isFull() {
        return arrayStack2.isFull();
    }

    //TODO dopiero gdy stack2.isEmpty to przerzucamy ze stack1, wiecej miejsca
    //Ale wszystko bo inaczej sie miesza
    @Override
    public T dequeue() throws EmptyStackException {
        if (arrayStack2.isEmpty()) throw new EmptyStackException();
        return arrayStack2.pop();
    }

    @Override
    public void enqueue(T elem) throws FullStackException, EmptyStackException {
        if (arrayStack2.isFull()) throw new FullStackException();
        arrayStack1.push(elem);
        arrayStack2.push(arrayStack1.pop());
    }

    @Override
    public int size() {
        return arrayStack2.size();
    }

    @Override
    public T first() throws EmptyStackException {
        if (arrayStack2.isEmpty()) throw new EmptyStackException();
        return arrayStack2.top();
    }
}
