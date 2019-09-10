package com.foxminded.rodin.anagram;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Anagram test")
public class AnagramTest {

    private AnagramMaker anagramMaker;

    @BeforeAll
    protected void setUp() {
        this.anagramMaker = new AnagramMaker();
    }

    @BeforeEach
    private void reportTestStatus(TestInfo testInfo, TestReporter testReporter) {
        String messageText = "Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags();
        testReporter.publishEntry(messageText);
    }

    @Nested
    @DisplayName("Correct input tests")
    @Tag("Anagram")
    class CorrectInputTest {

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

        /**
         * Test anagram maker for only numbers by comparing expected result and input
         * data.
         * 
         * @result Output of <code>makeAnagram<code> method.
         */
        @Test
        @Tag("Anagram")
        @DisplayName("Testing numbers input for makeAnagram() method")
        public void makeAnagramOnlyNumbersTest() throws Exception {

            String[] sentences = { "3.14 23,2", "2,7182818284590452353602874713527 12.3456" };

            String[] anagrams = { "3.14 23,2", "2,7182818284590452353602874713527 12.3456" };

            int size = Math.min(sentences.length, anagrams.length);

            for (int i = 0; i < size; i++) {

                String resultAnagram = anagramMaker.makeAnagram(sentences[i]);
                boolean isAnagram = resultAnagram.equals(anagrams[i]);
                assertTrue(isAnagram);

            }
        }

        /**
         * Test anagram maker with not-words input by comparing expected result and
         * input data.
         * 
         * @result Output of <code>makeAnagram<code> method.
         */
        @Test
        @Tag("Anagram")
        @DisplayName("Testing not-words sentences input for makeAnagram() method")
        public void makeAnagramNotWordsTest() throws Exception {

            String[] sentences = { "!@#$%^ @#_+~~~~~ 7$$<>{}", "... --- |||" };

            String[] anagrams = { "!@#$%^ @#_+~~~~~ 7$$<>{}", "... --- |||" };

            int size = Math.min(sentences.length, anagrams.length);

            for (int i = 0; i < size; i++) {

                String resultAnagram = anagramMaker.makeAnagram(sentences[i]);
                boolean isAnagram = resultAnagram.equals(anagrams[i]);
                assertTrue(isAnagram);

            }
        }

        /**
         * Test anagram maker with not English words input by comparing expected result
         * and input data.
         * 
         * @result Output of <code>makeAnagram<code> method.
         */
        @Test
        @Tag("Anagram")
        @DisplayName("Testing not-English words sentences input for makeAnagram() method")
        public void makeAnagramNotEnglishTest() throws Exception {

            String[] sentences = { "Съешь ещё этих мягких французских булок, да выпей же чаю",
                    "목에 하얀 수건을 둘러놓고 얼굴을 씻겨주던" };

            String[] anagrams = { "ьшеъС ёще хитэ хикгям хиксзуцнарф колуб, ад йепыв еж юач",
                    "에목 얀하 을건수 고놓러둘 을굴얼 던주겨씻" };

            int size = Math.min(sentences.length, anagrams.length);

            for (int i = 0; i < size; i++) {

                String resultAnagram = anagramMaker.makeAnagram(sentences[i]);
                boolean isAnagram = resultAnagram.equals(anagrams[i]);
                assertTrue(isAnagram);

            }
        }

    }

    @Nested
    @DisplayName("Boundary conditions test")
    @Tag("Anagram")
    class BoundaryConditionsTest {

        /**
         * Test null exception for AnagramMaker class.
         * 
         * @result Caught NullPointerException for <code>AnagramMaker<code> class.
         */
        @Test
        @Tag("Anagram")
        @DisplayName("Testing NullPointerException for makeAnagram() method")
        public void makeAnagramNullTest() throws NullPointerException {
            try {
                String resultAnagram = anagramMaker.makeAnagram(null);
                fail("Check null exeption");
            } catch (NullPointerException e) {

            }

        }

        /**
         * Test anagram maker with tab-divided sentences input by comparing expected
         * result and input data.
         * 
         * @result Output of <code>makeAnagram<code> method.
         */
        @Test
        @Tag("Anagram")
        @DisplayName("Testing tab-divided sentences input for makeAnagram() method")
        public void makeAnagramNotWordsTest() throws Exception {

            // String[] sentences = { "This text is divided by tabs", "T10 Vietnam" };
            String tabDelimiter = "\t";
            String[] sentences = { "This" + tabDelimiter + "text" + tabDelimiter + "is" + tabDelimiter + "divided"
                    + tabDelimiter + "by" + tabDelimiter + "tabs", "T10" + tabDelimiter + "Vietnam" };
            String[] anagrams = { "sihT" + tabDelimiter + "txet" + tabDelimiter + "si" + tabDelimiter + "dedivid"
                    + tabDelimiter + "yb" + tabDelimiter + "sbat", "T10" + tabDelimiter + "manteiV" };

            int size = Math.min(sentences.length, anagrams.length);

            for (int i = 0; i < size; i++) {

                String resultAnagram = anagramMaker.makeAnagram(sentences[i]);
                boolean isNotAnagram = !resultAnagram.equals(anagrams[i]);
                assertTrue(isNotAnagram);

            }
        }

        /**
         * Test anagram maker with empty string input by comparing expected result and
         * input data.
         * 
         * @result Output of <code>makeAnagram<code> method.
         */
        @Test
        @Tag("Anagram")
        @DisplayName("Testing empty string for makeAnagram() method")
        public void makeAnagramEmptyStringTest() throws Exception {

            String resultAnagram = anagramMaker.makeAnagram("");
            assertEquals(resultAnagram, "");

        }

    }
}
