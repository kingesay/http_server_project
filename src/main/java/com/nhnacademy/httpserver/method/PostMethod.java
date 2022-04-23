package com.nhnacademy.httpserver.method;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.httpserver.exception.ParseFailureException;
import com.nhnacademy.httpserver.parser.body.PostBody;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PostMethod extends Method{
    private static final Log log = LogFactory.getLog(PostMethod.class);
    private final PostBody body;

    public PostMethod(PostBody body) {
        this.body = body;
    }

    @Override
    protected String makeJsonTemplate() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(body);
        } catch (JsonProcessingException e) {
            log.warn(e);
        }
        throw new ParseFailureException("POST Method json 데이터 파싱에 실패했습니다.");
    }
}
