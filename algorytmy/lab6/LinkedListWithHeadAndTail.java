package lab6;

import java.util.Iterator;

public class LinkedListWithHeadAndTail<E> implements Iterable<E> {

    private class Element {
        private E value;
        private Element next;

        private Element(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Element getNext() {
            return next;
        }

        public void setNext(Element next) {
            this.next = next;
        }
    }

    private Element head;
    private Element tail;

    public LinkedListWithHeadAndTail() {}

    private Element getElement(int index) {
        if (index < 0) throw new IndexOutOfBoundsException();
        Element curr = head;
        while (index > 0 && curr!=null) {
            index--;
            curr = curr.getNext();
        }
        if (curr == null) throw new IndexOutOfBoundsException();
        return curr;
    }

    public E get(int index) {
        return getElement(index).getValue();
    }

    public boolean add(int index, E wartosc) {
        if (index < 0) throw new IndexOutOfBoundsException();
        Element temp = new Element(wartosc);
        if (index == 0) {
            temp.setNext(head);
            head = temp;
            return true;
        }
        Element prev = getElement(index-1);
        temp.setNext(prev.getNext());
        prev.setNext(temp);
        return true;
    }

    public boolean add(E wartosc) {
        Element temp = new Element(wartosc);
        if (head == null) {
            head = temp;
        } else {
            tail.setNext(temp);
        }
        tail = temp;
        return true;
    }

    public E remove(int index) {
        if (index < 0 || head == null) throw new IndexOutOfBoundsException();
        if (index == 0) {
            E headValue = head.getValue();
            if (tail != head) {
                head = head.getNext();
            } else {
                head = null;
                tail = null;
            }
            return headValue;
        }
        Element curr = getElement(index-1);
        if (curr == tail) throw new IndexOutOfBoundsException();
        E result = curr.getNext().getValue();
        if (curr.getNext() == tail) {
            tail = curr;
        }
        curr.setNext(curr.getNext().getNext());
        return result;
    }

    public boolean isEmpty() {
        return head==null;
    }

    public int size() {
        int pos = 0;
        Element curr = head;
        while (curr != null) {
            pos++;
            curr = curr.getNext();
        }
        return pos;
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorLL();
    }

    private class IteratorLL implements Iterator<E> {

        Element curr;

        public IteratorLL() {
            curr = head;
        }

        @Override
        public boolean hasNext() {
            return curr!=null;
        }

        @Override
        public E next() {
            E value = curr.getValue();
            curr = curr.getNext();
            return value;
        }
    }
}
