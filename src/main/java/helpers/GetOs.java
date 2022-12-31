package helpers;

import java.util.Scanner;

public class GetOs implements Helper<String>{
    @Override
    public String getHelper() {
        var scanner = new Scanner(System.in);
        String value = "";
        System.out.println("Введите операционную систему:");
        if (scanner.hasNextLine())
            value = scanner.nextLine();
        return value;
    }
}
