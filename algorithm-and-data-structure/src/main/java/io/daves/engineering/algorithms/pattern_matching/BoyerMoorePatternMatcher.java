package io.daves.engineering.algorithms.pattern_matching;

import java.util.HashMap;
import java.util.Map;

public class BoyerMoorePatternMatcher {

    public static void main(String[] args) {

        String sampleText = "yfyjtvkj aerd,jhbhjbyugvbjh eljhaerdbluej ,m jljhjhaerdvhvvh";
        String pattern = "aerd";

        BoyerMoorePatternMatcher matcher = new BoyerMoorePatternMatcher();
        int result = matcher.matchPatter(sampleText.toCharArray(), pattern.toCharArray());

        System.out.println("Patter found at position: " + result);
        
        pattern = "aaaaa";
        result = matcher.matchPatter(sampleText.toCharArray(), pattern.toCharArray());

        System.out.println("Patter found at position: " + result);
    }

    /*
     * The method returns the first index matching or -1 if the pattern doesn't match
     *
     * This implementation of the Boyer-Moore algorithm use only the Bad character heuristic
     */
    private int matchPatter(char[] text, char[] pattern) {

        if (pattern.length == 0) {
            return -1;
        }

        int textLength = text.length;
        int patternLength = pattern.length;

        Map<Character, Integer> lastCharMapping = getLastCharMapping(text, pattern, textLength, patternLength);

        int textIndex = patternLength - 1;
        int patternIndex = textIndex;

        while (textIndex < textLength) {

            if(text[textIndex] == pattern[patternIndex]) {
                if (patternIndex == 0) {
                    return textIndex;
                }
                textIndex--;
                patternIndex--;
            } else {
                textIndex += patternLength - Math.min(patternIndex, (lastCharMapping.get(text[textIndex]) + 1));
                patternIndex = patternLength - 1;
            }

        }

        return -1;
    }

    private Map<Character, Integer> getLastCharMapping(char[] text, char[] pattern, int textLength, int patternLength) {
        Map<Character, Integer> lastCharMapping = new HashMap<>();
        for (int i = 0; i < textLength; i++) {
            lastCharMapping.put(text[i], -1);
        }

        for (int i = 0; i < patternLength; i++) {
            lastCharMapping.put(pattern[i], i);
        }

        return lastCharMapping;
    }

}
