package helpers;

import java.util.Scanner;

public class GetHddVolume implements Helper<Integer>{
    @Override
    public Integer getHelper() {
        var scanner = new Scanner(System.in);
        int value = 0;
        System.out.println("Введите минимальный объем HDD:");
        if (scanner.hasNextInt())
            value = scanner.nextInt();
        return value;
    }
}
