package com.company;

public enum AnswerStatus {
    SUCCESS(""),
    ALREADY_USED("Слово/фраза уже использовалось"),
    NOT_PALINDROME("Слово/фраза не палиндромно");

    private final String message;

    AnswerStatus(String message) {
        this.message = message;
    }

    public String getMessage() { return this.message; }
}
