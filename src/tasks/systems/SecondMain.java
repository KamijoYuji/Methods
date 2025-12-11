package tasks.systems;

import java.math.BigDecimal;
import java.util.Vector;

public class SecondMain {
    public static void main(String[] args){
        Vector<Vector<BigDecimal>> vectors = new Vector<>();

        Vector<BigDecimal> expression1 = new Vector<>();
        expression1.add(new BigDecimal(3));
        expression1.add(new BigDecimal(4));
        expression1.add(new BigDecimal(2));

        Vector<BigDecimal> expression2 = new Vector<>();
        expression2.add(new BigDecimal(5));
        expression2.add(new BigDecimal(-6));
        expression2.add(new BigDecimal(-4));

        Vector<BigDecimal> expression3 = new Vector<>();
        expression3.add(new BigDecimal(-4));
        expression3.add(new BigDecimal(5));
        expression3.add(new BigDecimal(3));

        vectors.add(expression1);
        vectors.add(expression2);
        vectors.add(expression3);

        Vector<BigDecimal> solution = new Vector<>();
        solution.add(new BigDecimal(5));
        solution.add(new BigDecimal(-3));
        solution.add(new BigDecimal(1));

        SquareMatrix squareMatrix = new SquareMatrix(vectors, solution);
        solution = squareMatrix.Gauss();

        System.out.println(solution);
    }
}
