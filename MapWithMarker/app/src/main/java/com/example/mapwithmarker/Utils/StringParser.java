package com.example.mapwithmarker.Utils;

public class StringParser {
    public static String parseString(String input) {
        String[] words = input.split(" ");

        StringBuilder result = new StringBuilder();
        for (String word : words) {
            String lowerCaseWord = word.toLowerCase();
            String capitalizedWord = lowerCaseWord.substring(0, 1).toUpperCase() + lowerCaseWord.substring(1);
            result.append(capitalizedWord).append(" ");
        }
        return result.toString().trim();
    }

}
