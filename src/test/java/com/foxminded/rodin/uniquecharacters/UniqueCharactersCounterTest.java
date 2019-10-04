package com.foxminded.rodin.uniquecharacters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class UniqueCharactersCounterTest {

    @Test
    public void shouldReturnCorrectResultWhenGetSameStrings() {

        UniqueCharactersCounter counter = new UniqueCharactersCounter();

        String firstResult = counter.countUniqueCharacters("hello world!");
        String secondResult = counter.countUniqueCharacters("hello world!");
        
        String expectedResult = "\"h\" - 1\n" + 
                "\"e\" - 1\n" + 
                "\"l\" - 3\n" + 
                "\"o\" - 2\n" + 
                "\" \" - 1\n" + 
                "\"w\" - 1\n" + 
                "\"r\" - 1\n" + 
                "\"d\" - 1\n" + 
                "\"!\" - 1\n";

        assertEquals(expectedResult, firstResult);

        assertEquals(expectedResult, secondResult);
    }
    
    
    @Test
    public void shouldReturnCorrectResultWhenGetDifferenStrings() {

        UniqueCharactersCounter counter = new UniqueCharactersCounter();

        String firstResult = counter.countUniqueCharacters("hello world!");
        String secondResult = counter.countUniqueCharacters("Hash table and linked list implementation of the Map interface");
        
        assertEquals("\"h\" - 1\n" + 
                "\"e\" - 1\n" + 
                "\"l\" - 3\n" + 
                "\"o\" - 2\n" + 
                "\" \" - 1\n" + 
                "\"w\" - 1\n" + 
                "\"r\" - 1\n" + 
                "\"d\" - 1\n" + 
                "\"!\" - 1\n", firstResult);

        assertEquals("\"H\" - 1\n" + 
                "\"a\" - 6\n" + 
                "\"s\" - 2\n" + 
                "\"h\" - 2\n" + 
                "\" \" - 9\n" + 
                "\"t\" - 6\n" + 
                "\"b\" - 1\n" + 
                "\"l\" - 4\n" + 
                "\"e\" - 7\n" + 
                "\"n\" - 5\n" + 
                "\"d\" - 2\n" + 
                "\"i\" - 5\n" + 
                "\"k\" - 1\n" + 
                "\"m\" - 2\n" + 
                "\"p\" - 2\n" + 
                "\"o\" - 2\n" + 
                "\"f\" - 2\n" + 
                "\"M\" - 1\n" + 
                "\"r\" - 1\n" + 
                "\"c\" - 1\n", secondResult);
    }    

    @Test
    public void shouldReturnNullPointerExceptionIfGetNullArgument() {

        assertThrows(IllegalArgumentException.class, () -> {
            UniqueCharactersCounter counter = new UniqueCharactersCounter();
            String result = counter.countUniqueCharacters(null);
        });

    }

    @Test
    public void shouldReturnEmptyStringIfGetEmptyString() {

        UniqueCharactersCounter counter = new UniqueCharactersCounter();

        String result = counter.countUniqueCharacters("");

        assertEquals(result, "");

    }
    
    @Test
    public void shouldReturnCorrectResultIfGetNewline() {

        UniqueCharactersCounter counter = new UniqueCharactersCounter();

        String result = counter.countUniqueCharacters("\n");

        assertEquals(result, "\"\n\" - 1\n");

    }    

}