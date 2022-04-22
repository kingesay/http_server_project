package com.nhnacademy.httpserver.parser.args;

import com.nhnacademy.httpserver.parser.Parseable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class ArgsParser implements Parseable<Args> {
    private static final int KEY = 0;
    private static final int VALUE = 1;

    @Override
    public Args parse(String data) { // data = "/get?sa=123&asdq=1234'
        if(!data.contains("?")){
            return new Args(Collections.emptyMap());
        }

        String params = data.split("\\?")[1];
        StringTokenizer param = new StringTokenizer(params ,"&");
        Map<String, String> msgMap = new HashMap<>();
        while(param.hasMoreTokens()) {
            String msg = param.nextToken();
            String[] keyAndValue = msg.split("=");
            msgMap.put(keyAndValue[KEY], keyAndValue[VALUE]);
        }

        return new Args(msgMap);
    }
}
