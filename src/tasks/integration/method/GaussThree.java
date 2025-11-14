package tasks.integration.method;

import helper.Func;

import java.math.BigDecimal;
import java.math.MathContext;

public class GaussThree implements IntegrationMethod{

    @Override
    public BigDecimal calculateIntegral(BigDecimal lowerBound, BigDecimal upperBound, Func<BigDecimal, BigDecimal> func) {
        BigDecimal t1 = new BigDecimal("-0.7745966692414834");
        BigDecimal t2 = new BigDecimal("0");
        BigDecimal t3 = new BigDecimal("0.7745966692414834");

        BigDecimal c1 = new BigDecimal("0.5555555555555556");
        BigDecimal c2 = new BigDecimal("0.8888888888888889");
        BigDecimal c3 = new BigDecimal("0.5555555555555556");

        BigDecimal val1 = upperBound.add(lowerBound).divide(BigDecimal.TWO,MathContext.DECIMAL128);
        BigDecimal val2 = upperBound.subtract(lowerBound).divide(BigDecimal.TWO,MathContext.DECIMAL128);

        BigDecimal x1 = val1.add(val2.multiply(t1));
        BigDecimal x2 = val1.add(val2.multiply(t2));
        BigDecimal x3 = val1.add(val2.multiply(t3));

        return val2.multiply(c1.multiply(func.calculate(x1)).add(
                c2.multiply(func.calculate(x2))).add(
                c3.multiply(func.calculate(x3)))
        );
    }
}
