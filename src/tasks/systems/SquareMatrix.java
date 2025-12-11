package tasks.systems;

import helper.Func;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Vector;

public class SquareMatrix {
    Vector<Vector<BigDecimal>> matrix;
    Vector<BigDecimal> solution;
    public SquareMatrix(Vector<Vector<BigDecimal>> matrix, Vector<BigDecimal> solution) {
        if(matrix == null)
            throw new NullPointerException("Matrix is null!");
        if(matrix.isEmpty())
            throw new IllegalArgumentException("Matrix is empty!");
        if(solution == null)
            throw new NullPointerException("Vector of solutions is null!");
        if(solution.isEmpty())
            throw new IllegalArgumentException("Vector of solutions is empty!");
        int s = solution.size();
        int n = matrix.size();
        if(s!=n)
            throw new IllegalArgumentException("The size of the vector of solutions must be the same as the number of columns in the matrix!");

        int m = matrix.getFirst().size();
        for(int i = 0; i < n; i++) {
            m = Math.min(m, matrix.get(i).size());
            if(solution.get(i) == null)
                throw new NullPointerException("Null element in the vector of solutions!");
            for(int j = 0; j < matrix.get(i).size(); j++)
                if(matrix.get(i).get(j) == null)
                    throw new NullPointerException("Null element in the matrix!");
        }
        if(n!=m)
            throw new IllegalArgumentException("It is not the square matrix!");

        this.matrix = matrix;
        this.solution = solution;
    }

    public void switchRows(int firstRow, int secondRow) {
        int n = matrix.size();
        if (firstRow >= n || secondRow >= n)
            throw new IndexOutOfBoundsException();
        var temp = new Vector<>(matrix.get(firstRow));
        matrix.set(firstRow, matrix.get(secondRow));
        matrix.set(secondRow, temp);
    }

    public void switchColumns(int firstColumn, int secondColumn) {
        int n = matrix.size();
        for (int i = 0; i < n; i++) {
            var tempVar = matrix.get(i).get(firstColumn);
            matrix.get(i).set(firstColumn, matrix.get(i).get(secondColumn));
            matrix.get(i).set(secondColumn, tempVar);
        }
    }

    public Vector<BigDecimal> Gauss() {
        int indexAbsMaxX;
        int indexAbsMaxY;
        int size = matrix.size();

        Func<BigDecimal,BigDecimal> abs = x -> x.compareTo(BigDecimal.ZERO) > 0 ? x : x.negate();
        Vector<Integer> order = new Vector<>();

        for(int k = 0; k < size; k++) {
            indexAbsMaxX = k;
            indexAbsMaxY = k;

            for (int i = k; i < size; i++)
                for (int j = k; j < size; j++)
                    if (abs.calculate(matrix.get(i).get(j)).compareTo(abs.calculate(matrix.get(indexAbsMaxX).get(indexAbsMaxY))) > 0) {
                        indexAbsMaxX = i;
                        indexAbsMaxY = j;
                    }

            switchRows(k, indexAbsMaxX);
            switchColumns(k, indexAbsMaxY);
            var temp = solution.get(k);
            solution.set(k, solution.get(indexAbsMaxX));
            solution.set(indexAbsMaxX, temp);

            order.add(indexAbsMaxX);

            for(int l = k+1; l < size; l++)
            {
                BigDecimal multiplier = matrix.get(l).get(k).divide(matrix.get(k).get(k), MathContext.DECIMAL128);
                for(int n = k; n < size; n++) {
                    matrix.get(l).set(n, matrix.get(l).get(n).subtract(matrix.get(k).get(n).multiply(multiplier)));
                    if(n == l && matrix.get(l).get(n).compareTo(BigDecimal.ZERO)==0)
                        throw new ArithmeticException("The determinant is zero!");
                }
                solution.set(l, solution.get(l).subtract(solution.get(k).multiply(multiplier)));
            }
        }

        for(int k = size-1; k >= 0; k--) {
            for(int l = k-1; l >= 0; l--)
            {
                BigDecimal multiplier = matrix.get(l).get(k).divide(matrix.get(k).get(k), MathContext.DECIMAL128);
                for(int n = k; n < size; n++)
                    matrix.get(l).set(n, matrix.get(l).get(n).subtract(matrix.get(k).get(n).multiply(multiplier)));
                solution.set(l, solution.get(l).subtract(solution.get(k).multiply(multiplier)));
            }
            solution.set(k, solution.get(k).divide(matrix.get(k).get(k), MathContext.DECIMAL128));
        }

        for(int i = 0; i < size; i++)
        {
            var var = solution.get(i);
            solution.set(i, solution.get(order.get(i)));
            solution.set(order.get(i), var);
        }

        return solution;
    }
}
