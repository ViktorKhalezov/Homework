package ru.geekbrains;

import java.util.Arrays;
import java.util.EmptyStackException;

public class PriorityQueueHomework<T extends Comparable<T>> {
    private T[] list;
    private int size;
    private final int DEFAULT_CAPACITY = 10;

    public PriorityQueueHomework(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        list = (T[]) new Comparable[capacity];
    }

    public PriorityQueueHomework() {
        list = (T[]) new Comparable[DEFAULT_CAPACITY];
    }

    public void insert(T item) {
        if (isFull()) {
           // throw new StackOverflowError();
            //
            int newLength = (int) (list.length * 1.5 + 1);
            T[] newList = (T[]) new Comparable[newLength];
            System.arraycopy(list, 0, newList, 0, size);
            list = newList;
        }
        list[size] = item;
        size++;
    }

    public T remove() {
        T maxPriorityElement = list[0];
        int maxPriorityIndex = 0;
        for(int i = 0; i < size; i++){
            if(list[i].compareTo(maxPriorityElement) > 0) {
                maxPriorityElement = list[i];
                maxPriorityIndex = i;
            }
        }
        swap(maxPriorityIndex, size - 1);
        size--;
        list[size] = null;
        return maxPriorityElement;
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T maxPriorityElement = list[0];
        for(int i = 1; i < size; i++) {
            if(list[i].compareTo(maxPriorityElement) > 0) {
                maxPriorityElement = list[i];
            }
        }
        return maxPriorityElement;
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

    private void swap(int index1, int index2) {
        T temp = list[index1];
        list[index1] = list[index2];
        list[index2] = temp;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(list, size));
    }

}
