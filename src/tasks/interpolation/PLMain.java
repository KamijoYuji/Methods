package tasks.interpolation;

import helper.Color;
import helper.Input;
import tasks.interpolation.method.PiecewiseLinear;
import tasks.interpolation.method.SecondNewton;

import java.math.BigDecimal;

public class PLMain {
    public static void main(String[] args) {
        Interpolation interpolation = new Interpolation();
        interpolation.setInterpolationMethod(new PiecewiseLinear());

        boolean doing = true;

        while (doing) {
            interpolation.clear();

            System.out.println("Введите кол-во точек: ");

            int size = Input.INT();

            while (size < 2) {
                System.out.println("Нужно минимум 2 точки!");
                System.out.println("Введите кол-во точек: ");
                size = Input.INT();
            }

            System.out.println("Введите координаты (x и y) каждой точки: ");
            for (int i = 0; i < size; i++) {
                boolean flag;
                do {
                    flag = false;
                    System.out.println("x" + (i + 1) + " = ");
                    BigDecimal x = Input.BIGDECIMAL();

                    System.out.println("y" + (i + 1) + " = ");
                    BigDecimal y = Input.BIGDECIMAL();

                    try {
                        interpolation.push(x, y);
                    } catch (IllegalArgumentException e) {
                        flag = true;
                        System.out.println("Уже есть точка с таким же значением x: " + x);
                    }
                } while (flag);

            }

            BigDecimal y = null;
            boolean unequallySpacedNodes = false;
            while (y == null) {
                System.out.println("Введите x: ");
                BigDecimal x = Input.BIGDECIMAL();

                try {
                    y = interpolation.calculateY(x);
                    unequallySpacedNodes = false;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("x находится за пределами диапазона интерполяции");
                } catch (IllegalArgumentException e) {
                    System.out.println("Неравномерно расположенные узлы");
                    unequallySpacedNodes = true;
                    break;
                }
            }

            if (!unequallySpacedNodes)
                System.out.println("y = " + y);

            System.out.println(Color.GREEN + "Ещё раз? (1 - Да; 0 - Нет): " + Color.RESET);
            int answer = Input.INT();
            while (answer!=1 && answer!=0)
            {
                System.out.println(Color.GREEN + "(1 - Да; 0 - Нет): " + Color.RESET);
                answer = Input.INT();
            }

            doing = answer==1;
        }
    }
}
