package com.foxminded.rodin.anagram;

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

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.anagramMaker = new AnagramMaker();
    }

    @BeforeEach
    private void reportTestStatus(TestInfo testInfo, TestReporter testReporter) {
        String messageText = "Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags();
        testReporter.publishEntry(messageText);
    }

    /**
     * Test anagram maker by comparing expected result and input data.
     * 
     * @result Output of <code>makeAnagram<code> method.
     */
    @Test
    @Tag("Anagram")
    @DisplayName("Testing makeAnagram() method")
    public void makeAnagramTest() throws Exception {

        String[] sentences = { "abcd efgh", "a1bcd efg!h" };

        String[] anagrams = { "dcba hgfe", "d1cba hgf!e" };

        int size = Math.min(sentences.length, anagrams.length);

        for (int i = 0; i < size; i++) {

            String resultAnagram = anagramMaker.makeAnagram(sentences[i]);
            boolean isAnagram = resultAnagram.equals(anagrams[i]);
            assertTrue(isAnagram);

        }
    }

}
