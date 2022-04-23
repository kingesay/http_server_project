package com.nhnacademy.httpserver.parser.header;

import com.nhnacademy.httpserver.parser.Parseable;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class HeaderParser implements Parseable<Header> {

    @Override
    public Header parse(String data) {
        StringTokenizer sk = new StringTokenizer(data, "\r\n");
        StringBuilder sb = new StringBuilder();
        String protocols = sk.nextToken();

        while (sk.hasMoreTokens()){
            sb.append(sk.nextToken()).append("@");
        }

        StringTokenizer st = new StringTokenizer(protocols);
        String method = st.nextToken();
        String path = st.nextToken();
        String protocolVersion = st.nextToken();

        Map<String, String> headerMap = new HashMap<>();

        for (String header : sb.toString().split("@")) {
            String[] headerKeyValue = header.split(": ");
            String key = headerKeyValue[0];
            String value = headerKeyValue[1];
            headerMap.put(key, value);
        }

        return new Header(method, path, protocolVersion, headerMap);
    }
}
