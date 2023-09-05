package lab5;

import java.io.Serializable;
import java.util.Iterator;

public class L2KCzS<E> implements Iterable<E> {

    private class Element {
        private E value;
        private Element next;
        private Element prev;

        public E getValue() {return value;}
        public void setValue(E value) {this.value = value;}
        public Element getNext() {return next;}
        public void setNext(Element next) {this.next = next;}
        public Element getPrev() {return prev;}
        public void setPrev(Element prev) {this.prev = prev;}
        Element(E value) {this.value = value;}
        public void insertAfter(Element element) {
            element.setNext(this.getNext());
            element.setPrev(this);
            this.getNext().setPrev(element);
            this.setNext(element);
        }
        public void insertBefore(Element element) {
            element.setNext(this);
            element.setPrev(this.getPrev());
            this.getPrev().setNext(element);
            this.setPrev(element);
        }
        public void remove() {
            this.getNext().setPrev(this.getPrev());
            this.getPrev().setNext(this.getNext());
        }
    }

    private Element sentinel = null;

    public L2KCzS() {
        sentinel = new Element(null);
        sentinel.setNext(sentinel);
        sentinel.setPrev(sentinel);
    }
    private Element getElement(int index) {
        if (index < 0) throw new IndexOutOfBoundsException();
        Element element = sentinel.getNext();
        int counter = 0;
        while (element != sentinel && counter < index) {
            counter++;
            element = element.getNext();
        }
        if (element == sentinel) throw new IndexOutOfBoundsException();
        return element;
    }

    public boolean isEmpty() {
        return sentinel.getNext() == sentinel;
    }

    public void clear() {
        sentinel.setNext(sentinel);
        sentinel.setPrev(sentinel);
    }

    public int indexOf(E value) {
        Element element = sentinel.getNext();
        int counter = 0;
        while (element != sentinel && !element.getValue().equals(value)) {
            counter++;
            element = element.getNext();
        }
        if (element == sentinel) return -1;
        return counter;
    }

    public boolean contains(E value) {
        return indexOf(value) != -1;
    }

    public E get(int index) {
        return getElement(index).getValue();
    }

    public void set(int index, E value) {
        getElement(index).setValue(value);
    }

    public boolean add(E value) {
        sentinel.insertBefore(new Element(value));
        return true;
    }

    public boolean add(int index, E value) {
        if (index == 0) sentinel.insertAfter(new Element(value));
        else getElement(index-1).insertAfter(new Element(value));
        return true;
    }

    public E remove(int index) {
        E value = getElement(index).getValue();
        getElement(index).remove();
        return value;
    }

    public int size() {
        Element element = sentinel.getNext();
        int counter = 0;
        while (element != sentinel) {
            counter++;
            element = element.getNext();
        }
        return counter;
    }

    public Iterator<E> iterator() {
        return new TWCIterator();
    }

    private class TWCIterator implements Iterator<E> {

        boolean wasNext = false;
        boolean wasPrev = false;

        Element current = sentinel;

        @Override
        public boolean hasNext() {
            return current.getNext() != sentinel;
        }

        public boolean hasPrev() {
            return current != sentinel;
        }

        @Override
        public E next() {
            wasNext = true;
            wasPrev = false;
            current = current.getNext();
            return current.getValue();
        }

        public E prev() {
            wasPrev = true;
            wasNext = false;
            E value = current.getValue();
            current = current.getPrev();
            return value;
        }

        public void remove() {
            if (wasNext) {
                Element curr = current.getPrev();
                current.remove();
                current = curr;
                wasNext = false;
            }
            if (wasPrev) {
                current.getNext().remove();
                wasPrev = false;
            }
        }

        public void add(E value) {
            current.insertAfter(new Element(value));
            current = current.getNext();
        }

        public void set(E value) {
            if (wasNext) {
                current.setValue(value);
                wasNext = false;
            }
            if (wasPrev) {
                current.getNext().setValue(value);
                wasPrev = false;
            }
        }
    }
}
