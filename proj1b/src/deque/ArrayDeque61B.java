package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private int size = 0;
    private int nextFirst;
    private int nextLast;
    private T[] array;
    private static final int USAGE_THRESHOLD = 15;

    public ArrayDeque61B() {
        array = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = nextFirst + 1;
    }

    private T[] resizeUp() {
        T[] newArray = (T[]) new Object[array.length * 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = get(i);
        }
        nextFirst = -1;
        nextLast = array.length;
        return newArray;
    }

    @Override
    public void addFirst(T x) {
        if (size == array.length) {
            array = resizeUp();
        }
        array[Math.floorMod(nextFirst, array.length)] = x;
        nextFirst--;
        size++;
    }

    @Override
    public void addLast(T x) {
        if (size == array.length) {
            array = resizeUp();
        }
        array[Math.floorMod(nextLast, array.length)] = x;
        nextLast++;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            returnList.add(get(i));
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

    private T[] resizeDown() {
        float factor = (float) size / array.length;
        if (array.length <= USAGE_THRESHOLD || factor > 0.25) {
            return array;
        } else {
            int newLen = array.length / 2;
            while ((float) size / newLen <= 0.25 && newLen > USAGE_THRESHOLD) {
                newLen /= 2;
            }
            T[] newArray = (T[]) new Object[newLen];
            for (int i = 0; i < size; i++) {
                newArray[i] = get(i);
            }
            nextFirst = -1;
            nextLast = size;
            return newArray;
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            T item = get(0);
            nextFirst++;
            size--;
            array = resizeDown();
            return item;
        }
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            T item = get(size - 1);
            nextLast--;
            size--;
            array = resizeDown();
            return item;
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        } else {
            return array[Math.floorMod(nextFirst + index + 1, array.length)];
        }
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        int curr = 0;

        @Override
        public boolean hasNext() {
            return curr < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            } else {
                curr++;
                return get(curr - 1);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deque61B<?> other) {
            if (other.size() != size) {
                return false;
            } else {
                for (int i = 0; i < size; i++) {
                    if (!other.get(i).equals(get(i))) {
                        return false;
                    }
                }
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return toList().toString();
    }
}
