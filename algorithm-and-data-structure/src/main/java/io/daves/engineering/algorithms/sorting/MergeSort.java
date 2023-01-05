package io.daves.engineering.algorithms.sorting;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        mergeSort(sample);
        print(sample);
    }

    private static void mergeSort(int[] items) {
        if (items.length == 1) {
            return;
        }

        int middle = items.length / 2;

        int[] left = Arrays.copyOfRange(items, 0, middle);
        int[] right = Arrays.copyOfRange(items, middle, items.length);

        mergeSort(left);
        mergeSort(right);

        merge(items, left, right);
    }

    private static void merge(int[] items, int[] left, int[] right) {
        int i = 0, j = 0;
        while (i + j < items.length) {
            if(j == right.length || (i < left.length && left[i] < right[j])) {
                items[i+j] = left[i++];
            } else {
                items[i+j] = right[j++];
            }
        }
    }

    private static void print(int[] input) {
        for (int item : input) {
            System.out.println(item);
        }
    }

    private static int[] sample = {6,0,4,11,5,7,13,8,9,15,2,6,3,10,14,1};
}
