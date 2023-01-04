package io.daves.engineering.algorithms.knapsack;

import java.util.Random;

/**
 * Strategies used:
 *  - Dynamic Programming
 *  - Memoization
 */

public class KnapsackSolver {

    public int solveWithBruteForce(int capacity, int weights[], int values[], int n) {

        if (n == 0 || capacity == 0) return 0;

        if (weights[n - 1] > capacity) {
            return solveWithBruteForce(capacity, weights, values, n - 1);
        } else {
            return Math.max(
                    (solveWithBruteForce((capacity - weights[n - 1]), weights, values, (n - 1)) + values[n - 1]),
                    solveWithBruteForce(capacity, weights, values, (n - 1))
            );
        }
    }

    public int solveWithDp(int capacity, int weights[], int values[], int n, int[][] dpTable) {

        if (n == 0 || capacity == 0) return 0;

        if (dpTable[capacity][n] != -1) return dpTable[capacity][n];

        if (weights[n - 1] > capacity) {
            return dpTable[capacity][n] = solveWithDp(capacity, weights, values, n - 1, dpTable);
        } else {
            return dpTable[capacity][n] = Math.max(
                    (solveWithDp((capacity - weights[n - 1]), weights, values, (n - 1), dpTable) + values[n - 1]),
                    solveWithDp(capacity, weights, values, (n - 1), dpTable)
            );
        }
    }

    public int solveWithDp(int capacity, int weights[], int values[], int n) {
        int[][] dpTable = new int[capacity + 1 ][n + 1];
        initializeDpTable(dpTable, (capacity + 1), (n + 1));

        return solveWithDp(capacity, weights, values, n, dpTable);
    }

    private void initializeDpTable(int[][] dpTable, int capacity, int n) {
        for(int i = 0; i < capacity; i++) {
            for(int j = 0; j < n; j++) {
                dpTable[i][j] = -1;
            }
        }
    }

    public static void main(String args[]) {

        int values[] = generateRandomValues(100, 200);
        int weights[] =  generateRandomValues(100, 50);
        int capacity = 50;
        int n = values.length;

        KnapsackSolver solver = new KnapsackSolver();

        long init = System.currentTimeMillis();
        int result = solver.solveWithBruteForce(capacity, weights, values, n);
        long end = System.currentTimeMillis();
        System.out.println("Result: " + result);
        System.out.println("Brute Force - Elapsed: " + (end - init) + "\n");

        init = System.currentTimeMillis();
        result = solver.solveWithDp(capacity, weights, values, n);
        end = System.currentTimeMillis();
        System.out.println("Result: " + result);
        System.out.println("Dynamic Programming - Elapsed: " + (end - init));
    }

    private static int[] generateRandomValues(int length, int upperLimit) {
        Random random = new Random();
        int[] result = new int[length];

        for (int i = 0; i < length; i++) {
            result[i] = random.nextInt(upperLimit);
        }
        return result;
    }
}
