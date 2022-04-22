package com.nhnacademy.httpserver.parser.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.httpserver.exception.body.DataParseException;
import com.nhnacademy.httpserver.parser.Parseable;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JsonBodyParser implements Parseable<JsonBody> {
    private static final Log log = LogFactory.getLog(JsonBodyParser.class);
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public JsonBody parse(String data) {
        try {
            Map<String,String> parserData = objectMapper.readValue(data,
                new TypeReference<>() {});
            return new JsonBody(parserData);
        } catch (JsonProcessingException e) {
            log.warn(e);
        }
        throw new DataParseException("클라이언트 데이터 변환 실패");
    }
}
