package lab4;

import java.util.Arrays;

public class ArrayStack<T> implements IStack<T> {

	private static final int DEFAULT_CAPACITY = 16;
	private T[] array;
	private int topIndex;

	@SuppressWarnings("unchecked")
	public ArrayStack(int initialSize){
		this.array = (T[])(new Object[initialSize]);
		this.topIndex = 0;
	}
	
	public ArrayStack(){
		this(DEFAULT_CAPACITY);
	}

	public ArrayStack<T> reverseStack() throws FullStackException, EmptyStackException {
		ArrayStack<T> helpStack = new ArrayStack<>(array.length);
		int tempIndex = this.topIndex;
		while (!this.isEmpty()) {
			helpStack.push(this.pop());
		}
		this.topIndex = tempIndex;
		// Ponizsza metoda jezeli odwracamy obecny stos
		// copy(helpStack);
		return helpStack;
	}

	private void copy(ArrayStack<T> arrayStack) {
		this.array = arrayStack.array;
		this.topIndex = arrayStack.topIndex;
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
		ArrayStack<?> that = (ArrayStack<?>) o;
		return topIndex == that.topIndex && Arrays.equals(array, that.array);
	}

	@Override
	public String toString() {
		return "ArrayStack{" +
				"array=" + Arrays.toString(array) +
				", topIndex=" + topIndex +
				'}';
	}
}
