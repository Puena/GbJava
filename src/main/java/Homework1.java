import java.io.IOException;
import java.util.Scanner;
import java.util.function.ToDoubleBiFunction;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Homework1 {
    public static void task1(int n){
        int sum = 1;
        for (int i = 1; i < n + 1; i++){
            sum = sum * i;
        }
        System.out.println(sum);
    }

    public static void task2() {
        int start = 1;
        int stop = 1000;
        int num = 0;
        System.out.println("Prime numbers from 1 to 100 are :");
        for (start = 1; start <= stop; start++)
        {
            int counter=0;
            for(num =start; num>=1; num--)
            {
                if(start%num==0)
                {
                    counter = counter + 1;
                }
            }
            if (counter ==2)
            {
                //Appended the Prime number to the String
                System.out.printf("%d ", start);
            }
        }
    }

    public static void task3() throws IOException {
        double num1;
        double num2;
        double ans;
        char op;
        var logger = Logger.getLogger(Homework1.class.getName());
        var fh = new FileHandler("./");
        logger.addHandler(fh);
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter two numbers: ");
        num1 = reader.nextDouble();
        num2 = reader.nextDouble();
        logger.log(Level.INFO, String.format("Enter numbers %f %f", num1, num2));
        System.out.println("Enter an operator (+ -, *, /): ");
        op = reader.next().charAt(0);
        switch (op) {
            case '+' -> {
                ans = num1 + num2;
                logger.log(Level.INFO, String.format("Add %f %f", num1, num2));
            }
            case '-' -> {
                ans = num1 - num2;
                logger.log(Level.INFO, String.format("Subtract %f %f", num1, num2));
            }
            case '*' -> {
                ans = num1 * num2;
                logger.log(Level.INFO, String.format("Multiply %f %f", num1, num2));
            }
            case '/' -> {
                ans = num1 / num2;
                logger.log(Level.INFO, String.format("Divide %f %f", num1, num2));
            }
            default -> {
                System.out.println("Ошибка! Не известный оператор");
                logger.log(Level.INFO, "Неизвестный оператор");
                return;
            }
        }
        System.out.print("\nРезультат выражения:\n");
        System.out.printf(num1 + " " + op + " " + num2 + " = " + ans);
    }
}
