import java.util.List;
import java.util.ArrayList; // import the ArrayList class

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private int size;
    private Node sentinel;


    private class Node {
        T item;
        Node prev;
        Node next;

        public Node() {
            item = null;
            next = null;
            prev = null;
        }

        public Node(T x, Node p, Node n) {
            item = x;
            next = n;
            prev = p;
        }
    }

    public LinkedListDeque61B() {
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        Node n = new Node(x, this.sentinel, this.sentinel.next);
        this.sentinel.next = n;
        n.next.prev = n;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        Node n = new Node(x, this.sentinel.prev, this.sentinel);
        this.sentinel.prev = n;
        n.prev.next = n;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node p = this.sentinel.next;
        while (p != this.sentinel) {
            returnList.add(p.item);
            p = p.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        } else {
            T first = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size--;
            return first;
        }
    }

    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        } else {
            T last = this.sentinel.prev.item;
            this.sentinel.prev = this.sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size--;
            return last;
        }
    }

    @Override
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        Node p = this.sentinel.next;
        while (index != 0) {
            p = p.next;
            index--;
        }
        return p.item;
    }

    private T getHelper(Node node, int index) {
        if (index == 0) {
            return node.item;
        } else {
            return getHelper(node.next, index - 1);
        }
    }

    @Override
    public T getRecursive(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        return getHelper(sentinel.next, index);
    }
}
