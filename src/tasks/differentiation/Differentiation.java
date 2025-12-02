package tasks.differentiation;

import helper.Func;

import java.math.BigDecimal;
import java.math.MathContext;

public class Differentiation {
    private static final MathContext MC = MathContext.DECIMAL128;

    BigDecimal rightDiff(Func<BigDecimal, BigDecimal> f, BigDecimal x, BigDecimal h){
        validateParams(f, x, h);
        //(f(x+h) - f(x))/h
        return (f.calculate(x.add(h, MC)).subtract(f.calculate(x), MC))
                .divide(h, MC);
    }

    BigDecimal leftDiff(Func<BigDecimal, BigDecimal> f, BigDecimal x, BigDecimal h){
        validateParams(f, x, h);
        //(f(x) - f(x-h))/h
        return (f.calculate(x).subtract(f.calculate(x.subtract(h, MC)), MC))
                .divide(h, MC);
    }

    BigDecimal centralDiff(Func<BigDecimal, BigDecimal> f, BigDecimal x, BigDecimal h){
        validateParams(f, x, h);
        //(f(x+h) - f(x-h))/2h
        return (f.calculate(x.add(h, MC)).subtract(f.calculate(x.subtract(h, MC)), MC))
                .divide(h.multiply(BigDecimal.valueOf(2)), MC);
    }

    BigDecimal secondDiff(Func<BigDecimal, BigDecimal> f, BigDecimal x, BigDecimal h){
        validateParams(f, x, h);
        BigDecimal h2 = h.multiply(h, MC);
        //(f(x+h) - 2f(x) + f(x-h))/(h^2)
        return (f.calculate(x.add(h, MC))
                .subtract(f.calculate(x).multiply(BigDecimal.valueOf(2), MC)
                        .add(f.calculate(x.subtract(h, MC)), MC), MC))
                .divide(h2, MC);
    }

    private void validateParams(Func<BigDecimal, BigDecimal> f, BigDecimal x, BigDecimal h) {
        if (f == null || x == null || h == null) {
            throw new NullPointerException("Parameters cannot be null");
        }
        if (h.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("Step h cannot be zero");
        }
    }
}
