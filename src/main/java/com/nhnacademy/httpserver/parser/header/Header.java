package com.nhnacademy.httpserver.parser.header;

import java.util.Map;

public class Header {
    private final String method;
    private final String path;
    private final String protocolVersion;
    private final Map<String, String> headerMap;

    public Header(String method, String path, String protocolVersion,
                  Map<String, String> headerMap) {
        this.method = method;
        this.path = path;
        this.protocolVersion = protocolVersion;
        this.headerMap = headerMap;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getProtocolVersion() {
        return protocolVersion;
    }

    public Map<String, String> getHeader(){
        return this.headerMap;
    }
}
