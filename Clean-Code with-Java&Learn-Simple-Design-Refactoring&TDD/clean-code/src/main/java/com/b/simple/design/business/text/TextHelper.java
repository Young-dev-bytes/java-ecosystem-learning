package com.b.simple.design.business.text;


public class TextHelper {

    public String swapLastTwoCharacters(String str) {

        // if str == "hello"
        int length = str.length();
        if (length < 2) return str;
        char lastChar = str.charAt(length - 1);
        char secondLastChar = str.charAt(length - 2);
        // [0, 3), don't contain the last one
        String restOfTheString = str.substring(0, length - 2);
        return restOfTheString + lastChar + secondLastChar;
    }

    public String truncateAInFirst2Positions(String str) {

        if (str.length() < 2)
            return str.replaceAll("A", "");
        String first2Characters = str.substring(0, 2);
        String first2CharactersUpdated = first2Characters.replaceAll("A", "");
        String restOfTheString = str.substring(2);
        return first2CharactersUpdated + restOfTheString;
    }

}
