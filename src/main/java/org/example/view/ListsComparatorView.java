package org.example.view;

import lombok.AllArgsConstructor;
import org.example.task.NumbListsComparator;

import java.util.List;

/**
 * View для компаратора списков по среднему значению
 */
@AllArgsConstructor
public class ListsComparatorView {
    private final NumbListsComparator comparator;

    /**
     * Метод для сравнения списков по среднему значению и вывода результатов в консоль
     *
     * @param list1 первый список
     * @param list2 второй список
     * @throws IllegalArgumentException
     */
    public void compareByAverage(List<? extends Number> list1, List<? extends Number> list2) throws IllegalArgumentException {
        printResults(comparator.compareAverage(list1, list2));
    }

    /**
     * Метод для вывода результатов сравнения в консоль
     *
     * @param compareResult результат сравнения в int
     */
    private void printResults(int compareResult) {
        switch (compareResult) {
            case -1:
                System.out.println("Второй список имеет большее среднее значение.");
                break;
            case 0:
                System.out.println("Средние значения равны");
                break;
            case 1:
                System.out.println("Первый список имеет большее среднее значение.");
                break;
            default:
                throw new IllegalArgumentException(String.format
                        ("Некорректный аргумент compareResult=%d! Результат сравнения должен быть int от -1 до 1!", compareResult));
        }
    }
}
