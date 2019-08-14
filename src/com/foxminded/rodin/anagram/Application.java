package com.foxminded.rodin.anagram;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter sentence:");
        String inputSentence = scanner.nextLine();
        scanner.close();

        String anagram = AnagramMaker.makeAnagram(inputSentence);

        System.out.println(anagram);

    }

}
