package com.foxminded.rodin.uniquecharacters;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class UniqueCharactersCounter {

    private Map<String, String> previousResults;

    public UniqueCharactersCounter() {
        previousResults = new HashMap<String, String>();
    }

    /**
     * Gets the original sentence and return the calculated number of unique
     * characters.
     * 
     * @param inputSentence - an original sentence.
     * @return presentation of the number of unique characters occurrence.
     */
    public String countUniqueCharacters(String inputString) {

        if (inputString == null) {
            throw new IllegalArgumentException("Input string should not be null.");
        }

        if (previousResults.containsKey(inputString)) {
            return previousResults.get(inputString);
        }

        LinkedHashMap<Character, Integer> counterMap = new LinkedHashMap<Character, Integer>();
        char[] charsOfSentence = inputString.toCharArray();
        StringBuilder resultBuilder = new StringBuilder();

        for (int i = 0; i < charsOfSentence.length; i++) {
            char currentCharacter = charsOfSentence[i];
            if (counterMap.containsKey(currentCharacter)) {
                int currentOccurenceNumber = counterMap.get(currentCharacter);
                counterMap.put(currentCharacter, currentOccurenceNumber + 1);
            } else {
                counterMap.put(currentCharacter, 1);
            }
        }

        Iterator<Map.Entry<Character, Integer>> counterMapIterator = counterMap.entrySet().iterator();
        while (counterMapIterator.hasNext()) {
            Map.Entry<Character, Integer> entry = counterMapIterator.next();
            resultBuilder.append("\"" + entry.getKey() + "\" - " + entry.getValue() + "\n");
        }

        previousResults.putIfAbsent(inputString, resultBuilder.toString());
        return previousResults.get(inputString);

    }

}
