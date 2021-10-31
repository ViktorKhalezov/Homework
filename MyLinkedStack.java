public class MyLinkedStack <T>{
    private MyLinkedList<T> list;

    public MyLinkedStack() {
        this.list = new MyLinkedList<>();
    }

    public void push(T item) {
        list.insertLast(item);
    }

    public T pop() {
        return list.deleteLast();
    }

    public T peek() {
        return list.getLast();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

}
