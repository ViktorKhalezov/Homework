package ru.geekbrains;

public class MyDeque<T> {
    private T[] list;
    private int size;
    private final int DEFAULT_CAPACITY = 10;
    private int leftEnd;
    private int rightEnd;

    //0 1 2 3 4
    //      B
    //  E
    //4     3 2

    public MyDeque(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        list = (T[]) new Object[capacity];
    }

    public MyDeque() {
        list = (T[]) new Object[DEFAULT_CAPACITY];
    }


    public void insertLeft(T item) {
        if (previousIndex(leftEnd) == rightEnd) {
          //  throw new RuntimeException("Deque is full");
            int newLength = (int) (list.length * 1.5 + 1);
            T[] newList = (T[]) new Object[newLength];
            int j = 0;

                for(int i = leftEnd; i < list.length; i++) {
                    newList[j] = list[i];
                    j++;
                }
                for(int i = 0; i <= rightEnd; i++) {
                    newList[j] = list[i];
                }
            list = newList;
            leftEnd = 0;
            rightEnd = size - 1;
        }
        size++;
        leftEnd = previousIndex(leftEnd);
        list[leftEnd] = item;
        if(size == 1) {
            rightEnd = leftEnd;
        }
    }

    public void insertRight(T item) {
        if (previousIndex(leftEnd) == rightEnd) {
            //  throw new RuntimeException("Deque is full");
            int newLength = (int) (list.length * 1.5 + 1);
            T[] newList = (T[]) new Object[newLength];
            int j = 0;

            for(int i = leftEnd; i < list.length; i++) {
                newList[j] = list[i];
                j++;
            }
            for(int i = 0; i <= rightEnd; i++) {
                newList[j] = list[i];
            }
            list = newList;
            leftEnd = 0;
            rightEnd = size - 1;
        }
        size++;
        rightEnd = nextIndex(rightEnd);
        list[rightEnd] = item;
        if(size == 1) {
            leftEnd = rightEnd;
        }
    }


    public T removeLeft() {
        T temp = peekLeft();
        list[leftEnd] = null;
        leftEnd = nextIndex(leftEnd);
        size--;
        return temp;
    }

    public T removeRight() {
        T temp = peekRight();
        list[rightEnd] = null;
        rightEnd = previousIndex(rightEnd);
        size--;
        return temp;
    }


    public T peekLeft() {
        if (isEmpty()) {
            throw new RuntimeException("Deque is empty");
        }
        return list[leftEnd];
    }

    public T peekRight() {
        if (isEmpty()) {
            throw new RuntimeException("Deque is empty");
        }
        return list[rightEnd];
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


    private int nextIndex(int index) {
        return (index + 1) % list.length;
    }

    private int previousIndex(int index) {
        if(index == 0) return list.length - 1;
        return (index - 1) % list.length;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = leftEnd, j = 0; j < size; i = nextIndex(i), j++) {
            sb.append(list[i]).append(", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append("]");
        return sb.toString();
    }


}
