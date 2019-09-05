package com.coursejavaee.cleancode.anagrams;

public class AnagramMaker {

    private static final String WORDS_DELIMITER = " ";

    /**
     * Gets the original sentence and convert it into anagrams sentence.
     * 
     * @param inputSentence - an original sentence.
     * @return an anagrams sentence.
     */
    public static String makeAnagram(String inputSentence) {

        StringBuilder outputSentence = new StringBuilder();

        String[] words = inputSentence.split(WORDS_DELIMITER);
        for (String word : words) {
            char[] charsOfWord = word.toCharArray();
            String reversedWord = reverseWord(charsOfWord);
            outputSentence.append(reversedWord);
            outputSentence.append(WORDS_DELIMITER);
        }

        return outputSentence.toString().trim();
    }

    private static String reverseWord(char[] word) {

        char[] reversedWord = new char[word.length];

        StringBuilder onlyLettersWord = new StringBuilder();
        for (int i = 0; i < word.length; i++) {
            if (Character.isLetter(word[i])) {
                onlyLettersWord.append(word[i]);
            }
        }
        onlyLettersWord.reverse();
        char[] onlyLettersReversedWord = onlyLettersWord.toString().toCharArray();

        for (int i = 0, letterCounts = 0; i < word.length; i++) {
            if (Character.isLetter(word[i])) {
                reversedWord[i] = onlyLettersReversedWord[letterCounts];
                letterCounts++;
            } else {
                reversedWord[i] = word[i];
            }
        }

        return new String(reversedWord);
    }

    /**
     * Gets a word and reverses it.
     * 
     * @param word - an original word.
     * @return a reversed word.
     */
    public static String getReversedWord(String word) {
        char[] charsOfWord = word.toCharArray();
        return reverseWord(charsOfWord);
    }
}
