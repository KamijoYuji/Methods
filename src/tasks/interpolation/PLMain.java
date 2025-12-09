package tasks.interpolation;

import helper.Input;
import tasks.interpolation.method.PiecewiseLinear;

import java.math.BigDecimal;

public class PLMain {
    public static void main(String[] args){
        Interpolation interpolation = new Interpolation();
        interpolation.setInterpolationMethod(new PiecewiseLinear());

        System.out.println("Введите кол-во точек: ");

        int size = Input.INT();

        while (size < 2){
            System.out.println("Нужно минимум 2 точки!");
            System.out.println("Введите кол-во точек: ");
            size = Input.INT();
        }

        System.out.println("Введите координаты (x и y) каждой точки: ");
        for(int i = 0; i < size; i++){
            boolean flag;
            do {
                flag = false;
                System.out.println("x"+(i+1)+" = ");
                BigDecimal x = Input.BIGDECIMAL();

                System.out.println("y"+(i+1)+" = ");
                BigDecimal y = Input.BIGDECIMAL();

                try {
                    interpolation.push(x, y);
                } catch (IllegalArgumentException e) {
                    flag = true;
                    System.out.println("Уже есть точка с таким же значением x: " + x);
                }
            }while (flag);

        }

        BigDecimal y = null;
        while (y == null){
            System.out.println("Введите x: ");
            BigDecimal x = Input.BIGDECIMAL();

            try {
                y = interpolation.calculateY(x);
            } catch (IllegalArgumentException e){
                System.out.println("x находится за пределами диапазона интерполяции");
            }
        }

        System.out.println("y = "+y);
    }
}
