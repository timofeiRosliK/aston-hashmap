package dz_1;

public interface GenMap <K, V>{
    void put(K key, V value);
    V get(K key);
    void remove(K key);
    int size();
}
