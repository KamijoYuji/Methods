package tasks.systems;

import helper.DFunc;
import java.math.BigDecimal;
import java.math.MathContext;

public class FirstMain {
    private static final DFunc<BigDecimal,BigDecimal,BigDecimal> F = (x, y) -> BigDecimal.TWO.multiply(x.pow(3)).
            subtract(y.pow(2)).
            subtract(BigDecimal.ONE);

    private static final DFunc<BigDecimal,BigDecimal,BigDecimal> G = (x,y) -> x.multiply(y.pow(3)).
            subtract(y).
            subtract(new BigDecimal("4"));

    public static void main(String[] args){
        ModifiedNewton modifiedNewton = new ModifiedNewton(F,G,
                BigDecimal.ONE,
                BigDecimal.ONE,
                new BigDecimal("0.001"),
                1000);

        BigDecimal X = modifiedNewton.getX();
        BigDecimal Y = modifiedNewton.getY();

        System.out.println("X = " + X);
        System.out.println("Y = " + Y);
        System.out.println("F(X,Y) = "+ F.calculate(X,Y).round(MathContext.DECIMAL32));
        System.out.println("G(X,Y) = "+ G.calculate(X,Y).round(MathContext.DECIMAL32));
        System.out.println("Calculated in "+ modifiedNewton.getIteration() +" iterations");
    }
}
