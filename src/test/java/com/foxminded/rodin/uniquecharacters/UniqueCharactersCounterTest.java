package com.foxminded.rodin.uniquecharacters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class UniqueCharactersCounterTest {

    @Test
    public void shouldReturnCorrectResultWhenGetSameStrings() {

        UniqueCharactersCounter counter = new UniqueCharactersCounter();

        String firstResult = counter.performUniqueCharactersPresentation("hello world!");
        String secondResult = counter.performUniqueCharactersPresentation("hello world!");
        
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

        String firstResult = counter.performUniqueCharactersPresentation("hello world!");
        String secondResult = counter.performUniqueCharactersPresentation("Hash table and linked list implementation of the Map interface");
        
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
    public void shouldCacheResultWhenGetSameStrings() {

        UniqueCharactersCounter counter = new UniqueCharactersCounter();

        counter.performUniqueCharactersPresentation("hello world!");
        counter.performUniqueCharactersPresentation("hello world!");
        
        assertEquals(1, counter.getCacheSize());

    }   
    
    @Test
    public void shouldCacheResultWhenGetDifferenStrings() {

        UniqueCharactersCounter counter = new UniqueCharactersCounter();

        counter.performUniqueCharactersPresentation("hello world!");
        counter.performUniqueCharactersPresentation("Hash table and linked list implementation of the Map interface");
        
        assertEquals(2, counter.getCacheSize());
        
    }   
    
    @Test
    public void shouldGetResultFromCacheWhenGetSameStrings() {

        UniqueCharactersCounter counter = new UniqueCharactersCounter();

        counter.performUniqueCharactersPresentation("hello world!");
       
        assertEquals(true, counter.isSentenceCached("hello world!"));

    }      
    
    @Test
    public void shouldThrowIllegalArgumentExceptionIfGetNullArgument() {

        assertThrows(IllegalArgumentException.class, () -> {
            UniqueCharactersCounter counter = new UniqueCharactersCounter();
            String result = counter.performUniqueCharactersPresentation(null);
        });

    }

    @Test
    public void shouldReturnEmptyStringIfGetEmptyString() {

        UniqueCharactersCounter counter = new UniqueCharactersCounter();
        
        assertEquals("", counter.performUniqueCharactersPresentation(""));

    }
    
    @Test
    public void shouldReturnCorrectResultIfGetNewline() {

        UniqueCharactersCounter counter = new UniqueCharactersCounter();

        assertEquals("\"\n\" - 1\n", counter.performUniqueCharactersPresentation("\n"));

    }    

}
