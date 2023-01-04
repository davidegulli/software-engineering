package io.daves.engineering.models;

public class PointsPair {

    private Point a;
    private Point b;

    private double distance;

    public PointsPair(Point a, Point b) {
        this.a = a;
        this.b = b;

        distance = calculateDistance();
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public double getDistance() {
        return distance;
    }

    private double calculateDistance() {
        double abx = Math.pow((b.getX() - a.getX()), 2);
        double aby = Math.pow((b.getY() - a.getY()), 2);

        return Math.sqrt(abx + aby);
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append("-- Start processing --------------------\n")
            .append(a)
            .append("\n")
            .append(b)
            .append("\n-------------")
            .toString();
    }
}
