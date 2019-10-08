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
    public String countUniqueCharacters(String inputSentence) {

        if (inputSentence == null) {
            throw new IllegalArgumentException("Input string should not be null.");
        }

        if (charactersAmountsBySentences.containsKey(inputSentence)) {
            return charactersAmountsBySentences.get(inputSentence);
        }

        LinkedHashMap<Character, Integer> charactersAmounts = new LinkedHashMap<Character, Integer>();
        char[] charsOfSentence = inputSentence.toCharArray();
        StringBuilder resultBuilder = new StringBuilder();

        for (int i = 0; i < charsOfSentence.length; i++) {

            char currentCharacter = charsOfSentence[i];

            charactersAmounts.computeIfPresent(currentCharacter, (k, v) -> v + 1);
            charactersAmounts.computeIfAbsent(currentCharacter, (k) -> 1);

        }

        charactersAmounts.forEach((k, v) -> resultBuilder.append("\"" + k + "\" - " + v + "\n"));

        charactersAmountsBySentences.putIfAbsent(inputSentence, resultBuilder.toString());
        return charactersAmountsBySentences.get(inputSentence);

    }

}
