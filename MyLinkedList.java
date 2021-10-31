

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public final class MyLinkedList<T> implements Iterable<T> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        Node prev;
        T value;
        Node next;

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }

        public Node(Node prev, T value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iter();
    }

    public ListIterator<T> listIterator() {
        return new ListIter();
    }

    private class ListIter implements ListIterator<T> {
       Node current = first;

        @Override
        public boolean hasNext() {
            return current.getNext() != null;
        }

        @Override
        public T next() {
            if(hasNext()) {
                current = current.getNext();
            } else {
               // System.out.println("Текущий элемент последний. Следующего элемента нет");
                return null;
            }
            return current.getValue();
        }

        @Override
        public boolean hasPrevious() {
            return current.getPrev() != null;
        }

        @Override
        public T previous() {
            if(hasPrevious()) {
                current = current.getPrev();
            } else{
              //  System.out.println("Текущий элемент первый. Предудыщего элемента нет");
                return null;
            }
            return current.getValue();
        }

        @Override
        public int nextIndex() {
            int index = 0;
            Node temp = first;
            while(temp != current) {
                index++;
            }
            if(hasNext()) {
                return index + 1;
            }

            return -1;
        }

        @Override
        public int previousIndex() {
            int index = 0;
            Node temp = first;
            while(temp != current) {
                index++;
            }
            if(hasPrevious()) {
                return index - 1;
            }
            return -1;
        }

        @Override
        public void remove() {
            if (current == first) {
                first = current.getNext();
                if (first == null) {
                    last = null;
                } else {
                    first.setPrev(null);
                    current = first;
                }
            } else if (current == last) {
                last = current.getPrev();
                if (last == null) {
                    first = null;
                } else {
                    last.setNext(null);
                    current = last;
                }
            } else {
                current.getNext().setPrev(current.getPrev());
                current.getPrev().setNext(current.getNext());
                current = current.getNext();
            }
            size--;
        }

        @Override
        public void set(T t) {
            current.setValue(t);
        }

        @Override
        public void add(T t) {
            Node newNode = new Node(t, null);
            try {
                if (first == null) {
                    if (first.getNext() == null) {
                        last = newNode;
                    } else {
                        first.getNext().setPrev(newNode);
                        newNode.setNext(first);
                    }
                    first = newNode;
                } else if (current == last) {
                    last.setNext(newNode);
                    newNode.setPrev(last);
                    last = newNode;
                } else {
                    newNode.setNext(current.getNext());
                    newNode.setPrev(current);
                    current.getNext().setPrev(newNode);
                    current.setNext(newNode);

                }
            } catch(NullPointerException e) {
                    if(last == null) {
                        last = newNode;
                    } else {
                        first.getNext().setPrev(newNode);
                        newNode.setNext(first.getNext());
                    }
                first = newNode;
            }
            size++;
            current = newNode;
        }

    }

    private class Iter implements Iterator<T> {
        Node current = first;

        @Override
        public boolean hasNext() {
            return current.getNext() != null;
        }

        @Override
        public T next() {
            current = current.getNext();
            return current.getValue();
        }
    }

    public void insertFirst(T item) {
        Node newNode = new Node(item, first);
        if (isEmpty()) {
            last = newNode;
        } else {
            first.setPrev(newNode);
        }
        first = newNode;
        size++;
    }

    public void insertLast(T item) {
        Node newNode = new Node(last, item, null);
        if (isEmpty()) {
            first = newNode;
        } else {
            last.setNext(newNode);
        }
        last = newNode;
        size++;
    }

    public T deleteFirst() {
        T temp = getFirst();
        first = first.getNext();
        if (isEmpty()) {
            last = null;
        } else {
            first.setPrev(null);
        }
        size--;
        return temp;
    }

    public T deleteLast() {
        T temp = getLast();
        if (last.getPrev() == null) {
            first = null;
        } else {
            last.getPrev().setNext(null);
        }
        last = last.getPrev();
        size--;
        return temp;
    }

    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("empty");
        }
        return first.getValue();
    }

    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("empty");
        }
        return last.getValue();
    }

    public int indexOf(T item) {
        Node current = first;
        int index = 0;
        while (current != null) {
            if (item.equals(current.getValue())) {
                return index;
            }
            current = current.getNext();
            index++;
        }
        return -1;
    }

    public boolean contains(T item) {
        return indexOf(item) > -1;
    }

    public void insert(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index " + index);
        }
        if (index == 0) {
            insertFirst(item);
            return;
        }
        if (index == size) {
            insertLast(item);
            return;
        }
        Node current = first;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }
        Node newNode = new Node(current, item, current.getNext());
        current.setNext(newNode);
        current.getNext().setPrev(newNode);
        size++;
    }

    public boolean delete(T item) {
        if (isEmpty()) {
            return false;
        }
        if (item.equals(first.getValue())) {
            deleteFirst();
            return true;
        }

        Node current = first;
        while (current != null && !item.equals(current.getValue())) {
            current = current.getNext();
        }
        if (current == null) {
            return false;
        }

        if (current == last) {
            deleteLast();
            return true;
        }

        current.getNext().setPrev(current.getPrev());
        current.getPrev().setNext(current.getNext());
        size--;
        return true;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node current = first;
        for (int i = 0; i < size; i++) {
            sb.append(current.getValue()).append(", ");
            current = current.getNext();
        }
        if (size > 0) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");
        return sb.toString();
    }
}
