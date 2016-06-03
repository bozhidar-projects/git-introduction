package com.softacademy.designpatterns.strategy;

public class StrategyExample {

    public static void main(String[] args) {
        int[] elements = { 15, 23, 15, 33, 17, 5 };

        SortStrategy insertionSort = new InsertionSortStrategy();
        SortStrategy bubbleSort = new BubbleSortStrategy();

        sortElements(elements, bubbleSort);
    }

    private static void sortElements(int[] elements,
            SortStrategy sortStrategy) {
        int[] sortedArray = sortStrategy.sort(elements);
        for (int i : sortedArray) {
            System.out.println(i);
        }
    }

}
