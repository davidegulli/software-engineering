package io.daves.engineering.algorithms.closest_points;

import io.daves.engineering.models.Point;
import io.daves.engineering.models.PointsPair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Strategies used:
 *  - Divide et Impera
 */

public class ClosestPairsSolver {

    public PointsPair bruteForce(List<Point> points) {
        PointsPair closest = null;

        for(int i = 0; i < points.size(); i++) {
            for (int j = 0; j < points.size(); j++) {
                if(j == i) continue;

                PointsPair currentPairs = new PointsPair(points.get(i), points.get(j));
                if(closest == null || closest.getDistance() > currentPairs.getDistance()) {
                    closest = currentPairs;
                }
            }
        }

        return closest;
    }

    public PointsPair divideAndConquer(List<Point> points) {

        if (points.size() <= 3) {
            return bruteForce(points);
        }

        points.sort(Point.getComparatorByX());

        int mediumPoint = (points.size() / 2);

        PointsPair leftClosestPair = divideAndConquer(points.subList(0, mediumPoint));
        PointsPair rightClosestPair = divideAndConquer(points.subList(mediumPoint, points.size()));

        PointsPair closestPair = minDistanceBetweenPairs(leftClosestPair, rightClosestPair);

        List<Point> strippedPoints = new ArrayList<>();
        for (Point point : points) {
            if(Math.abs(points.get(mediumPoint).getX() - point.getX()) < closestPair.getDistance()) {
                strippedPoints.add(point);
            }
        }

        strippedPoints.sort(Point.getComparatorByY());
        PointsPair medClosestPair = getVerticalClosestPair(strippedPoints, closestPair);

        return medClosestPair != null ? minDistanceBetweenPairs(closestPair, medClosestPair) : closestPair;
    }

    private PointsPair getVerticalClosestPair(List<Point> strippedPoints, PointsPair closestPair) {
        strippedPoints.sort(Point.getComparatorByY());

        PointsPair medClosestPair = closestPair;
        double minDistance = closestPair.getDistance();

        for (int i = 0; i < strippedPoints.size(); i++) {
            for (int j = 0; j < strippedPoints.size(); j++) {
                if(i == j) continue;
                if((strippedPoints.get(j).getY() - strippedPoints.get(i).getY()) >= minDistance) break;

                PointsPair pair = new PointsPair(strippedPoints.get(i), strippedPoints.get(j));
                if(pair.getDistance() < medClosestPair.getDistance()) {
                    medClosestPair = pair;
                }
            }
        }

        return medClosestPair;
    }

    private PointsPair minDistanceBetweenPairs(PointsPair left, PointsPair right) {
        return left.getDistance() < right.getDistance() ? left : right;
    }

    public static void main(String[] args) {
        ArrayList<Point> input = generateRandomPoints(10000);

        ClosestPairsSolver closestPair = new ClosestPairsSolver();

        long init = System.currentTimeMillis();
        PointsPair closest = closestPair.divideAndConquer(input);
        long end = System.currentTimeMillis();
        System.out.println(closest + " Divide et Impera - Elapsed: " + (end - init) + "\n");

        init = System.currentTimeMillis();
        closest = closestPair.bruteForce(input);
        end = System.currentTimeMillis();
        System.out.println(closest + " Brute Force - Elapsed: " + (end - init));
    }

    private static ArrayList<Point> generateRandomPoints(int numberOfPoints) {
        ArrayList<Point> result = new ArrayList<>();
        for(int i = 0; i < numberOfPoints; i++) {
            Random random = new Random();
            double x = random.nextDouble();
            double y = random.nextDouble();
            result.add(new Point(x, y));
        }

        return result;
    }

}
