package junit.service;

import dz_1.GenHashMap;
import dz_1.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenHashMapTest<K, V> {
    private GenHashMap<String, Integer> genHashMap;
    private Node<String, Integer>[] table;
    private  int size;

    @BeforeEach
    void setup(){
        size = 16;
        table = new Node[size];
        genHashMap = new GenHashMap<>();
    }

    @Test
    void put_WhenPuttingElementInHashMap_ElementPut(){
        genHashMap.put("medical physicist", 500);
        genHashMap.put("driver", 600);
        genHashMap.put("engineer", 1000);
        genHashMap.put("developer", 1500);

        assertEquals(4, genHashMap.size());
    }

}
