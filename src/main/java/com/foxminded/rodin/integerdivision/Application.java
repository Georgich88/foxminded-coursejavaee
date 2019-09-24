package com.foxminded.rodin.integerdivision;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        int dividend = 0;
        int divisor = 0;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter dividend (> 0)");
        dividend = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter divisor (> 0)");
        divisor = Integer.parseInt(scanner.nextLine());

        IntegerDivision integerDivison = new IntegerDivision(dividend, divisor);
        String result = integerDivison.performDivision();

        System.out.println(result);

    }

}
