package com.nhnacademy.httpserver.parser.json;

import java.util.Map;

public class JsonBody {
    private final Map<String, String> jsonBodyData;

    public JsonBody(Map<String, String> jsonBodyData) {
        this.jsonBodyData = jsonBodyData;
    }

    public Map<String, String> getJsonBodyData() {
        return jsonBodyData;
    }
}
