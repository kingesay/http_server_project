package com.nhnacademy.httpserver.parser.body;

public class GetOriginBody extends Body{
    private final String origin;

    public GetOriginBody(String origin) {
        this.origin = origin;
    }

    public String getOrigin() {
        return origin;
    }
}
