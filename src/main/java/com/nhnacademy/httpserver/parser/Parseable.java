package com.nhnacademy.httpserver.parser;

public interface Parseable<T> {
    T parse(String data);
}
