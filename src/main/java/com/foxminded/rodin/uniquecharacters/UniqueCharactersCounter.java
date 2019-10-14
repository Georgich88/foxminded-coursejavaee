package com.foxminded.rodin.uniquecharacters;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class UniqueCharactersCounter {

    private Map<String, LinkedHashMap<Character, Integer>> charactersAmountsBySentences = new HashMap<String, LinkedHashMap<Character, Integer>>();

    /**
     * Gets the original sentence and return the calculated number of unique
     * characters.
     * 
     * @param inputSentence - an original sentence.
     * @return presentation of the number of unique characters occurrence.
     */
    public String performUniqueCharactersPresentation(String inputSentence) {

        StringBuilder resultBuilder = new StringBuilder();
        LinkedHashMap<Character, Integer> charactersAmounts;

        if (inputSentence == null) {
            throw new IllegalArgumentException("Input string should not be null.");
        }

        if (isSentenceCached(inputSentence)) {
            charactersAmounts = getCharacterAmountsFromCache(inputSentence);
        } else {
            charactersAmounts = computeCharacterAmounts(inputSentence);
            charactersAmountsBySentences.put(inputSentence, charactersAmounts);
        }

        charactersAmounts.forEach((k, v) -> resultBuilder.append("\"" + k + "\" - " + v + "\n"));

        return resultBuilder.toString();

    }

    /**
     * Return the cache.
     * 
     * @return the local cache.
     */
    public Map<String, LinkedHashMap<Character, Integer>> getCache() {
        return new HashMap<String, LinkedHashMap<Character, Integer>>(charactersAmountsBySentences);
    }

    private boolean isSentenceCached(String inputSentence) {
        return charactersAmountsBySentences.containsKey(inputSentence);
    }

    private LinkedHashMap<Character, Integer> computeCharacterAmounts(String inputSentence) {

        LinkedHashMap<Character, Integer> charactersAmounts = new LinkedHashMap<Character, Integer>();
        char[] charsOfSentence = inputSentence.toCharArray();

        for (int i = 0; i < charsOfSentence.length; i++) {

            char currentCharacter = charsOfSentence[i];

            charactersAmounts.computeIfPresent(currentCharacter, (k, v) -> v + 1);
            charactersAmounts.computeIfAbsent(currentCharacter, (k) -> 1);

        }
        return charactersAmounts;
    }

    /**
     * Gets the original sentence and return cached characters' amounts.
     * 
     * @param inputSentence - an original sentence.
     * @return cached characters' amounts.
     */
    public LinkedHashMap<Character, Integer> getCharacterAmountsFromCache(String inputSentence) {

        if (inputSentence == null) {
            throw new IllegalArgumentException("Input string should not be null.");
        }

        return new LinkedHashMap<Character, Integer>(charactersAmountsBySentences.get(inputSentence));
    }

}
