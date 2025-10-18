package tasks.interpolation.method;

import helper.Pair;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Comparator;
import java.util.List;

public class SecondGauss implements InterpolationMethod {
    @Override
    public BigDecimal calculateY(BigDecimal x, List<Pair<BigDecimal, BigDecimal>> points) {
        if (points.size() < 2) {
            throw new IllegalArgumentException("At least 2 points are required");
        }

        points.sort(Comparator.comparing(o -> o.first));

        BigDecimal h = points.get(1).first.subtract(points.get(0).first);
        for (int i = 1; i < points.size() - 1; i++) {
            BigDecimal currentH = points.get(i + 1).first.subtract(points.get(i).first);
            if (currentH.compareTo(h) != 0) {
                throw new IllegalArgumentException("Points are not equally spaced");
            }
        }

        int n = points.size();
        BigDecimal[][] diff = new BigDecimal[n][n];
        for (int i = 0; i < n; i++) {
            diff[i][0] = points.get(i).second;
        }

        for (int j = 1; j < n; j++) {
            for (int i = n - 1; i >= j; i--) {
                diff[i][j] = diff[i][j - 1].subtract(diff[i - 1][j - 1]);
            }
        }

        BigDecimal q = x.subtract(points.get(n - 1).first).divide(h, MathContext.DECIMAL128);
        BigDecimal result = diff[n - 1][0];
        BigDecimal term = BigDecimal.ONE;

        for (int k = 1; k < n; k++) {
            term = term.multiply(q.add(BigDecimal.valueOf(k - 1)))
                    .divide(BigDecimal.valueOf(k), MathContext.DECIMAL128);
            result = result.add(term.multiply(diff[n - 1][k]));
        }

        return result;
    }
}