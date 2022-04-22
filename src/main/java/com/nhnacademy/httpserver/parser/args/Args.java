package com.nhnacademy.httpserver.parser.args;

import java.util.Map;

public class Args {
    private final Map<String, String> argsBodyData;

    public Args(Map<String, String> jsonBodyData) {
        this.argsBodyData = jsonBodyData;
    }

    public Map<String, String> getJsonBodyData() {
        return argsBodyData;
    }
}
