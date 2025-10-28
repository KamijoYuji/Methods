package tasks.interpolation;

import tasks.interpolation.method.SecondNewton;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LNMain {
    public static void main(String[] args){
        Interpolation interpolationHyperbola = new Interpolation();
        Interpolation interpolationCubicParabola = new Interpolation();
        interpolationHyperbola.setInterpolationMethod(new SecondNewton());
        interpolationCubicParabola.setInterpolationMethod(new SecondNewton());


        double h = 0.5;
        double x = 1;
        for(int i = 0; i < 4; i++)
        {
            interpolationHyperbola.push(new BigDecimal(x), new BigDecimal(1/x));
            interpolationCubicParabola.push(new BigDecimal(x), new BigDecimal(x*x*x));

            x+=h;
        }


        for(int i = 0; i < 31; i++){
            System.out.println("X = " + (1+i*0.05) + "; 1/X = " + interpolationHyperbola.calculateY(new BigDecimal(1+i*0.05)).setScale(10, RoundingMode.HALF_UP));
            System.out.println("X = " + (1+i*0.05) + "; x^3 = " + interpolationCubicParabola.calculateY(new BigDecimal(1+i*0.05)).setScale(10, RoundingMode.HALF_UP));
            System.out.println("___________________________________________");
        }
    }
}
