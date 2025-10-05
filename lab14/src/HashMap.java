import edu.princeton.cs.algs4.In;

import java.util.Iterator;
import java.util.LinkedList;

public class HashMap<K, V> implements Map61BL<K, V> {
    private LinkedList<Entry<K, V>>[] buckets;
    private int initialCapacity = 16;
    private int capacity;
    private int size;
    private double loadFactor = 0.75;

    /* Creates a new hash map with a default array of size 16 and a maximum load factor of 0.75. */
    HashMap() {
        capacity = initialCapacity;
        size = 0;
        buckets = (LinkedList<Entry<K, V>>[]) new LinkedList[initialCapacity];
        for (int i = 0; i < initialCapacity; i++) {
            buckets[i] = new LinkedList<Entry<K, V>>();
        }
    }

    /* Creates a new hash map with an array of size INITIALCAPACITY and a maximum load factor of 0.75. */
    HashMap(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        capacity = initialCapacity;
        size = 0;
        buckets = (LinkedList<Entry<K, V>>[]) new LinkedList[initialCapacity];
        for (int i = 0; i < initialCapacity; i++) {
            buckets[i] = new LinkedList<Entry<K, V>>();
        }
    }

    /* Creates a new hash map with INITIALCAPACITY and LOADFACTOR. */
    HashMap(int initialCapacity, double loadFactor) {
        this.loadFactor = loadFactor;
        this.initialCapacity = initialCapacity;
        capacity = initialCapacity;
        size = 0;
        buckets = (LinkedList<Entry<K, V>>[]) new LinkedList[initialCapacity];
        for (int i = 0; i < initialCapacity; i++) {
            buckets[i] = new LinkedList<Entry<K, V>>();
        }
    }

    /* Returns the length of this HashMap's internal array. */
    public int capacity() {
        return capacity;
    }

    @Override
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        int i = Math.floorMod(key.hashCode(), capacity);
        for (Entry<K, V> item: buckets[i]) {
            if (item.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        V value = null;
        if (containsKey(key)) {
            int i = Math.floorMod(key.hashCode(), capacity);
            for (Entry<K, V> item: buckets[i]) {
                if (item.key.equals(key)) {
                    value = item.value;
                    break;
                }
            }
        }
        return value;
    }

    @Override
    public void put(K key, V value) {
        int i = Math.floorMod(key.hashCode(), capacity);
        if (!containsKey(key)) {
            buckets[i].add(new Entry<>(key, value));
            size++;
            resize();
        } else {
            remove(key);
            buckets[i].add(new Entry<>(key, value));
            size++;
        }

    }

    @Override
    public V remove(K key) {
        int i = Math.floorMod(key.hashCode(), capacity);
        V value = null;
        int j;
        for (j = 0; j < buckets[i].size(); j++) {
            if (buckets[i].get(j).key.equals(key)) {
                value = buckets[i].get(j).value;
                size--;
                break;
            }
        }
        buckets[i].remove(j);
        return value;
    }

    @Override
    public boolean remove(K key, V value) {
        int i = Math.floorMod(key.hashCode(), capacity);
        boolean success = buckets[i].remove(new Entry<>(key, value));
        if (success) {
            size--;
        }
        return success;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<K> iterator() {
        return new hashMapIterator();
    }

    private class hashMapIterator implements Iterator<K> {
        int i = 0;
        int j = 0;

        hashMapIterator(){
            while (i < buckets.length) {
                if (buckets[i].size() > 0) {
                    break;
                }
                i++;
            }
        }

        @Override
        public boolean hasNext() {
            if (i < buckets.length && j < buckets[i].size()) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public K next() {
            if (hasNext()) {
                K key = buckets[i].get(j).key;
                while (i < buckets.length) {
                    if (j + 1 < buckets[i].size()) {
                        j++;
                        break;
                    }
                    i++;
                    j = 0;
                    if (i < buckets.length && j < buckets[i].size()) {
                        break;
                    };
                }
                return key;
            } else {
                return null;
            }
        }
    }

    private void resize() {
        if ((double) size / capacity > loadFactor) {
            LinkedList<Entry<K, V>>[] newBuckets;
            newBuckets = (LinkedList<Entry<K, V>>[]) new LinkedList[capacity * 2];
            for (int i = 0; i < newBuckets.length; i++) {
                newBuckets[i] = new LinkedList<>();
            }
            for (int i = 0; i < buckets.length; i++) {
                for (Entry<K, V> item: buckets[i]) {
                    K key = item.key;
                    int k = Math.floorMod(key.hashCode(), capacity);
                    newBuckets[k].add(item);
                }
            }
            this.buckets = newBuckets;
            capacity *= 2;
        }
    }

    private static class Entry<K, V> {

        private K key;
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /* Returns true if this key matches with the OTHER's key. */
        public boolean keyEquals(Entry other) {
            return key.equals(other.key);
        }

        /* Returns true if both the KEY and the VALUE match. */
        @Override
        public boolean equals(Object other) {
            return (other instanceof Entry
                    && key.equals(((Entry) other).key)
                    && value.equals(((Entry) other).value));
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> h = new HashMap<String, Integer>(2);
        h.put("zoe", 12345);
        h.put("jay", 345);
        h.put("ryan", 162);
        h.put("grace", 12345);

        for (String name : h) {
            System.out.println(name);
        }
    }
}
