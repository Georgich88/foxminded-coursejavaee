package com.foxminded.rodin.anagram;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AnagramMakerTest {

    @Test
    public void shouldRevertSentence() {

        String input = "abcd efgh a1bcd efg!h";

        String result = AnagramMaker.makeAnagram(input);

        assertEquals("dcba hgfe d1cba hgf!e", result);
    }

    @Test
    public void shouldNotRevertNumbers() {

        String input = "3.14 23,2 2,7182818284590452353602874713527 12.3456";

        String result = AnagramMaker.makeAnagram(input);

        assertEquals("3.14 23,2 2,7182818284590452353602874713527 12.3456", result);
    }

    @Test
    public void shouldNotRevertSpecialCharacters() {

        String input = "!@#$%^ @#_+~~~~~ 7$$<>{}";

        String result = AnagramMaker.makeAnagram(input);

        assertEquals("!@#$%^ @#_+~~~~~ 7$$<>{}", result);

    }

    @Test
    public void shouldNotRevertNotLatinSymbols() {

        String input = "⺐⺑⺒⺓⺔⺕， ⻤⻥⻦⻧";

        String result = AnagramMaker.makeAnagram(input);

        assertEquals(input, result);
    }

    @Test
    public void shouldReturnNullPointerExceptionIfGetNullArgument() {

        String result = AnagramMaker.makeAnagram(null);

        assertEquals(result, null);
    }

    @Test
    public void shouldMakeWrongAnagramFromSenteceDividedByTab() {

        String input = "This\ttext\ttabDelimiter\tdivided\tby\ttabs";

        String result = AnagramMaker.makeAnagram(input);

        assertEquals("sbat\tybde\tdividretimil\teDbattx\tet\tsihT", result);
    }

    @Test
    public void shouldReturnEmptyStringIfGetEmptyString() {

        String result = AnagramMaker.makeAnagram("");

        assertEquals(result, "");

    }

}
