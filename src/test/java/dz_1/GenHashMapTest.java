package dz_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class GenHashMapTest {
    private GenHashMap<String, String> genHashMap;

    @BeforeEach
    void setup() {
        genHashMap = new GenHashMap<>();
        genHashMap.put("Belarus", "Minsk");
        genHashMap.put("Japan", "Tokyo");
        genHashMap.put("Russia", "Moscow");
        genHashMap.put("Ukraine", "Kyiv");
        genHashMap.put("Lithuania", "Vilnius");
        genHashMap.put("Latvia", "Riga");
        genHashMap.put("USA", "Washington");
        genHashMap.put("Brazil", "Brasilia");
        genHashMap.put("China", "Beijing");
        genHashMap.put("Japan", "Tokyo");
        genHashMap.put("Poland", "Warzcaw");
        genHashMap.put("Norway", "Oslo");
        genHashMap.put("France", "Paris");
        genHashMap.put("Spain", "Madrid");
        genHashMap.put("Portugal", "Lisbon");
    }

    @Test
    void put_WhenPutElementInHashMap_ShouldIncreaseSize() {
        int size = genHashMap.size();

        genHashMap.put("Turkey", "Istanbul");

        assertEquals(size + 1, genHashMap.size());
        assertNotNull(genHashMap);
    }

    @Test
    void put_WhenKeyIsDuplicated_ShouldRewriteValue() {
        int size = genHashMap.size();

        genHashMap.put("Ukraine", "Lviv");

        assertEquals("Lviv", genHashMap.get("Ukraine"));
        assertEquals(size, genHashMap.size());
    }

    @Test
    void put_WhenPutKeyNull_ShouldSuccessfullyAdd(){
        int size = genHashMap.size();

        genHashMap.put(null, "Lida");

        assertEquals(size + 1, genHashMap.size());
    }

    @Test
    void get_WhenGettingElementInHashMap_ShouldElementGet(){
        String actual = genHashMap.get("Belarus");

        assertEquals("Minsk", actual);
    }

    @Test
    void get_WhenKeyIsNotExist_ShouldReturnNull(){
        String actual = genHashMap.get("Argentina");

        assertNull(actual);
    }

    @Test
    void remove_WhenRemoveElement_ShouldElementRemove(){
        int size = genHashMap.size();

        genHashMap.remove("France");

        assertEquals(size - 1, genHashMap.size());
    }

    @Test
    void remove_WhenKeyIsNotExist_ShouldSizeRemain(){
        int size = genHashMap.size();

        genHashMap.remove("Egypt");

        assertEquals(size, genHashMap.size());
    }

}
