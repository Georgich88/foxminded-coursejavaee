/*
 * Copyright (c) 2019 Georgich88
 * This program is made available under the terms of the MIT License.
 */
package com.coursejavaee.cleancode.anagrams;

/**
 * An class that reverses all the words of input text. All non-letter symbols
 * should stay on the same places. Uses Latin alphabet for test only.
 * 
 * @version 1.02 2019-08-07
 * @author Georgy Isaev
 */
public class AnagramMaker {

    public static final String DELIMITER = "\b";

    /**
     * Gets the original sentence and convert it into anagrams sentence.
     * 
     * @param inputSentence - an original sentence.
     * @return an anagrams sentence.
     */
    public static String makeAnagram(String inputSentence) {

        if (inputSentence.length() == 0) {
            return "";
        }

        StringBuilder outputSentence = new StringBuilder();

        // Separate sentences into words.
        String[] words = inputSentence.split(DELIMITER);
        for (String word : words) {
            char[] charsOfWord = word.toCharArray();
            String reversedWord = reverseWord(charsOfWord);
            outputSentence.append(reversedWord);
            outputSentence.append(DELIMITER);
        }

        return outputSentence.toString().trim();
    }

    /**
     * Gets a word and reverse it without moving non-letter symbols.
     * 
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
            if (Character.isLetter(word[i])) {
                reversedWord[i] = onlyLettersReversedWord[letterCounts];
                letterCounts++;
            } else {
                reversedWord[i] = word[i];
            }
        }

        return new String(reversedWord);
    }

}
