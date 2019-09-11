package com.foxminded.rodin.anagram;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class AnagramMakerTest {

    @Test
    public void shouldReturnAnagramIfGetCorrectSentences() throws Exception {

        String sentence = "abcd efgh a1bcd efg!h";
        String expectedResult = "dcba hgfe d1cba hgf!e";

        String result = AnagramMaker.makeAnagram(sentence);
        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldReturnNotAnagramOnlyNumbersIfGetOnlyNumbers() throws Exception {

        String sentence = "3.14 23,2 2,7182818284590452353602874713527 12.3456";
        String expectedResult = "3.14 23,2 2,7182818284590452353602874713527 12.3456";

        String result = AnagramMaker.makeAnagram(sentence);
        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldReturnSentenceWithoutWordsIfGetSentenceWithoutWords() throws Exception {

        String sentence = "!@#$%^ @#_+~~~~~ 7$$<>{}";
        String expectedResult = "!@#$%^ @#_+~~~~~ 7$$<>{}";

        String result = AnagramMaker.makeAnagram(sentence);
        assertEquals(expectedResult, result);

    }

    @Test
    public void shouldReturnNotEnglishAnagramIfGetNotEnglishSentence() throws Exception {

        String sentence = "목에 하얀 수건을 둘러놓고 얼굴을 씻겨주던";
        String expectedResult = "에목 얀하 을건수 고놓러둘 을굴얼 던주겨씻";

        String result = AnagramMaker.makeAnagram(sentence);
        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldReturnNullPointerExceptionIfGetNullArgument() throws NullPointerException {
        try {
            String result = AnagramMaker.makeAnagram(null);
            fail("Check null exeption");
        } catch (NullPointerException e) {

        }

    }

    @Test
    public void shouldReturnNotAnagramIfGetWordsDividedByTab() throws Exception {

        String tabDelimiter = "\t";

        String sentence = "This" + tabDelimiter + "text" + tabDelimiter + "is" + tabDelimiter + "divided" + tabDelimiter
                + "by" + tabDelimiter + "tabs";
        String expectedResult = "sihT" + tabDelimiter + "txet" + tabDelimiter + "si" + tabDelimiter + "dedivid"
                + tabDelimiter + "yb" + tabDelimiter + "sbat";

        String result = AnagramMaker.makeAnagram(sentence);
        assertNotEquals(expectedResult, result);
    }

    @Test
    public void shouldReturnEmptyStringIfGetEmptyString() throws Exception {

        String resultAnagram = AnagramMaker.makeAnagram("");
        assertEquals(resultAnagram, "");

    }

}
