package tasks.differentiation;

import helper.Func;

import java.math.BigDecimal;
import java.math.MathContext;

public class Differentiation {
    static public BigDecimal rightDiff(Func<BigDecimal, BigDecimal> f, BigDecimal x, BigDecimal h){
        validateParams(f, x, h);
        //(f(x+h) - f(x))/h
        return (f.calculate(x.add(h, MathContext.DECIMAL128)).subtract(f.calculate(x), MathContext.DECIMAL128))
                .divide(h, MathContext.DECIMAL128);
    }

    static public BigDecimal leftDiff(Func<BigDecimal, BigDecimal> f, BigDecimal x, BigDecimal h){
        validateParams(f, x, h);
        //(f(x) - f(x-h))/h
        return (f.calculate(x).subtract(f.calculate(x.subtract(h, MathContext.DECIMAL128)), MathContext.DECIMAL128))
                .divide(h, MathContext.DECIMAL128);
    }

    static public BigDecimal centralDiff(Func<BigDecimal, BigDecimal> f, BigDecimal x, BigDecimal h){
        validateParams(f, x, h);
        //(f(x+h) - f(x-h))/2h
        return (f.calculate(x.add(h, MathContext.DECIMAL128)).subtract(f.calculate(x.subtract(h, MathContext.DECIMAL128)), MathContext.DECIMAL128))
                .divide(h.multiply(BigDecimal.valueOf(2)), MathContext.DECIMAL128);
    }

    static public BigDecimal secondDiff(Func<BigDecimal, BigDecimal> f, BigDecimal x, BigDecimal h){
        validateParams(f, x, h);
        BigDecimal h2 = h.multiply(h, MathContext.DECIMAL128);
        //(f(x+h) - 2f(x) + f(x-h))/(h^2)
        return (f.calculate(x.add(h, MathContext.DECIMAL128))
                .subtract(f.calculate(x).multiply(BigDecimal.valueOf(2), MathContext.DECIMAL128)
                        .add(f.calculate(x.subtract(h, MathContext.DECIMAL128)), MathContext.DECIMAL128), MathContext.DECIMAL128))
                .divide(h2, MathContext.DECIMAL128);
    }

    static private void validateParams(Func<BigDecimal, BigDecimal> f, BigDecimal x, BigDecimal h) {
        if (f == null || x == null || h == null) {
            throw new NullPointerException("Parameters cannot be null");
        }
        if (h.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("Step h cannot be zero");
        }
    }
}