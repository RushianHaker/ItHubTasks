package ru.enterprise.patterns;

/*
 * @created 19/01/2021 - 0:10
 * @project IntelliJ IDEA
 * @author Temnyakov Nikolay
 */

public class RuntimeException extends Exception {
    public RuntimeException() {
    }

    public RuntimeException(String message) {
        super(message);
    }

    public RuntimeException(String message, Exception innerException) {
        super(message, innerException);
    }

    protected RuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
