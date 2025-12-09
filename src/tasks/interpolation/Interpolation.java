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

    public void setInterpolationMethod(InterpolationMethod interpolationMethod) {
        this.interpolationMethod = interpolationMethod;
    }

    public Interpolation() {
        points = new ArrayList<>();
        interpolationMethod = new PiecewiseLinear();
    }

    public void push(BigDecimal x, BigDecimal y) {
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

    public void push(Pair<BigDecimal, BigDecimal> newPoint) {
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

    public boolean remove(BigDecimal x, BigDecimal y){
        return points.remove(new Pair<>(x,y));
    }

    public boolean remove(Pair<BigDecimal, BigDecimal> point){
        return points.remove(point);
    }

    public void clear(){
        points.clear();
    }

    public BigDecimal calculateY(BigDecimal x) {
        return interpolationMethod.calculateY(x, points);
    }
}