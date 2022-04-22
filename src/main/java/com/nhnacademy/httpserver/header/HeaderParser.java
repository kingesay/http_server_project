package com.nhnacademy.httpserver.header;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class HeaderParser {

    public Header parse(String protocols, String headers) {
        StringTokenizer st = new StringTokenizer(protocols);
        String method = st.nextToken();
        String path = st.nextToken();
        String protocolVersion = st.nextToken();

        Map<String, String> headerMap = new HashMap<>();

        for (String header : headers.split(";")) {
            StringTokenizer headerKeyValue = new StringTokenizer(header, ": ");
            String key = headerKeyValue.nextToken();
            String value = headerKeyValue.nextToken();
            headerMap.put(key, value);
        }

        return new Header(method, path, protocolVersion, headerMap);
    }
}
