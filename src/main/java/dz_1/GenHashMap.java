package dz_1;

public class GenHashMap<K, V> implements GenMap<K, V> {
    private Node<K, V>[] table;
    private static final int INITIAL_CAPACITY = 16;
    private int size = 0;

    public GenHashMap(){
        this(INITIAL_CAPACITY);
    }


    public GenHashMap(int initialCapacity) {
        table = new Node[initialCapacity];
    }


    public int getHash(K key) {
        return key.hashCode();
    }



//    public boolean equalsKey(K key){
//        return this.equals(key);
//    }

    @Override
    public void put(K key, V value) {
        Node<K, V> entry = new Node<>(key, value, null);
        int position = getHash(key) % table.length;

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
        int position = getHash(key) % table.length;
        Node<K, V> element = table[position];

        while (element.next != null) {
            if (element.getKey().equals(key)) {
                return element.getValue();
            }
            element = element.next;
        }
        return null;
    }

    @Override
    public void remove(K key) {
        if (key == null) {
            return;
        }

        int position = getHash(key) & table.length;

        Node<K, V> current = table[position];
        Node<K, V> prev = null;
        while (current.next != null) {
            if (current.getKey().equals(key)) {
                if(prev == null) {
                    table[position] = current.next;
                }else {
                    prev.next = current.next;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    @Override
    public int size() {
        return size;
    }
}
