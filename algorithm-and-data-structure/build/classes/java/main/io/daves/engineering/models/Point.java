package io.daves.engineering.models;

import java.util.Comparator;
import java.util.Locale;

public class Point {

    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static Comparator<Point> getComparatorByX() {
        return new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                if(p1.getX() < p2.getX()) return -1;
                if(p1.getX() > p2.getX()) return -1;
                return 0;
            }
        };
    }

    public static Comparator<Point> getComparatorByY() {
        return new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                if(p1.getY() < p2.getY()) return -1;
                if(p1.getY() > p2.getY()) return -1;
                return 0;
            }
        };
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append("( x: ")
            .append(String.format(Locale.ITALIAN, "%.14f", x))
            .append("\t y: ")
            .append(String.format(Locale.ITALIAN, "%.14f", y))
            .append(" )")
            .toString();
    }
}
