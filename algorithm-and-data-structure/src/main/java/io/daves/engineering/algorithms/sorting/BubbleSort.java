package io.daves.engineering.algorithms.sorting;

public class BubbleSort {

    public static void main(String[] args) {
        sort(sample);
        print(sample);
    }

    private static void sort(int[] input) {
        int length = input.length;
        boolean swapped;

        do {
            swapped = false;
            for (int i = 0; i < length; i++) {
                if((i + 1) < length && input[i] > input[i+1]) {
                    int temp = input[i];
                    input[i] = input[i+1];
                    input[i+1] = temp;
                    swapped = true;
                }
            }
            length = length - 1;
        } while (swapped);
    }

    private static void print(int[] input) {
        for (int item : input) {
            System.out.println(item);
        }
    }

    private static int[] sample = {6,0,4,11,5,7,13,8,9,15,2,6};
}
