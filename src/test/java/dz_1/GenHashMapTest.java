package dz_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class GenHashMapTest {
    private GenHashMap<String, String> genHashMap;
    private GenHashMap<Object, Object> bigHashMap;

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

        bigHashMap = new GenHashMap<>();
        for (int i = 0; i < 100_000; i++) {
            bigHashMap.put(i, i);

        }
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
    void put_WhenPutLargeAmountOfElementsInHashMap_ShouldIncreaseSize(){
        int size = bigHashMap.size();

        for (int i = 100_001; i < 200_001; i++) {
            bigHashMap.put(i, i);
        }

        assertEquals(size + 100000, bigHashMap.size());
        assertNotNull(bigHashMap);
    }


    @Test
    void get_WhenGettingElementFromHashMap_ShouldElementGet(){
        String actual = genHashMap.get("Belarus");

        assertEquals("Minsk", actual);
    }

    @Test
    void get_WhenKeyIsNotExist_ShouldReturnNull(){
        String actual = genHashMap.get("Argentina");

        assertNull(actual);
    }

    @Test
    void get_WhenGettingBigNumberOfElementFromBigHashMap_ShouldElementGet(){
        for (int i = 0; i < 1000; i++) {
            Object actual = bigHashMap.get(i);
            assertEquals(i, actual);
        }
    }

    @Test
    void remove_WhenRemoveElement_ShouldElementRemove(){
        int size = genHashMap.size();

        genHashMap.remove("France");

        assertEquals(size - 1, genHashMap.size());
    }

    @Test
    void remove_WhenRemoveBigNumberOfElements_ShouldElementsRemove(){
        int size = bigHashMap.size();

        for (int i = 0; i < bigHashMap.size(); i = i + 2) {
            bigHashMap.remove(i);
        }

        assertEquals(50000, size / 2);
    }

    @Test
    void remove_WhenKeyIsNotExisted_ShouldSizeRemain(){
        int size = genHashMap.size();

        genHashMap.remove("Egypt");

        assertEquals(size, genHashMap.size());
    }

}
