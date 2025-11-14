package tasks.integration;

import helper.Func;
import java.math.BigDecimal;

public class Main {
    private static double func1_proto(double x){
        return 6*x*x*x*x*x + 3*x*x;
    }
    private static final Func<BigDecimal, BigDecimal> func1 = x -> new BigDecimal(func1_proto(x.doubleValue()));
    private static final Func<BigDecimal, BigDecimal> func2 = x -> new BigDecimal(Math.sin(x.doubleValue()));
    public static void main(String[] args){
        BigDecimal lowerBound1 = BigDecimal.ZERO;
        BigDecimal upperBound1 = BigDecimal.TWO;
        Integration integration1 = new Integration();
        System.out.println(integration1.calculateIntegral(lowerBound1,upperBound1,func1));
        System.out.println("Точное значение: 72");

        BigDecimal lowerBound2 = BigDecimal.ZERO;
        BigDecimal upperBound2 = new BigDecimal("3.14159265358979");
        Integration integration2 = new Integration();
        System.out.println(integration2.calculateIntegral(lowerBound2,upperBound2,func2));
        System.out.println("Точное значение: 2");
    }
}
