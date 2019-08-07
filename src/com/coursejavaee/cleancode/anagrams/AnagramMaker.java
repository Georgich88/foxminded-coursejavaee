/*
 * Copyright (c) 2019 Georgich88
 * This program is made available under the terms of the MIT License.
 */
package com.coursejavaee.cleancode.anagrams;

import java.util.Scanner;

/**
 * An class that reverses all the words of input text.
 * All non-letter symbols should stay on the same places.
 * Uses Latin alphabet for test only.
 * @version 1.02 2019-08-07
 * @author Georgy Isaev
 */
public class AnagramMaker {

	public AnagramMaker(String inputSentence) {
		System.out.println(AnagramMaker.makeAnagram(inputSentence));
	}
	
	/**
	 * Tests anagram creation via standard input
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter sentence:");
		String text = scanner.nextLine();
		scanner.close();
		System.out.println(AnagramMaker.makeAnagram(text));

	}
	
	/**
	 * Gets the original sentence and convert it into anagrams sentence.
	 * @param inputSentence - an original sentence.
	 * @return an anagrams sentence.
	 * @throws IllegalArgumentException if inputSentence.length() == 0
	 */	
	public static String makeAnagram(String inputSentence) throws IllegalArgumentException {
		
		assert (inputSentence.length() == 0) : new IllegalArgumentException("An empty input text");
		
        StringBuilder outputSentence = new StringBuilder();
               
        // Separate sentences into words.
        for (String splitIn : inputSentence.split(" ", 2)) {

            char[] word = splitIn.toCharArray();
            String reversedWord = reverseWord(word);      
            outputSentence.append(reversedWord);
            outputSentence.append(" ");
        }

        return outputSentence.toString().trim();
	}
	
	/**
	 * Gets a word and reverse it without moving non-letter symbols.
	 * @param word - an original word.
	 * @return a reversed word.
	 */	
	private static String reverseWord(char[] word) {
		
		char[] reversedWord = new char[word.length];
		
		// Create a reversed string only with letters. E.g. "a1bcd" => "dcba".
		StringBuilder onlyLettersWord = new StringBuilder();
        for (int i = 0; i < word.length; i++) {
            if (Character.isLetter(word[i])) {
            	onlyLettersWord.append(word[i]);
            }
        }
		onlyLettersWord.reverse();
		char[] onlyLettersReversedWord = onlyLettersWord.toString().toCharArray();
		
		// Paste a non-letter characters in the reversed string. E.g. "dcba" => "d1cba".
		int letterCounts = 0;
        for (int i = 0; i < word.length; i++) {
            if (!Character.isLetter(word[i])) {
            	reversedWord[i] = word[i];
            } else {
            	reversedWord[i] = onlyLettersReversedWord[letterCounts];
            	letterCounts++;
            }
        }

        return new String(reversedWord);
	}
	
}
