package com.foxminded.rodin.uniquecharacters;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class UniqueCharactersCounter {

    private Map<String, String> resultsByInputStrings = new HashMap<String, String>();

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

        if (resultsByInputStrings.containsKey(inputString)) {
            return resultsByInputStrings.get(inputString);
        }

        LinkedHashMap<Character, Integer> countersByCharacters = new LinkedHashMap<Character, Integer>();
        char[] charsOfSentence = inputString.toCharArray();
        StringBuilder resultBuilder = new StringBuilder();

        for (int i = 0; i < charsOfSentence.length; i++) {
            char currentCharacter = charsOfSentence[i];

            countersByCharacters.computeIfPresent(currentCharacter, (k, v) -> v + 1);
            countersByCharacters.computeIfAbsent(currentCharacter, (k) -> 1);

        }

        countersByCharacters.forEach((k, v) -> resultBuilder.append("\"" + k + "\" - " + v + "\n"));

        resultsByInputStrings.putIfAbsent(inputString, resultBuilder.toString());
        return resultsByInputStrings.get(inputString);

    }

}
