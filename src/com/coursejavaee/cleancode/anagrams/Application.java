/**
 * 
 */
package com.coursejavaee.cleancode.anagrams;

import java.util.Scanner;

/**
 * An class that tests anagram creation
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
        String text = scanner.nextLine();
        scanner.close();
        System.out.println(AnagramMaker.makeAnagram(text));

    }

}
