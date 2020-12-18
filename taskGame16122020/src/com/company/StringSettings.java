package com.company;

public class StringSettings {
    public static boolean isPalindrome(String msg) {
        msg = standardizationText(msg);
        final int length = msg.length();
        final int halfLength = length / 2;

        for (int i = 0; i < halfLength; i++) {
            if (msg.charAt(i) != msg.charAt(length - i - 1)) {
                System.out.println("...Слово не полиндром!...");
                return false;
            }
        }
        return true;
    }

    public static String standardizationText(String text) {
        return text.toLowerCase().replaceAll("\\s+", "");

    }
}
