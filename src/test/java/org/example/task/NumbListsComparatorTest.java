package org.example.task;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumbListsComparatorTest {
    private static NumbListsComparator comparator;
    private static Map<String, List<Integer>> testMap;

    @BeforeAll
    public static void init() {
        comparator = new NumbListsComparator();

        testMap = new HashMap<>();
        //todo названия ключей = среднее значение списка
        testMap.put("null", Collections.emptyList());
        testMap.put("zero", List.of(-7, 7));
        testMap.put("four", List.of(2, 6));
        testMap.put("nine", List.of(7, 15));
    }

    @Test
    void compareAverageWhenEmpty() {
        Assertions.assertThatThrownBy(() -> comparator.compareAverage(testMap.get("null"), testMap.get("zero")))
                .isInstanceOf(IllegalArgumentException.class).message().contains("пуст");
    }

    @Test
    void compareAverageTest() {
        assertEquals(1, comparator.compareAverage(testMap.get("nine"), testMap.get("zero")));
        assertEquals(0, comparator.compareAverage(testMap.get("zero"), testMap.get("zero")));
        assertEquals(-1, comparator.compareAverage(testMap.get("four"), testMap.get("nine")));
    }
}