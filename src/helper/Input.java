package helper;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Scanner;

public class Input {
    public static int INT(){
        int n = 0;
        boolean isNotInt;
        do {
            Scanner scanner = new Scanner(System.in);
            try {
                n = scanner.nextInt();
                isNotInt = false;
            } catch (Exception e) {
                System.out.println("Введине целое число!");
                isNotInt = true;
            }
        } while (isNotInt);

        return n;
    }

    public static BigDecimal BIGDECIMAL(){
        BigDecimal n = null;
        boolean isNotBigDecimal;
        do {
            Scanner scanner = new Scanner(System.in);
            try {
                n = scanner.nextBigDecimal();
                isNotBigDecimal = false;
            } catch (Exception e) {
                System.out.println("Введине число!");
                isNotBigDecimal = true;
            }
        } while (isNotBigDecimal);

        return n;
    }

    public static String STRING(Collection<String> badStrings){
        String n = null;
        boolean isBadString;
        do{
            Scanner scanner = new Scanner(System.in);
            n = scanner.nextLine();
            isBadString = badStrings.contains(n);
            if(isBadString)
                System.out.println("Введите разрешённую строку!");
        } while (isBadString);
        return n;
    }
}
