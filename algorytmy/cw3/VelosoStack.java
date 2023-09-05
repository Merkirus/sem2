package cw3;

public class VelosoStack<T> implements IStack<T> {

    private static final int DEFAULT_CAPACITY = 16;
    private T[] array;
    private int topIndex;
    private int caret;

    @SuppressWarnings("unchecked")
    public VelosoStack(int init) {
        this.array = (T[]) new Object[init];
        this.topIndex = 0;
        this.caret = 0;
    }

    @SuppressWarnings("unchecked")
    public VelosoStack() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
        this.topIndex = 0;
        this.caret = 0;
    }

    public void down() {
        if (caret == 1) {
            myTop();
            System.out.println("Bottom of the stack reached");
            System.out.println("Moved to the top");
        } else {
            caret--;
        }
    }

    public T peek() {
        return array[--caret];
    }

    @Override
    public boolean isEmpty() {
        return topIndex == 0;
    }

    @Override
    public boolean isFull() {
        return topIndex == array.length;
    }

    @Override
    public T pop() throws EmptyStackException {
        if(isEmpty())
            throw new EmptyStackException();
        T temp = array[topIndex-1];
        array[--topIndex] = null;
        caret = topIndex;
        return temp;
    }

    @Override
    public void push(T elem) throws FullStackException {
        if(isFull())
            throw new FullStackException();
        array[topIndex++]=elem;
        caret = topIndex;
    }

    @Override
    public int size() {
        return topIndex;
    }

    @Override
    public T top() throws EmptyStackException {
        if(isEmpty())
            throw new EmptyStackException();
        return array[topIndex-1];
    }

    public void myTop() {
        caret = topIndex;
    }
}
