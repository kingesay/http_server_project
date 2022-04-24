package com.nhnacademy.httpserver.parser.body;

import java.util.Map;

// Body를 생성하는 팩토리 Util class이다.
public class Body {
    public static GetBody factoryGetBody(Map<String, String> args, String origin, String url,
                                         Map<String, String> headers){
        return new GetBody(args, origin, url, headers);
    }

    public static GetOriginBody factoryGetOriginBody(String ip) {
        return new GetOriginBody(ip);
    }

    public static PostBody factoryPostBody(Map<String, String> argsBodyData, String clientIp,
                                           String url, Map<String, String> header, String data,
                                           Map<String, String> files,
                                           Map<String, String> form,
                                           Map<String, String> jsonBodyData) {
        return new PostBody(argsBodyData, clientIp, url, header, data, files, form, jsonBodyData);
    }
}
