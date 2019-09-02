package com.foxminded.rodin.anagram;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;

import junit.framework.TestCase;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Anagram test")
public class AnagramTest extends TestCase {

    private AnagramMaker anagramMaker;
    private BufferedReader testSentences;
    private BufferedReader testAnagrams;
    private BufferedReader testWords;
    private BufferedReader testReversedWords;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.anagramMaker = new AnagramMaker();
    }

    @BeforeAll
    private void initializeTestData() throws FileNotFoundException {

        String fileSeparator = System.getProperty("file.separator");
        String testCasesPath = "test_cases" + fileSeparator;

        FileReader sentencesList = new FileReader(testCasesPath + "sentenceslist.txt");
        FileReader anagramsList = new FileReader(testCasesPath + "anagramslist.txt");
        testSentences = new BufferedReader(sentencesList);
        testAnagrams = new BufferedReader(anagramsList);

        FileReader wordsList = new FileReader(testCasesPath + "wordslist.txt");
        FileReader reversedWordsList = new FileReader(testCasesPath + "reversed_wordslist.txt");
        testWords = new BufferedReader(wordsList);
        testReversedWords = new BufferedReader(reversedWordsList);
    }

    @AfterAll
    private void cleanupTestData() throws IOException {

        testSentences.close();
        testAnagrams.close();

        testWords.close();
        testReversedWords.close();
    }

    @BeforeEach
    private void reportTestStatus(TestInfo testInfo, TestReporter testReporter) {
        String messageText = "Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags();
        testReporter.publishEntry(messageText);
    }

    /**
     * Test anagram maker by comparing expected result and input data.
     * 
     * @result Output of <code>makeAnagram<code> method for each line of
     *         sentenceslist.txt file should return the same result that is in
     *         wordslist.txt file
     */
    @Test
    @Tag("Anagram")
    @DisplayName("Testing makeAnagram() method")
    public void makeAnagramTest() throws Exception {

        String sentence;
        String anagram;
        boolean isAnagram;

        while ((sentence = testSentences.readLine()) != null) {
            if ((anagram = testAnagrams.readLine()) != null) {
                @SuppressWarnings("static-access")
                String resultAnagram = anagramMaker.makeAnagram(sentence);
                isAnagram = resultAnagram.equals(anagram);
                assertTrue(isAnagram);
            } else {
                fail("Number of sentence doesn't equals to anagram number");
            }

        }
    }

    /**
     * Test auxiliary method reverseWord( by comparing expected result and input
     * data.
     * 
     * @result Output of <code>getReversedWord<code> method for each line of
     *         wordslist.txt file should return the same result that is stored in
     *         reversed_wordslist.txt file
     */
    @Test
    @Tag("Anagram")
    @DisplayName("Testing reverseWord() method")
    public void reverseWordTest() throws Exception {

        String word;
        String reveresedWord;
        boolean isReversedCorrectly;

        while ((word = testWords.readLine()) != null) {
            if ((reveresedWord = testReversedWords.readLine()) != null) {
                String resultReveresedWord = AnagramMaker.getReversedWord(word);
                isReversedCorrectly = resultReveresedWord.equals(reveresedWord);
                assertTrue(isReversedCorrectly);
            } else {
                fail("Number of words doesn't equals to number of reversed words");
            }

        }

    }

}
