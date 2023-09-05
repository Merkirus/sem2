package cw3;

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

}
