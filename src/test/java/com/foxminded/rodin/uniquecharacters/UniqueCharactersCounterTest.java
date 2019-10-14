package com.foxminded.rodin.uniquecharacters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

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
    public void shouldReturnCorrectCacheWhenGetSameStrings() {

        UniqueCharactersCounter counter = new UniqueCharactersCounter();

        counter.performUniqueCharactersPresentation("hello world!");
        counter.performUniqueCharactersPresentation("hello world!");

        Map<String, LinkedHashMap<Character, Integer>> expected = new HashMap<String, LinkedHashMap<Character, Integer>>();
        expected.computeIfAbsent("hello world!", (k) -> {
            LinkedHashMap<Character, Integer> v = new LinkedHashMap<Character, Integer>();
            v.put('h', 1);
            v.put('e', 1);
            v.put('l', 3);       
            v.put('o', 2);             
            v.put(' ', 1);                 
            v.put('w', 1);     
            v.put('r', 1);      
            v.put('d', 1);     
            v.put('!', 1);             
            return v;
        });

        assertEquals(expected, counter.getCache());

    }   
    
    @Test
    public void shouldReturnCorrectCacheWhenGetDifferenStrings() {

        UniqueCharactersCounter counter = new UniqueCharactersCounter();

        counter.performUniqueCharactersPresentation("hello world!");
        counter.performUniqueCharactersPresentation("Hash table and linked list implementation of the Map interface");

        Map<String, LinkedHashMap<Character, Integer>> expected = new HashMap<String, LinkedHashMap<Character, Integer>>();
        expected.computeIfAbsent("Hash table and linked list implementation of the Map interface", (k) -> {
            LinkedHashMap<Character, Integer> v = new LinkedHashMap<Character, Integer>();
            v.put('H' ,1); 
            v.put('a' ,6); 
            v.put('s' ,2); 
            v.put('h' ,2); 
            v.put(' ' ,9); 
            v.put('t' ,6); 
            v.put('b' ,1); 
            v.put('l' ,4); 
            v.put('e' ,7); 
            v.put('n' ,5); 
            v.put('d' ,2); 
            v.put('i' ,5); 
            v.put('k' ,1); 
            v.put('m' ,2); 
            v.put('p' ,2); 
            v.put('o' ,2); 
            v.put('f' ,2); 
            v.put('M' ,1); 
            v.put('r' ,1); 
            v.put('c' ,1);          
            return v;
        });        
        expected.computeIfAbsent("hello world!", (k) -> {
            LinkedHashMap<Character, Integer> v = new LinkedHashMap<Character, Integer>();
            v.put('h', 1);
            v.put('e', 1);
            v.put('l', 3);       
            v.put('o', 2);             
            v.put(' ', 1);                 
            v.put('w', 1);     
            v.put('r', 1);      
            v.put('d', 1);     
            v.put('!', 1);             
            return v;
        });        
        
        assertEquals(expected, counter.getCache());
        
    }   
    
    @Test
    public void shouldGetResultFromCacheWhenGetSameStrings() {

        UniqueCharactersCounter spyCounter = Mockito.spy(new UniqueCharactersCounter());

        spyCounter.performUniqueCharactersPresentation("hello world!");
        Mockito.verify(spyCounter, Mockito.never()).getCharacterAmountsFromCache("hello world!");

        spyCounter.performUniqueCharactersPresentation("hello world!");
        Mockito.verify(spyCounter, Mockito.atLeastOnce()).getCharacterAmountsFromCache("hello world!");

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
