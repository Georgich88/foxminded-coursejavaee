package com.foxminded.rodin.uniquecharacters;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter sentence:");
        String inputSentence = scanner.nextLine();
        scanner.close();

        UniqueCharactersCounter uniqueCharactersCounter = new UniqueCharactersCounter();

        System.out.println(uniqueCharactersCounter.performUniqueCharactersPresentation(inputSentence));

    }

}
