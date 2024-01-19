package org.example.task;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Компаратор списков по среднему значению
 */
@Getter
public class NumbListsComparator {

    /**
     * Метод сравнения двух списков по среднему значению
     *
     * @param list1 первый список
     * @param list2 второй список
     * @return 1/0/-1, как обычный Comparator
     * @throws IllegalArgumentException
     */
    public int compareAverage(List<? extends Number> list1, List<? extends Number> list2) throws IllegalArgumentException {
        if (list1.isEmpty() || list2.isEmpty()) {
            throw new IllegalArgumentException("Один из списков пуст! Сравнение невозможно...");
        } else {
            Double averageInList1 = getAverageValue(convertNumbToDouble(list1));
            Double averageInList2 = getAverageValue(convertNumbToDouble(list2));
            return averageInList1.compareTo(averageInList2);
        }
    }

    /**
     * Метод для конвертации списка Number в список Double
     *
     * @param numbers список для конвертации
     * @return список Double
     */
    private List<Double> convertNumbToDouble(List<? extends Number> numbers) {
        List<Double> doubles = new ArrayList<>();
        for (Number numb : numbers) {
            doubles.add(numb.doubleValue());
        }
        return doubles;
    }

    /**
     * Метод получения среднего значения списка
     *
     * @param doubles список для анализа
     * @return среднее значение
     */
    private Double getAverageValue(List<Double> doubles) {
        return doubles.stream().mapToDouble(Double::doubleValue).average().getAsDouble();
    }
}
