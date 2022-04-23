package com.nhnacademy.httpserver.parser.body;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Map;

@JsonPropertyOrder({"args", "headers", "origin", "url"})
public class GetBody implements Body {
    private Map<String, String> args;
    private Map<String, String> headers;
    private String origin;
    private String url;

    public GetBody(Map<String, String> args, String origin, String url,
                   Map<String, String> headers) {
        this.args = args;
        this.origin = origin;
        this.url = url;
        this.headers = headers;
    }

    public Map<String, String> getArgs() {
        return args;
    }

    public String getOrigin() {
        return origin;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
