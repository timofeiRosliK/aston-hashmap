package dz_1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GenHashMap<K, V> implements GenMap<K, V> {

    private static class Node<K, V> {
        private final K key;
        private V value;
        Node<K,V> next;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

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

    public GenHashMap() {
        this(INITIAL_CAPACITY);
    }

    public GenHashMap(int initialCapacity) {
        table = new Node[initialCapacity];
    }

    public int getHash(K key) {
        return key.hashCode();
    }

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

            if(existing.getKey() == null){
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

    @Override
    public V get(K key) {
        int position = getPosition(key);
        Node<K, V> existing = table[position];

        while (existing!= null) {
            if (existing.getKey().equals(key)) {
                return existing.getValue();
            }
            existing = existing.next;
        }
        return null;
    }

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

    @Override
    public int size() {
        return size;
    }

    public void resize() {
        int newLength = table.length * 2;
        Node<K, V>[] newTable = new Node[newLength];

        System.arraycopy(table, 0, newTable, 0, table.length);
        table = newTable;

        threshold = (int) (INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    }

    public int getPosition(K key){
        if(key == null){
            return 0;
        }
        return Math.abs(getHash(key) & INITIAL_CAPACITY - 1);
    }

}
