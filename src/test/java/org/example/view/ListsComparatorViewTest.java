package org.example.view;

import org.assertj.core.api.Assertions;
import org.example.task.NumbListsComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListsComparatorViewTest {
    private ListsComparatorView view;
    private NumbListsComparator comparator;
    private ByteArrayOutputStream out;

    @BeforeEach
    public void init() {
        comparator = mock(NumbListsComparator.class);
        view = new ListsComparatorView(comparator);

        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @ParameterizedTest
    @CsvSource({"-1, Второй", "0, равны", "1, Первый"})
    void compareByAverage(int value, String string) {
        when(comparator.compareAverage(anyList(), anyList())).thenReturn(value);

        view.compareByAverage(anyList(), anyList());

        assertTrue(out.toString().contains(string));
    }

    @Test
    void compareByAverageNegative() {
        when(comparator.compareAverage(anyList(), anyList())).thenReturn(2);

        Assertions.assertThatThrownBy(() -> view.compareByAverage(anyList(), anyList()))
                .isInstanceOf(IllegalArgumentException.class).message().contains("Некорректный аргумент compareResult");
    }
}