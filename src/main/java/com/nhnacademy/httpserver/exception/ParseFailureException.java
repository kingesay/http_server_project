package com.nhnacademy.httpserver.exception;

public class ParseFailureException extends RuntimeException {
    public ParseFailureException(String message) {
        super(message);
    }
}
