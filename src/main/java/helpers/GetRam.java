package helpers;

import java.util.Scanner;

public class GetRam implements Helper<Integer>{
    @Override
    public Integer getHelper() {
        var scanner = new Scanner(System.in);
        int value = 0;
        System.out.println("Введите минимальное колличетво ОЗУ:");
        if (scanner.hasNextInt())
            value = scanner.nextInt();
        return value;
    }
}
