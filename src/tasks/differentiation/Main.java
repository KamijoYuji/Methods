package tasks.differentiation;

import helper.Func;

import java.math.BigDecimal;
import java.math.MathContext;

public class Main {
    private static final Func<BigDecimal, BigDecimal> func = x -> new BigDecimal(Math.sin(x.doubleValue()));
    private static final String PI = "3.14159265358979323846264";

    public static void main(String[] args){
        BigDecimal x = new BigDecimal(PI).divide(new BigDecimal(3), MathContext.DECIMAL128);
        BigDecimal h = new BigDecimal("0.00001");

        System.out.println(Differentiation.centralDiff(func,x,h));
    }

}
