package tasks.interpolation;

import helper.Pair;
import tasks.interpolation.method.InterpolationMethod;
import tasks.interpolation.method.PiecewiseLinear;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Interpolation {
    private final List<Pair<BigDecimal, BigDecimal>> points;
    private InterpolationMethod interpolationMethod;
    private final List<List<BigDecimal>> dy;

    public void setInterpolationMethod(InterpolationMethod interpolationMethod) {
        this.interpolationMethod = interpolationMethod;
    }

    public Interpolation() {
        points = new ArrayList<>();
        interpolationMethod = new PiecewiseLinear();
        dy = new ArrayList<>();
    }

    void push(BigDecimal x, BigDecimal y) {
        Pair<BigDecimal, BigDecimal> newPoint = new Pair<>(x, y);
        int index = 0;
        while (index < points.size()) {
            Pair<BigDecimal, BigDecimal> point = points.get(index);
            int comparison = x.compareTo(point.first);
            if (comparison == 0)
                throw new IllegalArgumentException("There is already a point with the same x value: " + x);
                else if (comparison < 0)
                break;
            index++;
        }
        points.add(index, newPoint);
    }

    void push(Pair<BigDecimal, BigDecimal> newPoint) {
        BigDecimal x = newPoint.first;
        int index = 0;
        while (index < points.size()) {
            Pair<BigDecimal, BigDecimal> point = points.get(index);
            int comparison = x.compareTo(point.first);
            if (comparison == 0)
                throw new IllegalArgumentException("There is already a point with the same x value: " + x);
                else if (comparison < 0)
                break;
            index++;
        }
        points.add(index, newPoint);
    }

    BigDecimal calculateY(BigDecimal x) {
        return interpolationMethod.calculateY(x, points);
    }
}