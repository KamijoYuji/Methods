package tasks.integration;

import helper.Func;
import java.math.BigDecimal;

import static java.lang.System.*;

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
        out.println(integration1.calculateIntegral(lowerBound1,upperBound1,func1,null));
        out.println("Точное значение: 72");
        //Решаем: S(6x^5+3x^2)dx = 6S(x^5)dx + 3S(x^2)dx = (6x^6)/6 + (3x^3)/3 + C = x^6 + x^3 + C
        //Подставляем 2 и 0: (2^6 + 2^3) - (0^6 + 0^3) = (64 + 8) - (0 + 0) = 72 - 0 = 72

        BigDecimal lowerBound2 = BigDecimal.ZERO;
        BigDecimal upperBound2 = new BigDecimal("3.14159265358979");
        Integration integration2 = new Integration();
        out.println(integration2.calculateIntegral(lowerBound2,upperBound2,func2,null));
        out.println("Точное значение: 2");
        //Решаем: S(sin(x))dx = -cos(x) + C
        //Подставляем pi и 0: (-cos(pi)) - (-cos(0)) = (1) - (-1) = 2
    }
}
