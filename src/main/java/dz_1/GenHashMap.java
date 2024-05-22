package dz_1;

public class GenHashMap<K, V> implements GenMap<K, V> {

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

    public void resize() {
        int newLength = table.length * 2;
        Node<K, V>[] newTable = new Node[newLength];

        System.arraycopy(table, 0, newTable, 0, table.length);
        table = newTable;

        threshold = (int) (INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    }

    @Override
    public void put(K key, V value) {

        if (size >= threshold) {
            resize();
        }

        Node<K, V> entry = new Node<>(key, value, null);
        int position = Math.abs(getHash(key) % table.length);
        Node<K, V> existing = table[position];

        if (entry.getKey() == null) {
            table[0] = entry;
            size++;
        }

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


            // check the last element
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
        int position = Math.abs(getHash(key) % table.length);
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

        int position = Math.abs(getHash(key) % table.length);

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
        StringBuilder sb = new StringBuilder();

        for (Node<K, V> node : table) {
            Node<K, V> current = node;
            while (current != null) {
                sb.append("{")
                        .append(current.getKey())
                        .append(" = ")
                        .append(current.getValue())
                        .append("}, ");
                current = current.next;
            }
        }
        if(!sb.isEmpty()){
            sb.setLength(sb.length() - 2);
        }
        return "{" + sb + "}";
    }

    @Override
    public int size() {
        return size;
    }

}
