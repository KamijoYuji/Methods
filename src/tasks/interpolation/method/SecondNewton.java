package tasks.interpolation.method;

import helper.Pair;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

public class SecondNewton implements InterpolationMethod {
    @Override
    public BigDecimal calculateY(BigDecimal x, List<Pair<BigDecimal, BigDecimal>> points) {
        if (points.size() < 2)
            throw new IllegalArgumentException("At least 2 points are required");


        BigDecimal h = points.get(1).first.subtract(points.getFirst().first);
        for (int i = 2; i < points.size(); i++) {
            BigDecimal currentStep = points.get(i).first.subtract(points.get(i-1).first);
            if (currentStep.compareTo(h) != 0)
                throw new IllegalArgumentException("Points must be equally spaced");

        }

        BigDecimal q = (x.subtract(points.getLast().first)).divide(h, MathContext.DECIMAL128);

        List<List<BigDecimal>> dy = buildDifferenceTable(points);

        BigDecimal Pn = points.getLast().second;
        BigDecimal product = BigDecimal.ONE;
        BigDecimal factorial = new BigDecimal(1);

        for (int i = 1; i <= points.size()-1; i++) {
            product = product.multiply(q.add(new BigDecimal(i - 1)));

            BigDecimal difference = getBackwardDifference(dy, points.size()-1, i);
            factorial = factorial.multiply(new BigDecimal(i));

            Pn = Pn.add(product.multiply(difference).divide(factorial, MathContext.DECIMAL128));
        }

        return Pn;
    }

    private List<List<BigDecimal>> buildDifferenceTable(List<Pair<BigDecimal, BigDecimal>> points) {
        List<List<BigDecimal>> dy = new ArrayList<>();

        dy.add(new ArrayList<>());
        for (Pair<BigDecimal, BigDecimal> point : points)
            dy.getFirst().add(point.second);

        for (int i = 1; i < points.size(); i++) {
            dy.add(new ArrayList<>());
            for (int j = 0; j < dy.get(i-1).size() - 1; j++) {
                BigDecimal diff = dy.get(i-1).get(j+1).subtract(dy.get(i-1).get(j));
                dy.get(i).add(diff);
            }
        }
        return dy;
    }

    private BigDecimal getBackwardDifference(List<List<BigDecimal>> dy, int n, int order) {
        if (order < dy.size() && (n - order) >= 0 && (n - order) < dy.get(order).size())
            return dy.get(order).get(n - order);

        return BigDecimal.ZERO;
    }

}
