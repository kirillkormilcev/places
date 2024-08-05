package ru.aston.places.error.exception;

public class IncorrectRequestParamException extends RuntimeException {
    public IncorrectRequestParamException(final String message) {
        super(message);
    }
}
