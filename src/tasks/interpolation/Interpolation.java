package tasks.interpolation;

import helper.Pair;
import tasks.interpolation.method.InterpolationMethod;
import tasks.interpolation.method.PiecewiseLinear;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Interpolation {
    private final List<Pair<BigDecimal,BigDecimal>> points;
    private InterpolationMethod interpolationMethod;

    public void setInterpolationMethod(InterpolationMethod interpolationMethod) {
        this.interpolationMethod = interpolationMethod;
    }

    public Interpolation() {
        points = new ArrayList<>();
        interpolationMethod = new PiecewiseLinear();
    }

    void push(BigDecimal x, BigDecimal y){
        for (Pair<BigDecimal, BigDecimal> point : points) {
            if (point.first.equals(x)) {
                System.out.println("There is already a point with the same x value: " + x);
                return;
            }
        }

        points.add(new Pair<>(x, y));
    }

    void push(Pair<BigDecimal, BigDecimal> newPoint){
        for (Pair<BigDecimal, BigDecimal> point : points) {
            if (point.first.equals(newPoint.first)) {
                System.out.println("There is already a point with the same x value: " + newPoint.first);
                return;
            }
        }

        points.add(newPoint);
    }

    BigDecimal calculateY(BigDecimal x){
        return interpolationMethod.calculateY(x, points);
    }
}
