package io.daves.engineering.algorithms.sorting;

public class QuickSort {

    public static void main(String[] args) {
        quickSort(sample, 0, (sample.length - 1));
        print(sample);
    }

    private static void quickSort(int[] items, int start, int end) {
        if (start >= end) {
            return;
        }

        int pivotIndex = findPivotIndex(items);
        int pivot = items[pivotIndex];
        int temp;

        temp = items[end];
        items[end] = items[pivotIndex];
        items[pivotIndex] = temp;

        int left = start;
        int right = end - 1;

        while (left <= right) {
            while (left <= right && items[left] < pivot) {
                left++;
            }

            while (left <= right && items[right] > pivot) {
                right--;
            }

            if (left <= right) {
                temp = items[left];
                items[left] = items[right];
                items[right] = temp;

                left++; right--;
            }
        }

        temp = items[left];
        items[left] = items[end];
        items[end] = temp;

        quickSort(items, start, left - 1);
        quickSort(items, left + 1, end);
    }

    private static int findPivotIndex(int[] items) {
        int a = items[0];
        int b = items[items.length / 2];
        int c = items[items.length - 1];

        if(a < c && c < b ) {
            return items.length - 1;
        }

        if(b < a && a < c ) {
            return 0;
        }

        return items.length / 2;
    }

    private static void print(int[] input) {
        for (int item : input) {
            System.out.println(item);
        }
    }

    private static int[] sample = {6,0,4,11,5,7,13,8,9,15,2,6,3,10,14,1};
}
