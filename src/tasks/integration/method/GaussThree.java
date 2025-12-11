package tasks.integration.method;

import helper.Func;

import java.math.BigDecimal;
import java.math.MathContext;

public class GaussThree implements IntegrationMethod{

    @Override
    public BigDecimal calculateIntegral(BigDecimal lowerBound, BigDecimal upperBound, Func<BigDecimal, BigDecimal> func, BigDecimal step) {
        if(lowerBound == null)
            throw new NullPointerException("Lower bound is null!");
        if(upperBound == null)
            throw new NullPointerException("Upper bound is null!");
        if(func == null)
            throw new NullPointerException("Function is null!");


        BigDecimal t1 = new BigDecimal("-0.7745966692414834");
        BigDecimal t2 = new BigDecimal("0");
        BigDecimal t3 = new BigDecimal("0.7745966692414834");

        BigDecimal c1 = new BigDecimal("0.5555555555555556");   //5/9
        BigDecimal c2 = new BigDecimal("0.8888888888888889");   //8/9
        BigDecimal c3 = new BigDecimal("0.5555555555555556");   //5/9

        BigDecimal val1 = upperBound.add(lowerBound).divide(BigDecimal.TWO,MathContext.DECIMAL128);     //(b+a)/2
        BigDecimal val2 = upperBound.subtract(lowerBound).divide(BigDecimal.TWO,MathContext.DECIMAL128);//(b-a)/2

        BigDecimal x1 = val1.add(val2.multiply(t1));    //(b+a)/2 + t<1>(b-a)/2 = (b+a)/2 - t<3>(b-a)/2
        BigDecimal x2 = val1.add(val2.multiply(t2));    //(b+a)/2 + t<2>(b-a)/2 = (b+a)/2
        BigDecimal x3 = val1.add(val2.multiply(t3));    //(b+a)/2 + t<3>(b-a)/2 = (b+a)/2 + t<3>(b-a)/2

        //((b-a)/2) * (5f(x<1>)/9 + 8f(x<2>)/9 + 5f(x<3>)/9)
        return val2.multiply(c1.multiply(func.calculate(x1)).add(
                c2.multiply(func.calculate(x2))).add(
                c3.multiply(func.calculate(x3)))
        );
    }
}
