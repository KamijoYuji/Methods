package tasks.interpolation.method;

import helper.Pair;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

public class PiecewiseLinear implements InterpolationMethod {
    @Override
    public BigDecimal calculateY(BigDecimal x, List<Pair<BigDecimal,BigDecimal>> points) {
        if (points.size() < 2)
            throw new IllegalArgumentException("At least 2 points are required");

        int size = points.size();

        for(int i = 0; i < size-1; i++)
            if(x.compareTo(points.get(i).first)>=0 && x.compareTo(points.get(i+1).first)<=0) {
                BigDecimal x1 = points.get(i).first;    //x<i>
                BigDecimal x2 = points.get(i+1).first;  //x<i+1>
                BigDecimal y1 = points.get(i).second;   //y<i>
                BigDecimal y2 = points.get(i+1).second; //y<i+1>

                if(x1.equals(x2))
                    throw new IllegalArgumentException("Duplicate x values: " + x1);

                //((y<i+1> - y<i>) / (x<i+1> - x<i>)) * (x - x<i>) + y<i>
                return (y2.subtract(y1)).divide(x2.subtract(x1), MathContext.DECIMAL128).multiply(x.subtract(x1)).add(y1);
            }


        throw new IllegalArgumentException("x is outside interpolation range");
    }
}
