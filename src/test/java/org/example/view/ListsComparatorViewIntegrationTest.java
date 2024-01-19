package org.example.view;

import org.example.task.NumbListsComparator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListsComparatorViewIntegrationTest {
    private static Map<String, List<Integer>> testMap;
    private static ListsComparatorView listsComparatorView;
    private static ByteArrayOutputStream out;

    @BeforeAll
    public static void init() {
        listsComparatorView = new ListsComparatorView(new NumbListsComparator());

        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        testMap = new HashMap<>();
        //todo названия ключей = среднее значение списка
        testMap.put("null", Collections.emptyList());
        testMap.put("zero", List.of(-7, 7));
        testMap.put("four", List.of(2, 6));
        testMap.put("nine", List.of(7, 15));
    }

    @BeforeEach
    public void cleanOut() {
        try {
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void compareByAverageWhenEmpty() {
        assertThatThrownBy(() -> listsComparatorView.compareByAverage(testMap.get("null"), testMap.get("zero"))).
                isInstanceOf(IllegalArgumentException.class).message().contains("пуст");
    }

    @Test
    void compareByAverageWhenSecondIsLargest() {
        listsComparatorView.compareByAverage(testMap.get("zero"), testMap.get("nine"));

        assertTrue(out.toString().contains("Второй"));
    }

    @Test
    void compareByAverageWhenFirstIsLargest() {
        listsComparatorView.compareByAverage(testMap.get("four"), testMap.get("zero"));

        assertTrue(out.toString().contains("Первый"));
    }

    @Test
    void compareByAverageWhenEquals() {
        listsComparatorView.compareByAverage(testMap.get("zero"), testMap.get("zero"));

        assertTrue(out.toString().contains("равны"));
    }
}