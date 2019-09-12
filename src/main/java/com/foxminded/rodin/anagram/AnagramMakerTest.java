package com.foxminded.rodin.anagram;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AnagramMakerTest {

    @Test
    public void shouldRevertSentence() {

        String sentence = "abcd efgh a1bcd efg!h";
        String expectedResult = "dcba hgfe d1cba hgf!e";

        String result = AnagramMaker.makeAnagram(sentence);

        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldNotRevertNumbers() {

        String sentence = "3.14 23,2 2,7182818284590452353602874713527 12.3456";
        String expectedResult = "3.14 23,2 2,7182818284590452353602874713527 12.3456";

        String result = AnagramMaker.makeAnagram(sentence);

        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldNotRevertSpecialCharacters() {

        String sentence = "!@#$%^ @#_+~~~~~ 7$$<>{}";
        String expectedResult = "!@#$%^ @#_+~~~~~ 7$$<>{}";

        String result = AnagramMaker.makeAnagram(sentence);

        assertEquals(expectedResult, result);

    }

    @Test
    public void shouldNotRevertNotLatinSymbols() {

        String sentence = "维基百科，自由";
        String expectedResult = "科百基维，由自";

        String result = AnagramMaker.makeAnagram(sentence);

        assertNotEquals(expectedResult, result);
    }

    @Test
    public void shouldReturnNullPointerExceptionIfGetNullArgument() {

        assertThrows(NullPointerException.class, () -> {
            String result = AnagramMaker.makeAnagram(null);
        });

    }

    @Test
    public void shouldMakeWrongAnagramFromSenteceDividedByTab() {

        String sentence = "This\ttext\ttabDelimiter\tdivided\tby\ttabs";
        String expectedResult = "sihT\ttxet\tsi\tdedivid\tyb\tsbat";

        String result = AnagramMaker.makeAnagram(sentence);

        assertNotEquals(expectedResult, result);
    }

    @Test
    public void shouldReturnEmptyStringIfGetEmptyString() {

        String resultAnagram = AnagramMaker.makeAnagram("");

        assertEquals(resultAnagram, "");

    }

}
