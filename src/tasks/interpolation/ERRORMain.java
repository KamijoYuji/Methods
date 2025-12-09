package tasks.interpolation;

import tasks.interpolation.method.SecondNewton;

import java.math.BigDecimal;

public class ERRORMain {
    public static void main(String[] args){
        Interpolation interpolation = new Interpolation();
        interpolation.setInterpolationMethod(new SecondNewton());
        interpolation.push(new BigDecimal(-1),new BigDecimal(-1));
        interpolation.push(new BigDecimal(0),new BigDecimal(0));
        interpolation.push(new BigDecimal(1),new BigDecimal(1));
        System.out.println(interpolation.calculateY(new BigDecimal(5)));
    }
}
