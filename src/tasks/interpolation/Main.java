package tasks.interpolation;

import tasks.interpolation.method.FirstGauss;
import tasks.interpolation.method.PiecewiseLinear;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isBigDecimal(String str) {
        try {
            new BigDecimal(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args){
        Interpolation interpolation = new Interpolation();
        interpolation.setInterpolationMethod(new PiecewiseLinear());

        System.out.println("Введите кол-во точек: ");

        Scanner scanner = new Scanner(System.in);
        String stringScanner = scanner.nextLine();

        while (!isInteger(stringScanner)){
            System.out.println("Введите кол-во точек (целое число!): ");
            stringScanner = scanner.nextLine();
        }

        int size = Integer.parseInt(stringScanner);

        while (size < 2){
            System.out.println("Нужно минимум 2 точки!");
            System.out.println("Введите кол-во точек: ");

            stringScanner = scanner.nextLine();

            while (!isInteger(stringScanner)){
                System.out.println("Введите кол-во точек (целое число!): ");
                stringScanner = scanner.nextLine();
            }
            size = Integer.parseInt(stringScanner);
        }

        System.out.println("Введите координаты (x и y) каждой точки: ");
        for(int i = 0; i < size; i++){
            System.out.println("x"+(i+1)+" = ");
            stringScanner = scanner.nextLine();
            while (!isBigDecimal(stringScanner)){
                System.out.println("Должно быть число: ");
                stringScanner = scanner.nextLine();
            }

            BigDecimal x = new BigDecimal(stringScanner);

            System.out.println("y"+(i+1)+" = ");
            stringScanner = scanner.nextLine();
            while (!isBigDecimal(stringScanner)){
                System.out.println("Должно быть число: ");
                stringScanner = scanner.nextLine();
            }

            BigDecimal y = new BigDecimal(stringScanner);


            interpolation.push(x,y);
        }

        System.out.println("Введите x: ");

        stringScanner = scanner.nextLine();
        while (!isBigDecimal(stringScanner)){
            System.out.println("Должно быть число: ");
            stringScanner = scanner.nextLine();
        }

        BigDecimal x = new BigDecimal(stringScanner);


        BigDecimal y = interpolation.calculateY(x);

        if(y != null)
            System.out.println("y = "+y);
    }
}
