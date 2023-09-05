package cw3;

public class EffectiveDrowningStack<T> implements IStack<T> {

    private static final int DEFAULT_CAPACITY = 16;
    private T[] array;
    private int topIndex;
    private int bottomIndex;

    @SuppressWarnings("unchecked")
    public EffectiveDrowningStack(int initialCapacity) {
        this.array = (T[])(new Object[initialCapacity+1]);
        this.topIndex = 0;
        this.bottomIndex = 0;
    }

    public EffectiveDrowningStack() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public boolean isEmpty() {
        return topIndex == bottomIndex;
    }

    @Override
    public boolean isFull() {
        if (isEmpty()) return false;
        if (bottomIndex > topIndex) return (bottomIndex - topIndex) == 1;
        return (topIndex-bottomIndex) == array.length-1;
    }

    @Override
    public T pop() throws EmptyStackException {
        if(isEmpty())
            throw new EmptyStackException();
        popHelp(topIndex);
        return array[--topIndex];
    }

    @Override
    public void push(T elem) throws FullStackException {
        if (isFull()) {
            shift(bottomIndex);
            shift(topIndex, elem);
            return;
        }
        shift(topIndex, elem);
    }

    @Override
    public int size() {
        if (isEmpty()) return 0;
        if (topIndex > bottomIndex) return topIndex - bottomIndex;
        return (array.length-bottomIndex+topIndex);
    }

    public T top() throws EmptyStackException {
        if(isEmpty())
            throw new EmptyStackException();
        int temp = topHelp(topIndex);
        return array[temp-1];
    }

    // True for bottom, False for top
    private void shift(int index) {
        if (index == array.length-1) {
            bottomIndex = 0;
        } else {
            bottomIndex++;
        }
    }

    private void shift(int index, T elem) {
        if (index == array.length-1) {
            array[topIndex] = elem;
            topIndex = 0;
            return;
        }
        array[topIndex++] = elem;
    }

    private void popHelp(int index) {
        if (index == 0) {
            topIndex = array.length;
        }
    }

    private int topHelp(int index) {
        if (index == 0) {
            return array.length;
        }
        return topIndex;
    }
}
