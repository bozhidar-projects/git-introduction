package com.softacademy.designpatterns.strategy;

import java.util.Arrays;

public class BubbleSortStrategy implements SortStrategy {

    @Override
    public int[] sort(int[] unsortedArray) {
        int[] result = Arrays.copyOf(unsortedArray, unsortedArray.length);

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length - i - 1; j++) {
                if (result[j] > result[j + 1]) {
                    int temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }

        return result;
    }

}
