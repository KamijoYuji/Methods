package tasks.interpolation;

import helper.Color;
import tasks.interpolation.method.SecondNewton;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class SNMain {
    public static void main(String[] args){
        Interpolation interpolationHyperbola = new Interpolation();
        Interpolation interpolationCubicParabola = new Interpolation();
        interpolationHyperbola.setInterpolationMethod(new SecondNewton());
        interpolationCubicParabola.setInterpolationMethod(new SecondNewton());


        //добавляем x с 1 и по 2.5 с шагом 0.5 и y для этого x
        BigDecimal h = new BigDecimal("0.5");
        BigDecimal x = new BigDecimal("1");
        for(int i = 0; i < 4; i++)
        {
            interpolationHyperbola.push(x, BigDecimal.ONE.divide(x, MathContext.DECIMAL128));
            interpolationCubicParabola.push(x, x.multiply(x.multiply(x)));
            x = x.add(h);
        }

        for(int i = 0; i < 31; i++){
            System.out.println(Color.RESET +"X = " + (1+i*0.05) + ';' +Color.GREEN +" 1/X = " + interpolationHyperbola.calculateY(new BigDecimal(1+i*0.05)).setScale(10, RoundingMode.HALF_UP));
            System.out.println(Color.RESET+"X = " + (1+i*0.05) + ';' +Color.BLUE+" X^3 = " + interpolationCubicParabola.calculateY(new BigDecimal(1+i*0.05)).setScale(10, RoundingMode.HALF_UP));
            System.out.println(Color.RESET+"___________________________________________");
        }
    }
}
