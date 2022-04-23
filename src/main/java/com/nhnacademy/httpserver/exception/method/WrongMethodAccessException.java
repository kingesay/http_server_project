package com.nhnacademy.httpserver.exception.method;

public class WrongMethodAccessException extends RuntimeException {
    public WrongMethodAccessException(String message) {
        super(message);
    }
}
