package com.nhnacademy.httpserver.method;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.httpserver.exception.ParseFailureException;
import com.nhnacademy.httpserver.parser.body.GetBody;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class GetMethod extends Method {
    private final GetBody body;

    private static final Log log = LogFactory.getLog(GetMethod.class);

    public GetMethod(GetBody body) {
        this.body = body;
    }

    @Override
    protected String makeJsonTemplate() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
            return json;
        } catch (JsonProcessingException e) {
            log.warn(e);
        }
        throw new ParseFailureException("getmethod json 데이터 파싱에 실패했습니다.");
    }
}
