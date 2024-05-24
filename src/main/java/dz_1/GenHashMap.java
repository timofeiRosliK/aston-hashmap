package dz_1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GenHashMap<K, V> implements GenMap<K, V> {

    private static class Node<K, V> {
        private final K key;
        private V value;
        Node<K, V> next;

        /**
         *
         *
         * @param key the key that associated with node
         * @param value the value that associated with node
         * @param next the next node in the chain
         */
        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        /**
         * Returns the key associated with the node
         *
         * @return the key associated with the node
         */
        public K getKey() {
            return key;
        }

        /**
         * Returns the value associated with the node
         *
         * @return the value associated with the node
         */
        public V getValue() {
            return value;
        }

        /**
         * Sets the value associated with the node
         *
         * @param value the new value associated with the node
         */

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return key + " = " + value;
        }
    }

    private Node<K, V>[] table;
    private static final int INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private int threshold;
    private int size = 0;

    /**
     * Constructs an empty hash map with the default initial capacity
     */

    public GenHashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Constructs an empty hash map with the specified initial capacity
     *
     * @param initialCapacity the initial capacity of hash map
     */
    public GenHashMap(int initialCapacity) {
        table = new Node[initialCapacity];
    }

    /**
     * This method returns the hash of key
     *
     * @param key the key whose hash is calculated
     * @return the hash of key
     */
    public int getHash(K key) {
        return key.hashCode();
    }

    /**
     * This is a method that inserts pair key-value in hash map.
     * If the key is not existed in hash map, the value is added in specified position
     * If the key already exists in hash map, the value is updated with provided value.
     * If the size exceeds the threshold, the hashMap is resized.
     *
     * @param key   the key that is associated with specified value
     * @param value the value that is associated with specified key
     */
    @Override
    public void put(K key, V value) {
        if (size >= threshold) {
            resize();
        }

        Node<K, V> entry = new Node<>(key, value, null);
        int position = getPosition(key);
        Node<K, V> existing = table[position];

        if (existing == null) {
            table[position] = entry;
            size++;
        } else {
            while (existing.next != null) {
                if (existing.getKey().equals(key)) {
                    existing.setValue(value);
                    return;
                }
                existing = existing.next;
            }

            if (existing.getKey() == null) {
                existing.setValue(value);
                return;
            }

            if (existing.getKey().equals(key)) {
                existing.setValue(value);
            } else {
                existing.next = entry;
                size++;
            }
        }
    }

    /**
     * This is a method that returns the value with specified key.
     * If the key is not found, returns {@code null}.
     *
     * @param key the key that is associated value.
     * @return the value is associated with specified key.
     */
    @Override
    public V get(K key) {
        int position = getPosition(key);
        Node<K, V> existing = table[position];

        while (existing != null) {
            if (existing.getKey().equals(key)) {
                return existing.getValue();
            }
            existing = existing.next;
        }
        return null;
    }

    /**
     * This is a method that removes the element of hash map by given key.
     * If the key is {@code null}, the method does nothing.
     * If the key is found, the element is removed and the size is decremented by one.
     * @param key the key that is associated with specified value.
     */

    @Override
    public void remove(K key) {
        if (key == null) {
            return;
        }

        int position = getPosition(key);

        Node<K, V> current = table[position];
        Node<K, V> prev = null;

        while (current != null) {
            if (current.getKey().equals(key)) {
                if (prev == null) {
                    table[position] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public String toString() {
        String keyValueTemplate = "{%s = %s}";
        List<String> pairs = new ArrayList<>();

        for (Node<K, V> current : table) {
            while (current != null) {
                pairs.add(String.format(keyValueTemplate, current.key, current.value));
                current = current.next;
            }
        }
        return "{" + pairs.stream()
                .collect(Collectors.joining(", ")) + "}";
    }

    /**
     * This is a method that returns the number of key-value pairs in this map
     *
     * @return the number of key-value pairs in this map
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * This is a method that doubles the capacity of hash map.
     * This method is called when the number of elements exceeds the threshold.
     * The new threshold is recalculated based on the initial capacity and load factor.
     */
    public void resize() {
        int newLength = table.length * 2;
        Node<K, V>[] newTable = new Node[newLength];

        System.arraycopy(table, 0, newTable, 0, table.length);
        table = newTable;

        threshold = (int) (INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    }

    /**
     * This is a method that calculates the position in hash map for a given key.
     *
     * @param key the key whose position is calculated
     * @return the index of key
     */

    public int getPosition(K key) {
        if (key == null) {
            return 0;
        }
        return Math.abs(getHash(key) & INITIAL_CAPACITY - 1);
    }

}
