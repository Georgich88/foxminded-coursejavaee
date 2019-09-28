package com.foxminded.rodin.integerdivision;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter dividend (> 0)");
        int dividend = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter divisor (> 0)");
        int divisor = Integer.parseInt(scanner.nextLine());

        IntegerDivisionVisualizer integerDivison = new IntegerDivisionVisualizer();
        String result = integerDivison.performDivision(dividend, divisor);

        System.out.println(result);

    }

}
