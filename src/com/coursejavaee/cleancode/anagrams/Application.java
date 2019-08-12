/**
 * 
 */
package com.coursejavaee.cleancode.anagrams;

import java.util.Scanner;

/**
 * The class that tests anagram creation
 * 
 * @version 1.02 2019-08-07
 * @author Georgy Isaev
 */
public class Application {

    /**
     * Tests anagram creation via standard input
     * 
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter sentence:");
        String inputSentence = scanner.nextLine();
        scanner.close();

        String outputSentence = AnagramMaker.makeAnagram(inputSentence);

        System.out.println(outputSentence);

    }

}
