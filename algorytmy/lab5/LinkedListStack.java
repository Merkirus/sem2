package lab5;

import lab3.OneWayLinkedListWithHead;
import lab4.EmptyStackException;
import lab4.FullStackException;
import lab4.IStack;

public class LinkedListStack<E> implements IStack<E> {

    private OneWayLinkedListWithHead<E> list;

    public LinkedListStack() {
        this.list = new OneWayLinkedListWithHead<>();
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
    public E pop() throws EmptyStackException {
        E value = list.remove(0);
        if (value == null) throw new EmptyStackException();
        return value;
    }

    @Override
    public void push(E elem) throws FullStackException {
        list.add(0, elem);
    }

    @Override
    public int size() {
        return list.size();
    }

    public E top() throws EmptyStackException {
        E value = list.get(0);
        if (value == null) throw new EmptyStackException();
        return value;
    }
}
