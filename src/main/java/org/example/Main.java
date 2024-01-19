package org.example;

import org.example.task.NumbListsComparator;
import org.example.view.ListsComparatorView;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list1 = List.of(23);
        List<Double> list2 = List.of(23.0);

        ListsComparatorView listsComparatorView = new ListsComparatorView(new NumbListsComparator());
        listsComparatorView.compareByAverage(list1, list2);
    }
}