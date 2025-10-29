package tasks.interpolation.method;

import helper.Pair;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

public class SecondNewton implements InterpolationMethod {
    @Override
    public BigDecimal calculateY(BigDecimal x, List<Pair<BigDecimal, BigDecimal>> points) {
        if (points.size() < 2)
            throw new IllegalArgumentException("At least 2 points are required");

        //h = x1 - x0
        BigDecimal h = points.get(1).first.subtract(points.getFirst().first);

        //q = (x - xn)/h
        BigDecimal q = x.subtract(points.getLast().first).divide(h,MathContext.DECIMAL128);

        //dy[0] - это все данные значения y, а dy[i][j] (i = 1,...,n; j = 0,...,n-i) - это разностные производные i-ого порядка
        List<List<BigDecimal>> dy = new ArrayList<>();
        for(int i = 0; i < points.size(); i++) {
            dy.add(new ArrayList<>());
            for(int j = 0; j < points.size() - i; j++) {
                if (i == 0)
                    dy.getFirst().add(points.get(j).second);
                else
                    dy.getLast().add(dy.get(i - 1).get(j + 1).subtract(dy.get(i - 1).get(j)));
            }
        }

        BigDecimal fractal = new BigDecimal(1); //0!, 1!, 2!, 3!, ..., n!
        BigDecimal partWithQ = new BigDecimal(1); //q, q(q+1), q(q+1)(q+2), ..., q(q+1)...(q+n-1)
        BigDecimal Pn = dy.getFirst().getLast(); //Pn = yn + dy<n-1>*q + d2y<n-2>*q(q+1)/2 + d3y<n-3>*q(q+1)(q+2)/6 +...

        for(int i = 1; i < points.size(); i++){
            fractal = fractal.multiply(new BigDecimal(i));
            partWithQ = partWithQ.multiply(q.add(new BigDecimal(i-1)));

            Pn = Pn.add(dy.get(i).getLast().multiply(partWithQ).divide(fractal, MathContext.DECIMAL128));
        }

        return Pn;
    }
}
