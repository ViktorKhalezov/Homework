package ru.geekbrains;

import java.util.EmptyStackException;

public class MyStack<T> {
    private T[] list;
    private int size;
    private final int DEFAULT_CAPACITY = 10;

    public MyStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        list = (T[]) new Object[capacity];
    }

    public MyStack() {
        list = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public void push(T item) {
        if (isFull()) {
           // throw new StackOverflowError();
            //
            int newLength = (int) (list.length * 1.5 + 1);
            T[] newList = (T[]) new Object[newLength];
            System.arraycopy(list, 0, newList, 0, size);
            list = newList;
        }
        list[size] = item;
        size++;
    }

    public T pop() {
        T temp = peek();
        size--;
        list[size] = null;
        return temp;
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list[size - 1];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == list.length;
    }

    private void reCapacity(int newCapacity) {
        T[] tempArr = (T[]) new Object[newCapacity];
        System.arraycopy(list, 0, tempArr, 0, size);
        list = tempArr;
    }
}
