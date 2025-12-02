package helper;

import java.math.BigDecimal;
import java.util.Scanner;

public class IN {
    public BigDecimal in()
    {
        Scanner scanner = new Scanner(System.in);
        String stringScanner = scanner.nextLine();
        return new BigDecimal(stringScanner);
    }
}
