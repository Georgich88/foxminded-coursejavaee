package com.foxminded.rodin.uniquecharacters;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class UniqueCharactersCounter {

    private Map<String, String> charactersAmountsBySentences = new HashMap<String, String>();

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

        if (charactersAmountsBySentences.containsKey(inputString)) {
            return charactersAmountsBySentences.get(inputString);
        }

        LinkedHashMap<Character, Integer> charactersAmounts = new LinkedHashMap<Character, Integer>();
        char[] charsOfSentence = inputString.toCharArray();
        StringBuilder resultBuilder = new StringBuilder();

        for (int i = 0; i < charsOfSentence.length; i++) {

            char currentCharacter = charsOfSentence[i];

            charactersAmounts.computeIfPresent(currentCharacter, (k, v) -> v + 1);
            charactersAmounts.computeIfAbsent(currentCharacter, (k) -> 1);

        }

        charactersAmounts.forEach((k, v) -> resultBuilder.append("\"" + k + "\" - " + v + "\n"));

        charactersAmountsBySentences.putIfAbsent(inputString, resultBuilder.toString());
        return charactersAmountsBySentences.get(inputString);

    }

}
